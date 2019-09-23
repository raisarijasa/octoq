<template>
  <div class="user">
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <!-- <b-form-group id="ig-id" label="Id" label-for="ig-password">
        <b-form-input id="i-id" v-model="user.id"></b-form-input>
      </b-form-group>-->
      <b-form-group id="ig-email" label="Email address" label-for="ig-email">
        <b-form-input
          id="i-email"
          v-model="user.email"
          type="email"
          required
          placeholder="Enter email"
        >{{user.email}}</b-form-input>
      </b-form-group>

      <b-form-group id="ig-password" label="Password" label-for="ig-password">
        <b-form-input id="i-password" v-model="user.password" required placeholder="Enter password"></b-form-input>
      </b-form-group>

      <b-form-group id="ig-fullname" label="Full Name" label-for="ig-fullname">
        <b-form-input
          id="i-fullname"
          v-model="user.fullname"
          required
          placeholder="Enter Full Name"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="ig-roles" label="Roles">
        <!-- <b-form-checkbox-group v-model="user.roles" id="cb-roles"> -->
        <b-form-checkbox v-model="cbAdmin">Admin</b-form-checkbox>
        <b-form-checkbox v-model="cbUser">User</b-form-checkbox>
        <!-- </b-form-checkbox-group> -->
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import userApi from '@/services/UserApi'
export default {
  data () {
    return {
      user: {
        id: null,
        email: '',
        fullname: '',
        roles: []
      },
      foods: [{ text: 'Select One', value: null }, 'Carrots', 'Beans', 'Tomatoes', 'Corn'],
      show: true,
      cbAdmin: false,
      cbUser: true
    }
  },
  methods: {
    onSubmit (evt) {
      evt.preventDefault()
      this.user.roles = []
      if (this.cbAdmin) {
        this.user.roles.push('ADMIN')
      }
      if (this.cbUser) {
        this.user.roles.push('USER')
      }
      userApi.createUser(this.user)
        .then((response) => {
          console.log('success')
          this.$router.go(-1)
        })
        .catch((err) => {
          console.log(err)
          console.log('exception error')
        })
    },
    onReset (evt) {
      evt.preventDefault()
      // Reset our form values
      this.user = {}
      this.cbAdmin = false
      this.cbUser = true
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }
  },
  computed: {
    ...mapState([
      'userData'
    ])
  },
  mounted () {
    var user = this[this.$route.params.email]
    if (!user) {
      user = {
        id: null,
        email: '',
        fullname: '',
        roles: []
      }
    }
    console.log(user)
    this.user = user
  }
}
</script>

<style scoped>
.btn {
  margin: 0 5px;
}
</style>
