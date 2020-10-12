import Vue from 'vue'
import Router from 'vue-router'
import Login  from '@/components/login/Login'
import Home from '@/components/home/Home'


Vue.use(Router)

export default new Router({
    mode: "history",
    routes: [
        {
            path: '/',
            name: 'Login',
            component: Login,
            hidden: true
        },
        {
            //路由地址
            path: '/home',
            //名称
            name: 'Home',
            //指向的vue
            component: Home,
            hidden: true,
            meta: {
                //requireAuth属性作用是表明该路由是否需要登录验证
                requireAuth: true
            }
        }
    ]
})