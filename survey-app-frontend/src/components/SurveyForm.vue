<template>
  <div class="survey-form">
    <div class="survey-header">
      <h2 class="survey-title">{{ surveyTitle }}</h2>
      <p class="survey-description">Please rate all questions before submitting the survey.</p>
    </div>
    <form @submit.prevent="submitSurvey">
      <div
        v-for="(question, index) in questions"
        :key="question.id"
        class="question-card"
      >
        <p class="question-text">{{ index + 1 }}. {{ question.questionText }}</p>
        <div class="rating-buttons">
          <button
            v-for="rating in [1, 2, 3, 4, 5]"
            :key="rating"
            type="button"
            class="rating-button"
            :class="{ selected: responses[index] === rating }"
            @click="selectRating(index, rating)"
          >
            {{ rating }}
          </button>
        </div>
        <p
          v-if="showErrors && responses[index] === null"
          class="error-message"
        >
          Please select a rating for this question.
        </p>
      </div>
      <button type="submit" class="submit-button">Submit Survey</button>
    </form>
    <p v-if="showErrors && hasUnansweredQuestions" class="global-error">
      All questions must be answered before submitting the survey.
    </p>
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
      showErrors: false,
    };
  },
  computed: {
    hasUnansweredQuestions() {
      return this.responses.includes(null);
    },
  },
  methods: {
    selectRating(index, rating) {
      this.responses[index] = rating;
    },
    async submitSurvey() {
      if (this.hasUnansweredQuestions) {
        this.showErrors = true;
        return;
      }

      try {
        const payload = {
          responses: this.questions.map((q, index) => ({
            questionId: q.id,
            rating: this.responses[index],
          })),
        };
        await this.$axios.post(`/api/surveys/${this.surveyId}/responses`, payload);
        this.$router.push("/thank-you");
      } catch (error) {
        console.error(error);
        alert("Failed to submit the survey. Please try again.");
      }
    },
  },
};
</script>


<style scoped>
.survey-form {
  max-width: 800px;
  margin: 30px auto;
  padding: 20px;
  background: white;
  border-radius: 20px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
  font-family: 'Inter', sans-serif;
}

.survey-header {
  text-align: center;
  margin-bottom: 30px;
}

.survey-title {
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

.survey-description {
  font-size: 16px;
  color: #666;
}

.question-card {
  margin-bottom: 25px;
  padding: 20px;
  background: #f9f9f9;
  border-radius: 15px;
  transition: transform 0.3s, box-shadow 0.3s;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
}

.rating-buttons {
  display: flex;
  gap: 10px;
  flex-wrap: wrap; 
  justify-content: flex-start;
}

.question-text {
  font-size: 18px;
  font-weight: 500;
  margin-bottom: 15px;
  color: #444;
  text-align: left;
  width: 100%;
}

.rating-button {
  width: 50px;
  height: 50px;
  font-size: 18px;
  font-weight: bold;
  border: none;
  border-radius: 50%;
  background: linear-gradient(145deg, #e0e0e0, #f0f0f0);
  color: #555;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.rating-button:hover {
  background: linear-gradient(145deg, #85d7ff, #009dff);
  color: white;
}

.rating-button.selected {
  background: linear-gradient(145deg, #ff758c, #ff7eb3);
  color: white;
  transform: scale(1.1);
  box-shadow: 0 6px 12px rgba(255, 117, 140, 0.5);
}

.error-message {
  color: #ff4d4f;
  font-size: 14px;
  margin-top: 10px;
}

.global-error {
  color: #ff4d4f;
  font-size: 16px;
  text-align: center;
  margin-top: 20px;
}

.submit-button {
  display: block;
  width: 100%;
  padding: 15px;
  font-size: 18px;
  font-weight: bold;
  color: white;
  background: linear-gradient(to right, #ff758c, #ff7eb3);
  border: none;
  border-radius: 30px;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

.submit-button:hover {
  background: linear-gradient(to right, #ff7eb3, #ff758c);
  transform: scale(1.05);
}

.submit-button:disabled {
  background: #ccc;
  cursor: not-allowed;
}

@media screen and (max-width: 768px) {
  .survey-title {
    font-size: 24px;
  }

  .question-text {
    font-size: 16px;
  }

  .rating-button {
    width: 45px;
    height: 45px;
    font-size: 16px;
  }

  .submit-button {
    padding: 12px;
    font-size: 18px;
  }
}

@media screen and (max-width: 480px) {
  .survey-title {
    font-size: 20px;
  }

  .question-text {
    font-size: 14px;
  }

  .rating-button {
    width: 40px;
    height: 40px;
    font-size: 14px;
  }

  .submit-button {
    padding: 10px;
    font-size: 16px;
  }
}
</style>
