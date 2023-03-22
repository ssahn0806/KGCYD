<template>
  <div>
    <div id="product">
      <b-card bg-variant="light">
        <b-card-img
          :src="url"
          alt="등록된이미지가 없습니다"
          style="max-width: 20rem"
          @click="openModal"
        >
        </b-card-img>
        <hr />
        <b-row align-h="center" align-v="center">
          <b-col cols="7">변경할 이미지 첨부</b-col>
          <b-col cols="4"
            ><b-form-file
              accept="image/*"
              :placeholder="ph"
              v-model="file"
              browse-text="이미지 찾기"
            ></b-form-file
          ></b-col>
          <b-col>
            <b-button variant="warning" @click="clearFileInfo">삭제</b-button>
          </b-col>
        </b-row>
        <hr />
        <div class="box">
          <b-row align-h="center" align-v="center">
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col cols="4"><strong>대분류</strong></b-col>
                <b-col
                  ><b-form-select
                    v-model="mainCode"
                    :options="mainOpts"
                    @change="subReset"
                    disabled
                  ></b-form-select></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col cols="4"><strong>중분류</strong></b-col>
                <b-col
                  ><b-form-select
                    v-model="subCode"
                    :options="subOpts"
                    disabled
                  ></b-form-select></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col cols="3"
              ><b-row align-h="center" align-v="center">
                <b-col cols="4"><strong>제품명</strong></b-col>
                <b-col><b-form-input v-model="product.productName"></b-form-input></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col>
              <b-row align-h="center" align-v="center">
                <b-col cols="3"><strong>구성</strong></b-col>
                <b-col
                  ><b-form-input v-model="productInfo.composition">"</b-form-input></b-col
                ></b-row
              >
            </b-col>
            <div class="vl"></div>
            <b-col>
              <b-row align-h="center" align-v="center">
                <b-col cols="3"><strong>용량</strong></b-col>
                <b-col><b-form-input v-model="productInfo.volume"></b-form-input></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col>
              <b-row align-h="center" align-v="center">
                <b-col cols="3"><strong>용법</strong></b-col>
                <b-col><b-form-input v-model="productInfo.usage"></b-form-input></b-col
              ></b-row>
            </b-col>
          </b-row>
        </div>
        <hr />
        <div class="box">
          <b-row align-h="center" align-v="center">
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>판매여부</strong></b-col>
                <b-col><b-checkbox v-model="productInfo.sale" size="lg"></b-checkbox></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>추천상품여부</strong></b-col>
                <b-col
                  ><b-checkbox
                    v-model="isRecommend"
                    size="lg"
                    @change="updateRecommend"
                  ></b-checkbox></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>건강기능식품</strong></b-col>
                <b-col
                  ><b-checkbox v-model="productInfo.health" size="lg"></b-checkbox></b-col></b-row
            ></b-col>
          </b-row>
        </div>
        <hr />
        <div class="box">
          <b-row align-h="center" align-v="center">
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>할인진행여부</strong></b-col>
                <b-col
                  ><b-checkbox v-model="productInfo.discount" size="lg"></b-checkbox></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>할인시작일</strong></b-col>
                <b-col
                  ><b-form-datepicker
                    v-show="productInfo.discount"
                    v-model="productInfo.startdiscount"
                  ></b-form-datepicker></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>할인종료일</strong></b-col>
                <b-col
                  ><b-form-datepicker
                    v-show="productInfo.discount"
                    v-model="productInfo.enddiscount"
                  ></b-form-datepicker></b-col></b-row
            ></b-col>
          </b-row>
        </div>
        <hr />
        <div class="box">
          <b-row align-h="center" align-v="center">
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>정상가격(원)</strong></b-col>
                <b-col
                  ><b-form-input
                    v-model="productInfo.price"
                    size="lg"
                    type="number"
                  ></b-form-input></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>할인율(%)</strong></b-col>
                <b-col
                  ><b-form-input
                    v-model="productInfo.discountRate"
                    size="lg"
                    type="number"
                    :disabled="!productInfo.discount"
                  ></b-form-input></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>할인금액(원)</strong></b-col>
                <b-col
                  ><b-form-input
                    v-model="productInfo.discountPrice"
                    size="lg"
                    type="number"
                    :disabled="!productInfo.discount"
                  ></b-form-input></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>총 할인금액(원)</strong></b-col>
                <b-col
                  ><b-form-input
                    v-model="totalDiscountFormat"
                    disabled
                    size="lg"
                  ></b-form-input></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>판매가격(원)</strong></b-col>
                <b-col
                  ><b-form-input
                    v-model="salePriceFormat"
                    disabled
                    size="lg"
                  ></b-form-input></b-col></b-row
            ></b-col>
          </b-row>
        </div>
        <hr />

        <h5>제품 요약 설명</h5>
        <b-form-textarea v-model="productInfo.comment" max-rows="3"></b-form-textarea>
        <hr />
        <h5>제품 상세 설명</h5>
        <b-form-textarea v-model="productInfo.detail" max-rows="3"></b-form-textarea>
        <hr />
        <h5>섭취시 유의사항</h5>
        <b-form-textarea v-model="productInfo.alert" max-rows="5"></b-form-textarea>
        <hr />

        <b-row class="mr-0 mt-2" align-h="end" align-v="center">
          <b-button class="ml-2" variant="primary" @click="onClickCancel">취소</b-button>
          <b-button class="ml-2" variant="danger" @click.prevent="onClickUpdate">수정</b-button>
        </b-row>
      </b-card>
      <b-modal id="imageViewer" ok-only ok-title="닫기">
        <b-embed type="embed" aspect="4by3" :src="url" allowfullscreen></b-embed>
      </b-modal>
    </div>
  </div>
