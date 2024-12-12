<template>
    <div class="home">
      <h1>Welcome to the Survey App</h1>
      <ul>
        <li v-for="survey in surveys" :key="survey.id">
          <router-link :to="{ name: 'SurveyView', params: { id: survey.id } }">
            {{ survey.title }}
          </router-link>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        surveys: [],
      };
    },
    async created() {
      try {
        const response = await this.$axios.get("/api/surveys");
        this.surveys = response.data;
      } catch (error) {
        console.error("Failed to fetch surveys:", error);
      }
    },
  };
  </script>
  
  <style scoped>
  .home {
    text-align: center;
  }
  </style>
  