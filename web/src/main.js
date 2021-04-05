import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import axios from 'axios'
import VueAxios from 'vue-axios'

import LightBootstrap from './light-bootstrap-main'
import vuetify from './plugins/vuetify'

import VueCookies from 'vue-cookies'

Vue.use(VueRouter)
Vue.use(LightBootstrap)
Vue.use(VueAxios, axios)
Vue.use(VueCookies)

const token = store.state.currentUser.token
if (token != null) {
  axios.defaults.headers.common.Authorization = 'Bearer ' + token
}

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  axios,
  render: h => h(App)
}).$mount('#app')
