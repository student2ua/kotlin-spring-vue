import axios from "axios";

export const AXIOS = axios.create({
  // baseURL: "/openAPI/api"
  // baseURL: "/openAPI/rest/api"
  baseURL:
    process.env.NODE_ENV === "production" ? "/openAPI/rest/api" : "/openAPI/api"
});
