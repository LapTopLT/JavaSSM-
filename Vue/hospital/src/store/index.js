import Vue from 'vue'
//用于响应组件中的动作
import vuex from "vuex";
import state from './state';
import mutations from './mutations';
import actions from './actions';

import persistedState from 'vuex-persistedstate'

Vue.use(vuex)


//创建并暴露store
export default new vuex.Store({
    actions,
    mutations,
    state,
    plugins:[persistedState({
        storage : window.sessionStorage
    })]
})