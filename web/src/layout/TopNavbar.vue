<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <a
        class="navbar-brand"
        href="#"
      >Главная</a>
      <button
        type="button"
        class="navbar-toggler navbar-toggler-right"
        :class="{toggled: $sidebar.showSidebar}"
        aria-controls="navigation-index"
        aria-expanded="false"
        aria-label="Toggle navigation"
        @click="toggleSidebar"
      >
        <span class="navbar-toggler-bar burger-lines" />
        <span class="navbar-toggler-bar burger-lines" />
        <span class="navbar-toggler-bar burger-lines" />
      </button>
      <div class="collapse navbar-collapse justify-content-end">
        <ul class="nav navbar-nav mr-auto">
          <base-dropdown tag="li">
            <template slot="title">
              <i class="nc-icon nc-planet" />
              <b class="caret" />
              <span class="notification">5</span>
            </template>
            <a
              class="dropdown-item"
              href="#"
            >Notification 1</a>
            <a
              class="dropdown-item"
              href="#"
            >Notification 2</a>
            <a
              class="dropdown-item"
              href="#"
            >Notification 3</a>
            <a
              class="dropdown-item"
              href="#"
            >Notification 4</a>
            <a
              class="dropdown-item"
              href="#"
            >Another notification</a>
          </base-dropdown>
        </ul>
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <log-out/>
            <sign-in class="nav-link"/>
            <sign-up class="nav-link"/>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
export default {
  data () {
    return {
      activeNotifications: false
    }
  },
  computed: {
    routeName () {
      const { name } = this.$route
      return this.capitalizeFirstLetter(name)
    }
  },
  methods: {
    capitalizeFirstLetter (string) {
      return string.charAt(0).toUpperCase() + string.slice(1)
    },
    toggleNotificationDropDown () {
      this.activeNotifications = !this.activeNotifications
    },
    closeDropDown () {
      this.activeNotifications = false
    },
    toggleSidebar () {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar)
    },
    hideSidebar () {
      this.$sidebar.displaySidebar(false)
    }
  }
}

</script>
<style>

</style>
