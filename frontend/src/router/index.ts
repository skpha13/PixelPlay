import { createRouter, createWebHistory } from 'vue-router'
import LandingPage from "@/views/LandingPageView.vue";
import NotFound from "@/views/NotFoundView.vue"
import TicTacToePVP from "@/views/TicTacToePVPView.vue";
import TicTacToeAI from "@/views/TicTacToeAIView.vue";
import ConnectFourPVP from "@/views/ConnectFourPVPView.vue";
import ConnectFourAI from "@/views/ConnectFourAIView.vue";
import Greeting from "@/components/Greeting.vue";
import BlackjackPVP from "@/views/BlackjackPVPView.vue";
import BlackjackAI from "@/views/BlackjackAIView.vue";


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
      path: '/connect-four-pvp',
      name: 'connectFourPVP',
      component: ConnectFourPVP
    },
    {
      path: '/connect-four-ai',
      name: 'connectFourAI',
      component: ConnectFourAI
    },
    {
      path: '/greeting',
      name: 'greeting',
      component: Greeting
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'notFound',
      component: NotFound
    },
    {
      path: '/blackjack-pvp',
      name: 'blackjackPVP',
      component: BlackjackPVP
    },
    {
      path: '/blackjack-ai',
      name: 'blackjackAI',
      component: BlackjackAI
    }
  ]
})

export default router
