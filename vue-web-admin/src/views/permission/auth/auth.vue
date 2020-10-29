<template>
  <div class="app-container">
    <el-container>
      <el-header>
        <div class="tip">
          <p>权限管理</p>
        </div>
      </el-header>
      <el-main>
        <el-form :inline="true" :model="formInline" class="demo-form-inline" size="small">
          <el-form-item>
            <el-input v-model="formInline.authName" placeholder="登录名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-date-picker
              v-model="formInline.timeRange"
              type="datetimerange"
              :picker-options="formInline.pickerOptions"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
              value-format="yyyy-MM-dd HH:mm:ss"
            ></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="search">查询</el-button>
          </el-form-item>
        </el-form>
        <el-table :data="tableData" border stripe style="width: 100%; border-radius: 4px" >
          <el-table-column prop="authId" label="主键ID" width="180"></el-table-column>
          <el-table-column prop="authName" label="权限名" width="180"></el-table-column>
          <el-table-column prop="authValue" label="权限值"></el-table-column>
          <el-table-column prop="state" label="状态">
            <template slot-scope="scope">
              {{computedStatusType(scope.row.status)}}
            </template>
          </el-table-column>
          <el-table-column prop="appId" label="系统">
            <template slot-scope="scope">
              {{computedRoleType(scope.row.appId)}}
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间"></el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button @click.native="showAddAuth()" type="text" size="small" v-permission="['107']">新增</el-button>
              <el-button type="text" size="small" @click.native="showUpdateAuth(scope.row)" v-permission="['108']">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          :current-page="paginationData.currentPage"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="paginationData.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="paginationData.total"
          @current-change="changePage"
          @size-change="changePageSize"
        ></el-pagination>
      </el-main>
    </el-container>
    <!--    新增   -->
    <el-dialog title="新增用户" :visible.sync="dialogAdd.dialogAddFormVisible">
      <el-form :model="dialogAdd.form" label-position="left" label-width="80px" size="mini">
        <el-form-item label="权限名">
          <el-input v-model="dialogAdd.form.authName"></el-input>
        </el-form-item>
        <el-form-item label="权限值">
          <el-input v-model="dialogAdd.form.authValue"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="dialogAdd.form.state" placeholder="请选择">
            <el-option
              v-for="item in stateList"
              :key="item.value"
              :label="item.lable"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="系统">
          <el-select v-model="dialogAdd.form.appId" placeholder="请选择">
            <el-option
              v-for="item in tableDataApp"
              :key="item.appId"
              :label="item.appDesc"
              :value="item.appId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogAdd.dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAuth()">确 定</el-button>
      </div>
    </el-dialog>
    <!--    修改   -->
    <el-dialog title="修改用户" :visible.sync="dialogUpdate.dialogFormVisible">
      <el-form :model="dialogUpdate.form" label-position="left" label-width="80px" size="mini">
        <el-form-item label="主键ID">
          <el-input v-model="dialogUpdate.form.authId" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="权限名">
          <el-input v-model="dialogUpdate.form.authName"></el-input>
        </el-form-item>
        <el-form-item label="权限值">
          <el-input v-model="dialogUpdate.form.authValue"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="dialogUpdate.form.state" placeholder="请选择">
            <el-option
              v-for="item in stateList"
              :key="item.value"
              :label="item.lable"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="系统">
          <el-select v-model="dialogUpdate.form.appId" placeholder="请选择">
            <el-option
              v-for="item in tableDataApp"
              :key="item.appId"
              :label="item.appDesc"
              :value="item.appId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogUpdate.dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateAuth">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getAllAuths, updateAuth, addAuth, getAllApp } from '@/api/auth'
