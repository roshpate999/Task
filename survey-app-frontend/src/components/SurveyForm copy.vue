<template>
  <div class="survey-form">
    <h2>{{ surveyTitle }}</h2>
    <form @submit.prevent="submitSurvey">
      <div v-for="(question, index) in questions" :key="question.id" class="question">
        <p>{{ index + 1 }}. {{ question.questionText }}</p>
        <input
          type="number"
          v-model.number="responses[index]"
          :min="1"
          :max="5"
          required
          placeholder="Rate 1-5"
        />
      </div>
      <button type="submit" class="btn-submit">Submit</button>
    </form>
  </div>
</template>

<script>
export default {
  props: {
    surveyId: {
      type: Number,
      required: true,
    },
    surveyTitle: {
      type: String,
      required: true,
    },
    questions: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      responses: Array(this.questions.length).fill(null),
    };
  },
  methods: {
    async submitSurvey() {
      try {
        const payload = {
          responses: this.questions.map((q, index) => ({
            questionId: q.id,
            rating: this.responses[index],
          })),
        };
        await this.$axios.post(`/api/surveys/${this.surveyId}/responses`, payload);
        this.$emit("survey-submitted");
        alert("Survey submitted successfully!");
      } catch (error) {
        console.error(error);
        alert("Failed to submit the survey. Please try again.");
      }
    },
  },
};
</script>
