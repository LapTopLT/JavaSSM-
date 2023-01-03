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
          <el-table :data="medList">
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
        label="总价"
        width="140">
    </el-table-column>
    <el-table-column
        label="缴费状态"
        width="100">
      <template slot-scope="scope">
        <el-tag type="info" v-if="scope.row.prestate === '3'" disable-transitions>
          已完成
        </el-tag>
        <el-tag type="success" v-if="scope.row.prestate === '2'" disable-transitions>
          已缴费
        </el-tag>
        <el-button type="danger" v-else-if="scope.row.prestate === '1'"
              @click="pay(scope.row)">待缴费
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      background
      small
      layout="prev, pager, next"
      :total="total"
      :page-size="pageSize"
      :current-page="currentPage">
  </el-pagination>
</div>
</template>

<script>
export default {
  name: "PayBill",
  data(){
    return{
      loading : true,
      timeout : 1000,
      tableData:[],
      medList:[],
      pid : '',
      currentPage : 1,
      total : 0,
      pageSize : 5
    }
  },
  methods:{
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
        this.loading = false
      })
    },
     async pay(row){
      //缴费（简易版）
      this.$confirm('确定缴费吗?金额：'+row.totalPrice+'元','缴费',{
        confirmButtonText : '确定',
        cancelButtonText : '取消'
      }).then(async()=>{
        const postData = this.$qs.stringify({
          preid : row.preid,
          prestate : 2
        })
        this.loading = true
        setTimeout(await this.axios({
          url : '/patient/pay',
          method : 'post',
          data : postData
        }).then(res=>{
          if(res.data === 1){
            this.$message.success('缴费成功!')
            row.prestate = '2'
          }
          this.loading = false
        }),this.timeout)

      })
    },
    async getPreListById(){
      const dataPost = this.$qs.stringify({
        pid : this.pid
      })
      this.loading = true
      setTimeout(await this.axios({
        url : '/patient/getPreListByPid',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.tableData = res.data
        this.loading = false
      }),this.timeout)
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
    }
  },
  async created() {
    if(sessionStorage.getItem('patientData') === null){
      await this.axios({
        method : 'post',
        url : '/patient/query',
        data : this.$qs.stringify({uid: JSON.parse(sessionStorage.getItem('user')).uid})
      }).then(async res=>{
        await sessionStorage.setItem('patientData',JSON.stringify(res.data))
        this.loading = false
      })
    }
    const patientData = JSON.parse(sessionStorage.getItem('patientData'))
    if(patientData.pid === undefined){
      await this.$alert('请先创建病人信息！')
      await this.$router.push('/patient')
    }else{
      this.pid = patientData.pid
      await this.getPreListById()
      this.total = this.tableData.length
    }
  }
}
</script>

<style scoped>

</style>