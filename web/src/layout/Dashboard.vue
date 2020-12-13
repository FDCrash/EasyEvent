<template>
  <div class="wrapper">
    <side-bar >
      <mobile-menu slot="content" />
      <sidebar-link v-show="$store.getters.TOKEN == null" to="/home">
        <i class="nc-icon nc-notes" />
        <p>Главная</p>
      </sidebar-link>
      <sidebar-link
        v-show="$store.getters.TOKEN != null"
        to="/user/current"
      >
        <i class="nc-icon nc-circle-09" />
        <p>Профиль</p>
      </sidebar-link>
      <sidebar-link v-show="$store.getters.TOKEN != null" to="/events">
        <i class="nc-icon nc-notes" />
        <p>Мероприятия</p>
      </sidebar-link>
      <sidebar-link v-show="$store.getters.TOKEN != null" to="/offers">
        <i class="nc-icon nc-notes" />
        <p>Заявки артистов</p>
      </sidebar-link>
      <sidebar-link v-show="$store.getters.TOKEN != null" to="/my-events">
        <i class="nc-icon nc-chart-pie-35" />
        <p>Мои мероприятия</p>
      </sidebar-link>
      <sidebar-link v-show="$store.getters.TOKEN != null && $store.getters.ROLES === 'ORGANIZATION'" to="/accepted-offers">
        <i class="nc-icon nc-paper-2" />
        <p>Принятые заявки</p>
      </sidebar-link>
      <sidebar-link v-show="$store.getters.TOKEN != null && $store.getters.ROLES === 'ARTIST'" to="/my-offers">
        <i class="nc-icon nc-paper-2" />
        <p>Мои заявки</p>
      </sidebar-link>
    </side-bar>
    <div class="main-panel">
      <top-navbar />

      <dashboard-content  @click="toggleSidebar" />

      <content-footer />
    </div>
  </div>
</template>
<style lang="scss">

</style>
<script>
import TopNavbar from './TopNavbar.vue'
import ContentFooter from './ContentFooter.vue'
import DashboardContent from './Content.vue'
import MobileMenu from './MobileMenu.vue'

export default {
  components: {
    TopNavbar,
    ContentFooter,
    DashboardContent,
    MobileMenu
  },
  methods: {
    toggleSidebar () {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false)
      }
    }
  }
}

</script>
