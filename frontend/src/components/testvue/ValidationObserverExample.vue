<template>
    <!--     https://habr.com/ru/company/constanta/blog/444900/
    ValidationObserver передает дефолтному слоту все флаги валидаций.
    В примере используется только флаг valid для стилизации кнопки отправки формы
  -->
    <ValidationObserver v-slot="{ valid }">
        <form @submit.prevent="doAction()">
            <!--
              ValidationProvider добавляет обработчики на все поля ввода внутри слота
              и передает слоту все ошибки и флаги валидации

              name - атрибут, который будет отображаться в ошибках
            -->
            <ValidationProvider
                    name="серия и номер паспорта"
                    :rules="{ required: true, regex: /^\d{4} \d{6}$/ }"
                    v-slot="{ errors }"
            >
                <input type="text" v-model="passportData">
                <span v-if="errors[0]">
          {{ errors[0] }}
        </span>
            </ValidationProvider>

            <!--
              По дефолту валидация данных сработает сразу после события input,
              для валидации после blur или change используйте mode="lazy" или mode="eager"
            -->
            <ValidationProvider
                    name="дата выдачи паспорта"
                    :rules="{ required: true, date_format: 'dd.MM.yyyy' }"
                    v-slot="{ errors }"
                    mode="lazy"
            >
                <input type="text" v-model="passportDate">
                <span v-if="errors[0]">
          {{ errors[0] }}
        </span>
            </ValidationProvider>

            <!--
              Если хотите отобразить сразу все ошибки поля,
              то используйте входной параметр :bails="false"
            -->
            <ValidationProvider
                    name="имя"
                    :rules="{ required: true, alpha: true, max: 10 }"
                    v-slot="{ errors }"
                    :bails="false"
            >
                <input type="text" v-model="name">
                <span
                        v-for="error in errors" :key="error"
                >
          {{ error }}
        </span>
            </ValidationProvider>

            <button type="submit" :disabled="!valid">
                Отправить форму
            </button>
        </form>
    </ValidationObserver>
</template>

<script>
    import {ValidationObserver, ValidationProvider} from 'vee-validate';

    export default {
        name: "ValidationObserverExample",
        components: {
            ValidationProvider,
            ValidationObserver,
        },

        data() {
            return {
                passportData: null,
                passportDate: null,
                name: null,
            };
        },
    };
</script>

<style scoped>

</style>