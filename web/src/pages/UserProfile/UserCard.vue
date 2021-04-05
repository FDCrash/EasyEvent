<template>
  <card class="card-user">
    <img
      slot="image"
      src="https://ununsplash.imgix.net/photo-1431578500526-4d9613015464?fit=crop&fm=jpg&h=300&q=75&w=400"
      alt="..."
    >
    <div class="author">
        <img v-bind:src="require('../../../public/img/faces/'+userData.id + '.jpg')"
          class="avatar border-gray"
          alt="..."
        >
        <p class="title" >{{userData.firstName}} {{userData.lastName}}<br>
          <p class="title">{{userData.login}}</p>
    </div>
    <div v-show="userData.role === 'ORGANIZATION'">
      <p class="description text-center">Организация: {{userData.name}}</p>
      <p class="description text-center">Тип организации: {{userData.type}}</p>
    </div>
    <div v-show="userData.role === 'ARTIST'">
      <p class="description text-center">Псевдоним артиста: {{userData.name}}</p>
      <p class="description text-center">Тип артиста: {{userData.type}}</p>
    </div>
    <p class="title description text-center" >Контактные данные</p>
    <p class="description text-center" >Номер телефона:<br>{{userData.phone}}</p>
    <p class="description text-center" >Email:<br>{{userData.email}}</p>
    <p class="description text-center">
      Описание: <br>{{userData.description}}
    </p>
    <div
      slot="footer"
      class="text-center d-flex justify-content-center"
    >
      <button
        href="#"
        class="btn btn-simple"
      >
        <i class="fa fa-facebook-square" />
      </button>
      <button
        href="#"
        class="btn btn-simple"
      >
        <i class="fa fa-twitter" />
      </button>
      <button
        href="#"
        class="btn btn-simple"
      >
        <i class="fa fa-google-plus-square" />
      </button>
    </div>
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
  }
}

</script>
<style>

</style>
