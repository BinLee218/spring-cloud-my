import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index.js'
import {initMenu} from './utils/menu';
import {getRequest} from './utils/axios'
import {postRequest} from './utils/axios'
import {deleteRequest} from './utils/axios'
import {putRequest} from './utils/axios'
import ElementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.min.css'
import {isUndefined} from "element-ui/src/utils/types";

Vue.config.productionTip = false;
Vue.use(ElementUi);
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;

router.beforeEach((to, from, next) => {
    // console.info(to);
    // console.info(from);
    if (to.name === 'Login') {
        next();
        return;
    }
    let sStorage = JSON.parse(sessionStorage.getItem("store"));
    console.info(sStorage);
    let userName = sStorage.user.userName;
    console.info(sStorage.user.userName);
    if (isUndefined(userName)) {
        if (to.meta.requireAuth) {
            next({path: '/', query: {redirect: to.path}})
        } else {
            next();
        }
    } else {
        initMenu(router, store);
        next();
    }
})

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app')