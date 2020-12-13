import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const getDefaultState = () => {
  return {
    currentUser: {
      id: null,
      login: null,
      token: null,
      roles: null
    }
  }
}

export { getDefaultState }

export default new Vuex.Store({
  state: {
    futureLoad: {
      id: null
    },
    currentUser: {
      id: localStorage.getItem('id') === 'null' ? null : localStorage.getItem('id'),
      login: null,
      token: localStorage.getItem('access_token') === 'null' ? null : localStorage.getItem('access_token'),
      roles: localStorage.getItem('role') === 'null' ? null : localStorage.getItem('role')
    }
  },
  getters: {
    FUTURE_ID: state => state.futureLoad.id,
    ID: state => state.currentUser.id,
    USER: state => {
      if ((state.currentUser.token === null) && (state.currentUser.login === null)) {
        return null
      } else { return state.currentUser }
    },
    LOGIN: state => state.currentUser.login,
    TOKEN: state => state.currentUser.token,
    ROLES: state => state.currentUser.roles
  },
  mutations: {
    SET_FUTURE_ID: (state, payload) => {
      state.futureLoad.id = payload
    },
    RESET_STATE (state) {
      // acquire initial state
      const s = getDefaultState()
      Object.keys(s).forEach(key => {
        state[key] = s[key]
      })
    },
    SET_LOGIN: (state, payload) => {
      state.currentUser.login = payload
    },
    SET_TOKEN: (state, payload) => {
      state.currentUser.token = payload
    },
    SET_ROLES: (state, payload) => {
      state.currentUser.roles = payload
    },
    SET_ID: (state, payload) => {
      state.currentUser.id = payload
    }
  },
  actions: {
  },
  modules: {
  }
})
