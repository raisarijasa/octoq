import axios from 'axios'
var API_DEFAULT = 'http://localhost:9090/api/users'

export default {
  async getAllUser () {
    const response = await axios.get(API_DEFAULT)
    return response.data
  },
  async updateUserStatus (email, status) {
    const response = await axios.put(API_DEFAULT + '/activation/' + email + '/' + status)
    return response.data
  },
  async deleteUser (email) {
    const response = await axios.delete(API_DEFAULT + '/' + email)
    return response.data
  },
  async createUser (user) {
    const response = await axios.post(API_DEFAULT,
      JSON.stringify(user), {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    )
    return response.data
  }
}
