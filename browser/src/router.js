import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
import Home from './views/Home.vue'
import Test1 from './views/test1.vue'
import Test2 from './views/test2.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      hidden: true
    }, {
      path: '/home',
      name: 'Home',
      component: Home,
      hidden: true
    },{
      path: '/home', 
      name: '导航一',
      component: Home,
      hidden: false,
      children: [
        {
          path: '/test1',
          name: 'Test1',
          component: Test1
        }, {
          path: '/test2',
          name: 'Test2',
          component: Test2
        }
      ]
    }
  ]
})
