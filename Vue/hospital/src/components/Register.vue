<template>
  <div>
    <el-row :gutter="20" class="el-row">
      <!-- gutter 栅格间距 -->
      <el-col :span="8" :offset="8">
        <!-- span 栅格占的列数，offset是偏移列数 -->
        <el-card shadow="always" class="el-card">
          <h1>用户注册</h1>
          <el-divider></el-divider>
          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="uname">
              <el-input v-model="ruleForm.uname"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="upassword">
              <el-input type="password" v-model="ruleForm.upassword" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="uemail">
              <el-input v-model="ruleForm.uemail"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "RegisterPage",
  data()
  {
    const validateUname=async(rule,value,callback) =>{

    const data = this.$qs.stringify({
      uname : value
    })
     await this.axios({
      url : '/user/checkUname',
      method : 'post',
      data
    }).then(res=>{
      if(res.data !== 0){
        return callback(new Error('用户名已被占用！'))
      }else{
        callback()
      }
    })
  };
    const checkUname = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户名不能为空！'));
      }else if(value.length > 8){
        return callback(new Error('用户名最长不能超过8位！'))
      }else{
        callback()
      }
    };
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.upassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        upassword: '',
        checkPass: '',
        uname: '',
        uemail:''
      },
      rules: {
        uemail:[
          {required : true,message : '必须填写邮箱！',trigger:'blur'},
          {type : "email",message : '邮箱格式不正确！',trigger: 'blur'}
        ],
        upassword: [
          {required : true, message : '用户名不能为空！' ,trigger: 'blur'},
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          {required : true, message : '密码不能为空！' ,trigger: 'blur'},
          { validator: validatePass2, trigger: 'blur' }
        ],
        uname: [
          {required : true, message: '用户名不能为空',trigger: 'blur'},
          { validator: checkUname, trigger: 'blur' },
          {validator : validateUname,trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let postData=this.$qs.stringify({
            uname : this.ruleForm.uname,
            upassword : this.ruleForm.upassword,
            uemail : this.ruleForm.uemail
          })
          this.axios({
            method:'post',
            url : '/user/regist',
            data : postData
          }).then(res=>{
            if(res.data !== 1){
              this.$message({
                message : '注册失败！',
                type : 'error'
              })
              return
            }
            this.$message({
              message : '注册成功！',
              type : 'success'
            })
            this.$router.push('/')
          })
        } else {

          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
.content{
  margin: 0 auto;
}
.el-card{
  border-radius:30px;
  /*box-shadow: 0 2px 12px 0 rgb(243, 102, 102);*/
  /*box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);*/
}
.grid-content {
  /* background: rgb(14, 214, 131); */
  border-radius: 4px;
  min-height: 80px;
}
.el-row {
  margin-top: 85px;
  margin-bottom: 20px;
}
</style>