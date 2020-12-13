<template>
  <v-dialog
    v-model="dialog"
    persistent
    max-width="800px"
  >
    <template v-slot:activator="{ on }">
      <a v-show="$store.getters.TOKEN == null"
        color="primary"
        v-on="on"
      >Регистрация</a>
    </template>
    <form @submit.prevent="onsubmit()">
    <v-card>
      <v-card-title>
        <span class="headline">Регистрация</span>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col
              cols="12"
              sm="6"
            >
              <v-text-field
                      v-model="userData.firstName"
                label="Введите имя*"
                required
              />
            </v-col>
            <v-col
              cols="12"
              sm="6"
            >
              <v-text-field v-model="userData.lastName" label="Введите фамилию*" />
            </v-col>
            <v-col
              cols="12"
              sm="6"
            >
              <v-text-field v-model="userData.login"
                label="Введите ваш логин*"
                hint="Поможет идентифировать вас как пользователя"
                persistent-hint
                required
              />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="userData.email"
                label="Почта*"
                required
              />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="userData.password"
                label="Пароль*"
                type="password"
                required
              />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="userData.phone"
                      label="Номер телефона*"
                      required
              />
            </v-col>
            <v-col
              cols="12"
              sm="6"
            >
              <v-autocomplete v-model="userData.role"
                :items="['Артист', 'Организатор']"
                label="Вид деятильности"
              />
            </v-col>
            <v-col
                    cols="12"
                    sm="6"
            >
            </v-col>
            <v-col  v-show="userData.role == 'Организатор'"
                    cols="12"
                    sm="6">
              <v-text-field v-model="userData.name"
                            label="Наименование организации*"
                            hint="Оставьте пустым, если использовать логин по умолчанию"
                            required
              />
            </v-col>
            <v-col  v-show="userData.role == 'Организатор'"
                    cols="12"
                    sm="6">
              <v-text-field v-model="userData.type"
                            label="Тип организации*"
                            required
              />
            </v-col>
            <v-col  v-show="userData.role == 'Организатор'"
                    cols="12">
              <label>Введите описание организации*</label>
              <textarea
                      v-model="userData.description"
                      rows="4"
                      class="form-control border-input"
              />
            </v-col>
            <v-col  v-show="userData.role == 'Артист'"
                    cols="12"
                    sm="6">
              <v-text-field v-model="userData.name"
                            label="Ваш псевдоним*"
                            hint="Оставьте пустым, если использовать логин по умолчанию"
                            required
              />
            </v-col>
            <v-col  v-show="userData.role == 'Артист'"
                    cols="12"
                    sm="6">
              <v-text-field v-model="userData.type"
                            label="Тип артиста*"
                            required
              />
            </v-col>
            <v-col  v-show="userData.role == 'Артист'"
                    cols="12">
              <label>Введите описание артиста*</label>
              <textarea
                      v-model="userData.description"
                      rows="4"
                      class="form-control border-input"
              />
            </v-col>
          </v-row>
        </v-container>
        <small>*обозначает обязательные поля</small>
      </v-card-text>
      <v-card-actions>
        <v-spacer />
        <v-btn
          color="blue darken-1"
          text
          type="submit"
        >
          Создать
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
var url = 'http://localhost:8081/security'
export default {
  name: 'SignUp',
  data () {
    return {
      userData: {
        firstName: null,
        lastName: null,
        login: null,
        email: null,
        password: null,
        phone: null,
        role: null,
        name: null,
        type: null,
        description: null
      },
      dialog: false,
      loginErrors: [],
      passwordErrors: [],
      emailErrors: []
    }
  },
  computed: {
    formReady () {
      return !(this.loginErrors.length || this.passwordErrors.length || this.emailErrors.length)
    }
  },
  methods: {
    onsubmit () {
      this.loginErrors = this.userData.login ? [] : ['Логин обязателен']
      this.passwordErrors = this.userData.password ? [] : ['Пароль обязателен']
      this.emailErrors = this.userData.email ? [] : ['Пароль обязателен']
      if (!this.formReady) {
        return
      }
      this.changeRole()
      this.register()
    },
    register () {
      var userId = null
      var error = null
      this.axios.post(url, this.userData).then(function (response) {
        userId = response.data
        if (userId) {}
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
          this.dialog = false
          this.$notifications.notify(
            {
              message: 'Регистрация прошла успешно, подтвердите почту!',
              icon: 'nc-icon nc-app',
              horizontalAlign: 'right',
              verticalAlign: 'top',
              type: 'success'
            })
        }
      }, 200)
    },
    changeRole () {
      if (this.userData.role === 'Артист') {
        this.userData.role = 'ARTIST'
      } else {
        this.userData.role = 'ORGANIZATION'
      }
    }
  }
}
</script>

<style lang="scss">
</style>
