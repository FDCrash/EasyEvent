<template>
    <v-dialog
            v-model="dialog"
            persistent
            max-width="800px"
    >
        <template v-slot:activator="{ on }">
            <div class="container-fluid align-center justify-center">
                <v-btn style="outline: none" v-show="$store.getters.ROLES === 'ORGANIZATION' || $store.getters.ROLES === 'ADMIN'" v-on="on" block>Создать мероприятие</v-btn>
                <br>
            </div>
        </template>
        <form @submit.prevent="onsubmit()">
            <v-card>
                <v-card-title>
                    <span class="headline">Создание мероприятия</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col
                                    cols="12"
                            >
                                <v-text-field
                                        v-model="eventData.name"
                                        label="Введите название мероприятия*"
                                        required
                                />
                            </v-col>
                            <v-col
                                    cols="12"
                            >
                                <label>Введите описание*</label>
                                <textarea
                                        v-model="eventData.description"
                                        rows="4"
                                        class="form-control border-input"
                                />
                            </v-col>
                            <v-col
                                    cols="12"
                            >
                                <v-text-field v-model="eventData.cost"
                                              label="Введите предварительную стоимость"
                                />
                            </v-col>
                            <v-col cols="12"  sm="6"
                                   md="4">
                                <v-menu
                                    v-model="start"
                                    :close-on-content-click="false"
                                    :nudge-right="40"
                                    transition="scale-transition"
                                    offset-y
                                    min-width="290px"
                            >
                                <template v-slot:activator="{ on }">
                                    <v-text-field
                                            v-model="eventData.startDate"
                                            label="Дата начала набора"
                                            readonly
                                            v-on="on"
                                    ></v-text-field>
                                </template>
                                <v-date-picker v-model="eventData.startDate" @input="start = false"></v-date-picker>
                            </v-menu>
                            </v-col>
                            <v-col cols="12"  sm="6"
                                   md="4">
                                <v-menu
                                        v-model="end"
                                        :close-on-content-click="false"
                                        :nudge-right="40"
                                        transition="scale-transition"
                                        offset-y
                                        min-width="290px"
                                >
                                    <template v-slot:activator="{ on }">
                                        <v-text-field
                                                v-model="eventData.endDate"
                                                label="Дата окончания набора"
                                                readonly
                                                v-on="on"
                                        ></v-text-field>
                                    </template>
                                    <v-date-picker v-model="eventData.endDate" @input="end = false"></v-date-picker>
                                </v-menu>
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
var url = 'http://localhost:8081/events'
export default {
  name: 'CreateEvent',
  data () {
    return {
      start: false,
      end: false,
      eventData: {
        name: null,
        startDate: new Date().toISOString().substr(0, 10),
        endDate: new Date().toISOString().substr(0, 10),
        description: null,
        cost: null,
        organizations: {},
        artists: {}
      },
      dialog: false
    }
  },
  methods: {
    onsubmit () {
      this.eventData.organizations[this.$store.getters.ID] = null
      this.createEvent()
    },
    createEvent () {
      var responseData
      this.axios.post(url, this.eventData).then(function (response) {
        responseData = response.data
        if (responseData) {}
      })
      setTimeout(() => {
        this.dialog = false
        this.$notifications.notify(
          {
            message: 'Мероприятие создано успешно',
            icon: 'nc-icon nc-app',
            horizontalAlign: 'right',
            verticalAlign: 'top',
            type: 'success'
          })
      }, 200)
    }
  }
}
</script>

<style lang="scss">
</style>
