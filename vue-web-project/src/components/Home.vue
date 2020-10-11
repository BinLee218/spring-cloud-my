<template>
    <div>
        <el-container class="home-container">
            <el-aside width="180px" class="home-aside">
                <div style="display: flex;justify-content: flex-start;width: 180px;text-align: left;">
                    <el-menu style="background: #ececec;width: 180px;" unique-opened router>
                        <template v-for="(item,index) in this.routes">
                            <el-submenu :key="index" :index="index+''">
                                <template slot="title">
                                    <i :class="item.iconCls" style="color: #20a0ff;width: 14px;"></i>
                                    <span>{{item.name}}</span>
                                </template>
                                <el-menu-item
                                        style="padding-left: 30px;padding-right:0px;margin-left: 0px;width: 100%;text-align: left"
                                        v-for="child in item.children"
                                        :index="child.path"
                                        :key="child.path">{{child.name}}
                                </el-menu-item>
                            </el-submenu>
                        </template>
                    </el-menu>
                </div>
            </el-aside>
            <el-container>
                <el-header class="home-header">
                    <span class="home_title">人事管理</span>
                    <div style="display: flex;align-items: center;margin-right: 7px">
                        <el-dropdown @command="handleCommand">
                        <span class="el-dropdown-link home_userinfo" style="display: flex;align-items: center">
                            {{user.userName}}
                            <i><img v-if="user.userface!=''" :src="user.face"
                                    style="width: 40px;height: 40px;margin-right: 5px;margin-left: 5px;border-radius: 40px"/></i>
                        </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item>个人中心</el-dropdown-item>
                                <el-dropdown-item>设置</el-dropdown-item>
                                <el-dropdown-item command="logout" divided>注销</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </el-header>
            </el-container>
            <el-main>
                <el-breadcrumb separator-class="el-icon-arrow-right">
                    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
                </el-breadcrumb>
                <keep-alive>
                    <router-view v-if="this.$route.meta.keepAlive"></router-view>
                </keep-alive>
                <router-view v-if="!this.$route.meta.keepAlive"></router-view>
            </el-main>
        </el-container>


<!--                <el-container  class="home-container" >-->
<!--                    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">-->
<!--                        <el-menu :default-openeds="['1', '3']">-->
<!--                            <el-submenu index="1">-->
<!--                                <template slot="title"><i class="el-icon-message"></i>导航一</template>-->
<!--                                <el-menu-item-group>-->
<!--                                    <template slot="title">分组一</template>-->
<!--                                    <el-menu-item index="1-1">选项1</el-menu-item>-->
<!--                                    <el-menu-item index="1-2">选项2</el-menu-item>-->
<!--                                </el-menu-item-group>-->
<!--                                <el-menu-item-group title="分组2">-->
<!--                                    <el-menu-item index="1-3">选项3</el-menu-item>-->
<!--                                </el-menu-item-group>-->
<!--                                <el-submenu index="1-4">-->
<!--                                    <template slot="title">选项4</template>-->
<!--                                    <el-menu-item index="1-4-1">选项4-1</el-menu-item>-->
<!--                                </el-submenu>-->
<!--                            </el-submenu>-->
<!--                            <el-submenu index="2">-->
<!--                                <template slot="title"><i class="el-icon-menu"></i>导航二</template>-->
<!--                                <el-menu-item-group>-->
<!--                                    <template slot="title">分组一</template>-->
<!--                                    <el-menu-item index="2-1">选项1</el-menu-item>-->
<!--                                    <el-menu-item index="2-2">选项2</el-menu-item>-->
<!--                                </el-menu-item-group>-->
<!--                                <el-menu-item-group title="分组2">-->
<!--                                    <el-menu-item index="2-3">选项3</el-menu-item>-->
<!--                                </el-menu-item-group>-->
<!--                                <el-submenu index="2-4">-->
<!--                                    <template slot="title">选项4</template>-->
<!--                                    <el-menu-item index="2-4-1">选项4-1</el-menu-item>-->
<!--                                </el-submenu>-->
<!--                            </el-submenu>-->
<!--                            <el-submenu index="3">-->
<!--                                <template slot="title"><i class="el-icon-setting"></i>导航三</template>-->
<!--                                <el-menu-item-group>-->
<!--                                    <template slot="title">分组一</template>-->
<!--                                    <el-menu-item index="3-1">选项1</el-menu-item>-->
<!--                                    <el-menu-item index="3-2">选项2</el-menu-item>-->
<!--                                </el-menu-item-group>-->
<!--                                <el-menu-item-group title="分组2">-->
<!--                                    <el-menu-item index="3-3">选项3</el-menu-item>-->
<!--                                </el-menu-item-group>-->
<!--                                <el-submenu index="3-4">-->
<!--                                    <template slot="title">选项4</template>-->
<!--                                    <el-menu-item index="3-4-1">选项4-1</el-menu-item>-->
<!--                                </el-submenu>-->
<!--                            </el-submenu>-->
<!--                        </el-menu>-->
<!--                    </el-aside>-->

