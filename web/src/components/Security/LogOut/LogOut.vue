<template>
    <a @click.prevent="setEmptyToken"
            v-show="$store.getters.TOKEN != null"
            color="primary"
    >Выйти</a>
</template>

<script>
import axios from 'axios'

export default {
  name: 'LogOut',
  methods: {
    setEmptyToken () {
      delete axios.defaults.headers.common.Authorization
      localStorage.setItem('access_token', null)
      localStorage.setItem('role', null)
      localStorage.setItem('id', null)
      this.$store.commit('RESET_STATE', null)
      this.$router.push('/home')
    }
  }
}
</script>

<style lang="scss">
</style>
