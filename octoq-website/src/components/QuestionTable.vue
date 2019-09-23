<template>
  <div>
    <h1>Question List</h1>
    <b-table striped hover :items="questions" :fields="fields">
    </b-table>
  </div>
</template>

<script>
export default {
  props: {
    questions: Array
  },
  data () {
    return {
      fields: [
        {
          key: 'id',
          sortable: true
        },
        {
          key: 'userId',
          label: 'User Id',
          sortable: true
        },
        {
          key: 'title',
          sortable: true
        },
        {
          key: 'description',
          sortable: true
        }
      ]
    }
  }
}
</script>

<style scoped>
.btn {
  margin: 0 5px;
}
</style>
