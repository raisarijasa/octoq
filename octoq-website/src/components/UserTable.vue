<template>
  <div>
    <h1>User List</h1>
    <b-button id="btnReg" variant="primary" @click="registerUser()">Register User</b-button>
    <b-table striped hover :items="users" :fields="fields">
      <template v-slot:cell(enabled)="enabled">
        <b-form-checkbox
          id="checkbox-1"
          v-model="enabled.value"
          v-bind="users[enabled.index]"
          name="checkbox-1"
          value="true"
          unchecked-value="false"
          disabled
        >Active</b-form-checkbox>
      </template>
      <template v-slot:cell(roles)="roles">
        <label>{{getRolesName(roles.value)}}</label>
      </template>
      <template v-slot:cell(actions)="actions">
        <!-- <router-link :to="`/users/${users[actions.index].id}`"> -->
          <b-button variant="primary" @click="openUserDetail(users[actions.index])">Detail</b-button>
        <!-- </router-link> -->
        <b-button
          variant="warning"
          @click="updateUserStatus(users[actions.index])"
        >{{getUserStatus(users[actions.index].enabled)}}</b-button>
        <b-button variant="danger" @click="deleteUser(users[actions.index].email)">Delete</b-button>
      </template>
    </b-table>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import userApi from '@/services/UserApi'
export default {
  props: {
    users: Array
  },
  data () {
    return {
      fields: [
        {
          key: 'email',
          sortable: true
        },
        {
          key: 'fullname',
          label: 'Full Name',
          sortable: true
        },
        {
          key: 'enabled',
          label: 'Active',
          sortable: true
        },
        {
          key: 'roles',
          sortable: true
        },
        {
          key: 'actions',
          sortable: false
        }
      ],
      checked: false
    }
  },
  computed: {
    ...mapGetters([
      'userData'
    ])
  },
  methods: {
    ...mapActions(['addUser']),
    getRolesName (roles) {
      var rolesName = ''
      roles.forEach((value) => {
        if (rolesName) {
          rolesName += ', '
        }
        rolesName += value
      })
      return rolesName
    },
    getUserStatus (status) {
      if (status) {
        return 'Disable'
      } else {
        return 'Enable'
      }
    },
    openUserDetail (user) {
      this.$router.push('/users/' + user.email)
      this.addUser(user)
    },
    updateUserStatus (user) {
      var status
      if (user.enabled) {
        status = false
      } else {
        status = true
      }
      userApi.updateUserStatus(user.email, status)
        .then((response) => {
          this.$router.go()
        })
        .catch((err) => {
          console.log(err)
          console.log('exception error')
        })
    },
    deleteUser (email) {
      userApi.deleteUser(email)
        .then((response) => {
          this.$router.go()
        })
        .catch((err) => {
          console.log(err)
          console.log('exception error')
        })
    },
    registerUser () {
      this.$router.push('/users/register')
    }
  }
}
</script>

<style scoped>
.btn {
  margin: 0 5px;
}
#btnReg {
  margin-bottom: 5px;
}
</style>
