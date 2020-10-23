<template>
  <div class="app-container">
    <el-container>
      <el-header>
        <div class="tip">
          <p>角色列表</p>
        </div>
      </el-header>
      <el-main>
        <el-form :inline="true" :model="formInline" class="demo-form-inline" size="small">
          <el-form-item>
            <el-input v-model="formInline.roleName" placeholder="角色名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input v-model="formInline.roleValue" placeholder="角色值"></el-input>
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
            <el-button type="primary" @click="roleSearch">查询</el-button>
            <el-button type="primary" @click="addRole">添加</el-button>
          </el-form-item>
        </el-form>
        <el-table :data="tableData" border stripe style="width: 100%; border-radius: 4px" :row-class-name="tableRowClassName">
          <el-table-column prop="roleId" label="主键ID" width="180"></el-table-column>
          <el-table-column prop="roleName" label="角色名" width="180"></el-table-column>
          <el-table-column prop="roleValue" label="角色值"></el-table-column>
          <el-table-column prop="state" label="状态"></el-table-column>
          <el-table-column prop="createTime" label="创建时间"></el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button @click.native="showRole(scope.row)" type="text" size="small">查看</el-button>
              <el-button type="text" size="small" v-if="scope.row.roleValue!='admin'" @click.native="showUpdateRole(scope.row)" >编辑</el-button>
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
    <!--    修改   -->
    <el-dialog title="修改用户角色" :visible.sync="dialogUpdate.dialogFormVisible">
      <el-form :model="dialogUpdate.form" label-position="left" label-width="80px" size="mini">
        <el-form-item label="主键ID">
          <el-input v-model="dialogUpdate.form.roleId" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="角色名称">
          <el-input v-model="dialogUpdate.form.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色值">
          <el-input v-model="dialogUpdate.form.roleValue"></el-input>
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogUpdate.dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateRole">确 定</el-button>
      </div>
    </el-dialog>
    <!--    查看   -->
    <el-dialog title="查看用户角色" :visible.sync="dialogUpdate.dialogRoleShowVisible">
      <el-form :model="dialogUpdate.form" label-position="left" label-width="80px" size="mini">
        <el-form-item label="主键ID">
          <el-input v-model="dialogUpdate.form.roleId" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="角色名称">
          <el-input v-model="dialogUpdate.form.roleName" disabled></el-input>
        </el-form-item>
        <el-form-item label="角色值">
          <el-input v-model="dialogUpdate.form.roleValue" disabled></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="dialogUpdate.form.state" disabled placeholder="请选择">
            <el-option
              v-for="item in stateList"
              :key="item.value"
              :label="item.lable"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getAllRole, updateRole } from '@/api/role'

export default {
  data() {
    return {
      formInline: {
        roleName: '',
        roleValue: '',
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
      paginationData: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      },
      dialogUpdate: {
        form: {
          roleId: '',
          roleName: '',
          roleValue: '',
          state: ''
        },
        dialogFormVisible: false,
        dialogRoleShowVisible: false
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
    this.getAllRoles()
  },
  methods: {
    getAllRoles() {
      const data = {
        pageNum: this.paginationData.currentPage,
        pageSize: this.paginationData.pageSize,
        roleName: this.formInline.roleName.trim(),
        roleValue: this.formInline.roleValue.trim(),
        startTime: this.formInline.timeRange[0],
        endTime: this.formInline.timeRange[1]
      }
      console.info(data.startTime)
      console.info(data.endTime)
      getAllRole(data).then(response => {
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
    roleSearch() {
      this.getAllRoles()
    },
    changePage(val) {
      this.paginationData.currentPage = val
      this.getAllRoles()
    },
    changePageSize(val) {
      this.paginationData.pageSize = val
      this.getAllRoles()
    },
    formatDate(value) {
      this.value1 = new Date(value.createdTime)
      return this.$moment(this.value1).format('yyyy-MM-dd HH:mm:ss')
    },
    addRole() {
      this.$router.push({ path: '/role/addRole' })
    },
    updateRole() {
      const data = {
        roleId: this.dialogUpdate.form.roleId,
        roleName: this.dialogUpdate.form.roleName.trim(),
        roleValue: this.dialogUpdate.form.roleValue.trim(),
        state: this.dialogUpdate.form.state
      }
      updateRole(data).then(response => {
        if (response.subCode === 20000) {
          this.$message.success('更新成功')
          this.getAllRoles()
          this.dialogUpdate.dialogFormVisible = false
        } else {
          this.$message('更新失败!')
        }
      })
    },
    showUpdateRole(data) {
      this.dialogUpdate.form.roleId = data.roleId
      this.dialogUpdate.form.roleName = data.roleName
      this.dialogUpdate.form.roleValue = data.roleValue
      this.dialogUpdate.form.state = data.state
      this.dialogUpdate.dialogFormVisible = true
    },
    showRole(data) {
      this.dialogUpdate.form.roleId = data.roleId
      this.dialogUpdate.form.roleName = data.roleName
      this.dialogUpdate.form.roleValue = data.roleValue
      this.dialogUpdate.form.state = data.state
      this.dialogUpdate.dialogRoleShowVisible = true
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
