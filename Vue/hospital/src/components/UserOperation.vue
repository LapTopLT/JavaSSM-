<template>
<div>
  <el-dialog title="编辑用户" :visible.sync="dialogFormVisible" append-to-body>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户ID" prop="uid">
        <el-input v-model="ruleForm.uid" disabled readonly></el-input>
      </el-form-item>
      <el-form-item label="用户名" prop="uname">
        <el-input v-model="ruleForm.uname"></el-input>
      </el-form-item>
      <el-form-item label="用户密码" prop="upassword">
        <el-input v-model="ruleForm.upassword"></el-input>
      </el-form-item>
      <el-form-item label="用户邮箱" prop="uemail">
        <el-input v-model="ruleForm.uemail"></el-input>
      </el-form-item>
      <el-form-item label="用户权限" prop="roleid">
        <el-select v-model="ruleForm.roleid" placeholder="请选择权限">
          <el-option
              v-for="item in roles"
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
      <el-input  placeholder="输入用户id查询"
                       v-model="search.uid"
                        @input="handleChange"
                       clearable>
    </el-input>
    </el-col>
    <el-col :span="8">
      <el-input placeholder="输入用户名查询"
                v-model="search.uname"
                @input="handleChange"
                clearable></el-input>
    </el-col>
  </el-row>
  <el-table
      border
      :data="tableData"
      style="width: 100%">
    <el-table-column
        prop="uid"
        label="用户id"
        width="180">
    </el-table-column>
    <el-table-column
        prop="uname"
        label="用户名"
        width="180">
    </el-table-column>
    <el-table-column
        label="用户密码"
        prop="upassword"
        width="140">
    </el-table-column>
    <el-table-column
        prop="uemail"
        label="邮箱"
        width="140">
    </el-table-column>
    <el-table-column
        prop="roleid"
        label="权限"
        width="140">
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :current-page.sync="currentPage"
      :page-size="pageSize"
      @current-change="handleChange">
  </el-pagination>
</div>
</template>

<script>
export default {
  name: "UserOperation",
  data() {
    return {
      roles:[{
        label : '病人',
        value : 1
      },{
        label : '医生',
        value : 2
      },{
        label : '管理员',
        value : 3
      },{
        label : '药房管理员',
        value : 4
      }],
      loading : true,
      currentPage : 1,
      total : 0,
      pageSize : 5,
      search :{
        uid : '',
        uname : '',
      },
      tableData: [],
      gridData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }],
      dialogTableVisible: false,
      dialogFormVisible: false,
      ruleForm: {
        uid : '',
        uname : '',
        upassword : '',
        uemail : '',
        roleid : 1
      },
      rules: {

      },
      formLabelWidth: '120px'
    };
    }
  ,
  methods: {
    getUser(){
      //传入uid，uname，currentpage查询user表
      const data = this.$qs.stringify({
        uid : this.search.uid,
        uname : this.search.uname,
        currentPage : this.currentPage
      })
      this.axios({
        url : '/admin/queryUser',
        method : 'post',
        data
      }).then(res=>{
        this.tableData = res.data
      })
    },
    getUserRowCount(){
      const data = this.$qs.stringify({
        uid : this.search.uid,
        uname : this.search.uname
      })
      this.axios({
        url : '/admin/getUserRowCount',
        method : 'post',
        data
      }).then(res=>{
        this.total = res.data
      })
    }
    ,
    async handleChange(){
      await this.getUser()
      await this.getUserRowCount()
    },
    handleEdit(index, row) {
      this.dialogFormVisible =true
      this.ruleForm = row
    },
    handleDelete(index, row) {
      const data = this.$qs.stringify({
        uid : row.uid
      })

      this.$confirm('确定要删除吗？删除后不可恢复','提示',{
        type : 'danger'
      }).then(async ()=>{
        await this.axios({
          url : '/admin/deleteUser',
          method : 'post',
          data
        }).then(res=>{
          if(res.data === 1){
            this.tableData.splice(index,1)
            this.$message.success('删除成功！')
          }
        })
      })
    },
    async updateUser(){
      const data = this.$qs.stringify(this.ruleForm)

      await this.axios({
        url : '/admin/updateUser',
        method : 'post',
        data
      }).then(res=>{
        if(res.data === 1){
          this.$message.success('更新成功！')
        }
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          await this.updateUser()
          this.handleChange()
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  },
  created() {
    this.handleChange()
  }
}
</script>

<style scoped>

</style>