import { createRouter, createWebHistory } from "vue-router";
import SurveyView from "@/views/SurveyView.vue";
import ThankYou from "@/views/ThankYou.vue";

const routes = [
  { path: "/", redirect: "/survey/1" },
  { path: "/survey/:id", name: "SurveyView", component: SurveyView },
  { path: "/thank-you", name: "ThankYou", component: ThankYou },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
