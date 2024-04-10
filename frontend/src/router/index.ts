import { createRouter, createWebHistory } from 'vue-router'
import LandingPage from "@/views/LandingPageView.vue";
import NotFound from "@/views/NotFoundView.vue"
import TicTacToePVP from "@/views/TicTacToePVPView.vue";
import TicTacToeAI from "@/views/TicTacToeAIView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'landingPage',
      component: LandingPage
    },
    {
      path: '/tic-tac-toe-pvp',
      name: 'ticTacToePVP',
      component: TicTacToePVP
    },
    {
      path: '/tic-tac-toe-ai',
      name: 'ticTacToeAI',
      component: TicTacToeAI
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'notFound',
      component: NotFound
    }
  ]
})

export default router
