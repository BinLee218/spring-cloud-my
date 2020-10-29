<template>
  <div class="app-container">
    <el-container>
      <el-header>
        <div class="tip">
          <p>用户管理</p>
        </div>
      </el-header>
      <el-main>
        <el-form :inline="true" :model="formInline" class="demo-form-inline" size="small">
          <el-form-item>
            <el-input v-model="formInline.userName" placeholder="登录名"></el-input>
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
            <el-button type="primary" @click="showAddUser" v-permission="['106']">添加</el-button>
          </el-form-item>
        </el-form>
        <el-table :data="tableData" border stripe style="width: 100%; border-radius: 4px" :row-class-name="tableRowClassName">
          <el-table-column prop="userId" label="主键ID" width="180"></el-table-column>
          <el-table-column prop="userName" label="登录名" width="180"></el-table-column>
          <el-table-column prop="realName" label="真实姓名"></el-table-column>
          <el-table-column prop="status" label="状态">
            <template slot-scope="scope">
              {{computedStatusType(scope.row.status)}}
            </template>
          </el-table-column>
          <el-table-column prop="roleId" label="角色">
            <template slot-scope="scope">
              {{computedRoleType(scope.row.roleId)}}
            </template>
          </el-table-column>
          <el-table-column prop="lastLoginTime" label="最后登录时间"></el-table-column>
          <el-table-column prop="createTime" label="创建时间"></el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button @click.native="showUser(scope.row)" type="text" size="small" >查看</el-button>
              <el-button type="text" size="small" @click.native="showUpdateUser(scope.row)" v-permission="['109']">编辑</el-button>
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
        <el-form-item label="登录名">
          <el-input v-model="dialogAdd.form.userName"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input v-model="dialogAdd.form.realName"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="dialogAdd.form.status" placeholder="请选择">
            <el-option
              v-for="item in stateList"
              :key="item.value"
              :label="item.lable"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="dialogAdd.form.roleValue" placeholder="请选择">
            <el-option
              v-for="item in tableDataRole"
              :key="item.roleId"
              :label="item.roleName"
              :value="item.roleId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogAdd.dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </div>
    </el-dialog>
    <!--    修改   -->
    <el-dialog title="修改用户" :visible.sync="dialogUpdate.dialogFormVisible">
      <el-form :model="dialogUpdate.form" label-position="left" label-width="80px" size="mini">
        <el-form-item label="主键ID">
          <el-input v-model="dialogUpdate.form.userId" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="登录名">
          <el-input v-model="dialogUpdate.form.userName"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input v-model="dialogUpdate.form.realName"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="dialogUpdate.form.status" placeholder="请选择">
            <el-option
              v-for="item in stateList"
              :key="item.value"
              :label="item.lable"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="dialogUpdate.form.roleId" placeholder="请选择">
            <el-option
              v-for="item in tableDataRole"
              :key="item.roleId"
              :label="item.roleName"
              :value="item.roleId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogUpdate.dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUser">确 定</el-button>
      </div>
    </el-dialog>
    <!--    查看   -->
    <el-dialog title="查看用户角色" :visible.sync="dialogUpdate.dialogShowVisible">
      <el-form :model="dialogUpdate.form" label-position="left" label-width="80px" size="mini">
        <el-form-item label="主键ID">
          <el-input v-model="dialogUpdate.form.userId" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="登录名">
          <el-input v-model="dialogUpdate.form.userName" disabled></el-input>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input v-model="dialogUpdate.form.realName" disabled></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="dialogUpdate.form.status" disabled placeholder="请选择">
            <el-option
              v-for="item in stateList"
              :key="item.value"
              :label="item.lable"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="dialogUpdate.form.roleId" disabled placeholder="请选择">
            <el-option
              v-for="item in tableDataRole"
              :key="item.roleId"
              :label="item.roleName"
              :value="item.roleId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getAllUsers, updateUser, addUser } from '@/api/user'
