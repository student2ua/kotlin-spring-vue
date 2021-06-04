<template>
  <div>
    <b-jumbotron>
      <template #header>Mark</template>

      <template slot="lead">
        Це тестовий додаток для проставлення оцінок до журналу викладача.
        Додавати у "Обране" саме цю сторінку.
        Увага! Внесення оцінок з 01.06.2021 припинено до початку нового семестру.
      </template>

      <hr class="my-4" />

      <p v-if="!this.$store.getters.isAuthenticated">
        Login and start
      </p>

      <router-link to="/login" v-if="!this.$store.getters.isAuthenticated">
        <b-button variant="primary">Login</b-button>
      </router-link>
    </b-jumbotron>
  </div>
</template>

<script>
  export default {
  name: "Home"
};
</script>

<style></style>
