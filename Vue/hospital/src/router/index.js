import Vue from 'vue'
import Router from 'vue-router'
import Login from "@/components/Login";
import Index from "@/components/Index";
import Welcome from "@/components/Welcome";
import Register from "@/components/Register";
import store from '@/store'

Vue.use(Router)

const router = new Router({
    /* eslint-disable */
    routes: [{
        path: '/',
        name: 'Login',
        meta:{
            isAuth : false,
        },
        component: Login
    },
        {
            path:'/register',
            name:'Register',
            meta:{
                isAuth: false
            },
            component: Register
        },
        {
            path:'/index',
            name:'Index',
            meta:{
                isAuth: false,
                title: '首页'
            },
            redirect:"/welcome",
            component:Index,
            children:[
                {
                    path:'/welcome',
                    name:'Welcome',
                    meta:{
                        isAuth: false,
                        title : ''
                    },
                    component:Welcome
                },
                {
                    path:'/test',
                    meta:{
                        isAuth : true,
                        title : '用户信息'
                    },
                    name:'TestFunc',
                    component:() => import('@/components/testFunc')
                }
            ]
        }
    ]
    /* eslint-disable */
})


    let rendered = false;

router.beforeEach((to,from,next)=>{

    if(!rendered){
        if(to.path === '/' || to.path === '/register'){
            next()
        }else{
            const menus = store.state.menus
            addRoutes(menus)
            store.dispatch('setTree',treeify(menus))
            rendered = true
            next(to.path)
        }
    }else{
        if(to.meta.isAuth){
            var user = sessionStorage.getItem('user');
            if(user === null){
                alert('请先登录！')
                next({
                    path : '/'
                })
            }else{
                next()
            }
        }else{
            next()
        }
    }
})
/*,
                {
                    path:'/patient',
                    meta:{
                        isAuth: true,
                        title: '病人信息'
                    },
                    name:'PatientInfo',
                    component:() => import('@/components/PatientInfo')
                }
*/

export function treeify(menus){
    let tree = []
    //两层结构的树化
    menus.forEach((menu)=>{
        if(menu.pid === 0){
            let father = menu
            father = {
                menuid: menu.menuid,
                pid: 0,
                path: null,
                name: menu.name,
                title: menu.title,
                children :[]
            }
            menus.forEach((m)=>{
                if(m.pid === menu.menuid){
                    father.children.push(m)
                }
            })
            tree.push(father)
        }
    })
    return tree
}

export function addRoutes(menus){
    menus.forEach((menu)=>{
        if(menu.pid === 0){
            router.addRoute('Index',{
                path: '',
                meta:{
                    isAuth: true,
                    title: menu.title
                },
                name:menu.name,
                children:[]
            })
            menus.forEach((m)=>{
                if(m.pid === menu.menuid){
                    router.addRoute('Index',{
                        path:m.path,
                        meta:{
                            isAuth: true,
                            title: m.title
                        },
                        name:m.name,
                        component:() => import('@/components/' + m.name)
                    })
                }
            })
        }
    })
}
export default router