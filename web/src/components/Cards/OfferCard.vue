<template>
    <div class="card" style="box-shadow: 0 0 15px rgba(0,0,0,0.1); border-style: none; padding: 5px">
        <div class="row">
            <div >
            <v-tooltip top>
                <template v-slot:activator="{ on }">
                    <v-btn @click="onUserClick" class="rounded-circle" src="../../../public/img/lol.jpg" style="width: 100px; height: 100px; margin-left: 20px">
                    <v-img v-on="on" class="rounded-circle" src="../../../public/img/lol.jpg" style="width: 100px; height: 100px"/>
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
                        <v-icon class="text-warning" style="font-size: 35px; margin-top: 8px;" v-on="on">nc-icon nc-bullet-list-67</v-icon>
                    </template>
                    <span>Подробности заявки</span>
                </v-tooltip>
            </div>
        </div>
    </div>
</template>
<script>
export default {
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
  }
}
</script>
<style>
</style>
