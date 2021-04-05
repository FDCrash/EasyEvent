<template>
    <div xclass="card" style="box-shadow: 0 0 15px rgba(0,0,0,0.1); border-style: none; padding: 5px">
        <div class="row">
            <div>
            <v-tooltip top>
                <template v-slot:activator="{ on }">
                    <v-btn @click="onUserClick" class="rounded-circle" style="width: 100px; height: 100px; margin-left: 20px">
                    <v-img v-on="on" class="rounded-circle" src="../../../public/img/faces/886f3191-9cc6-429f-b642-f532e6781eea.jpg" style="width: 100px; height: 100px"/>
                    </v-btn>
                </template>
                <span>{{offerData.artistName}}</span>
            </v-tooltip>
            </div>
            <div class="card-body"  style="margin-left: 100px">
                <span>Название заявки: <br>{{offerData.name}}</span>
            </div>
            <div class="card-body">
                <span>Стоимость стоимость заявки<br>{{offerData.cost}} $</span>
            </div>
            <div class="col-1 text">
                <v-tooltip  bottom>
                    <template  v-slot:activator="{ on }">
                        <v-btn @click="show=!show" icon style="margin-top: 8px; outline: none">
                        <v-icon class="text-warning" style="font-size: 35px;" v-on="on">nc-icon nc-bullet-list-67</v-icon>
                        </v-btn>
                    </template>
                    <span>Подробности заявки</span>
                </v-tooltip>
            </div>
        </div>
        <div v-if="show" class="row">
            <div class="card-body" style="padding-left: 235px">
                <span>Информация о заявке:</span>
                <br>
                <span>{{offerData.description}}</span>
            </div>
            <div style="padding-top: 20px; margin-right: 20px">
                <add-to-event></add-to-event>
            </div>
            <div style="padding-right: 30px" v-if="$route.name === 'My Offers'">
                <v-btn style="outline: none" >Изменить</v-btn><br>
            </div>
        </div>
    </div>
</template>
<script>
import AddToEvent from '../Modals/AddToEvent'
export default {
  components: { AddToEvent },
  data () {
    return {
      show: false
    }
  },
  name: 'OfferCard',
  props: ['offerData'],
  methods: {
    onUserClick () {
      if (this.offerData.artistId !== this.$store.getters.ID) {
        this.$router.push('/user/' + this.offerData.artistId)
      } else {
        this.$router.push('/user/current')
      }
    }
  },
  computed: {
    image () {
      return require('../../../public/img/faces/' + this.$props.offerData.artistId + '.jpg')
    }
  }
}
</script>
<style>
</style>
