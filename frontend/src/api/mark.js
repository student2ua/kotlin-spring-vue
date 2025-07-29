import AXIOS from "../components/http-commons";
// const BASE_URL = "/mark/rest/v199";

export const MarkAPI = {
  getTokenHeader(store) {
    return {
      headers: {
        Authorization: "Bearer " + store.getters.getToken,
      },
    };
  },

  getTable(store, subjectId, lessonTypeId, psgId) {
    const url = `/mark/rest/v199/subjects/${subjectId}/lessontypes/${lessonTypeId}/psgs/${psgId}/handsontable`;
    return AXIOS.get(url, this.getTokenHeader(store));
  },

  getSubjects(store) {
    return AXIOS.get(`/mark/rest/v199/subjects`, this.getTokenHeader(store));
  },

  getLessonTypes(store, subjectId) {
    return AXIOS.get(
      `/mark/rest/v199/subjects/${subjectId}/lessontypes`,
      this.getTokenHeader(store)
    );
  },

  getPsgs(store, subjectId, lessonTypeId) {
    return AXIOS.get(
      `/mark/rest/v199/subjects/${subjectId}/lessontypes/${lessonTypeId}/psgs`,
      this.getTokenHeader(store)
    );
  },

  saveCell(store, subjectId, lessonTypeId, psgId, payload) {
    return AXIOS.post(
      `/mark/rest/v199/subjects/${subjectId}/lessontypes/${lessonTypeId}/psgs/${psgId}/handsontable`,
      payload,
      this.getTokenHeader(store)
    );
  },
};
