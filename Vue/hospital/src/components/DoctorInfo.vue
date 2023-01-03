<template>
  <div>
    <el-descriptions class="margin-top" title="医生信息" :column="1" border>
      <template slot="extra">
        <el-button type="success" @click="timeVisible = true">编辑值班信息</el-button>
        <el-dialog :width="'85%'" title="编辑值班信息" :visible.sync="timeVisible" append-to-body>
          <el-row :gutter="24">
            <el-col :span="6">
                <el-input v-model="dutycharge">
                  <template slot="prepend">挂号费</template>
                  <template slot="append">元</template>
                </el-input>
            </el-col>
            <el-col :span="18">
              日期
              <el-select v-model="day" placeholder="请选择日期" @change="time = '1'">
                <el-option label="星期一" value="1"></el-option>
                <el-option label="星期二" value="2"></el-option>
                <el-option label="星期三" value="3"></el-option>
                <el-option label="星期四" value="4"></el-option>
                <el-option label="星期五" value="5"></el-option>
                <el-option label="星期六" value="6"></el-option>
                <el-option label="星期日" value="0"></el-option>
              </el-select>
              时间
              <el-select v-model="time" placeholder="请选择时间">
                <el-option label="上午" value="1"></el-option>
                <el-option label="下午" value="2"></el-option>
              </el-select>
              <el-button icon="el-icon-circle-plus-outline" type="text" @click="handleAdd">添加</el-button>
            </el-col>
          </el-row>
