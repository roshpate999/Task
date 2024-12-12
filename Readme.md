Backend
Overview
The backend is built with Spring Boot and provides RESTful APIs for managing surveys, submitting responses, and fetching results.
Key Features
•	Create surveys with multiple questions.
•	Submit survey responses.
•	Fetch aggregated survey results.
•	Fetch all individual survey responses.
Technologies Used
•	Spring Boot
•	Hibernate/JPA
•	MySQL
•	Lombok
•	Maven

The backend will be accessible at http://localhost:8080.
Key Endpoints
1.	Create Survey
o	POST /api/surveys/createSurvey
o	Creates a new survey with a title and questions.
2.	Fetch Survey Questions
o	GET /api/surveys/{id}
o	Fetches questions for the specified survey.
3.	Submit Survey Response
o	POST /api/surveys/{id}/responses
o	Submits responses for a survey.
4.	Fetch Aggregated Results
o	GET /api/surveys/{id}/results
o	Fetches average ratings for each question.
5.	Fetch All Responses
o	GET /api/surveys/{id}/allresponses
o	Fetches detailed responses for the survey.
________________________________________
Frontend
Overview
The frontend is built with Vue.js and provides a user interface for filling surveys and interacting with the backend APIs.
Key Features
•	Display survey questions and allow users to submit responses.
•	Display aggregated survey results.
•	Display all individual responses for a survey.

Frontend Endpoints
1.	Fill Survey
o	http://localhost:3000/survey/{id}
o	Displays survey questions and allows users to submit responses.
2.	View Aggregated Results
o	http://localhost:3000/api/surveys/{id}/results
o	Displays average ratings for each question in JSON format.
3.	View All Responses
o	http://localhost:3000/api/surveys/{id}/allresponses
o	Displays all individual responses in JSON format.
________________________________________
Postman Collection
A Postman collection is included for testing backend endpoints. 


