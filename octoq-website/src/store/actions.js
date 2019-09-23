export default {
  addUser: ({ commit }, payload) => {
    commit('appendUser', payload)
  }
}
