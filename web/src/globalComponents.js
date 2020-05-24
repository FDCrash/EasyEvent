import BaseInput from './components/Inputs/BaseInput.vue'
import BaseCheckbox from './components/Inputs/BaseCheckbox.vue'
import BaseRadio from './components/Inputs/BaseRadio.vue'
import BaseDropdown from './components/BaseDropdown.vue'
import Card from './components/Cards/Card.vue'
import SignUp from './components/Security/Modal/SignUp'
import SignIn from './components/Security/Modal/SignIn'
import LogOut from './components/Security/LogOut/LogOut'

/**
 * You can register global components here and use them as a plugin in your main Vue instance
 */

const GlobalComponents = {
  install (Vue) {
    Vue.component(LogOut.name, LogOut)
    Vue.component(BaseInput.name, BaseInput)
    Vue.component(BaseCheckbox.name, BaseCheckbox)
    Vue.component(BaseRadio.name, BaseRadio)
    Vue.component(BaseDropdown.name, BaseDropdown)
    Vue.component('card', Card)
    Vue.component(SignUp.name, SignUp)
    Vue.component(SignIn.name, SignIn)
  }
}

export default GlobalComponents
