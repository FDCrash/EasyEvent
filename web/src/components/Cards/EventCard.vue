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
                        <v-icon class="text-warning" style="font-size: 35px; margin-top: 10px" v-on="on">nc-icon nc-bullet-list-67</v-icon>
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
                                <v-btn x-small @click="onUserClick(key)" class="rounded-circle" src="../../../public/img/lol.jpg" style="width: 40px; height: 40px">
                                <v-img v-on="on" class="rounded-circle" src="../../../public/img/lol.jpg" style="size: 35px; width: 40px; height: 40px"/>
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
                            <v-btn x-small @click="onUserClick" class="rounded-circle" src="../../../public/img/lol.jpg" style="width: 40px; height: 40px; margin-right: 20px">
                                <v-img v-on="on"  class="rounded-circle" src="../../../public/img/lol.jpg" style="size: 35px; width: 40px; height: 40px"/>
                            </v-btn>
                        </template>
                        <span>{{value}}</span>
                    </v-tooltip>
                </div>
            </div>
            <br>
        </div>
    </div>
</template>
<script>
export default {
  name: 'EventCard',
  props: ['eventData'],
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
