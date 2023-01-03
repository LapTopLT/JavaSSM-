import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import axios from 'axios'
import qs from 'qs'
import store from './store'

Vue.prototype.$qs = qs
Vue.prototype.axios = axios
axios.defaults.baseURL='http://localhost:8082/'
Vue.use(ElementUI)
Vue.config.productionTip = false



new Vue({
  router,
  render: h => h(App),
  store,
  beforeCreate() {
    Vue.prototype.$bus  = this    //绑定全局事件总线
  }
}).$mount('#app')
