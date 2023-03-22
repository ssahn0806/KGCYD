<template>
  <div>
    <div id="product" v-show="visible">
      <b-card bg-variant="light">
        <b-card-img
          :src="url"
          alt="등록된이미지가 없습니다"
          style="max-width: 20rem"
          @click="openModal"
        >
        </b-card-img>
        <hr />
        <div class="box">
          <b-row align-h="center" align-v="center">
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col cols="4"><strong>대분류</strong></b-col>
                <b-col
                  ><b-button variant="danger">{{ product.mainName }}</b-button></b-col
                ></b-row
              ></b-col
            >
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col cols="4"><strong>중분류</strong></b-col>
                <b-col
                  ><b-button variant="warning">{{ product.subName }}</b-button></b-col
                ></b-row
              ></b-col
            >
            <div class="vl"></div>
            <b-col cols="3"
              ><b-row align-h="center" align-v="center">
                <b-col cols="4"><strong>제품명</strong></b-col>
                <b-col
                  ><b-button variant="primary">{{ product.productName }}</b-button></b-col
                ></b-row
              ></b-col
            >
            <div class="vl"></div>
            <b-col>
              <b-row align-h="center" align-v="center">
                <b-col cols="3"><strong>구성</strong></b-col>
                <b-col
                  ><b-button variant="info">{{
                    productInfo.composition ? productInfo.composition : "미입력"
                  }}</b-button></b-col
                ></b-row
              >
            </b-col>
            <div class="vl"></div>
            <b-col>
              <b-row align-h="center" align-v="center">
                <b-col cols="3"><strong>용량</strong></b-col>
                <b-col
                  ><b-button variant="success">{{
                    productInfo.volume ? productInfo.volume : "미입력"
                  }}</b-button></b-col
                ></b-row
              ></b-col
            >
            <div class="vl"></div>
            <b-col cols="2">
              <b-row align-h="center" align-v="center">
                <b-col cols="3"><strong>용법</strong></b-col>
                <b-col
                  ><b-button variant="dark">{{
                    productInfo.usage ? productInfo.usage : "미입력"
                  }}</b-button></b-col
                ></b-row
              >
            </b-col>
          </b-row>
        </div>
        <hr />
        <div class="box">
          <b-row align-h="center" align-v="center">
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>판매여부</strong></b-col>
                <b-col
                  ><b-checkbox
                    v-model="productInfo.sale"
                    disabled
                    size="lg"
                  ></b-checkbox></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>추천상품여부</strong></b-col>
                <b-col
                  ><b-checkbox v-model="isRecommend" disabled size="lg"></b-checkbox></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>건강기능식품</strong></b-col>
                <b-col
                  ><b-checkbox
                    v-model="productInfo.health"
                    disabled
                    size="lg"
                  ></b-checkbox></b-col></b-row
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
                  ><b-checkbox
                    v-model="productInfo.discount"
                    disabled
                    size="lg"
                  ></b-checkbox></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>할인시작일</strong></b-col>
                <b-col
                  ><b-form-datepicker
                    v-model="productInfo.startdiscount"
                    disabled
                  ></b-form-datepicker></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>할인종료일</strong></b-col>
                <b-col
                  ><b-form-datepicker
                    v-model="productInfo.enddiscount"
                    disabled
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
                    v-model="originPrice"
                    disabled
                    size="lg"
                  ></b-form-input></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>할인율(%)</strong></b-col>
                <b-col
                  ><b-form-input
                    v-model="productInfo.discountRate"
                    disabled
                    size="lg"
                  ></b-form-input></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>할인금액(원)</strong></b-col>
                <b-col
                  ><b-form-input
                    v-model="discountPrice"
                    disabled
                    size="lg"
                  ></b-form-input></b-col></b-row
            ></b-col>
            <div class="vl"></div>
            <b-col
              ><b-row align-h="center" align-v="center">
                <b-col><strong>총 할인금액(원)</strong></b-col>
                <b-col
                  ><b-form-input
                    v-model="totalDiscountPrice"
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
                    v-model="salePrice"
                    disabled
                    size="lg"
                  ></b-form-input></b-col></b-row
            ></b-col>
          </b-row>
        </div>
        <hr />

        <h5>제품 요약 설명</h5>
        <b-form-textarea v-model="productInfo.comment" max-rows="3" readonly></b-form-textarea>
        <hr />
        <h5>제품 상세 설명</h5>
        <b-form-textarea v-model="productInfo.detail" max-rows="3" readonly></b-form-textarea>
        <hr />
        <h5>섭취시 유의사항</h5>
        <b-form-textarea v-model="productInfo.alert" max-rows="5" readonly></b-form-textarea>
        <hr />
        <b-row>
          <b-col>이미지 다운로드</b-col>
          <b-col
            ><a :href="`${this.baseUrl}/image/download?fileName=${product.thumbnail}`">{{
              product.thumbnail
            }}</a></b-col
          >
        </b-row>
        <hr />
        <b-row class="mr-0 mt-2" align-h="end" align-v="center">
          <b-button class="ml-2" variant="danger" @click.prevent="onClickRemove" v-show="userInfo"
            >삭제</b-button
          >
          <b-button
            class="ml-2"
            variant="success"
            @click="move(`/products/modify/${productCode}`)"
            v-show="userInfo"
            >수정</b-button
          >
          <b-button class="ml-2" variant="primary" @click="move(`/products`)">목록</b-button>
        </b-row>
      </b-card>
      <b-modal id="imageViewer" ok-only ok-title="닫기">
        <b-embed type="embed" aspect="4by3" :src="url" allowfullscreen></b-embed>
      </b-modal>
    </div>
  </div>
</template>
<script>
import { productDetail, productRemove, productInfoRemove } from "@/api/product";
import { mapState } from "vuex";
const userStore = "UserStore";
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
      url: "",
      visible: false,
    };
  },
  methods: {
    openModal() {
      this.$bvModal.show("imageViewer");
    },
    move(payload) {
      this.$router.push(payload);
    },
    onClickRemove() {
      if (confirm("삭제하시겠습니까?")) {
        productInfoRemove(this.product.productCode, () => {
          productRemove(this.product.productCode, () => {
            this.move(`/products`);
          });
        });
      }
    },
    getPriceFormat(value) {
      // return value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
      const part = value.toString().split(".");
      part[0] = part[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
      return part.join(".");
    },
  },

  computed: {
    ...mapState(userStore, ["userInfo"]),
    isRecommend() {
      return this.productInfo.productLevel == 1;
    },
    originPrice() {
      return this.getPriceFormat(this.productInfo.price);
    },
    discountPrice() {
      return this.getPriceFormat(this.productInfo.discountPrice);
    },
    totalDiscountPrice() {
      return this.getPriceFormat(this.productInfo.price - this.productInfo.totalPrice);
    },
    salePrice() {
      return this.getPriceFormat(this.productInfo.totalPrice);
    },
  },
  created() {
    this.url = this.baseUrl + process.env.VUE_APP_IMAGE_URL;

    productDetail(this.productCode, ({ data }) => {
      const { productInfo, ...rest } = data;
      this.product = rest;
      this.productInfo = productInfo;
      this.url += this.product.thumbnail;
      this.visible = true;
    });
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
