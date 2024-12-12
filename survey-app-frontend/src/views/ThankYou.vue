<template>
    <div class="thank-you">
      <h1>Thank You!</h1>
      <p>We appreciate your response.</p>
      <router-link to="/" class="home-link">Go back to Home</router-link>
    </div>
  </template>
  
  <script>
  export default {
    name: "ThankYou",
  };
  </script>
  
  <style scoped>
  .thank-you {
    text-align: center;
    margin: 50px auto;
    padding: 20px;
    max-width: 500px;
    background: white;
    border-radius: 15px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    font-family: 'Inter', sans-serif;
  }
  
  h1 {
    font-size: 36px;
    font-weight: bold;
    color: #333;
    margin-bottom: 20px;
  }
  
  p {
    font-size: 18px;
    color: #555;
    margin-bottom: 30px;
  }
  
  .home-link {
    display: inline-block;
    padding: 10px 20px;
    font-size: 16px;
    font-weight: bold;
    color: white;
    background: linear-gradient(135deg, #ff758c, #ff7eb3);
    border: none;
    border-radius: 8px;
    text-decoration: none;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    transition: background 0.3s ease-in-out;
  }
  
  .home-link:hover {
    background: linear-gradient(135deg, #ff7eb3, #ff758c);
  }
  </style>
  