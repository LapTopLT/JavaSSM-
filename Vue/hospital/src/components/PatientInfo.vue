<template>
  <div>
    <el-descriptions class="margin-top" title="病人信息" :column="1" border>
      <template slot="extra">
        <el-button type="primary" size="medium" @click="editInfo">编辑</el-button>
        <el-dialog title="编辑病人信息" :visible.sync="dialogFormVisible" append-to-body>
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
            <el-form-item label="过敏史" prop="allergic">
              <el-input
                  type="textarea"
                  :autosize="{ minRows: 4, maxRows: 6}"
                  v-model="ruleForm.allergic"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" v-if="method === 'update'" @click="submitForm('ruleForm')">更新</el-button>
            <el-button type="primary" v-else-if="method === 'insert'" @click="submitForm('ruleForm')">新增</el-button>
          </div>
        </el-dialog>
      </template>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          病人ID
        </template>
        {{patientData.pid}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-message"></i>
          姓名
        </template>
        {{patientData.pname}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          性别
        </template>
        {{patientData.psex}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          年龄
        </template>
        {{patientData.page}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-phone"></i>
          电话号码
        </template>
        <el-tag size="small">{{patientData.pnumber}}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          过敏史
        </template>
        {{patientData.pallergic}}
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
export default {
  name: "PatientInfo",
  data(){
    return{
      patientData:{
        uid : '',
        pid : '您还未填写病人信息！请按右上角编辑进行填写！',
        pname : '',
        psex : '',
        page : '',
        pnumber : '',
        pallergic : '',
      },
        dialogFormVisible : false,
        formLabelWidth: '120px',
      ruleForm: {
        uid:'',
        name : '',
        gender : '',
        age : '',
        number : '',
        allergic : ''
      },
      sex : [
        {value : '男',label : '男'},
        {value : '女',label: '女'}
      ],
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
      method : ''
    }
  }
  ,
  async created() {
    if(sessionStorage.getItem('patientData') === null){
      await this.axios({
        method : 'post',
        url : '/patient/query',
        data : this.$qs.stringify({uid: JSON.parse(sessionStorage.getItem('user')).uid})
      }).then(async res=>{
        await sessionStorage.setItem('patientData',JSON.stringify(res.data))
      })
    }

    const data = sessionStorage.getItem('patientData')
    if(data !== "\"\""){
      this.patientData = JSON.parse(data)
      this.method = 'update'
    }else{
      this.method = 'insert'
    }
  },methods:{
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.method === 'insert'){
            this.axios({
              url : '/patient/insert',
              method : 'post',
              data : this.$qs.stringify({
                pid : '',
                uid : this.ruleForm.uid,
                pname : this.ruleForm.name,
                psex : this.ruleForm.gender,
                page : this.ruleForm.age,
                pnumber : this.ruleForm.number,
                pallergic : this.ruleForm.allergic,
              })
            }).then(res=>{
              if(res.data === 1){
                this.$message({
                  type : "success",
                  message : '新增病人信息成功！'
                })
                this.axios({
                  method : 'post',
                  url : '/patient/query',
                  data : this.$qs.stringify({uid: this.ruleForm.uid})
                }).then(res=>{
                  sessionStorage.setItem('patientData',JSON.stringify(res.data))
                  this.patientData = res.data
                  this.dialogFormVisible = false
                  this.method = 'update'
                })
              }else{
                this.$message({
                  type :"error",
                  message : "新增失败，请重试！"
                })
              }
            })

          }else{
            this.axios({
              method : 'post',
              url : '/patient/update',
              data : this.$qs.stringify({
                pid : this.patientData.pid,
                pname : this.ruleForm.name,
                psex : this.ruleForm.gender,
                page : this.ruleForm.age,
                pnumber : this.ruleForm.number,
                pallergic : this.ruleForm.allergic,
              })
            }).then(res=>{
              if(res.data === 1){
                this.$message({
                  type : "success",
                  message : '更新病人信息成功！'
                })
                this.axios({
                  method : 'post',
                  url : '/patient/query',
                  data : this.$qs.stringify({uid: this.ruleForm.uid})
                }).then(res=>{
                  sessionStorage.setItem('patientData',JSON.stringify(res.data))
                  this.patientData = res.data
                  this.dialogFormVisible = false
                })
              }else{
                this.$message({
                  type : "error",
                  message : '更新失败，请重试！'
                })
              }
            })

            this.dialogFormVisible = false
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    editInfo(){
      this.dialogFormVisible = true
      const data = JSON.parse(sessionStorage.getItem('patientData'))
      this.ruleForm.uid = this.$store.state.userData.uid
      if(data !== null){
        this.ruleForm.name = data.pname
        this.ruleForm.gender = data.psex
        this.ruleForm.age = data.page
        this.ruleForm.number = data.pnumber
        this.ruleForm.allergic = data.pallergic
      }
    }
  }

}
</script>

<style scoped>

</style>