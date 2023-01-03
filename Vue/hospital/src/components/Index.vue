<template>
  <div class="home">
    <el-container style="height: 100%" class="el-container">
      <el-header class="el-header">
          <el-image
              style="height: 50px;vertical-align: middle"
              :src="require('@/assets/titleIcon.jpeg')"
          ></el-image>
        <span style="vertical-align: middle">医疗门诊系统</span>
          <el-breadcrumb separator="/">
            <el-breadcrumb-item
                :to="{ path: item.path }"
                v-for="item in breadcrumbList"
                :key="item.path"
            >
              {{item.meta.title}}
            </el-breadcrumb-item>
          </el-breadcrumb>
        <div id="avatar" >
          <div style="vertical-align: middle;display: inline-block;" id="ava" @click="toUserInfo"><el-avatar > {{getUserNameShort}} </el-avatar></div>
          <div style="vertical-align: middle;display: inline-block;margin-left: 10px;"><label for="ava" class="lbl">{{getUserName}}</label></div>
          <div style="display: inline-block;vertical-align: middle;margin-left: 50px">
            <el-button
                type="text"
                @click="logout">退出登录</el-button>
          </div>
        </div>
      </el-header>
      <el-container style="height: 100%" class="el-container">
        <el-aside class="el-aside" width="200px" >
          <el-menu
              :default-active="this.$route.path"
              class="el-menu-vertical-demo"
              background-color="#545c64"
              text-color="#fff" router>
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>用户操作</span>
              </template>
              <el-menu-item-group>
                <template slot="title">用户</template>
                <el-menu-item index="/test">查看用户信息</el-menu-item>
              </el-menu-item-group>
            </el-submenu>

            <el-submenu v-for="(menu,i) in menus" :index="i + 2 + ''" :key="i + 1 + ''">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>{{menu.name}}</span>
              </template>
              <el-menu-item-group>
                <template slot="title">{{menu.title}}</template>
                <el-menu-item v-for="(c,i) in menu.children" :key="i" :index="c.path">
                  {{c.title}}
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main class="el-main"><router-view></router-view></el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>

export default {
  name: "IndexPage",
  data(){
    return{
      uname : '请先登录！',
      uid : '',
      upassword: '',
      uidcard: '',
      roleid:'',
      menus:[]
    }
  },
  computed:{
    breadcrumbList(){
      return this.$route.matched
    },
    getUserName(){
      return this.uname
    },
    getUserNameShort(){
      return this.uname.slice(0,1)
    }
  },
  methods:{
    logout(){
      sessionStorage.clear()
      this.$store.dispatch('logout')
      this.$router.push('/')
      this.$router.replace({path: '/'});
      location.reload();
    },
    toUserInfo(){
      this.$router.push('/test')
    }
  },created() {
    const user = JSON.parse(sessionStorage.getItem('user'))
    this.uid = user.uid;
    this.uname = user.uname;
    this.upassword = user.password;
    this.uidcard = user.uidcard;
    this.roleid = user.roleid;
    this.menus = this.$store.state.tree
  }
}
</script>

<style>
.home{
  position: absolute;
  height: 100%;
  width: 100%;
}

.el-container{
  margin-bottom: 40px;
  height: 100%;
}

.el-header, .el-footer {
  border-bottom: 1px solid #afafaf;
  position: fixed;
  z-index: 3;
  top: 0;
  left: 0;
  height: 80px;
  width: 100%;
  background-color: #fff;
  color: #333;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  font-size: 16px;
  line-height: 30px;
}


.el-aside {
  height:92vh;
  z-index: 2;
  margin-top: 50px;
  position: fixed;
  display: block;
  background-color: #545c64;
  color: white;
  text-align: center;
  line-height: 150px;
}
::-webkit-scrollbar {
  width: 0
}

.el-main {
  height: 100%;
  z-index: 1;
  margin-top: 55px;
  left:200px;
  right: 0;
  position: absolute;
  background-color: #ffffff;
  color: #333;
  /*text-align: center;*/
  line-height: 30px;
  overflow-x: hidden;
}

body,html{
  height: 100%;
  background-color: #e8e8e8;
  overflow: hidden;
}

.el-collapse-item__header {
  display: flex;
  align-items: center;
  height: 48px;
  line-height: 48px;
  background-color: #FFF;
  color: #303133;
  cursor: pointer;
  border-bottom: 1px solid #EBEEF5;
  font-size: 18px;
  font-weight: 500;
  transition: border-bottom-color .3s;
  outline: 0;
}

.el-breadcrumb{
  position: absolute;
  left: 240px;
  bottom: 10px;
  font-size: 18px;
}

#avatar {
  position: absolute;
  right: 15px;
  top: 10px;
}

#ava:hover{

  cursor: pointer;
}

.lbl:hover{
  color: #009be2;
  cursor: pointer;
}

.el-menu{
  overflow:hidden;
}

.el-descriptions-item__content {
  width: 70%;
  word-break: break-word;
  overflow-wrap: break-word;
}
</style>