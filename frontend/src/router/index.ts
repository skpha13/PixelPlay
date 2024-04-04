import { createRouter, createWebHistory } from 'vue-router'
import LandingPage from "@/views/LandingPageView.vue";
import NotFound from "@/views/NotFoundView.vue"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'landingPage',
      component: LandingPage
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'notFound',
      component: NotFound
    }
  ]
})

export default router
