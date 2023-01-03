<template>
  <div>
    <el-row :gutter="20" class="el-row">
      <!-- gutter 栅格间距 -->

      <el-col :span="8" :offset="8">
        <!-- span 栅格占的列数，offset是偏移列数 -->
        <el-card shadow="always" class="el-card">
          <div style="color: #009be2">
            <h1>医疗门诊系统</h1>
          </div>
          <el-divider>
            <div class="icon">
              <i aria-setsize="" class="el-icon-office-building" />
            </div>
          </el-divider>
          <el-form :model="nameValidateForm" ref="nameValidateForm" label-width="100px" class="demo-ruleForm">
            <!-- 用户名 -->
            <el-form-item
                label-width="1"
                class="item"
                prop="name"
                :rules="[
                    { required: true, message: '请输入用户名！'},
                    ]"
            >
              <el-col :span="24">
                <el-input @keyup.native.enter="submitForm('nameValidateForm')" prefix-icon="el-icon-user"  placeholder="请输入用户名" type="text" v-model="nameValidateForm.name" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>

            <!-- 密码 -->
            <el-form-item
                label-width="1"
                class="item"
                prop="password"
                :rules="[
                    { required: true, message: '请输入密码！'},
                    ]"
            >
              <el-col :span="24">
                <el-input @keyup.native.enter="submitForm('nameValidateForm')" prefix-icon="el-icon-lock" placeholder="请输入密码" v-model="nameValidateForm.password" show-password></el-input>
              </el-col>
            </el-form-item>
            <el-form-item
                label-width="1"
                class="item"
                prop="code"
                :rules="[
                    { required: true, message: '请输入验证码！'},
                    ]"
            >
              <el-col :span="16">
                <el-input @keyup.native.enter="submitForm('nameValidateForm')" prefix-icon="el-icon-key" placeholder="请输入验证码" v-model="nameValidateForm.code"></el-input>
              </el-col>
              <el-col :span="8">
                <el-image :src="code"
                        @click="changeCode"
                        style="cursor: pointer">
                  <div slot="error" class="image-slot">
                    <i class="el-icon-picture-outline"></i>
                  </div>
                </el-image>
              </el-col>
            </el-form-item>
            <div id="tools">
              <el-checkbox v-model="checked" style="display: inline-block">记住我</el-checkbox>
              <el-button @click="goToReg" type="text" style="display:inline-block;margin-left: 60%">点我注册</el-button>
            </div>
            <el-form-item class="item" label-width="1">
              <el-button style="width: 100%" type="primary"  @click="submitForm('nameValidateForm')" class="el-button">登录</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: "SysLogin",
  data()
  {
    return {
      code : 'http://localhost:8082/user/verifyCode',
      nameValidateForm:
          {
            name: '',
            password: '',
            code: ''
          },
      checked:false,
      dataTable:[]
    };
  },
  methods: {
    changeCode(){
      const time = new Date()
      this.code = 'http://localhost:8082/user/verifyCode?t='+time
    },
    submitForm(formName)
    {
      this.$refs[formName].validate((valid) =>
          {
            if (valid)
            {
              const data = this.$qs.stringify({
                code : this.nameValidateForm.code
              })

              this.axios({
                url : '/user/checkCode',
                method : 'post',
                data
              }).then(async res=>{
                if(res.data === 1){
                  let postData=this.$qs.stringify({
                    uname : this.nameValidateForm.name,
                    upassword : this.nameValidateForm.password
                  })
                  await this.axios({
                    method:'post',
                    url:'/user/login',
                    data : postData
                  }).then(async res => {
                    this.dataTable = res.data
                    if (this.dataTable.uname === undefined) {
                      this.$message({
                        message: '用户名或密码错误！',
                        type: "error"
                      })
                    } else {
                      this.$message({
                        message: '登陆成功！',
                        type: "success"
                      })
                      await this.$store.dispatch('login', res.data)
                      if(this.checked){
                        localStorage.setItem('user',JSON.stringify(res.data))
                      }
                      //请求用户权限对应的菜单
                      const role = this.$qs.stringify({
                        roleid: this.dataTable.roleid
                      })
                      await this.axios({
                        method: 'post',
                        url: '/user/queryMenu',
                        data: role
                      }).then(res => {
                        this.$store.dispatch('setMenus', res.data)
                      })
                      sessionStorage.setItem("user", JSON.stringify(res.data))
                      await this.$router.replace('/index')
                    }
                  })
                }
                else if(res.data === 0){
                  this.$message.error('验证码错误！')
                }
              })

            } else
            {
              console.log('error submit!!');
              return false;
            }
          }
      );
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },goToReg(){
      this.$router.push('/register')
    }
  },
  created() {
    const user = JSON.parse(localStorage.getItem('user'))
    if(user){
      sessionStorage.setItem('user',JSON.stringify(user))
      this.$store.dispatch('login',user)
      const role = this.$qs.stringify({
        roleid: user.roleid
      })
      this.axios({
        method: 'post',
        url: '/user/queryMenu',
        data: role
      }).then(res => {
        this.$store.dispatch('setMenus', res.data)
        this.$router.replace('/index')
      }).then(err=>{
        console.log(err)
      })
    }
  }
}
</script>

<style scoped>
.icon{
  color: #009be2;
  font-size: 25px;
}

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

#tools{
  width: 100%;
}


</style >