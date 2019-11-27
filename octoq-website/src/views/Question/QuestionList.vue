<template>
  <div class="questionlist">
    <QuestionTable :questions="questions" />
  </div>
</template>

<script>
import QuestionTable from '@/components/QuestionTable.vue'

export default {
  components: {
    QuestionTable
  },
  data () {
    return {
      questions: []
    }
  },
  mounted () {
    fetch('http://localhost:9090/api/questions/', {
      method: 'get'
    })
      .then(response => response.json())
      .then((jsonData) => {
        this.questions = jsonData.data
      })
      .catch((err) => console.log(err))
  }
}
</script>