import checkPermission from '@/utils/permission'
import permission from '@/directive/permission/index.js' // 权限判断指令
export default {
  directives: { permission },
  data() {
    return {
      formInline: {
        authName: '',
        timeRange: '',
        pickerOptions: {
          shortcuts: [
            {
              text: '最近一周',
              onClick(picker) {
                const end = new Date()
                const start = new Date()
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
                picker.$emit('pick', [start, end])
              }
            },
            {
              text: '最近一个月',
              onClick(picker) {
                const end = new Date()
                const start = new Date()
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
                picker.$emit('pick', [start, end])
              }
            },
            {
              text: '最近三个月',
              onClick(picker) {
                const end = new Date()
                const start = new Date()
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
                picker.$emit('pick', [start, end])
              }
            }
          ]
        }
      },
      tableData: [],
      tableDataApp: [],
      paginationData: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      },
      dialogUpdate: {
        form: {
          authId: '',
          authName: '',
          authValue: '',
          state: '',
          appId: ''
        },
        dialogFormVisible: false
      },
      dialogAdd: {
        form: {
          authName: '',
          authValue: '',
          state: '',
          appId: ''
        },
        dialogAddFormVisible: false
      },
      stateList: [
        {
          key: 1,
          lable: '可用',
          value: 1
        },
        {
          key: 0,
          lable: '不可用',
          value: 0
        }
      ]
    }
  },
  mounted() {
    this.getAllAuths()
    getAllApp().then(response => {
      if (response.subCode === 20000) {
        this.tableDataApp = response.data
      }
    })
  },
  methods: {
    checkPermission,
    getAllAuths() {
      const data = {
        pageNum: this.paginationData.currentPage,
        pageSize: this.paginationData.pageSize,
        authName: this.formInline.authName.trim(),
        startTime: this.formInline.timeRange[0],
        endTime: this.formInline.timeRange[1]
      }
      getAllAuths(data).then(response => {
        if (response.subCode === 20000) {
          this.tableData = response.data.list
          console.info(response)
          this.paginationData.total = response.data.total
          this.paginationData.currentPage = response.data.pageNum
        }
      })
    },
    search() {
      this.getAllAuths()
    },
    changePage(val) {
      this.paginationData.currentPage = val
      this.getAllAuths()
    },
    changePageSize(val) {
      this.paginationData.pageSize = val
      this.getAllAuths()
    },
    addAuth() {
      const data = {
        authName: this.dialogAdd.form.authName.trim(),
        authValue: this.dialogAdd.form.authValue.trim(),
        state: this.dialogAdd.form.state,
        appId: this.dialogAdd.form.appId
      }
      addAuth(data).then(response => {
        if (response.subCode === 20000) {
          this.$message.success('新增成功')
          this.dialogAdd.dialogAddFormVisible = false
          this.getAllAuths()
          this.dialogAdd.form.authName = ''
          this.dialogAdd.form.authValue = ''
          this.dialogAdd.form.state = ''
          this.dialogAdd.form.appId = ''
        } else {
          this.$message('新增失败!')
        }
      })
    },
    updateAuth() {
      const data = {
        authId: this.dialogUpdate.form.authId,
        authName: this.dialogUpdate.form.authName.trim(),
        authValue: this.dialogUpdate.form.authValue.trim(),
        state: this.dialogUpdate.form.state,
        appId: this.dialogUpdate.form.appId
      }
      updateAuth(data).then(response => {
        if (response.subCode === 20000) {
          this.$message.success('更新成功')
          this.getAllAuths()
          this.dialogUpdate.dialogFormVisible = false
        } else {
          this.$message('更新失败!')
        }
      })
    },
    showUpdateAuth(data) {
      this.dialogUpdate.form.authId = data.authId
      this.dialogUpdate.form.authName = data.authName
      this.dialogUpdate.form.authValue = data.authValue
      this.dialogUpdate.form.state = data.state
      this.dialogUpdate.form.appId = data.appId
      this.dialogUpdate.dialogFormVisible = true
    },
    computedStatusType(status) {
      if (status === 0) {
        return '不可用'
      }
      if (status === 1) {
        return '可用'
      }
    },
    computedRoleType(appId) {
      for (const role in this.tableDataApp) {
        if (this.tableDataApp[role].appId === appId) {
          return this.tableDataApp[role].appDesc
        }
      }
    },
    showAddAuth() {
      this.dialogAdd.dialogAddFormVisible = true
    }
  }
}
</script>

<style lang="scss" scoped>
  .app-container {
    .roles-table {
      margin-top: 30px;
    }
    .permission-tree {
      margin-bottom: 30px;
    }
  }
  .el-table .warning-row {
    background: oldlace;
  }
  .el-table .success-row {
    background: #f0f9eb;
  }
</style>
