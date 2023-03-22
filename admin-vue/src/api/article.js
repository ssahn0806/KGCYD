import { apiInstance } from "./index.js";

const api = apiInstance();

function articleList(isEvent, success, fail) {
  api.get(`/api/boards?isEvent=${isEvent}`).then(success).catch(fail);
}

function articleDetail(articleNo, success, fail) {
  api.get(`/api/boards/${articleNo}`).then(success).catch(fail);
}

function articleRegist(article, success, fail) {
  api
    .post(`/api/boards`, article, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then(success)
    .catch(fail);
}

function articleModify(article, success, fail) {
  api
    .put(`/api/boards`, article, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then(success)
    .catch(fail);
}

function articleRemove(articleNo, success, fail) {
  api.delete(`/api/boards/${articleNo}`).then(success).catch(fail);
}
export { articleList, articleDetail, articleRegist, articleModify, articleRemove };
