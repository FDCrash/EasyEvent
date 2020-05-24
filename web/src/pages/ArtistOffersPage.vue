<template>
    <div class="content">
        <create-offer></create-offer>
        <div class="container-fluid">
            <offer-card v-for="offer in offerData" :offer-data="offer"/>
        </div>
    </div>
</template>
<script>
import OfferCard from '../components/Cards/OfferCard'
import CreateOffer from '../components/Modals/CreateOffer'

var url = 'http://localhost:8081/offers'
export default {
  components: {
    OfferCard,
    CreateOffer
  },
  data () {
    return {
      offerData: [{
        id: null,
        name: null,
        description: null,
        cost: null,
        artistName: null,
        artistId: null
      }]
    }
  },
  methods: {
    loadOfferInfo () {
      var responseData = null
      this.axios.get(url).then(function (response) {
        responseData = response.data
      })
      setTimeout(() => {
        this.offerData = responseData
      }, 200)
    }
  },
  mounted () {
    this.loadOfferInfo()
  }
}
</script>
<style>
</style>
