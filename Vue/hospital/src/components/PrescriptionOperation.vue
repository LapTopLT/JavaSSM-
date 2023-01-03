<template>
  <div>
    <el-dialog :visible.sync="dialogVisible"
               width="30%" append-to-body>
      <el-form :model="ruleForm" status-icon ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <!--            zhe li yao gai cheng chu fang -->
        <el-form-item label="挂单号">
          <el-input v-model="ruleForm.regid"  :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="处方编号">
          <el-input v-model="ruleForm.preid" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="患者编号">
          <el-input v-model="ruleForm.pid"> </el-input>
        </el-form-item>
        <el-form-item label="医生编号">
          <el-input v-model="ruleForm.did" ></el-input>
        </el-form-item>
        <el-form-item label="病症">
          <el-input v-model="ruleForm.predesc"></el-input>
        </el-form-item>
        <el-form-item label="日期">
          <el-input v-model="ruleForm.predate" ></el-input>
        </el-form-item>
        <el-form-item label="缴费">
          <el-input v-model="ruleForm.totalPrice" ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="updatePre('ruleForm')">修 改</el-button>
  </span>
    </el-dialog>
    <el-row>
      <el-col :span="8">
        <!-- v-model="search.searchId" @input="handleChange"-->
        <el-input @input="handleChange" v-model="search.searchId" placeholder="请输入处方id" borderless></el-input>
      </el-col>
    </el-row>
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          label="挂单号"
          width="100">
        <template slot-scope="scope">
          {{ scope.row.regid }}
        </template>
      </el-table-column>
      <el-table-column
          label="处方编号"
          width="100">
        <template slot-scope="scope">
          {{ scope.row.preid }}
        </template>
      </el-table-column>

      <el-table-column
          label="患者编号"
          width="100">
        <template slot-scope="scope">
          {{ scope.row.pid }}
        </template>
      </el-table-column>

      <el-table-column
          label="医生编号"
          width="100">
        <template slot-scope="scope">
          <div  class="name-wrapper">
           {{ scope.row.did }}
          </div>
        </template>
      </el-table-column>

      <el-table-column
          label="病症"
          width="100">
        <template slot-scope="scope">
          {{scope.row.predesc}}
        </template>
      </el-table-column>


      <el-table-column
          label="日期"
          width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ dateFormat(scope.row.predate) }}</span>
        </template>
      </el-table-column>

      <el-table-column
          label="共缴费"
          width="100">
        <template slot-scope="scope">
          <el-tag size="medium">{{ scope.row.totalPrice }}元</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              @click="handleEdit(scope.row)">编辑
          </el-button>
          <el-popover
              placement="top"
              width="160"
              v-model="scope.row.visible">
            <p>确定删除该处方数据吗？</p>
            <div style="text-align : right; margin: 0">
              <el-button size="mini" type="text" @click="scope.row.visible = false">取消</el-button>
              <el-button type="danger" size="mini" @click="deletePre(scope.$index,scope.row)">确定</el-button>
            </div>
            <el-button type="danger" slot="reference">删除</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :current-page.sync="currentPage"
        :page-size="pageSize">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "PrescriptionOperation",
  data(){
    return{
      ruleForm: {
        regid:'',
        preid:'',
        pid:'',
        did:'',
        predesc:'',
        prestate:'',
        predate:'',
        totalPrice:''
      },
      search : {
        searchId : '',
      },
      visible2 : false,
      dialogVisible:false,
      currentPage : 1,
      total : 0,
      pageSize : 5,
      input: '',
      tableData: [{
        regid:'10110',
        preid:'10110',
        pid:'10110',
        did:'101110',
        predate: '2016-05-02',
        predesc:'心律不齐',
        totalPrice:'1011'
      }]
    }

  },
  methods:{
    getAllPre(){
      const data = this.$qs.stringify({
        currentPage : this.currentPage,
        preid : this.search.searchId
      })

      this.axios({
        url : '/admin/getAllPre',
        method : 'post',
        data
      }).then(res=>{
        this.tableData = res.data.map(item=>({
          ...item,
          visible : false
        }))
      })
    },
    getPreRowCount(){
      const data = this.$qs.stringify({
        preid : this.search.searchId
      })

      this.axios({
        url : '/admin/getPreRowCount',
        method : 'post',
        data
      }).then(res=>{
        this.total = res.data
      })
    },
    updatePre(formName){
      this.$refs[formName].validate(async (valid)=>{
        if(valid){
          const data = this.$qs.stringify(this.ruleForm)

          await this.axios({
            url : '/admin/updatePre',
            method : 'post',
            data
          }).then(async res=>{
            if(res.data === 1){
              await this.handleChange()
              this.$message.success('更改成功！')
              this.dialogVisible = false
            }
          })
        }else{
          console.log('error submit!')
        }
      })
    },
    deletePre(index,row){
      const data = this.$qs.stringify({
        preid : row.preid
      })
        this.axios({
          url : '/admin/deletePre',
          method : 'post',
          data
        }).then(res=>{
          if(res.data === 1){
            this.$message.success('删除成功！')
            this.tableData.splice(index,1)
          }
        })
    },
    handleEdit(row) {
      this.dialogVisible = true
      this.ruleForm = {
        regid:row.regid,
        preid:row.preid,
        pid:row.pid,
        did:row.did,
        predesc:row.predesc,
        prestate:row.prestate,
        predate : this.dateFormat(row.predate),
        totalPrice: row.totalPrice
      }
    },
    handleDelete(index, row) {

      console.log(index, row);
    },
    handleChange(){
      this.getAllPre()
      this.getPreRowCount()
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
  mounted() {
    this.handleChange()
  }
}
</script>

<style scoped>

</style>
