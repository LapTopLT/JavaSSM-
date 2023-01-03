<template>
  <div class="hello">
    <el-row>
      <el-col :span="8">
        <el-input  v-model="search.searchId" @input="handleChange" placeholder="请输入药品id" borderless></el-input>
      </el-col>
      <el-col :span="8" :offset="2">
        <el-input  v-model="search.searchName" @input="handleChange" placeholder="请输入药品名称" borderless></el-input>
      </el-col>
      <el-col :offset="2" :span="4">
        <el-button type="success" @click="dialogTableVisible = true">新增药品</el-button>
      </el-col>
    </el-row>
    <el-dialog :visible.sync="dialogVisible"
               width="30%" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="药品id">
          <el-input v-model="form.medid"  :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="药名">
          <el-input v-model="form.medname" ></el-input>
        </el-form-item>
        <el-form-item label="药品库存">
          <el-input v-model="form.medamount"></el-input>
        </el-form-item>
        <el-form-item label="药品价格">
          <el-input v-model="form.medprice"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="updateMed('form')">修 改</el-button>
  </span>
    </el-dialog>
    <el-dialog title="新增部门信息" :visible.sync="dialogTableVisible"
               :width="'60%'"
               append-to-body
    >
      <el-form :model="ruleForm" status-icon ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="药品编号" prop="medid">
          <el-input v-model="ruleForm.medid"></el-input>
        </el-form-item>
        <el-form-item label="药品名" prop="medname">
          <el-input v-model="ruleForm.medname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="药品数量" prop="medamount">
          <el-input v-model="ruleForm.medamount" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="单价" prop="medprice">
          <el-input v-model="ruleForm.medprice" autocomplete="off"></el-input>
        </el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">新增</el-button>
        <el-form-item>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-table :data="drugs" style="width: 100%">
      <el-table-column
          prop="medid"
          label="药物编号"
          width="180"
      ></el-table-column>
      <el-table-column
          prop="medname"
          label="药物名称"
          width="180"
      ></el-table-column>
      <el-table-column
          prop="medamount"
          label="药物库存"
          width="180"
      ></el-table-column>
      <el-table-column
          prop="medprice"
          label="药物价格（元）"
          width="180"
      ></el-table-column>
      <el-table-column
          label="操 作"
          width="180"
      >
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)">编辑</el-button>
          <el-button @click="deletemed(scope.$index,scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        @current-change="handleChange">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "MedicineOperation",
  data(){
    return {
      currentPage : 1,
      total : 0,
      pageSize : 5,
      search : {
        searchId : '',
        searchName : ''
      },
      dialogVisible: false,
      dialogTableVisible : false,
      //储存编辑那行的信息
      form:{
        medid:'11111',
        medname:'111111',
        medamount:'111111',
        medprice:'1111'
      },
      drugs: [],
      ruleForm : {
        medid : '',
        medname : '',
        medamount : '',
        medprice : ''
      }
    };
  },
  methods:{
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const data = this.$qs.stringify(this.ruleForm)
          await this.axios({
            url : '/admin/insertMed',
            method : 'post',
            data
          }).then((res)=>{
            if(res.data === 1){
              this.$message.success('新增药品成功')
              this.handleChange()
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
      this.dialogTableVisible = false
      this.$refs['ruleForm'].resetFields()
    },
    updateMed(formName){
      this.$refs[formName].validate(async (valid)=>{
        if(valid){
          const data = this.$qs.stringify(this.form)

          await this.axios({
            url : '/admin/updateMed',
            method : 'post',
            data
          }).then(async res=>{
            if(res.data === 1){
              await this.handleChange()
              this.$message.success('更改成功！')
              this.dialogVisible = false
            }
          })
        }else{
          console.log('error submit!')
        }
      })
    },
    handleEdit(row){
      this.dialogVisible = true
      this.form = row
    },
    deletemed(index,row){
      const data = this.$qs.stringify({
        medid : row.medid
      })

      this.$confirm('确认要删除吗？','提示',{
        type : 'danger'
      }).then(()=>{
        this.axios({
          url : '/admin/deleteMed',
          method : 'post',
          data
        }).then(res=>{
          if(res.data === 1){
            this.$message.success('删除成功！')
            this.drugs.splice(index,1)
          }
        })
      })
    },
    //获取所有药品信息,加上async和await表示阻塞等待
    async getAllMed(){
      //要用qs把数据转换成成请求格式
      const data = this.$qs.stringify({
        currentPage : this.currentPage,
        //把输入框里的元素传入（作为查询条件）
        medid : this.search.searchId,
        medname : this.search.searchName
      })
      //发送请求给后端
      await this.axios({
        url : '/admin/getMeds',
        method : 'post',
        data
      }).then(res=>{
        this.drugs = res.data
      })
    },
    //获取药品总数，用于分页的total属性
    async getMedRowCount(){
      const data = this.$qs.stringify({
        medid : this.search.searchId,
        medname : this.search.searchName
      })
      //发送请求给后端
      await this.axios({
        url : '/admin/getMedRowCount',
        method : 'post',
        data
      }).then(res=>{
        //调整总条数
        this.total = res.data
      })
    },
    //页面发生改变时调用
    handleChange(){
      this.getAllMed()
      this.getMedRowCount()
    }
  },
  mounted() {
      //mounted里面是页面挂载完成后进行的操作
    //请求药品信息列表
    this.handleChange()
  }
}
</script>

<style scoped>

</style>