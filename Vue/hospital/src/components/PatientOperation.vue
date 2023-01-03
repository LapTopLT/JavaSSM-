<template>
  <div>
    <el-dialog title="编辑病人信息" :visible.sync="dialogFormVisible" append-to-body>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="姓名" prop="pname">
          <el-input v-model="ruleForm.pname"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="psex">
          <el-select v-model="ruleForm.psex" placeholder="请选择">
            <el-option
                v-for="item in sex"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="page">
          <el-input v-model.number="ruleForm.page"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="pnumber">
          <el-input v-model="ruleForm.pnumber"></el-input>
        </el-form-item>
        <el-form-item label="过敏史" prop="pallergic">
          <el-input
              type="textarea"
              :autosize="{ minRows: 4, maxRows: 6}"
              v-model="ruleForm.pallergic"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')">更新</el-button>
      </div>
    </el-dialog>
    <el-row :gutter="24">
      <el-col :span="8">
        <el-input
            placeholder="输入查询病人编号"
            v-model="search.searchId"
            @input="handleChange"
        ></el-input>
      </el-col>
      <el-col :span="8">
        <el-input
            v-model="search.searchName"
            placeholder="输入查询病人名"
            @input="handleChange"></el-input>
      </el-col>
    </el-row>
    <el-table
        v-loading="loading"
        :data="tableData"
        height="400"
        border
        style="width: 100%">
      <el-table-column
          prop="pid"
          label="病人id"
          width="90">
      </el-table-column>
      <el-table-column
          prop="uid"
          label="用户id"
          width="90">
      </el-table-column>
      <el-table-column
          prop="pname"
          label="病人姓名"
          width="90">
      </el-table-column>
      <el-table-column
          prop="psex"
          label="病人性别"
          width="90">
      </el-table-column>
      <el-table-column
          prop="page"
          label="病人年龄"
          width="90">
      </el-table-column>
      <el-table-column
          prop="pnumber"
          label="病人电话号码"
          width="90">
      </el-table-column>
      <el-table-column
          prop="pallergic"
          label="过敏信息"
          width="90">
      </el-table-column>
      <el-table-column
          widith="140">
        <template slot-scope="scope">
          <el-button type="primary" @click="openDialogForm(scope.row)">编辑</el-button>
          <el-button type="danger" @click="handleDel(scope.$index,scope.row.pid)">删除</el-button>
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
  name: "PatientOperation",
  data(){
    return{
      loading : true,
      ruleForm: {
        uid : '',
        pid : '',
        pname : '',
        psex : '',
        page : '',
        pnumber : '',
        pallergic : '',
      },sex : [
        {value : '男',label : '男'},
        {value : '女',label: '女'}
      ]
      ,
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ],
        gender:[
          {required : true, message : '请选择性别！',trigger : 'change'}
        ],
        age:[
          {required : true,message:'请填写年龄！'}
        ],
        number:[
          {required : true,message:'请填写手机号！',trigger : 'blur'},
          {min : 11,max : 11,message: '请填写11位手机号！',trigger: 'blur'}
        ]
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
    }
  },
  methods:{
    openDialogForm(row){
      this.ruleForm.uid = row.uid
      this.ruleForm.pid = row.pid
      this.ruleForm.pname = row.pname
      this.ruleForm.psex = row.psex
      this.ruleForm.page = row.page
      this.ruleForm.pnumber = row.pnumber
      this.ruleForm.pallergic = row.pallergic

      this.dialogFormVisible = true
    },
    handleEdit(){
      const data = this.$qs.stringify({
        pid : this.ruleForm.pid,
        pname : this.ruleForm.pname,
        psex : this.ruleForm.psex,
        page : this.ruleForm.page,
        pnumber : this.ruleForm.pnumber,
        pallergic : this.ruleForm.pallergic,
      })
      this.axios({
        url : '/admin/updatePatient',
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
    handleDel(index,pid){
      const data = this.$qs.stringify({
        pid
      })
      this.$confirm('确定要删除吗？删除后不可恢复','提示',{
        confirmButtonText : '删除',
        cancelButtonText : '取消',
        type : 'warning'
      }).then(async ()=>{
        await this.axios({
          url : '/admin/deletePatientByPid',
          method : 'post',
          data
        }).then(res=>{
          if(res.data === 1){
            this.$message.success('删除成功！')
            this.tableData.splice(index,1)
            if (this.total % 5 === 1 && this.currentPage >= 1) {
              if (this.total / 5 < this.currentPage) {
                this.currentPage = this.currentPage - 1;
              }
            }
            this.handleChange()
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
          this.axios({
            method : 'post',
            url : '/admin/updatePatient',
            data : this.$qs.stringify({
              pid : this.ruleForm.pid,
              pname : this.ruleForm.pname,
              psex : this.ruleForm.psex,
              page : this.ruleForm.page,
              pnumber : this.ruleForm.pnumber,
              pallergic : this.ruleForm.pallergic,
            })
          }).then(res=>{
            if(res.data === 1){
              this.$message({
                type : "success",
                message : '更新医生信息成功！'
              })
              this.handleChange()
              this.dialogFormVisible = false
            }else{
              this.$message({
                type : "error",
                message : '更新失败，请重试！'
              })
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
      this.getAllPatient()
      this.getPatientRowCount()
    },
    async getAllPatient(){
      const dataPost = this.$qs.stringify({
        currentPage : this.currentPage,
        pid : this.search.searchId,
        pname : this.search.searchName
      })
      await this.axios({
        url : '/admin/getAllPatient',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.tableData = res.data
        this.loading = false
      })
    },
    async getPatientRowCount(){
      const dataPost = this.$qs.stringify({
        pid : this.search.searchId,
        pname : this.search.searchName
      })
      await this.axios({
        url : '/admin/getPatientRowCount',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.total = res.data
      })
    }
  },
  async created() {
    await this.getAllPatient();
    await this.getPatientRowCount()
  }
}
</script>

<style scoped>

</style>