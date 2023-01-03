export default {
    LOGIN(state,value){
        state.userData = value
        state.isLogin = true
    },
    LOGOUT(state){
        state.userData = null;
        state.isLogin = false;
        state.menus = null;
        sessionStorage.clear()
        localStorage.clear()
    },
    SETMENUS(state,value){
        state.menus = value;
    },
    SETTREE(state,value){
        state.tree = value
    }
}