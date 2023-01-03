<template>
<div>
  <el-dialog title="挂号信息"
             :visible.sync="dialogFormVisible"
              append-to-body
              :width="'90%'">
      <el-tabs tab-position="left" style="height: 600px;" @tab-click="tabClick">
        <el-tab-pane
            v-for="item in depts"
            :key="item.deptno"
            :label="item.deptname"
            :name="item.deptno"
            >
          <el-calendar :range="[startDate, endDate]"
                          :first-day-of-week="new Date().getDay()"
                          v-model="ruleForm.date1">
        </el-calendar>
          <el-table
              :data="tableData"
              style="width: 100%">
            <el-table-column
              prop="dname">
            </el-table-column>
            <el-table-column>
              <template slot-scope="scope">
                    时间：{{simpleFormat(ruleForm.date1)}} {{dutytime(scope.row.dutytime)}}
              </template>
            </el-table-column>
            <el-table-column>
              <template slot-scope="scope">
                <el-tag type="success" effect="light">总号数：{{scope.row.dutyamount}}</el-tag>
              </template>
            </el-table-column>
            <el-table-column>
              <template slot-scope="scope">
                <el-popover
                    placement="bottom"
                    width="400"
                    trigger="click">
                  <el-button v-for="item in timeArr"
                              :key="item"
                             @click="submitForm(scope.row,item)"
                              >{{item}}</el-button>
                  <el-button slot="reference" size="mini" v-if="scope.row.dutyamount > 0" type="success" @click="handleClick(scope.row)">预约</el-button>
                </el-popover>
                <el-button size="mini" v-if="scope.row.dutyamount === 0" type="info" disabled>无号源</el-button>
                <span style="color: #F56C6C;font-size: 18px">￥{{scope.row.dutycharge}}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

      </el-tabs>
<!--      <el-form-item label="科室" prop="deptno">
        <el-select v-model="deptno" placeholder="请选择科室">
          <el-option
              v-for="item in depts"
              :key="item.deptno"
              :label="item.deptname"
              :value="item.deptno">
          </el-option>
        </el-select>
      </el-form-item>-->
<!--      <el-form-item label="医生" prop="did">
        <el-select v-model="ruleForm.did" placeholder="请选择医生">
          <el-option
              v-for="item in doctors"
              :key="item.did"
              :label="item.dname"
              :value="item.did">
          </el-option>
        </el-select>
      </el-form-item>-->

<!--    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="submitForm('ruleForm')">挂号</el-button>
    </div>-->
  </el-dialog>
  <el-row :gutter="10">
    <el-col :span="8" :offset="18">
      <div >
        <el-button type="success" @click="regUpdate">更新状态</el-button>
        <el-button type="danger" @click="cancelReg" v-if="this.reg.regstate === 1">取消挂号</el-button>
      </div>
    </el-col>
  </el-row>
  <el-row :gutter="10">
    <el-col :offset="8" :span="8">
      <div><span style="font-size: 18px;font-weight: bold">挂号状态：
          <span v-if="this.reg.regstate === 1">{{getState()}}</span>
          <span v-else-if="this.reg.regstate === 2" style="color: #009be2">{{getState()}}</span>
      </span></div>
    </el-col>
  </el-row>
  <el-row :gutter="10" v-if="reg.regstate > 0">
    <el-col :offset="4" :span="12">
      <div class="grid-content bg-purple">
        <el-descriptions class="margin-top" border title="挂号单信息" :column="2">
        <el-descriptions-item label="挂号单编号">{{reg.regid}}</el-descriptions-item>
        <el-descriptions-item label="病人姓名">{{pname}}</el-descriptions-item>
        <el-descriptions-item label="看诊医生">
          <el-popover trigger="hover" placement="left">
            <p>医生编号: {{ doctorInfo.did }}</p>
            <p>医生姓名: {{ doctorInfo.dname }}</p>
            <p>医生性别：{{ doctorInfo.dsex }}</p>
            <p>医生年龄: {{ doctorInfo.dage }}</p>
            <p>医生电话: {{ doctorInfo.dnumber }}</p>
            <p>所在科室：{{ doctorInfo.deptname }}</p>
            <div slot="reference" class="name-wrapper">
              <el-button type="text">{{ doctorInfo.dname }}</el-button>
            </div>
          </el-popover>
        </el-descriptions-item>
        <el-descriptions-item label="预约时间">
          <el-tag size="small">{{reg.regtime}}</el-tag>
        </el-descriptions-item>
      </el-descriptions></div>
    </el-col>
