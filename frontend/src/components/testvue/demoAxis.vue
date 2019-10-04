<template>
    <div>
        <ul v-if="posts && posts.length">
            <li v-for="(index,post) of posts" :key="index">
                <p><strong>{{post.title}}}</strong></p>
            </li>
        </ul>

        <ul v-if="errors && errors.length">
            <li v-for="(index, error) of errors" :key="index">{{error.message}}}</li>
        </ul>
    </div>
</template>


<script>

    import { AXIOS2 } from "axios";
    export default {
        name: "demoAxis",
        data: () => ({
            posts: [],
            errors: []
        }),
        created() {
            AXIOS2.get('http://jsonplaceholder.typicode.com/posts')
                .then(response => {
                    this.posts = response.data
                })
                .catch(e => {
                    this.errors.push(e)
                })
        }
    }
</script>

<style scoped>

</style>