<template>
<div>
  <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%">
    <el-table-column
        prop="preid"
        label="处方单id"
        width="120"
    >
    </el-table-column>
    <el-table-column
        prop="regid"
        label="挂号单id"
        width="120">
    </el-table-column>
    <el-table-column
        prop="predesc"
        label="处方描述">
    </el-table-column>
    <el-table-column
        label="病人">
        <template slot-scope="scope">
          <el-popover
              placement="bottom"
              trigger="click"
              :content="pname">
          <el-button slot="reference" type="text" @click="getPatientName(scope.row.pid)">{{scope.row.pid}}</el-button>
          </el-popover>
        </template>
    </el-table-column>
    <el-table-column
        prop="predate"
        sortable
        :formatter="formatter"
        label="开具日期"
        column-key="predate"
        width="160">
    </el-table-column>
    <el-table-column
        prop="pharmacy"
        label="药物"
        width="120">
      <template slot-scope="scope">
        <el-popover
            placement="bottom"
            width="400"
            trigger="click">
          <el-table :data="medList" v-loading="loading">
            <el-table-column width="150" property="medname" label="药名"></el-table-column>
            <el-table-column width="100" property="amount" label="数量"></el-table-column>
          </el-table>
          <div slot="reference">
            <el-button type="text" @click="checkMedlist(scope.row.preid)">查看</el-button>
          </div>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column
        prop="totalPrice"
        label="处方费用"
        width="140">
    </el-table-column>
  </el-table>
  <el-pagination
      background
      small
      layout="prev, pager, next"
      :total="total"
      :page-size="pageSize"
      :current-page="currentPage"
      @current-change="handlePageChange">
  </el-pagination>
</div>
</template>

<script>
export default {
  name: "PreHistory",
  data(){
    return{
      timeout : Math.random() * 1000,
      loading : true,
      tableData:[],
      medList:[],
      did : '',
      pname : '',
      dname : '',
      currentPage : 1,
      total : 0,
      pageSize : 5
    }
  },methods:{
     getPatientName(pid){
      const dataPost = this.$qs.stringify({
        pid
      })
       this.axios({
        method : 'post',
        url : '/patient/queryByPid',
        data : dataPost
      }).then((res)=>{
        this.pname = res.data.pname
         this.loading = false
      }).catch((err)=>{
        console.log(err)
      })

    },
    handlePageChange(){
       this.getPreListByDid()

    }
    ,
    async getPreListByDid() {
      const dataPost = this.$qs.stringify({
        did : this.did,
        currentPage : this.currentPage
      })
      console.log(dataPost)
      this.loading = true
      setTimeout(()=>{this.axios({
        url : '/doctor/getPreListByDid',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.tableData = res.data
        this.loading = false
      })},this.timeout)

    },
    getPreRowCountByDid(){
       const data = this.$qs.stringify({
         did : this.did
       })
      this.axios({
        url : '/doctor/getPreRowCountByDid',
        method : 'post',
        data
      }).then(res=>{
        this.total = res.data
        this.loading = false
      })
    }
  ,
    formatter(row,column){
      let data = row[column.property]
      let dtime = new Date(data)
      const year = dtime.getFullYear()
      let month = dtime.getMonth() + 1
      if (month < 10) {
        month = '0' + month
      }
      let day = dtime.getDate()
      if (day < 10) {
        day = '0' + day
      }
      let hour = dtime.getHours()
      if (hour < 10) {
        hour = '0' + hour
      }
      let minute = dtime.getMinutes()
      if (minute < 10) {
        minute = '0' + minute
      }

      return year+ '-' + month+ '-' + day + ' ' + hour + ':' + minute
    },
    async checkMedlist(preid){
      const dataPost = this.$qs.stringify({
        preid
      })
      await this.axios({
        url : '/patient/checkMedlist',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.medList = res.data
        console.log(this.medList)
      })
    }
  },
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
        this.dname = doctorData.dname
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
      //获取到该医生的处方队列
      await this.getPreListByDid(this.did)
    }
  }
}
</script>

<style scoped>

</style>