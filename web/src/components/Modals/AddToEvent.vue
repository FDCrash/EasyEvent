<template>
    <v-dialog scrollable
            v-model="dialog"
            persistent
            max-width="800px"
    >
        <template v-slot:activator="{ on }">
            <div class="container-fluid align-center justify-center">
                <v-btn @click="toEventClick" style="outline: none" v-show="($store.getters.ROLES === 'ORGANIZATION' || $store.getters.ROLES === 'ADMIN') && $route.name !== 'My Events'" v-on="on" block>Пригласить на мероприятие</v-btn><br>
            </div>
        </template>
        <v-card>
        <v-card-title>Выберите мероприятие</v-card-title>
            <v-divider/>
        <v-card-text style="height: 300px;">
            <div v-for="(value, key) in events">
                <v-checkbox :label="value"></v-checkbox>
            </div>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
            <v-btn color="blue darken-1" text @click="dialog = false">Закрыть</v-btn>
            <v-btn color="blue darken-1" text @click="onsubmit">Отправить приглашение</v-btn>
        </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
var urlEvents = 'http://localhost:8081/events/organization/'
export default {
  name: 'AddToEvent',
  data () {
    return {
      events: null,
      selected: {},
      dialog: false
    }
  },
  methods: {
    onsubmit () {
      this.dialog = false
      this.$notifications.notify(
        {
          message: 'Приглашение отправлено',
          icon: 'nc-icon nc-app',
          horizontalAlign: 'right',
          verticalAlign: 'top',
          type: 'success'
        })
    },
    toEventClick () {
      var error = null
      var responseData = null
      this.axios.get(urlEvents + this.$store.getters.ID, this.offerData).then(function (response) {
        responseData = response.data
        if (responseData) {}
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
          this.events = responseData
        }
      }, 200)
    }
  }
}
</script>
<style lang="scss">
</style>
