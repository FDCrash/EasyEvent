<template>
  <card>
    <h4
      slot="header"
      class="card-title"
    >
      Изменить профиль
    </h4>
    <form>
      <div class="row">
        <div class="col-md-4">
          <base-input
            v-model="userData.login"
            type="text"
            label="Логин"
            :disabled="true"
          />
        </div>
        <div class="col-md-4">
          <base-input
            v-model="userData.firstName"
            type="text"
            label="Имя"
          />
        </div>
        <div class="col-md-4">
          <base-input
            v-model="userData.lastName"
            type="text"
            label="Фамилия"
          />
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <base-input
            v-model="userData.email"
            type="email"
            label="Email"
          />
        </div>
        <div v-show="userData.registration === false" class="col-md-6">
          <v-tooltip top>
            <template  v-slot:activator="{ on }">
              <v-btn v-on="on" class="text-warning" block style="margin-top: 28px">Почта не потдверждена</v-btn>
            </template>
            <span>Нажимите чтобы подтвердить почту</span>
          </v-tooltip>
        </div>
        <div v-show="userData.registration === true" class="col-md-6">
          <v-tooltip top>
            <template  v-slot:activator="{ on }">
              <v-btn v-on="on" class="text-success" block style="margin-top: 28px" @click="onsubmit">Почта потдверждена</v-btn>
            </template>
            <span>Действий не требуется</span>
          </v-tooltip>
        </div>
      </div>

      <div class="row">
        <div class="col-md-4">
          <base-input
            v-model="userData.phone"
            type="text"
            label="Номер телефона"
          />
        </div>
      </div>

      <div v-show="userData.role === 'ORGANIZATION'" class="row">
        <div class="col-md-6">
          <base-input
                  v-model="userData.name"
                  type="text"
                  label="Название организации"
          />
        </div>
        <div class="col-md-6">
          <base-input
            v-model="userData.type"
            type="text"
            label="Тип организации"
          />
        </div>
      </div>

      <div v-show="userData.role === 'ARTIST'" class="row">
        <div class="col-md-6">
          <base-input
                  v-model="userData.name"
                  type="text"
                  label="Псевдоним артиста"
          />
        </div>
        <div class="col-md-6">
          <base-input
                  v-model="userData.type"
                  type="text"
                  label="Тип артиста"
          />
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <div class="form-group">
            <label>О себе</label>
            <textarea
              v-model="userData.description"
              rows="5"
              class="form-control border-input"
            />
          </div>
        </div>
      </div>
      <div class="text-center">
        <button
          type="submit"
          class="btn btn-info btn-fill float-right"
          @click.prevent="updateProfile"
        >
          Обновить профиль
        </button>
      </div>
      <div class="clearfix" />
    </form>
  </card>
</template>
<script>
import Card from '../../components/Cards/Card.vue'

export default {
  components: {
    Card
  },
  props: ['userData'],
  data () {
    return {
    }
  },
  methods: {
    updateProfile () {
      alert('Your data: ' + JSON.stringify(this.user))
    },
    onsubmit () {
      this.$notifications.notify(
        {
          message: 'Организатор <u>belski</u> принял вашу заявку',
          icon: 'nc-icon nc-app',
          horizontalAlign: 'right',
          verticalAlign: 'top',
          type: 'info'
        })
    }
  }
}

</script>
<style>

</style>
