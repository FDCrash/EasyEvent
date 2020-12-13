<template>
    <div class="card" style="box-shadow: 0 0 15px rgba(0,0,0,0.1); border-style: none">
            <div class="row" style="margin-left: 20px">
                 <span class="card-body ">Мероприятие: <br>{{eventData.name}}</span>
                    <div class="card-body" style="margin-left: 100px">
                        <span>Период набора артистов: <br>{{eventData.startDate}} - {{eventData.endDate}}</span>
                    </div>
                <div class="col-1 text">
                <v-tooltip  bottom>
                    <template  v-slot:activator="{ on }">
                        <v-btn @click="show=!show" icon style="margin-top: 8px; outline: none">
                            <v-icon class="text-warning" style="font-size: 35px;" v-on="on">nc-icon nc-bullet-list-67</v-icon>
                        </v-btn>
                    </template>
                    <span>Подробнее о мероприятии</span>
                </v-tooltip>
            </div>
            </div>
        <div class="card-footer row">
            <div  style="margin-left: 27px">
                <span>Организаторы мероприятия:<br></span>
                    <div v-for="(value, key) in eventData.organizations">
                        <v-tooltip top>
                            <template  v-slot:activator="{ on }">
                                <v-btn x-small @click="onUserClick(key)" class="rounded-circle" style="width: 40px; height: 40px; outline: none;">
                                <v-img v-on="on" class="rounded-circle" v-bind:src="require('../../../public/img/faces/' + key + '.jpg')" style="size: 35px; width: 40px; height: 40px"/>
                                </v-btn>
                            </template>
                            <span>{{value}}</span>
                        </v-tooltip>
                    </div>
            </div>
            <div style="margin-left: 370px">
                <span>Артисты мероприятия:<br></span>
                <div v-for="(value, key) in eventData.artists">
                    <v-tooltip top>
                        <template  v-slot:activator="{ on }">
                            <v-btn x-small @click="onUserClick(key)" class="rounded-circle" src="../../../public/img/lol.jpg" style="width: 40px; height: 40px; margin-right: 20px">
                                <v-img v-on="on"  class="rounded-circle" v-bind:src="require('../../../public/img/faces/' + key + '.jpg')" style="size: 35px; width: 40px; height: 40px"/>
                            </v-btn>
                        </template>
                        <span>{{value}}</span>
                    </v-tooltip>
                </div>
            </div>
            <br>
        </div>
        <div v-if="show" class="row">
            <div class="card-body" style="padding-left: 50px">
                <span>Информация о мероприятии:</span>
                <br>
                <span>{{eventData.description}}</span>
            </div>
            <div class="card-body" style="padding-left: 135px">
                <span>Гонорар за мероприятие:</span>
                <br>
                <span>{{eventData.cost}} $</span>
            </div>
            <div style="padding-top: 20px; margin-right: 20px">
                <send-offer></send-offer>
            </div>
            <div style="padding-right: 30px" v-if="$route.name === 'My Events'">
                <v-btn style="outline: none" >Изменить</v-btn><br>
            </div>
        </div>
    </div>
</template>
<script>
import SendOffer from '../Modals/SendOffer'
export default {
  components: {
    SendOffer
  },
  name: 'EventCard',
  props: ['eventData'],
  data () {
    return {
      show: false
    }
  },
  methods: {
    onUserClick (id) {
      if (id !== this.$store.getters.ID) {
        this.$router.push('/user/' + id)
      } else {
        this.$router.push('/user/current')
      }
    }
  }
}
</script>
<style>
</style>
