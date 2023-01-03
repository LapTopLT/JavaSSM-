<template>
<div>
  <el-dialog title="已缴费待开药队列" :visible.sync="dialogTableVisible"
             v-loading="loading"
             :width="width"
             append-to-body
              @close="handleChange">
    <el-dialog
        :width="'60%'"
        title="药品"
        :visible.sync="innerVisible"
        append-to-body>
      <el-table
          v-loading="loading"
          :data="medList"
          style="width: 100%"
          :row-class-name="tableRowClassName">
        <el-table-column
              prop="preid"
              label="处方id"
              width="140">
        </el-table-column>
        <el-table-column
            prop="medid"
            label="药品编号"
            width="140">
        </el-table-column>
        <el-table-column
            prop="medname"
            label="药名"
            width="140">
        </el-table-column>
        <el-table-column
            width="140"
            label="所需数量">
            <template slot-scope="scope">
              <span :style="amountStyle">
              {{scope.row.amount}}
            </span>
            </template>
        </el-table-column>
        <el-table-column
            prop="medamount"
            width="140"
            label="库存">
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary"
                   :disabled="disableOutButton"
                   @click="handleMedOut">出库</el-button>
      </div>

    </el-dialog>
    <el-row :gutter="24">
      <el-col :span="12">
        <el-input placeholder="根据处方id查询"
                  v-model="search.searchPreId"
                  @input="handlePrescribe"></el-input>
      </el-col>
      <el-col :span="12">
        <el-input placeholder="根据病人姓名查询"
                  v-model="search.searchPrePname"
                    @input="handlePrescribe"></el-input>
      </el-col>
    </el-row>
<!--    如果没缴费或者已经完成，那么就不会被查询到-->
    <el-table :data="gridData" v-loading="loading">
      <el-table-column property="preid" label="处方id" width="150"></el-table-column>
      <el-table-column property="dname" label="医生姓名" width="150"></el-table-column>
      <el-table-column property="pname" label="病人姓名" width="150"></el-table-column>
      <el-table-column property="predate" label="开具日期" width="150" :formatter="formatter"></el-table-column>
      <el-table-column property="totalPrice" label="总价（元）" width="150"></el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button type="text" @click="handleDeliver(scope.row.preid,scope.row.$index)">查看药品</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        small
        layout="prev, pager, next"
        :total="preList.listTotal"
        :current-page.sync="preList.listCurrentPage"
        :page-size="preList.listPageSize"
        @current-change="handlePrescribe">
    </el-pagination>
  </el-dialog>
  <el-row :gutter="24">
    <el-col :span="8">
      <el-input
      placeholder="输入查询的药名"
      v-model="search.searchMedName"
      @input="handleChange"
      ></el-input>
    </el-col>
    <el-col :span="8">
      <el-input
          v-model="search.searchMedId"
          placeholder="输入查询的药品编号"
      @input="handleChange"></el-input>
    </el-col>
    <el-col :offset="4" :span="4">
      <el-button type="primary"
                @click="handlePrescribe">处方队列</el-button>
    </el-col>
  </el-row>
  <el-table
      v-loading="loading"
      :data="tableData"
      height="300"
      border
      style="width: 100%">
    <el-table-column
        prop="medid"
        label="药品编号"
        width="140">
    </el-table-column>
    <el-table-column
        prop="medname"
        label="药名"
        width="140">
    </el-table-column>
    <el-table-column
        prop="medamount"
        label="仓库数量"
        width="120">
    </el-table-column>
    <el-table-column
        prop="medprice"
        label="单价">
    </el-table-column>
    <el-table-column>
      <template slot-scope="scope">
        <el-button type="danger" @click="supply(scope.row)">申请进货</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      background
      small
      layout="prev, pager, next"
      :total="total"
      :current-page.sync="currentPage"
      :page-size="pageSize"
      @current-change="handleChange">
  </el-pagination>
</div>
</template>

<style scoped>
.customWidth{
  width: 80%;
}
.el-table .warning-row {
  background: #F56C6C;
}

.el-table .success-row {
  background: #67C23A;
}
</style>

