<template>
<div>
  <el-descriptions title="用户信息" class="margin-top" :column="1" border>

    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        用户名
      </template>
      {{userData.uname}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-message"></i>
        邮箱
      </template>
      {{userData.uemail}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        用户身份
      </template>
      <el-tag size="small" :type="userData.msgColor">{{getRole()}}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
        用户id
      </template>
      {{userData.uid}}
    </el-descriptions-item>
  </el-descriptions>
</div>
</template>

<script>
export default {
  name: "testFunc",
  data(){
    return{
      userData:{
        uid : '',
        uname : '',
        upassword : '',
        uemail : '',
        roleid : '',
        msgColor : ''
      }
    }
  },methods:{
    getRole(){
      if(this.userData.roleid === 1){
        return '病人'
      }else if(this.userData.roleid === 2){
        return '医生'
      }else if(this.userData.roleid === 3){
        this.userData.msgColor = 'success'
        return '管理员'
      }else if(this.userData.roleid === 4){
        return '药房管理员'
      }else if(this.userData.roleid === 5){
        this.userData.msgColor = 'danger'
        return '超级管理员'
      }
    }
  }
  ,
  mounted() {
    this.userData = JSON.parse(sessionStorage.getItem('user'))
    if(this.userData === null){
      this.$alert('请先登录！')
      this.$router.push('/')
    }
    this.getRole()
  }
}
</script>

<style>

</style>