import BaseCheckbox from './Inputs/BaseCheckbox.vue'
import Radio from './Inputs/BaseRadio.vue'
import BaseInput from './Inputs/BaseInput.vue'

import BaseDropdown from './BaseDropdown.vue'
import Table from './Table.vue'

import Card from './Cards/Card.vue'
import ChartCard from './Cards/ChartCard.vue'
import StatsCard from './Cards/StatsCard.vue'
import EventsCard from './Cards/EventCard'
import OfferCard from './Cards/OfferCard'

import SignUp from './Security/Modal/SignUp.vue'
import SignIn from './Security/Modal/SignIn.vue'
import LogOut from './Security/LogOut/LogOut'

import SidebarPlugin from './SidebarPlugin'
import AddToEvent from './Modals/AddToEvent'

const components = {
  AddToEvent,
  LogOut,
  SignUp,
  SignIn,
  BaseCheckbox,
  Radio,
  BaseInput,
  Card,
  EventsCard,
  OfferCard,
  ChartCard,
  StatsCard,
  Table,
  BaseDropdown,
  SidebarPlugin
}

export default components
