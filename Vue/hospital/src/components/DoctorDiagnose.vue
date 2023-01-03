<template>
  <div>
    <el-dialog title="挂号队列"
               width="70%"
               :visible.sync="dialogTableVisible"
               append-to-body>
      <el-table
          v-loading="loading"
          :data="tableData"
          height="380"
          style="width: 100%">
        <el-table-column
            fixed
            prop="regtime"
            label="预约日期"
            width="120">
        </el-table-column>
        <el-table-column
            prop="regid"
            label="挂号单编号"
            width="120">
        </el-table-column>
        <el-table-column
            label="看诊医师"
            width="120">
          {{dname}}
        </el-table-column>
        <el-table-column
            label="患者"
            width="120">
          <template slot-scope="scope">
            <el-popover  trigger="click" placement="bottom">
              <p>病人编号: {{ patientInfo.pid }}</p>
              <p>病人姓名: {{ patientInfo.pname }}</p>
              <p>病人性别：{{ patientInfo.psex }}</p>
              <p>病人年龄: {{ patientInfo.page }}</p>
              <p>过敏信息: {{ patientInfo.pallergic }}</p>
              <div slot="reference" class="name-wrapper">
                <el-button type="text" @click="getPatient(scope.row.pid)">{{scope.row.pid}}</el-button>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
            label="操作"
            width="120">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">叫号</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          :disabled="disablePage"
          :current-page.sync="currentPage"
          small
          layout="prev, pager, next"
          :page-size="pageSize"
          :total="total"
          @current-change="getRegList(did)">
      </el-pagination>
    </el-dialog>
    <el-dialog title="药品列表"
               width="70%"
               :visible.sync="medVisible"
               append-to-body>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="formInline.search" size="mini" placeholder="根据药名查询" v-on:input="handleSearch()">
        </el-input>
      </el-form-item>
    </el-form>
      <el-table
          v-loading="loading"
          :data="medlist"
          style="width: 100%"
          height="360">
        <el-table-column
            prop="medid"
            label="药品编号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="medname"
            label="药名"
            width="150">
        </el-table-column>
        <el-table-column
            prop="medamount"
            label="药品余量"
            width="150">
        </el-table-column>
        <el-table-column
            prop="medprice"
            label="单价（元）"
            width="150">
        </el-table-column>
        <el-table-column
            label="操作"
            width="150">
          <template slot-scope="scope">
            <el-button type="text" @click="addMed(scope.row)">开药</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          small
          :current-page.sync="medCurrentPage"
          :page-size="pageSize"
          :total="medTotal"
          layout="prev, pager, next"
      @current-change="handleMedPage">
      </el-pagination>
    </el-dialog>
    <el-form :model="pageData" :rules="rules" ref="pageData" label-width="100px" class="demo-ruleForm">
      <el-descriptions class="margin-top" title="看诊信息" :column="3">
        <template slot="extra">
          <el-button type="primary" @click="checkQueue">挂号队列</el-button>
          <el-button type="primary" @click="medicine">开药</el-button>
        </template>
        <el-descriptions-item label="挂号单编号">{{this.pageData.regid}}</el-descriptions-item>
        <el-descriptions-item label="病人编号">{{this.pageData.pid}}</el-descriptions-item>
        <el-descriptions-item label="病人姓名">{{this.pageData.pname}}</el-descriptions-item>
        <el-descriptions-item label="性别">{{this.pageData.psex}}</el-descriptions-item>
        <el-descriptions-item label="手机号">
          {{this.pageData.pnumber}}
        </el-descriptions-item>
        <el-descriptions-item label="年龄">{{this.pageData.page}}</el-descriptions-item>
        <el-descriptions-item label="过敏史">{{this.pageData.pallergic}}</el-descriptions-item>
      </el-descriptions>
      <el-form-item label="诊断结果" prop="predesc">
        <el-input type="textarea"
                  :autosize="{minRows : 4}"
                  v-model="pageData.predesc"></el-input>
      </el-form-item>
      <el-form-item
          label="药品列表：">
        <el-table
            :data="pageData.meds"
            style="width: 100%">
          <el-table-column
              label="药名"
              width="180"
              prop="medname">
          </el-table-column>
          <el-table-column
              label="数量"
              width="180">
            <template slot-scope="scope">
              <el-button type="text" @click="editMedAmount(scope.$index,scope.row)">{{scope.row.medamount}}</el-button>
            </template>
          </el-table-column>
          <el-table-column
              label="价格"
              width="180"
              prop="medprice">
          </el-table-column>
          <el-table-column
              label="操作"
              width="180">
            <template slot-scope="scope">
              <el-button type="text" @click="deleteMed(scope.$index,scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form-item>
      <el-form-item label="总价：">
        <span style="color: #409EFF">{{totalPrice}}元</span>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('pageData')">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "DoctorDiagnose",
  data(){
    return{
      loading : true,
      medlist : [],
      pageData : {
        regid : '',
        pid : '',
        pname : '',
        psex : '',
        pnumber : '',
        page : '',
        pallergic: '',
        predesc : '',
        meds : [],
        //用map集合存储key：药名，value ： 出现在meds数组的下标
        medMap : new Map()
      },
      medVisible : false,
      dialogTableVisible : false,
      tableData: [],
      did : '',
      dname : '',
      patientInfo : {
        pid : '',
        pname : '',
        psex : '',
        page : '',
        pnumber : '',
        pallergic: ''
      },
      formInline:{
        search: ''
      },
      currentPage : 1,
      pageSize: 5,
      total: 0,
      disablePage: false,
      medCurrentPage : 1,
      medTotal : 0,
      rules : {
        predesc:[
          {required : true,message : '请填写诊断结果！',trigger : 'blur'}
        ]
      }
    }
  },
  methods:{
    editMedAmount(index,row){
      this.$prompt('请输入数量',row.medname,{
        confirmButtonText : '确定',
        cancelButtonText : '取消',
        inputPattern : /^\+?[1-9][0-9]*$/,
        inputPlaceholder : '输入数量',
        inputErrorMessage : '请输入大于0的整数！',
        inputValue : row.medamount
      }).then(({value})=>{
        this.pageData.meds[index].medamount = value
        this.$message.success('更改成功')
      })
    },
    deleteMed(index,row){
      this.pageData.meds.splice(index,1)
      this.pageData.medMap.delete(row.medname)
      this.$message.warning('删除'+row.medname)
    },
    addMed(row){
      this.$prompt('请输入数量','提示',{
        confirmButtonText : '确定',
        cancelButtonText : '取消',
        inputPattern : /^\+?[1-9][0-9]*$/,
        inputPlaceholder : '输入数量',
        inputErrorMessage : '请输入大于0的整数！'
      }).then(({value})=>{
        if(!this.pageData.medMap.has(row.medname)){
          this.pageData.meds.push({
            medid : row.medid,
            medname : row.medname,
            medamount : Number(value),
            medprice : row.medprice
          })
          this.pageData.medMap.set(row.medname,this.pageData.meds.length - 1)
        }else{
          let index = this.pageData.medMap.get(row.medname)
          this.pageData.meds[index].medamount += Number(value)
        }
        this.$message.success('添加：' + row.medname + '*' + value)
      })
    },
    handleMedPage(){
      const postData = this.$qs.stringify({
        currentPage : this.medCurrentPage,
        medname : this.formInline.search,
        size : this.pageSize
      })
      this.axios({
        url : '/doctor/queryMedPage',
        method : 'post',
        data : postData
      }).then(res=>{
        this.medlist = res.data
        this.loading = false
      })
    },
    getMedRowCount(){
      const dataPost = this.$qs.stringify({
        medname : this.formInline.search
      })
      this.axios({
        url : '/doctor/getMedRowCount',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.medTotal = res.data
        this.loading = false
      }).then(err=>{
        console.log(err)
      })
    },
    //提交处方单（有挂号单进入才能通过）
    //完成处方后，发送到后台生成处方单号，生成当前日期，添加到处方信息中，prestate为1
    //prestate状态：1是未缴费，2是已缴费 在药房取药时要查看prestate为2才予以发放
    //药品要用List接收，在pharmacy药房数据库里用foreach遍历添加
    //提交处方信息，在药房表中添加处方id并且有对应的药品信息
    submitForm(formName) {
      if(this.pageData.regid === ''){
        this.$alert('请先选择一位病人就诊！')
      }
      this.$confirm('确定完成处方了吗？',{
        confirmButtonText : '确定',
        cancelButtonText : '取消'
      }).then(()=>{
        this.$refs[formName].validate(async (valid) => {
          if (this.pageData.regid === '') {
            this.$alert('请先从挂号队列中选择一名病人！')
            return false
          }
          if (valid) {
            console.log(this.pageData.meds)
            const postData = this.$qs.stringify({
              regid: this.pageData.regid,
              predesc: this.pageData.predesc,
              pid : this.pageData.pid,
              did : this.did,
              prestate: 1,
              predate: this.dateFormat(new Date()),
              totalPrice: this.totalPrice,
              meds: JSON.stringify(this.pageData.meds)
            })
            await this.axios({
              url: '/pre/addPre',
              method: 'post',
              data: postData
            }).then((res) => {
              if (res.data === this.pageData.meds.length + 2) {
                this.$message.success('处方添加成功！')
                this.total = this.total - 1
              }
            })
          } else {
            this.$alert('请填写所有必填项！')
            return false;
          }
          this.loading = false
          this.pageData = {
            regid: '',
            regdesc: '',
            pid: '',
            pname: '',
            psex: '',
            pnumber: '',
            page: '',
            pallergic: '',
            predesc: '',
            meds: [],
            medMap: new Map()
          }
        });
      })
    },
    //开药操作
    medicine(){
      this.medVisible = true
      this.handleMedPage()
      this.getMedRowCount()
    },
    handleClick(row){
      const postData = this.$qs.stringify({
        regstate : 2,
        regid : row.regid
      })
      this.axios({
        url : '/doctor/changeRegstate',
        method : 'post',
        data : postData
      }).then(async res=>{
        if(res.data === 1){
          await this.getPatient(row.pid)
          Object.assign(this.pageData,this.patientInfo)
          this.pageData.regid = row.regid
          this.$message.success('成功叫号：' + this.pageData.pname)
          this.dialogTableVisible = false
          this.loading = false
        }
      })
    },
    checkQueue(){
      this.dialogTableVisible = true
      //请求该医生的挂号队列
      this.getRegList(this.did)
      this.getRegRowCount()
    },
    getRegList(did){
      //传入当前医生的id以及当前页数获取pageSize条信息
      const dataPost = this.$qs.stringify({
        did,
        currentPage : this.currentPage
      })
      this.axios({
        url : '/doctor/queryRegList',
        method : 'post',
        data : dataPost
      }).then(async res=>{
        this.tableData = res.data
        this.loading = false
      }).catch(err=>{
        console.log(err)
      })
    },
    async getPatient(pid){
      await this.axios({
        method : 'post',
        url : '/patient/queryByPid',
        data : this.$qs.stringify({pid})
      }).then(res=>{
        this.patientInfo = res.data
        this.loading = false
      }).catch(err=>{
        console.log(err)
      })
    },
    getRegRowCount(){
      const dataPost = this.$qs.stringify({
        did : this.did
      })
      this.axios({
        url : '/doctor/getRegRowCount',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.total = res.data
        this.loading = false
      })
    },
    handleSearch(){
      this.handleMedPage()
      this.getMedRowCount()
    },
    dateFormat(time){
      const date = new Date(time)
      const year = date.getFullYear()
      const month = date.getMonth() + 1 < 10 ? '0' + date.getMonth() + 1 : date.getMonth() + 1
      const day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
      const hour = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
      const minute = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
      return year + '-' + month + '-' + day + ' ' + hour + ':' + minute
    }
  },
  computed:{
    totalPrice(){
      let sum = 0
      this.pageData.meds.forEach((med)=>{
        sum += med.medamount * med.medprice
      })
      return sum
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
      }).then(res => {
        sessionStorage.setItem('doctorData', JSON.stringify(res.data))
        this.did = res.data.did
        this.dname = res.data.dname
        this.loading = false
      }).catch(err=>{
        console.log(err)
      })
    }

    const doctorData = JSON.parse(sessionStorage.getItem('doctorData'))

    if(doctorData.did === undefined){
      await this.$alert('请先创建医生信息！')
      await this.$router.push('/doctor')
    }else{
      this.did = doctorData.did
      this.dname = doctorData.dname
      this.total = this.tableData.length
      //获取到该医生的处方队列

    }
  }
}
</script>

<style scoped>

</style>