</template>
<script>
import {
  productDetail,
  productModify,
  productInfoModify,
  mainCodeList,
  subCodeList,
} from "@/api/product";
export default {
  props: {
    productCode: {
      type: String,
      required: true,
    },
  },

  data() {
    return {
      product: {},
      productInfo: {},
      baseUrl: process.env.VUE_APP_BASE_URL,
      mainCode: "",
      subCode: "",
      mainOpts: [],
      subOpts: [],
      url: "",
      file: null,
      ph: "",
    };
  },
  methods: {
    mainLoad() {
      mainCodeList(({ data }) => {
        data.map((item) => this.mainOpts.push({ value: item.mainCode, text: item.mainName }));
      });
    },

    subLoad() {
      subCodeList(this.mainCode, ({ data }) => {
        data.map((item) => this.subOpts.push({ value: item.subCode, text: item.subName }));
      });
    },

    subReset() {
      this.clearOption("sub");
      this.subLoad();
    },
    clearOption(name) {
      if (name == "main") {
        this.mainCode = null;
        this.subCode = null;
        this.mainOpts = [{ value: null, text: "대분류 선택" }];
        this.subOpts = [{ value: null, text: "중분류 선택" }];
      }
      if (name == "sub") {
        this.subCode = null;
        this.subOpts = [{ value: null, text: "중분류 선택" }];
      }
    },
    clearFileInfo() {
      this.file = null;
      this.ph = "";
    },

    onClickUpdate() {
      if (
        this.productInfo.discount &&
        this.productInfo.startdiscount == null &&
        this.productInfo.enddiscount == null
      ) {
        alert("할인 제품인 경우 할인 기간을 등록해야 합니다.");
        return;
      }

      let formData = new FormData();
      formData.append(
        "product",
        new Blob([JSON.stringify(this.product)], { type: "application/json" })
      );
      formData.append("upfile", this.file);
      productModify(formData, () => {
        productInfoModify(this.productInfo, () => {
          this.move(`/products/detail/${this.productCode}`);
        });
      });
    },

    updateRecommend(checked) {
      if (checked) {
        this.productInfo.productLevel = 1;
      } else {
        this.productInfo.productLevel = 0;
      }
    },
    openModal() {
      this.$bvModal.show("imageViewer");
    },
    move(payload) {
      this.$router.push(payload);
    },
    onClickCancel() {
      if (confirm("수정 내용이 반영되지 않습니다")) {
        this.move(`/products/detail/${this.productCode}`);
      }
    },
    getPriceFormat(value) {
      const part = value.toString().split(".");
      part[0] = part[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
      return part.join(".");
    },
    async convertURLtoFile(url) {
      const response = await fetch(url);
      const data = await response.blob();
      const ext = url.split(".").pop(); // url 구조에 맞게 수정할 것
      const filename = url.split("/").pop(); // url 구조에 맞게 수정할 것
      const metadata = { type: `image/${ext}` };
      return new File([data], filename, metadata);
    },
  },

  computed: {
    isRecommend() {
      return this.productInfo.productLevel == 1;
    },
    totalDiscountPrice() {
      return parseInt(
        parseInt(this.productInfo.discountPrice) +
          parseInt(this.productInfo.price * this.productInfo.discountRate * 0.01)
      );
    },
    totalDiscountFormat() {
      return this.getPriceFormat(this.totalDiscountPrice);
    },
    salePrice() {
      return parseInt(this.productInfo.price - this.totalDiscountPrice);
    },
    salePriceFormat() {
      return this.getPriceFormat(this.productInfo.price - this.totalDiscountPrice);
    },
  },
  created() {
    this.url = this.baseUrl + process.env.VUE_APP_IMAGE_URL;

    productDetail(this.productCode, ({ data }) => {
      const { productInfo, ...rest } = data;
      this.product = { ...rest };
      this.productInfo = { ...productInfo };
      this.url += this.product.thumbnail;
      this.convertURLtoFile(this.url).then((res) => {
        this.file = res;
        this.ph = this.file.name;
        this.visible = true;
      });
      this.mainCode = this.productCode.substring(0, 2);
      this.subCode = this.productCode.substring(0, 3);
      this.mainLoad();
      this.subLoad();
    });
  },

  watch: {
    salePrice(newValue) {
      this.productInfo.price = parseInt(this.productInfo.price);
      this.productInfo.discountRate = parseInt(this.productInfo.discountRate);
      this.productInfo.discountPrice = parseInt(this.productInfo.discountPrice);
      this.productInfo.totalPrice = newValue;
    },
    "productInfo.price"(to) {
      if (isNaN(to) || to == "") {
        this.productInfo.price = 0;
      }
    },
    "productInfo.discount"(to) {
      if (!to) {
        if (this.productInfo.enddiscount !== "") this.productInfo.enddiscount = null;
        if (this.productInfo.startdiscount !== "") this.productInfo.startdiscount = null;
        this.productInfo.discountRate = 0;
        this.productInfo.discountPrice = 0;
      }
    },
    "productInfo.discountRate"(to) {
      if (isNaN(to) || to == "") {
        this.productInfo.discountRate = 0;
      }
    },
    "productInfo.discountPrice"(to) {
      if (isNaN(to) || to == "") {
        this.productInfo.discountPrice = 0;
      }
    },
    "product.productName"(to) {
      this.productInfo.productName = to;
    },
    file(to) {
      if (to != null) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.url = e.target.result;
        };
        reader.readAsDataURL(this.file);
      } else {
        this.url = "";
      }
    },
  },
};
</script>

<style scoped>
#product {
  width: 88%;
  margin: 0 auto;
  margin-top: 30px;
  border-radius: 10px;
}
.vl {
  border-left: 1px solid black;
  height: 40px;
}

.box {
  border: 1px solid gray;
  border-radius: 5px;
  padding: 10px;
}
</style>
