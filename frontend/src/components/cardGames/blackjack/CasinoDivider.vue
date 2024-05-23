<template>
    <div class="casino-divider">
      <span class="diamond">♦</span>
      <span class="line"></span>
      <span class="diamond">♦</span>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent } from 'vue';
  
  export default defineComponent({});
  </script>
  
  <style scoped>
  .casino-divider {
    display: flex;
    align-items: center;
    margin: 20px 0;
  }
  
  .diamond {
    color: #e74c3c;
    font-size: 24px;
    margin: 0 10px;
  }
  
  .line {
    flex: 1;
    height: 2px;
    background: linear-gradient(to right, #e74c3c, #ecf0f1, #e74c3c);
    border-radius: 5px;
  }
  </style>
  