import { getAllNameValue } from '@/api/role'
import checkPermission from '@/utils/permission'
import permission from '@/directive/permission/index.js' // 权限判断指令
export default {
  directives: { permission },
  data() {
    return {
      formInline: {
        userName: '',
        realName: '',
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
      tableDataRole: [],
      paginationData: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      },
      dialogUpdate: {
        form: {
          userId: '',
          userName: '',
          realName: '',
          status: '',
          roleId: ''
        },
        dialogFormVisible: false,
        dialogShowVisible: false
      },
      dialogAdd: {
        form: {
          userName: '',
          realName: '',
          status: '',
          roleValue: ''
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
    this.getAllUsers()
    getAllNameValue().then(response => {
      if (response.subCode === 20000) {
        this.tableDataRole = response.data
      }
    })
  },
  methods: {
    checkPermission,
    getAllUsers() {
      const data = {
        pageNum: this.paginationData.currentPage,
        pageSize: this.paginationData.pageSize,
        userName: this.formInline.userName.trim(),
        startTime: this.formInline.timeRange[0],
        endTime: this.formInline.timeRange[1]
      }
      console.info(data.startTime)
      console.info(data.endTime)
      getAllUsers(data).then(response => {
        if (response.subCode === 20000) {
          this.tableData = response.data.list
          console.info(response)
          this.paginationData.total = response.data.total
          this.paginationData.currentPage = response.data.pageNum
          // this.paginationData.pageSize = response.data.pageSize
        }
      })
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return 'warning-row'
      } else if (rowIndex === 3) {
        return 'success-row'
      }
      return ''
    },
    search() {
      this.getAllUsers()
    },
    changePage(val) {
      this.paginationData.currentPage = val
      this.getAllUsers()
    },
    changePageSize(val) {
      this.paginationData.pageSize = val
      this.getAllUsers()
    },
    addUser() {
      const data = {
        userName: this.dialogAdd.form.userName.trim(),
        realName: this.dialogAdd.form.realName.trim(),
        status: this.dialogAdd.form.status,
        roleValue: this.dialogAdd.form.roleValue
      }
      addUser(data).then(response => {
        if (response.subCode === 20000) {
          this.$message.success('新增成功')
          this.dialogAdd.dialogAddFormVisible = false
          this.getAllUsers()
          // this.$router.push({ path: '/user/index' })
          this.dialogAdd.form.userName = ''
          this.dialogAdd.form.realName = ''
          this.dialogAdd.form.status = ''
          this.dialogAdd.form.roleValue = ''
        } else {
          this.$message('新增失败!')
        }
      })
    },
    updateUser() {
      const data = {
        userId: this.dialogUpdate.form.userId,
        userName: this.dialogUpdate.form.userName.trim(),
        realName: this.dialogUpdate.form.realName.trim(),
        status: this.dialogUpdate.form.status,
        roleId: this.dialogUpdate.form.roleId
      }
      updateUser(data).then(response => {
        if (response.subCode === 20000) {
          this.$message.success('更新成功')
          this.getAllUsers()
          this.dialogUpdate.dialogFormVisible = false
        } else {
          this.$message('更新失败!')
        }
      })
    },
    showUpdateUser(data) {
      this.dialogUpdate.form.userId = data.userId
      this.dialogUpdate.form.userName = data.userName
      this.dialogUpdate.form.realName = data.realName
      this.dialogUpdate.form.status = data.status
      this.dialogUpdate.form.roleId = data.roleId
      this.dialogUpdate.dialogFormVisible = true
    },
    showUser(data) {
      this.dialogUpdate.form.userId = data.userId
      this.dialogUpdate.form.userName = data.userName
      this.dialogUpdate.form.realName = data.realName
      this.dialogUpdate.form.status = data.status
      this.dialogUpdate.form.roleId = data.roleId
      this.dialogUpdate.dialogShowVisible = true
    },
    computedStatusType(status) {
      if (status === 0) {
        return '不可用'
      }
      if (status === 1) {
        return '可用'
      }
    },
    computedRoleType(roleId) {
      // console.info(roleId)
      // console.info(this.tableDataRole)
      for (const role in this.tableDataRole) {
        if (this.tableDataRole[role].roleId === roleId) {
          return this.tableDataRole[role].roleName
        }
      }
    },
    showAddUser() {
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
