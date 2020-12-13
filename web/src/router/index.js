import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../pages/Home.vue'
import Dashboard from '../layout/Dashboard.vue'

import ArtistOffersPage from '../pages/ArtistOffersPage'
import EventsPage from '../pages/EventsPage.vue'
import UserProfile from '../pages/UserProfile.vue'
import MyEvents from '../pages/MyEvents'
import Maps from '../pages/Maps.vue'
import Notifications from '../pages/Notifications.vue'
import AcceptedOffers from '../pages/AcceptedOffers'
import MyOffers from '../pages/MyOffers'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: Dashboard,
    redirect: '/home',
    children: [
      {
        path: 'events',
        name: 'Events',
        component: EventsPage
      },
      {
        path: 'offers',
        name: 'Offers',
        component: ArtistOffersPage
      },
      {
        path: 'home',
        name: 'home',
        component: Home
      },
      {
        path: 'user/:id',
        name: 'User',
        component: UserProfile
      },
      {
        path: 'maps',
        name: 'User',
        component: Maps
      },
      {
        path: 'my-events',
        name: 'My Events',
        component: MyEvents
      },
      {
        path: 'accepted-offers',
        name: 'My Accepted Offers',
        component: AcceptedOffers
      },
      {
        path: 'my-offers',
        name: 'My Offers',
        component: MyOffers
      },
      {
        path: 'notifications',
        name: 'Notifications',
        component: Notifications
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