<!--    <el-col :span="8">
      <div class="grid-content bg-purple">
        <el-descriptions class="margin-top" border title="医生信息" :column="2">
        <el-descriptions-item label="医生编号">{{doctorInfo.did}}</el-descriptions-item>
        <el-descriptions-item label="医生姓名">{{doctorInfo.dname}}</el-descriptions-item>
        <el-descriptions-item label="医生性别">{{doctorInfo.dsex}}</el-descriptions-item>
        <el-descriptions-item label="年龄">
          {{doctorInfo.dage}}
        </el-descriptions-item>
        <el-descriptions-item label="电话号码">{{doctorInfo.dnumber}}</el-descriptions-item>
          <el-descriptions-item label="所在科室">{{doctorInfo.deptname}}</el-descriptions-item>
      </el-descriptions></div>
    </el-col>-->
  </el-row>
  <el-row :gutter="10" v-else>
    <el-col :offset="6" :span="12" >
      <div class="change-icon">
<!--        <el-image
            :src="picture"
            @click="addReg"
            style="cursor: pointer;"
            @mouseover="iover"
            @mouseleave="ileave">
        </el-image>-->
        <i class="el-icon-circle-plus"  :style="{cursor : 'pointer',color:iconColor}" @mouseleave="offMouse" @mouseover="onMouse" @mousedown="clickMouse" @mouseup="addReg"></i>
      </div>
    </el-col>
  </el-row>
</div>
</template>

