<template>
  <div class="app-container">
    <el-container>
      <el-header>
        <div class="tip">
          <p>添加角色</p>
        </div>
      </el-header>
    </el-container>
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="角色名称">
        <el-input v-model="form.roleName"/>
      </el-form-item>
      <el-form-item label="角色值">
        <el-input v-model="form.roleValue" />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="form.state" placeholder="请选择">
          <el-option label="可用" value="1"   />
          <el-option label="不可用" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="权限配置">
        <el-tree
          class="permission-tree"
          :data="el_treeData"
          show-checkbox
          default-expand-all
          node-key="id"
          ref="tree"
          highlight-current
          :props="defaultProps"
        >
        </el-tree>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Create</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { addRole } from '@/api/role'
import { AuthTreeWithoutRole } from '@/api/auth'

export default {
  data() {
    return {
      el_treeData: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      form: {
        roleName: '',
        roleValue: '',
        state: ''
      }
    }
  },
  mounted() {
    this.authTreeWithoutRole()
  },
  methods: {
    authTreeWithoutRole() {
      AuthTreeWithoutRole().then(response => {
        if (response.subCode === 20000) {
          this.el_treeData = response.data.auths
        }
      })
    },
    onSubmit() {
      const data = {
        roleName: this.form.roleName.trim(),
        roleValue: this.form.roleValue.trim(),
        state: this.form.state.trim(),
        treeValue: this.$refs.tree.getCheckedKeys()
      }
      addRole(data).then(response => {
        console.info(response)
        if (response.subCode === 20000) {
          this.$message.success('添加成功!')
          this.$router.push({ path: '/permission/role' })
        } else {
          this.$message('添加失败!')
        }
      }).catch(error => {
        console.error(error)
        this.$message.error('添加失败!')
      })
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
  .line{
    text-align: center;
  }
</style>
