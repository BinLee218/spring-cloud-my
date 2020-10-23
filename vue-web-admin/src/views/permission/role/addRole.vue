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
<!--      <el-form-item label="Activity time">-->
<!--        <el-col :span="11">-->
<!--          <el-date-picker v-model="form.date1" type="date" placeholder="Pick a date" style="width: 100%;" />-->
<!--        </el-col>-->
<!--        <el-col :span="2" class="line">-</el-col>-->
<!--        <el-col :span="11">-->
<!--          <el-time-picker v-model="form.date2" type="fixed-time" placeholder="Pick a time" style="width: 100%;" />-->
<!--        </el-col>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="Instant delivery">-->
<!--        <el-switch v-model="form.delivery" />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="Activity type">-->
<!--        <el-checkbox-group v-model="form.type">-->
<!--          <el-checkbox label="Online activities" name="type" />-->
<!--          <el-checkbox label="Promotion activities" name="type" />-->
<!--          <el-checkbox label="Offline activities" name="type" />-->
<!--          <el-checkbox label="Simple brand exposure" name="type" />-->
<!--        </el-checkbox-group>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="Resources">-->
<!--        <el-radio-group v-model="form.resource">-->
<!--          <el-radio label="Sponsor" />-->
<!--          <el-radio label="Venue" />-->
<!--        </el-radio-group>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="Activity form">-->
<!--        <el-input v-model="form.desc" type="textarea" />-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Create</el-button>
<!--        <el-button @click="onCancel">Cancel</el-button>-->
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { addRole } from '@/api/role'

export default {
  data() {
    return {
      form: {
        roleName: '',
        roleValue: '',
        state: ''
      }
    }
  },
  methods: {
    onSubmit() {
      const data = {
        roleName: this.form.roleName.trim(),
        roleValue: this.form.roleValue.trim(),
        state: this.form.state.trim()
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
