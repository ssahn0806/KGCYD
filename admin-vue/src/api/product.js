import { apiInstance } from "./index.js";

const api = apiInstance();

function productList(subCode, success, fail) {
  api.get(`/api/products/info?subCode=${subCode}`).then(success).catch(fail);
}

function productListByName(searchValue, success, fail) {
  api.get(`/api/products/search/name/${searchValue}/info`).then(success).catch(fail);
}

function productDetail(productCode, success, fail) {
  api.get(`/api/products/info/${productCode}`).then(success).catch(fail);
}

function productModify(product, success, fail) {
  api
    .put(`/api/products`, product, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then(success)
    .catch(fail);
}

function productInfoModify(info, success, fail) {
  api.put(`/api/products/info`, info).then(success).catch(fail);
}

function productRegist(product, success, fail) {
  api
    .post(`/api/products`, product, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then(success)
    .catch(fail);
}

function productInfoRegist(info, success, fail) {
  api.post(`/api/products/info`, info).then(success).catch(fail);
}
function productRemove(productCode, success, fail) {
  api.delete(`/api/products/${productCode}`).then(success).catch(fail);
}

function productInfoRemove(productCode, success, fail) {
  api.delete(`/api/products/${productCode}/info`).then(success).catch(fail);
}

function mainCodeList(success, fail) {
  api.get(`/api/products/mains`).then(success).catch(fail);
}

function subCodeList(mainCode, success, fail) {
  api.get(`/api/products/subs?mainCode=${mainCode}`).then(success).catch(fail);
}
export {
  productList,
  productListByName,
  productDetail,
  productModify,
  productInfoModify,
  productRegist,
  productInfoRegist,
  productRemove,
  productInfoRemove,
  mainCodeList,
  subCodeList,
};
