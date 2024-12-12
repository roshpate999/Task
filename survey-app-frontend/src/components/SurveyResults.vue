<template>
  <div class="survey-results">
    <h2>{{ surveyTitle }} - Results</h2>
    <p>Total Responses: {{ totalResponses }}</p>
    <ul>
      <li v-for="result in questionResults" :key="result.question">
        <p>{{ result.question }}</p>
        <p>Average Rating: {{ result.averageRating.toFixed(2) }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    surveyTitle: String,
    totalResponses: Number,
    questionResults: Array,
  },
};
</script>
