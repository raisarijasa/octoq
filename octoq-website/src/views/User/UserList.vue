<template>
  <div class="userslist">
    <UserTable :users="users" />
  </div>
</template>

<script>
import userApi from '@/services/UserApi'
import UserTable from '@/components/UserTable.vue'

export default {
  components: {
    UserTable
  },
  data () {
    return {
      users: []
    }
  },
  mounted () {
    userApi.getAllUser()
      .then(response => {
        this.users = response.data
      })
      .catch(error => {
        console.log(error)
        console.log('exception error')
      })
  }
}
</script>
