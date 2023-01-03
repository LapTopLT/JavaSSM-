<template>
  <div>
    <el-dialog title="编辑医生信息" :visible.sync="dialogFormVisible" append-to-body>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="ruleForm.gender" placeholder="请选择">
            <el-option
                v-for="item in sex"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model.number="ruleForm.age"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="number">
          <el-input v-model="ruleForm.number"></el-input>
        </el-form-item>
        <el-form-item label="所在科室" prop="deptname">
          <el-select v-model="ruleForm.deptno" placeholder="请选择">
            <el-option
                v-for="item in depts"
                :key="item.deptno"
                :label="item.deptname"
                :value="item.deptno">
            </el-option>
          </el-select>
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
            placeholder="输入查询医生编号"
            v-model="search.searchId"
            @input="handleChange"
        ></el-input>
      </el-col>
      <el-col :span="8">
        <el-input
            v-model="search.searchName"
            placeholder="输入查询医生名"
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
          prop="uid"
          label="用户id"
          width="90">
      </el-table-column>
      <el-table-column
          prop="did"
          label="医生编号"
          width="90">
      </el-table-column>
      <el-table-column
          prop="dname"
          label="医生姓名"
          width="90">
      </el-table-column>
      <el-table-column
          prop="dsex"
          label="医生性别"
          width="90">
      </el-table-column>
      <el-table-column
          prop="dage"
          label="医生年龄"
          width="90">
      </el-table-column>
      <el-table-column
          prop="dnumber"
          label="医生电话号码"
          width="90">
      </el-table-column>
      <el-table-column
          prop="deptno"
          label="所在部门编号"
          width="90">
      </el-table-column>
      <el-table-column
          prop="deptname"
          label="所在部门名"
          width="90">
      </el-table-column>
      <el-table-column
            widith="140">
        <template slot-scope="scope">
          <el-button type="primary" @click="openDialogForm(scope.row)">编辑</el-button>
          <el-button type="danger" @click="handleDel(scope.$index,scope.row.did)">删除</el-button>
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
  name: "DoctorOperation",
  data(){
    return{
      depts:[],
      loading : true,
      ruleForm: {
        uid:'',
        name : '',
        gender : '',
        age : '',
        number : '',
        deptno : '',
        deptname : ''
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
        ],
        deptno:[
          { required: true, message: '请选择部门', trigger: 'change' }
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
      /*uid:'',
        name : '',
        gender : '',
        age : '',
        number : '',
        deptno : '',
        deptname : ''*/
      this.ruleForm.uid = row.uid
      this.ruleForm.did = row.did
      this.ruleForm.name = row.dname
      this.ruleForm.gender = row.dsex
      this.ruleForm.age = row.dage
      this.ruleForm.number = row.dnumber
      this.ruleForm.deptno = row.deptno
      this.ruleForm.deptname = row.deptname
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
    handleDel(index,did){
      const data = this.$qs.stringify({
        did
      })
      this.$confirm('确定要删除吗？删除后不可恢复','提示',{
        confirmButtonText : '删除',
        cancelButtonText : '取消',
        type : 'warning'
      }).then(async ()=>{
        await this.axios({
          url : '/admin/deleteDoc',
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
          this.axios({
            method : 'post',
            url : '/doctor/update',
            data : this.$qs.stringify({
              did : this.ruleForm.did,
              dname : this.ruleForm.name,
              dsex : this.ruleForm.gender,
              dage : this.ruleForm.age,
              dnumber : this.ruleForm.number
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
      this.getAllDoc()
      this.getDocRowCount()
    },
    async getAllDoc(){
      const dataPost = this.$qs.stringify({
        currentPage : this.currentPage,
        did : this.search.searchId,
        dname : this.search.searchName
      })
      await this.axios({
        url : '/admin/getAllDoc',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.tableData = res.data
        this.loading = false
      })
    },
    async getDocRowCount(){
      const dataPost = this.$qs.stringify({
        did : this.search.searchId,
        dname : this.search.searchName
      })
      await this.axios({
        url : '/admin/getDocRowCount',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.total = res.data
      })
    }
  },
  async created() {
    await this.getAllDoc();
    await this.getDocRowCount()
    await this.axios({
      method : 'post',
      url : '/doctor/queryDepts'
    }).then(res =>{
      this.depts = res.data
    })
  }
}
</script>

<style scoped>

</style>