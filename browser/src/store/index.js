import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        // 接收服务器传过来的菜单List
        routes: []
    },
    mutations: {
        // 通过state访问routes
        initRoutes(state, data) {
            state.routes = data;
        }
    },
    actions: {
    }
})