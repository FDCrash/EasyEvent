<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div v-if="$route.params.id === 'current'" class="col-md-8">
          <edit-profile-form v-bind:userData="user" />
        </div>
        <div v-else-if="$route.params.id !== 'current' && this.user.role === 'ORGANIZATION'" class="col-md-8">
          <event-card v-bind:userData="user" />
        </div>
        <div class="col-md-4">
          <user-card v-bind:userData="user"/>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import EditProfileForm from './UserProfile/EditProfileForm.vue'
import UserCard from './UserProfile/UserCard.vue'
import EventCard from '../components/Cards/EventCard'
var userUrl = 'http://localhost:8081/users/'
var artistUrl = 'http://localhost:8081/artists/'
var organizationUrl = 'http://localhost:8081/organizations/'
export default {
  components: {
    EditProfileForm,
    UserCard,
    EventCard
  },
  props: ['userId', 'from'],
  data () {
    return {
      user: {
        id: null,
        login: null,
        email: null,
        firstName: null,
        lastName: null,
        registration: null,
        role: null,
        phone: null,
        name: null,
        description: null,
        type: null
      },
      artistInfo: {
        events: {},
        offers: {}
      },
      organizationInfo: {
        events: {}
      }
    }
  },
  methods: {
    loadUserInfo () {
      var responseData = null
      this.axios.get(userUrl + this.$route.params.id).then(function (response) {
        responseData = response.data
      })
      setTimeout(() => {
        if (responseData.role === 'ARTIST') {
          this.axios.get(artistUrl + responseData.id).then(function (response) {
            responseData = response.data
          })
        } else if (responseData.role === 'ORGANIZATION') {
          this.axios.get(organizationUrl + responseData.id).then(function (response) {
            responseData = response.data
          })
        }
        setTimeout(() => {
          this.user = responseData
        }, 200)
      }, 200)
    }
  },
  mounted () {
    this.loadUserInfo()
  }
}

</script>
<style>

</style>
