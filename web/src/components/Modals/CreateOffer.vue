<template>
    <v-dialog
            v-model="dialog"
            persistent
            max-width="800px"
    >
        <template v-slot:activator="{ on }">
            <div class="container-fluid align-center justify-center">
                <v-btn style="outline: none" v-show="$store.getters.ROLES === 'ARTIST' || $store.getters.ROLES === 'ADMIN'" v-on="on" block>Создать заявку</v-btn>
                <br>
            </div>
        </template>
        <form @submit.prevent="onsubmit()">
            <v-card>
                <v-card-title>
                    <span class="headline">Создание заявки</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col
                                    cols="12"
                            >
                                <v-text-field
                                        v-model="offerData.name"
                                        label="Введите название*"
                                        required
                                />
                            </v-col>
                            <v-col
                                    cols="12"
                            >
                                <label>Введите описание*</label>
                                <textarea
                                        v-model="offerData.description"
                                        rows="4"
                                        class="form-control border-input"
                                />
                            </v-col>
                            <v-col
                                    cols="12"
                            >
                                <v-text-field v-model="offerData.cost"
                                              label="Введите предварительную стоимость"
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
var url = 'http://localhost:8081/offers'
export default {
  name: 'CreateEvent',
  data () {
    return {
      offerData: {
        name: null,
        description: null,
        cost: null,
        artistId: this.$store.getters.ID
      },
      dialog: false
    }
  },
  methods: {
    onsubmit () {
      this.createEvent()
    },
    createEvent () {
      var error = null
      var responseData = null
      this.axios.post(url, this.offerData).then(function (response) {
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
          this.dialog = false
          this.$notifications.notify(
            {
              message: 'Заявка создана успешно',
              icon: 'nc-icon nc-app',
              horizontalAlign: 'right',
              verticalAlign: 'top',
              type: 'success'
            })
        }
      }, 200)
    }
  }
}
</script>
<style lang="scss">
</style>
