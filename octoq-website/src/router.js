import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import UserList from './views/User/UserList.vue'
import User from './views/User/User.vue'
import QuestionList from './views/Question/QuestionList.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/users',
      name: 'users',
      component: UserList
    },
    {
      path: '/users/:id',
      name: 'user',
      component: User
    },
    {
      path: '/questions',
      name: 'questions',
      component: QuestionList
    },
    {
      path: '/users/register',
      name: 'addUser',
      component: User
    }
  ]
})
