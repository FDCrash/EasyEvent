<template>
    <div class="content">
        <div class="container-fluid">
            <event-card v-for="event in eventsData" :event-data="event"/>
        </div>
    </div>
</template>
<script>
import EventCard from '../components/Cards/EventCard'

var urlOrganization = 'http://localhost:8081/events/organization/full/'
var urlArtist = 'http://localhost:8081/events/artist/full/'
export default {
  components: {
    EventCard
  },
  data () {
    return {
      eventsData: [{
        id: null,
        name: null,
        startDate: null,
        endDate: null,
        description: null,
        cost: null,
        createdAt: null,
        expiresAt: null,
        organizations: {},
        artists: {}
      }]
    }
  },
  methods: {
    loadEventsInfo () {
      var url
      if (this.$store.getters.ROLES === 'ORGANIZATION') {
        url = urlOrganization
      } else {
        url = urlArtist
      }
      var responseData = null
      this.axios.get(url + this.$store.getters.ID).then(function (response) {
        responseData = response.data
      })
      setTimeout(() => {
        responseData.forEach(resp => {
          resp.startDate = this.formatDate(new Date(resp.startDate), 2)
          resp.endDate = this.formatDate(new Date(resp.endDate), 2)
        })
        this.eventsData = responseData
      }, 200)
    },
    formatDate (dateObj, format) {
      var currDate = dateObj.getDate()
      var currMonth = dateObj.getMonth()
      currMonth = currMonth + 1
      var currYear = dateObj.getFullYear()
      var currMin = dateObj.getMinutes()
      var currHr = dateObj.getHours()
      var currSc = dateObj.getSeconds()
      if (currMonth.toString().length === 1) {
        currMonth = '0' + currMonth
      }
      if (currDate.toString().length === 1) {
        currDate = '0' + currDate
      }
      if (currHr.toString().length === 1) {
        currHr = '0' + currHr
      }
      if (currMin.toString().length === 1) {
        currMin = '0' + currMin
      }

      if (format === 1) {
        return currDate + '-' + currMonth + '-' + currYear
      } else if (format === 2) {
        return currYear + '-' + currMonth + '-' + currDate
      } else if (format === 3) {
        return currDate + '/' + currMonth + '/' + currYear
      } else if (format === 4) {
        return currMonth + '/' + currDate + '/' + currYear + ' ' + currHr + ':' + currMin + ':' + currSc
      }
    }
  },
  mounted () {
    this.loadEventsInfo()
  }
}
</script>
<style>
</style>
