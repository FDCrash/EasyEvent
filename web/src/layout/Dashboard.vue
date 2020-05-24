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
        <p>Офферы артистов</p>
      </sidebar-link>
      <sidebar-link to="/table-list">
        <i class="nc-icon nc-chart-pie-35" />
        <p>Table list</p>
      </sidebar-link>
      <sidebar-link to="/typography">
        <i class="nc-icon nc-paper-2" />
        <p>Typography</p>
      </sidebar-link>
      <sidebar-link to="/icons">
        <i class="nc-icon nc-atom" />
        <p>Icons</p>
      </sidebar-link>
      <sidebar-link to="/maps">
        <i class="nc-icon nc-pin-3" />
        <p>Maps</p>
      </sidebar-link>
      <sidebar-link to="/notifications">
        <i class="nc-icon nc-bell-55" />
        <p>Notifications</p>
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
