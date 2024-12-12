<template>
  <div class="survey-view">
    <survey-form
      v-if="survey"
      :survey-id="survey.id"
      :survey-title="survey.title"
      :questions="survey.questions"
      @survey-submitted="fetchResults"
    />
    <survey-results
      v-if="showResults"
      :survey-title="results?.surveyTitle"
      :total-responses="results?.totalResponses"
      :question-results="results?.questionResults"
    />
    <p v-else>Loading survey...</p>
  </div>
</template>

<script>
import SurveyForm from "@/components/SurveyForm.vue";
import SurveyResults from "@/components/SurveyResults.vue";

export default {
  components: { SurveyForm, SurveyResults },
  data() {
    return {
      survey: null,
      results: null,
      showResults: false,
    };
  },
  async created() {
    await this.fetchSurvey();
  },
  methods: {
    async fetchSurvey() {
      try {
        const response = await this.$axios.get(`/api/surveys/${this.$route.params.id}`);
        this.survey = response.data;
      } catch (error) {
        console.error("Failed to fetch survey:", error);
      }
    },
    async fetchResults() {
      try {
        const response = await this.$axios.get(`/api/surveys/${this.$route.params.id}/results`);
        this.results = response.data;
        this.showResults = true;
      } catch (error) {
        console.error("Failed to fetch results:", error);
      }
    },
  },
};
</script>