<el-divider></el-divider>
            <div>
              <el-tag
                  :key="item.dutyday + index"
                  v-for="(item,index) in list"
                  closable
                  :disable-transitions="false"
                  @close="handleClose(item)">
                {{getDay(item.dutyday)}} {{getTime(item.dutytime)}}
              </el-tag>
            </div>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="handleEdit">确 定</el-button>
          </div>
        </el-dialog>

        <el-button type="primary" size="medium" @click="editInfo">编辑医生信息</el-button>
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
            <el-form-item label="所在科室" prop="deptname" v-if="method === 'insert'">
              <el-select v-model="ruleForm.deptno" placeholder="请选择">
                <el-option
                    v-for="item in depts"
                    :key="item.deptno"
                    :label="item.deptname"
                    :value="item.deptno">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="科室名" v-else-if="method === 'update'">
              <el-tooltip content="不能随意更改科室，如有需要请联系管理员" placement="top" effect="dark">
                <el-input
                    placeholder="你没有在任何部门！"
                    v-model="doctorInfo.deptname"
                    :disabled="true">
                </el-input>
              </el-tooltip>
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
          医生ID
        </template>
        {{doctorInfo.did}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-message"></i>
          姓名
        </template>
        {{doctorInfo.dname}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          性别
        </template>
        {{doctorInfo.dsex}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          年龄
        </template>
        {{doctorInfo.dage}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-phone"></i>
          电话号码
        </template>
        <el-tag size="small">{{doctorInfo.dnumber}}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          科室编号
        </template>
        {{doctorInfo.deptno}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          科室名
        </template>
        {{doctorInfo.deptname}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-watch"></i>
          看诊时间
        </template>
        <el-tag
            type="success"
            v-for="(item,index) in duty"
            :key="item.dutyday + index"

        >{{getDay(item.dutyday)}} {{getTime(item.dutytime)}}</el-tag>
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
export default {
  name: "DoctorInfo",
  data(){
    return{
      timeVisible : false,
      doctorInfo : {
        uid : '',
        did : '您还未填写医生信息，点右上角填写！',
        dname : '',
        dsex : '',
        dage : '',
        dnumber : '',
        deptno : '',
        deptname : ''
      },
      ruleForm: {
        uid:'',
        name : '',
        gender : '',
        age : '',
        number : '',
        deptno : '',
        deptname : ''
      },
        sex : [
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
      method : '',
      depts:[],
      dialogFormVisible : false,
      duty : [],
      list : [],
      day : '',
      time : '',
      dutycharge : 8
    }
  },
  methods:{
    handleEdit(){
      const data = this.$qs.stringify({
        list : JSON.stringify(this.list)
      })
      console.log(data)
      console.log(this.list)
      this.axios({
        url : '/doctor/changeDuty',
        method : 'post',
        data
      }).then(res=>{
          if(res.data === this.duty.length + this.list.length){
            this.$message.success("设置值班时间成功！")
            this.timeVisible = false
            this.duty = this.list
          }
      })
      console.log(this.list)
    },
    handleClose(tag){
      this.list.splice(this.list.indexOf(tag), 1);
    },
    handleAdd(){
      let flag = true
      if(this.dutycharge < 0){
        this.$message.error('挂号费不能小于0！')
        flag = false
      }
      if(this.time === '' || this.day === ''){
        this.$message.error('请选择时间添加！')
        flag = false
      }else{
        let day = Number(this.day)
        let time = Number(this.time)
        this.list.forEach((item)=>{
          if(item.dutyday == day && item.dutytime == time){
            this.$message.error('不能添加重复的时间！')
            flag = false
            return
          }
        })
      }
      if(flag){
        this.list.push({
          did : this.doctorInfo.did,
          dutyamount : 30,
          dutycharge : this.dutycharge,
          dutyday : this.day,
          dutytime : this.time,
        })
      }
    },
    getDay(day){
      day = Number(day)
      if(day === 1){
        return '星期一'
      }else if(day === 2){
        return '星期二'
      }else if(day === 3){
        return '星期三'
      }else if(day === 4){
        return '星期四'
      }else if(day === 5){
        return '星期五'
      }else if(day === 6){
        return '星期六'
      }else if(day === 0){
        return '星期日'
      }
    },
    getTime(time){
      time = Number(time)
      if(time === 1){
        return '上午'
      }else if(time === 2){
        return '下午'
      }
    }
    ,
    async queryDutyByDid(did){
      const data = this.$qs.stringify({
        did
      })
      await this.axios({
        url : '/doctor/queryDutyByDid',
        method : 'post',
        data
      }).then(res=>{
        this.duty = res.data
        this.list = JSON.parse(JSON.stringify(res.data))
        console.log(this.list)
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const dataPost = this.$qs.stringify({
            did : '',
            uid : this.ruleForm.uid,
            dname : this.ruleForm.name,
            dsex : this.ruleForm.gender,
            dage : this.ruleForm.age,
            dnumber : this.ruleForm.number,
            deptno : this.ruleForm.deptno
          })
          console.log(dataPost)
          if(this.method === 'insert'){
            this.axios({
              url : '/doctor/insert',
              method : 'post',
              data : dataPost
            }).then(res=>{
              if(res.data === 1){
                this.$message({
                  type : "success",
                  message : '新增医生信息成功！'
                })
                this.axios({
                  method : 'post',
                  url : '/doctor/query',
                  data : this.$qs.stringify({uid: this.ruleForm.uid})
                }).then(res=>{
                  sessionStorage.setItem('doctorData',JSON.stringify(res.data))
                  this.doctorInfo = res.data
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
              url : '/doctor/update',
              data : this.$qs.stringify({
                did : this.doctorInfo.did,
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
                this.axios({
                  method : 'post',
                  url : '/doctor/query',
                  data : this.$qs.stringify({uid: this.ruleForm.uid})
                }).then(res=>{
                  sessionStorage.setItem('doctorData',JSON.stringify(res.data))
                  this.doctorInfo = res.data
                  this.dialogFormVisible = false
                })
              }else{
                this.$message({
                  type : "error",
                  message : '更新失败，请重试！'
                })
              }
            })
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    editInfo(){
      console.log("in editInfo")
      this.dialogFormVisible = true
      const data = JSON.parse(sessionStorage.getItem('doctorData'))
      this.ruleForm.uid = this.$store.state.userData.uid
      if(data !== null){
        this.ruleForm.name = data.dname
        this.ruleForm.gender = data.dsex
        this.ruleForm.age = data.dage
        this.ruleForm.number = data.dnumber
        this.ruleForm.deptno = data.deptno
        this.ruleForm.deptname = data.deptname
      }
    }
}
  ,

   async created() {
     const uid = JSON.parse(sessionStorage.getItem('user')).uid
    if (sessionStorage.getItem('doctorData') === null) {
       await this.axios({
        method: 'post',
        url: '/doctor/query',
        data: this.$qs.stringify({uid})
      }).then(async res => {
        await sessionStorage.setItem('doctorData', JSON.stringify(res.data))
      })
    }

    const data = sessionStorage.getItem('doctorData')
     if(data !== "\"\""){
       this.doctorInfo = JSON.parse(data)
       this.method = 'update'
     }else{
       this.method = 'insert'
     }

     await this.axios({
       method : 'post',
       url : '/doctor/queryDepts'
     }).then(res =>{
       this.depts = res.data
     })

     await this.queryDutyByDid(this.doctorInfo.did)
  }
}
</script>

<style scoped>

</style>