<template>
  <div>
    <el-dialog title="编辑挂号单" :visible.sync="dialogFormVisible" append-to-body>
      <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="挂号单id" prop="regid">
          <el-input v-model="ruleForm.regid" disabled readonly></el-input>
        </el-form-item>
        <el-form-item label="病人id" prop="pid">
          <el-input v-model="ruleForm.pid"></el-input>
        </el-form-item>
        <el-form-item label="医生id" prop="did">
          <el-input v-model="ruleForm.did"></el-input>
        </el-form-item>
        <el-form-item label="挂号日期" prop="regtime">
          <el-input v-model="ruleForm.regtime"></el-input>
        </el-form-item>
        <el-form-item label="挂号状态" prop="regstate">
          <el-select v-model="ruleForm.regstate" placeholder="请选择权限">
            <el-option
                v-for="item in state"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-row :gutter="24">
      <el-col :span="8">
        <el-input v-model="registrationNumber" @input="search" placeholder="请输入挂号编号"></el-input>
      </el-col>
    </el-row>
    <el-table :data="registrationList" v-loading="loading">
      <el-table-column prop="regid" width="140" label="挂号编号"></el-table-column>
      <el-table-column prop="pid" label="患者编号"></el-table-column>
      <el-table-column prop="did" label="医生编号"></el-table-column>
      <el-table-column prop="regtime" label="挂号日期"></el-table-column>
      <el-table-column prop="regstate" label="状态"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row)">编辑</el-button>
          <el-button @click="remove(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @current-change="search"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        :total="total"
        layout="total, prev, pager, next, jumper"
    ></el-pagination>
  </div>
</template>

<script>

export default {
  name: "RegistrationOperation",
  data() {
    return {
      state :[{
        label : '待叫号',
        value : 1
      },{
        label : '已叫号',
        value : 2
      },{
        label : '已完成',
        value : 0
      }],
      dialogFormVisible : false,
      registrationNumber: '',
      registrationList: [],
      loading: false,
      currentPage: 1,
      pageSize: 5,
      total: 5,
      ruleForm: {
        regid : '',
        pid : '',
        did : '',
        regtime : '',
        regstate : ''
      }
    };
  },
  computed: {

  },
  methods: {
    async updateReg(){
      const data = this.$qs.stringify(this.ruleForm)

      this.axios({
        url : '/admin/updateReg',
        method : 'post',
        data
      }).then(res=>{
        if(res.data === 1){
          this.$message.success('更新成功！')
        }
      })
    },
  async submitForm(formName) {
    this.$refs[formName].validate(async (valid) => {
      if (valid) {
        await this.updateReg()
        this.search()
      } else {
        console.log('error submit!!');
        return false;
      }
    });
  },
    getRegRowCount() {
      const data = this.$qs.stringify({
        regid : this.registrationNumber
      })
      this.axios({
        url : '/admin/getRegRowCount',
        method : 'post',
        data
      }).then(res=>{
        this.total = res.data
      })
    },
    getRegList() {
      const data = this.$qs.stringify({
        regid : this.registrationNumber,
        currentPage : this.currentPage
      })
      this.axios({
        url : '/admin/getRegList',
        method : 'post',
        data
      }).then(res=>{
        this.registrationList = res.data
      })
    }
    ,
    search() {
      // 根据挂号编号查询挂号信息
      this.getRegList()
      this.getRegRowCount()
    },
    edit(row) {
      // 编辑挂号信息
      this.ruleForm = row
      this.dialogFormVisible = true
    },
    remove(row) {
// 删除挂号信息
      console.log(row)
    }
  },mounted() {
    this.getRegList()
    this.getRegRowCount()
  }
};
</script>

<style>

</style>