<script>
const dayOfMs = 24 * 60 * 60 * 1000
export default {
  name: "PatientReg",
  data(){
    return{
      iconColor : '',
      startDate : this.simpleFormat(new Date()),
      endDate : this.simpleFormat(new Date() + 6 * dayOfMs),
      reg : {
        regid : '',
        pid : '',
        did : '',
        regtime : '',
        regstate : 0,
        regdesc : '',
      },
        doctorInfo:{
          did : '',
          dname : '',
          dsex : '',
          dage : '',
          dnumber : '',
          deptname : ''
        },
        tableData:[]
        ,
      picture : require('@/assets/add.png'),
      imgObj : {
        imgOn : require('@/assets/addBlue.png'),
        imgOff : require('@/assets/add.png')
      },
      dialogFormVisible : false,
      ruleForm: {
        regid : '',
        pid : '',
        did : '',
        regtime : '',
        regstate : 0,
        regdesc : '',
        date1: '',
        date2: ''
      },
      depts : [],
      deptno : '',
      doctors : [],
      pname : '',
      morning : ["9:00","9:30","10:00","10:30","11:00","11:30"],
      afternoon : ["14:00","14:30","15:00","15:30","16:00","16:30"],
      timeArr : []
    }
  },
  methods:{
    cancelReg(){
      const data = this.$qs.stringify({
        pid : this.reg.pid
      })
      console.log(this.reg.pid)
      this.$confirm('确定要取消挂号吗？','取消',{
        confirmButtonText : '确定',
        cancelButtonText : '取消'
      }).then(async ()=>{

        await this.axios({
          url : '/patient/cancelReg',
          method : 'post',
          data
        }).then(res=>{
          if(res.data === 1){
            this.$message.success('删除成功！')
            this.reg.regstate = 0
          }
        })
      })
    },
    onMouse(){
      this.iconColor = '#409EFF'
    },
    offMouse(){
      this.iconColor = '#909399'
    }
    ,
    clickMouse(){
      this.iconColor = '#7db5f3'
    }
  ,
    async queryDocList(){
      const dataPost = this.$qs.stringify({
        deptno : this.deptno,
        dutyday : this.getDay(this.ruleForm.date1)
      })
      await this.axios({
        url : '/patient/queryDocList',
        method : 'post',
        data : dataPost
      }).then(res=>{
        //如果现在大于12点，删除今天上午的挂号列表，如果超过下午5点，那么就删除下午的挂号列表
        let data = res.data,now = new Date()
        const hour = now.getHours()
        const today = now.getDay()
        if(hour >= 12 && today === this.getDay(this.ruleForm.date1)){
          for (let i = 0; i < data.length; i++) {
            if(data[i].dutytime === 1){
              data.splice(i,1)
              i-=1
            }
          }
        }
        if(hour >= 17 && today === this.getDay(this.ruleForm.date1)){
          data = []
        }

        this.tableData = data
      })
    },
    //预约按钮，点击后弹出选择时间，只有固定的时间段
    //早上9点-12点 每半个小时作为一次选择
    //下午2点-5点 每半个小时一次
    //9 + 0.5 * 下标 = 对应时间
    handleClick(row){
      const now = new Date()
      let hour = now.getHours(),minute = now.getMinutes(),today = now.getDay()
      if(row.dutytime === 1){
        //代表时间在上午并且是今天，进行删减.
        if(today === new Date(this.ruleForm.date1).getDay()){
          let start = (hour - 9) * 2
          let plus = minute >= 30 ? 2 : 1
          start += plus
          this.timeArr = this.morning.slice(start)
        }else{
          this.timeArr = this.morning
        }
      }else if(row.dutytime === 2){
        //时间在今天并且是下午，删减小于自己的时间
        if(today === new Date(this.ruleForm.date1).getDay()){
          let start = (hour - 14) * 2
          let plus = minute >= 30 ? 2 : 1
          start += plus
          this.timeArr = this.afternoon.slice(start)
        }else{
          this.timeArr = this.afternoon
        }
      }
      //展示出时间并让用户选择

    },
    dutytime(time){
      if(time === 1){
        return '上午'
      }else{
        return '下午'
      }
    },
    tabClick(tab){
      this.deptno = tab.name//tab.name是编号
      // this.$message.success('当前选中科室名:'+tab.label + "，编号为："+tab.name)
      this.queryDocList()
    },
    getDay(time){
      return new Date(time).getDay()
    }
    ,
    simpleFormat(time){
      const date = new Date(time)
      const year = date.getFullYear()
      const month = date.getMonth() + 1 < 10 ? '0' + date.getMonth() + 1 : date.getMonth() + 1
      const day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
      return year + '-' + month + '-' + day
    },
    dateFormat(time){
      const date = new Date(time)
      const year = date.getFullYear()
      const month = date.getMonth() + 1 < 10 ? '0' + date.getMonth() + 1 : date.getMonth() + 1
      const day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
      const hour = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
      const minute = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
      return year + '-' + month + '-' + day + ' ' + hour + ':' + minute
    },
    getState(){
      if(this.reg.regstate === 0){
        return '无'
      }else if(this.reg.regstate === 1){
        return '等待叫号中...'
      }else if(this.reg.regstate === 2){
        return '请前往医生处就诊！'
      }
    },
    addReg(){
      this.dialogFormVisible = true
    }
    ,
    iover(){
      this.picture = this.imgObj.imgOn
    },
    ileave(){
      this.picture = this.imgObj.imgOff
    },
    async regUpdate() {
      await this.axios({
        method: 'post',
        //根据病人的id查询regstate不为0的挂号数据（应该并且只能有一个）。
        //这里是查询还未完成的挂号单，
        url: 'patient/queryReg',
        data: this.$qs.stringify({
          pid: this.reg.pid,
          single : true
        })
      }).then(async res => {
        if(res.data.length !== 0){
          this.reg = res.data[0]
          await this.axios({
            url : '/doctor/queryByDid',
            method : 'post',
            data : this.$qs.stringify({
              did : this.reg.did
            })
          }).then(res =>{
             this.doctorInfo = res.data
          })
        }else{
          this.reg.regstate = 0
        }
      })
    },
    submitForm(row,time) {
      this.ruleForm.date2 = time
      this.$confirm('确定挂号？挂号费￥'+row.dutycharge,{
        confirmButtonText : '确认并缴费'
      }).then(()=>{
            const dataPost = this.$qs.stringify({
              regid: '',
              pid: this.reg.pid,
              did: row.did,
              regtime: this.simpleFormat(this.ruleForm.date1) + ' '+ this.ruleForm.date2,
              regstate: 1,
              regdesc: ''
            })
             this.axios({
              url: '/patient/reg',
              method: 'post',
              data:dataPost
            }).then(res => {
              if (res.data === 2) {
                this.$message({
                  type: 'success',
                  message: '挂号成功，请耐心等待医生叫号！'
                })
                this.reg.regstate = 1
                this.regUpdate()
                this.dialogFormVisible = false
              } else if(res.data === 1){
                this.$message({
                  type: 'error',
                  message: '更新挂号数时出错，请检查日期'
                })
              }else{
                this.$message({
                  type: 'error',
                  message: '挂号失败，请重试！'
                })
              }
            })
      })
    }
  }
  ,
  async created(){
    if(sessionStorage.getItem('patientData') === null){
      await this.axios({
        method : 'post',
        url : '/patient/query',
        data : this.$qs.stringify({uid: JSON.parse(sessionStorage.getItem('user')).uid})
      }).then(async res=>{
        await sessionStorage.setItem('patientData',JSON.stringify(res.data))
      })
    }
    const patientData = JSON.parse(sessionStorage.getItem('patientData'))
    if(patientData.pid === undefined){
      await this.$alert('请先创建病人信息！')
      this.$router.push('/patient')
    }
    this.reg.pid = patientData.pid
    this.pname = patientData.pname

    await this.axios({
      method : 'post',
      url : 'doctor/queryDepts'
    }).then(res =>{
      this.depts = res.data
    })

    await this.regUpdate()

  },watch:{
    'ruleForm.date1'(){
      this.queryDocList()
    }
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.change-icon{
  font-size: 260px;
}

</style>