<script>
export default {
  name: "MedicineManagement",
  data(){
    return{
      loading : true,
      preList : {
        listCurrentPage : 1,
        listTotal : 0,
        listPageSize : 5
      },
      currentPreIndex : 0,
      currentPreid : '',
      amountStyle : '',
      disableOutButton : false,
      gridData : [],
      dialogTableVisible : false,
      innerVisible : false,
      search:{
        searchMedName : '',
        searchMedId : '',
        searchPreId : '',
        searchPrePname : ''
      },
      width : '80%',
      tableData:[],
      total : 0,
      currentPage : 1,
      pageSize : 5,
      medList : [],
      supplyAmount : 0
    }
  },
  methods:{
    //补充货源
    supply(row){
      this.$prompt('请输入进货数量', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^\d+$/,
        inputErrorMessage: '只能输入大于0的正整数'
      }).then(({ value }) => {
        this.supplyAmount = value
        const data = this.$qs.stringify({
          medid : row.medid,
          medname : row.medname,
          medamount : Number(row.medamount) + Number(this.supplyAmount),
          medprice : row.medprice
        })

        this.axios({
          url : '/admin/updateMed',
          method : 'post',
          data
        }).then(res=>{
          if(res.data === 1){
            this.$message.success('成功补充'+row.medname+this.supplyAmount+'份')
            this.handleChange()
          }else{
            this.$message.error('补充失败，请重试！')
          }
        })
      }).catch(() => {
        this.supplyAmount = 0
      });


    },
    //药物真正出库的操作，完成出库后应该把prestate改为3，无法再查询到。
    //修改响应数据库里的药物数量，一个update搞定
    handleMedOut(){
      const dataPost = this.$qs.stringify({
        preid : this.currentPreid,
        prestate : 3
      })
          this.axios({
        url : '/admin/medOut',
        method : 'post',
        data : dataPost
      }).then(res=>{
        if(res.data === this.medList.length + 1){
          this.$message.success('药品出库成功')
          this.gridData.splice(this.currentPreIndex,1)
          this.innerVisible = false
          this.loading = false
        }else{
          this.$alert("后台出现问题，请重试！")
        }
      })
    },
    tableRowClassName({row, rowIndex}) {
      if(rowIndex === 0){
        if (row.amount > row.medamount) {
          this.$notify.error({
            title: '库存不足！',
            message: '有一种或多种的药库存不足！无法出库！',
            duration : 0
          });
          this.amountStyle = 'color: #F56C6C;'
          this.disableOutButton = true
          return 'warning-row';
        }
      }
      return '';
    },
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
    handleDeliver(preid,index){
      this.currentPreIndex = index
      this.currentPreid = preid
      this.innerVisible = true
      this.disableOutButton = false
      this.amountStyle = ''
      const dataPost = this.$qs.stringify({
        preid
      })
      this.axios({
        url : '/admin/getDeliverMedList',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.medList = res.data
        this.loading = false
      })
    },
    async handlePrescribe(){
      //prestate ： 1 未缴费，2已缴费，3已完成
      this.dialogTableVisible = true
      //获取已缴费的队列，不显示已完成的和未缴费的，
      const dataPost = this.$qs.stringify({
        preid : this.search.searchPreId,
        pname : this.search.searchPrePname,
        currentPage : this.preList.listCurrentPage
      })
      await this.axios({
        url : '/admin/pharmacyQueryPre',
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.gridData = res.data
        this.loading = false
      })
    },
    handleChange(){
      this.getMedicine()
      this.getRowCount()
    },
    async getMedicine(){
      const dataPost = this.$qs.stringify({
        currentPage : this.currentPage,
        medname : this.search.searchMedName,
        medid : this.search.searchMedId
      })
      await this.axios({
        url : "/admin/getMeds",
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.tableData = res.data
        this.loading = false
      })
    },
    async getRowCount(){
      const dataPost  = this.$qs.stringify({
        medname : this.search.searchMedName,
        medid : this.search.searchMedId
      })
      await this.axios({
        url : "/admin/getMedRowCount",
        method : 'post',
        data : dataPost
      }).then(res=>{
        this.total = res.data
        this.loading = false
      })
    }
  },
  async created() {
    await this.getMedicine()
    await this.getRowCount()
  }
}
</script>

