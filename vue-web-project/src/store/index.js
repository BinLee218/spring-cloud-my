import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: {
            id: window.localStorage.getItem('user' || '[]') == null ? '-1' : JSON.parse(window.localStorage.getItem('user' || '[]')).id,
            userName: window.localStorage.getItem('user' || '[]') == null ? '未登录' : JSON.parse(window.localStorage.getItem('user' || '[]')).userName,
            role: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).role,
            face: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).face
        },
        routes: []
    },
    mutations: {
        initMenu(state,menus) {
            state.routes = menus;
        },
        login(state,user) {
            state.user = user;
            window.localStorage.setItem('user',JSON.stringify(user));

        },
        logout(state) {
            window.localStorage.removeItem('user');
            state.routes = [];
        },
        SET_TOKEN(state,token){
            state.token = token;
            window.sessionStorage.setItem('user',token);
        }
    }

})