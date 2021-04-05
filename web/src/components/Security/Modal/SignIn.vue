<template>
  <v-dialog
    v-model="dialog"
    persistent
    max-width="700px"
  >
    <template v-slot:activator="{ on }">
      <a v-show="$store.getters.TOKEN == null"
        color="primary"
        v-on="on"
      >Войти</a>
    </template>
    <form @submit.prevent="onsubmit()">
      <v-card>
        <v-card-title>
          <span class="headline">Вход</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  v-model="login"
                  label="Введите логин*"
                  required
                />
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="password"
                  label="Введите пароль*"
                  type="password"
                  required
                />
              </v-col>
              <v-col cols="12">
                <a href="/">Забыли пароль?</a>
              </v-col>
            </v-row>
          </v-container>
          <small>*обозначает обязательные поля</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn
            type="submit"
            color="blue darken-1"
            text
          >
            Войти
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
            Закрыть
          </v-btn>
        </v-card-actions>
      </v-card>
    </form>
  </v-dialog>
</template>

<script>
import axios from 'axios'

var url = 'http://localhost:8081/security/auth'
export default {
  name: 'SignIn',
  data () {
    return {
      dialog: false,
      login: null,
      password: null,
      loginErrors: [],
      passwordErrors: []
    }
  },
  computed: {
    formReady () {
      return !(this.loginErrors.length || this.passwordErrors.length)
    }
  },
  methods: {
    onsubmit () {
      this.loginErrors = this.login ? [] : ['Логин обязателен']
      this.passwordErrors = this.password ? [] : ['Пароль обязателен']
      if (!this.formReady) {
        return
      }
      this.auth()
    },
    auth () {
      var payload = {
        login: this.login,
        password: this.password
      }
      var responseData = {
        role: null,
        token: null,
        id: null
      }
      var error = null
      this.axios.post(url, payload).then(function (response) {
        responseData = response.data
        localStorage.setItem('access_token', responseData.token)
        localStorage.setItem('role', responseData.role)
        localStorage.setItem('id', responseData.id)
        this.changeCondition(responseData)
      }).catch(function (err) {
        error = err.response.data.errorMessage
      })
      setTimeout(() => {
        if (error !== null) {
          this.$notifications.notify(
            {
              message: error,
              icon: 'nc-icon nc-app',
              horizontalAlign: 'right',
              verticalAlign: 'top',
              type: 'danger'
            })
        } else {

        }
      }, 200)
    },
    changeCondition (responseData) {
      this.$store.commit('SET_TOKEN', responseData.token)
      this.$store.commit('SET_ROLES', responseData.role)
      this.$store.commit('SET_ID', responseData.id)
      this.dialog = false
      axios.defaults.headers.common.Authorization = 'Bearer ' + this.$store.getters.TOKEN
      this.$router.push('user/current')
    }
  }
}
</script>

<style lang="scss">
</style>
