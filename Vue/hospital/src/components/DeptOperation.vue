<template>
  <div>
    <el-dialog title="新增部门信息" :visible.sync="dialogTableVisible"
               v-loading="loading"
               :width="width"
               append-to-body
               >
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="部门编号" prop="deptno">
          <el-input v-model="ruleForm.deptno"></el-input>
        </el-form-item>
        <el-form-item label="部门名" prop="deptname">
          <el-input v-model="ruleForm.deptname" autocomplete="off"></el-input>
        </el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">新增</el-button>
        <el-form-item>
        </el-form-item>
      </el-form>
      </el-dialog>
    <el-dialog title="修改部门信息" :visible.sync="dialogFormVisible"
               append-to-body
               >
      <el-form :model="form">
        <el-form-item label="部门编号" :label-width="formLabelWidth">
          <el-input v-model="form.deptno" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="部门名称" :label-width="formLabelWidth">
          <el-input v-model="form.deptname" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleEdit">确 定</el-button>
      </div>
    </el-dialog>
    <el-row :gutter="24">
      <el-col :span="8">
        <el-input
            placeholder="按下回车键查询部门编号"
            v-model="search.searchId"
            @keyup.enter.native="handleChange"
        ></el-input>
      </el-col>
      <el-col :span="8">
        <el-input
            v-model="search.searchName"
            placeholder="输入查询的部门名"
            @input="handleChange"></el-input>
      </el-col>
      <el-col :offset="4" :span="4">
        <el-button type="primary"
                   @click="handleClick">新增部门信息</el-button>
      </el-col>
    </el-row>
    <el-table
        v-loading="loading"
        :data="tableData"
        height="400"
        border
        style="width: 100%">
      <el-table-column
          prop="deptno"
          label="部门编号"
          width="140">
      </el-table-column>
      <el-table-column
          prop="deptname"
          label="部门名"
          width="140">
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <el-button type="primary" @click="openDialogForm(scope.row)">编辑</el-button>
          <el-button type="danger" @click="handleDel(scope.$index,scope.row.deptno)">删除</el-button>
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
  name: "DeptOperation",
  data(){
    return{
      rules: {
        deptno : [
          {required : true,message : '请填写部门编号',trigger : 'blur'}
        ],
        deptname : [
          {required : true,message : '请填写部门名称',trigger : 'blur'}
        ]
      },
      loading : true,
      ruleForm : {
        deptno : '',
        deptname : ''
      },
      dialogTableVisible : false,
      search:{
        searchId : '',
        searchName : '',
      },
      width : '70%',
      tableData:[],
      total : 0,
      currentPage : 1,
      pageSize : 5,
      formLabelWidth : '120px',
      dialogFormVisible: false,
      form: {
        deptno : '',
        deptname : ''
      }
    }
  },
  methods:{
    openDialogForm(row){
      this.form.deptno = row.deptno
      this.form.deptname = row.deptname
      this.dialogFormVisible = true
    },
    handleEdit(){
      const data = this.$qs.stringify({
        deptno : this.form.deptno,
        deptname : this.form.deptname
      })
      this.axios({
        url : '/admin/updateDept',
        method : 'post',
        data
      }).then((res)=>{
        if(res.data === 1){
          this.$message.success('更新成功')
          this.handleChange()
          this.dialogFormVisible = false
        }
      })
    },
    handleDel(index,deptno){
      const data = this.$qs.stringify({
        deptno
      })
      this.$confirm('确定要删除吗？删除后不可恢复','提示',{
        confirmButtonText : '删除',
        cancelButtonText : '取消',
        type : 'warning'
      }).then(async ()=>{
        await this.axios({
          url : '/admin/deleteDept',
          method : 'post',
          data
        }).then(res=>{
          if(res.data === 1){
            this.$message.success('删除成功！')
            this.tableData.splice(index,1)
          }else{
            this.$message.error('删除出错！')
          }
        })
      })
    }
    ,
     submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const data = this.$qs.stringify({
            deptno : this.ruleForm.deptno,
            deptname : this.ruleForm.deptname
          })
          await this.axios({
            url : '/admin/insertDept',
            method : 'post',
            data
          }).then((res)=>{
            if(res.data === 1){
              this.$message.success('新增部门成功')
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
    handleClick(){
      this.dialogTableVisible = true
    },
    handleChange(){
      this.getAllDept()
      this.getDeptRowCount()
    },
    async getAllDept(){
      const dataPost = this.$qs.stringify({
        currentPage : this.currentPage,
        deptno : this.search.searchId,
        deptname : this.search.searchName
      })
      await this.axios({
        url : '/admin/getAllDept',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.tableData = res.data
        this.loading = false
      })
    },
    async getDeptRowCount(){
      const dataPost = this.$qs.stringify({
        deptno : this.search.searchId,
        deptname : this.search.searchName
      })
      await this.axios({
        url : '/admin/getDeptRowCount',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.total = res.data
      })
    }
  },
  async created() {
    await this.getAllDept();
    await this.getDeptRowCount()
  },
  watch:{
    'search.searchId'(val){
      if(val === ''){
        this.handleChange()
      }
    }
  }
}
</script>

<style scoped>

</style>