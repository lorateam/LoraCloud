import index from '@/view/index';
import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

const routes = [
  {
    path: '/',
    name: 'HelloWorld',
    component: index,
  },
];

export default new Router({
  routes,
});