<!--                    <el-container>-->
<!--                        <el-header style="text-align: right; font-size: 12px">-->
<!--                            <el-dropdown>-->
<!--                                <i class="el-icon-setting" style="margin-right: 15px"></i>-->
<!--                                <el-dropdown-menu slot="dropdown">-->
<!--                                    <el-dropdown-item>查看</el-dropdown-item>-->
<!--                                    <el-dropdown-item>新增</el-dropdown-item>-->
<!--                                    <el-dropdown-item>删除</el-dropdown-item>-->
<!--                                </el-dropdown-menu>-->
<!--                            </el-dropdown>-->
<!--                            <span>王小虎</span>-->
<!--                        </el-header>-->

<!--                        <el-main>-->
<!--                            <el-table :data="tableData">-->
<!--                                <el-table-column prop="date" label="日期" width="140">-->
<!--                                </el-table-column>-->
<!--                                <el-table-column prop="name" label="姓名" width="120">-->
<!--                                </el-table-column>-->
<!--                                <el-table-column prop="address" label="地址">-->
<!--                                </el-table-column>-->
<!--                            </el-table>-->
<!--                        </el-main>-->
<!--                    </el-container>-->
<!--                </el-container>-->
    </div>
</template>
<script>
    export default {
        methods: {
            handleCommand(cmd) {
                var _this = this;
                if (cmd == 'logout') {
                    this.$confirm('注销登录, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        _this.getRequest("/auth/logout");
                        _this.$store.commit('logout');
                        _this.$router.replace({path: '/'});
                        _this.$message("注销成功");
                    }).catch(() => {
                        _this.$message({
                            type: 'info',
                            message: '取消'
                        });
                    });
                }
            }
        },
        computed: {
            user() {
                return this.$store.state.user;
            },
            routes() {
                return this.$store.state.routes
            }
        }
    }
</script>
<style>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }

    .home-container {
        height: 100%;
        position: absolute;
        top: 0px;
        left: 0px;
        width: 100%;
    }

    .home-header {
        background-color: #20a0ff;
        color: #333;
        text-align: center;
        display: flex;
        align-items: center;
        justify-content: space-between;
        box-sizing: content-box;
        padding: 0px;
    }

    /*.home-aside {*/
    /*    background-color: #ECECEC;*/
    /*}*/
    .sidebar-container {
        -webkit-transition: width 0.28s;
        transition: width 0.28s;
        width: 180px !important;
        height: 100%;
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        z-index: 1001;
    }

    .scroll-container {
        position: relative;
        width: 100%;
        height: 100%;
        background-color: #304156;
    }

    .home-main {
        background-color: #fff;
        color: #000;
        text-align: center;
        margin: 0px;
        padding: 0px;;
    }

    .home_title {
        color: #fff;
        font-size: 22px;
        display: inline;
        margin-left: 8px;
    }

    .home_userinfo {
        color: #fff;
        cursor: pointer;
    }

    .home_userinfoContainer {
        display: inline;
        margin-right: 20px;
    }

    .el-submenu .el-menu-item {
        width: 180px;
        min-width: 175px;
    }
</style>