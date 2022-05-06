<template>
  <div>
    <b-jumbotron>
      <template #header>Mark</template>

      <template slot="lead">
        Це тестовий додаток для проставлення оцінок до журналу викладача.
        Додавати у "Обране" саме цю сторінку.
      </template>

      <hr class="my-4" />
      <p>
        Шановні викладачі, з 01 вересня 2021 р. оцінки студентам необхідно проставляти до електронного журналу
        лише у цілих числах, без десяткових значень.
      </p>
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
