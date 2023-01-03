export default {
    login(context,value){
        context.commit('LOGIN',value)
    },
    logout(context){
        context.commit('LOGOUT')
    },
    setMenus(context,value){
        context.commit('SETMENUS',value)
    },
    setTree(context,value){
        context.commit('SETTREE',value)
    }
}