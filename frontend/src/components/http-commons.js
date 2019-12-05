import axios from "axios";

export const AXIOS = axios.create({
  baseURL: "/openAPI/api"
  // baseURL: "/openAPI/rest/api"
});
