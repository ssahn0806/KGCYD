"use strict";(self["webpackChunkadmin_vue"]=self["webpackChunkadmin_vue"]||[]).push([[983],{4983:function(t,o,r){r.r(o),r.d(o,{default:function(){return b}});var e=function(){var t=this,o=t._self._c;return o("div",[o("div",{directives:[{name:"show",rawName:"v-show",value:t.visible,expression:"visible"}],attrs:{id:"product"}},[o("b-card",{attrs:{"bg-variant":"light"}},[o("b-card-img",{staticStyle:{"max-width":"20rem"},attrs:{src:t.url,alt:"등록된이미지가 없습니다"},on:{click:t.openModal}}),o("hr"),o("div",{staticClass:"box"},[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"4"}},[o("strong",[t._v("대분류")])]),o("b-col",[o("b-button",{attrs:{variant:"danger"}},[t._v(t._s(t.product.mainName))])],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"4"}},[o("strong",[t._v("중분류")])]),o("b-col",[o("b-button",{attrs:{variant:"warning"}},[t._v(t._s(t.product.subName))])],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",{attrs:{cols:"3"}},[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"4"}},[o("strong",[t._v("제품명")])]),o("b-col",[o("b-button",{attrs:{variant:"primary"}},[t._v(t._s(t.product.productName))])],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"3"}},[o("strong",[t._v("구성")])]),o("b-col",[o("b-button",{attrs:{variant:"info"}},[t._v(t._s(t.productInfo.composition?t.productInfo.composition:"미입력"))])],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"3"}},[o("strong",[t._v("용량")])]),o("b-col",[o("b-button",{attrs:{variant:"success"}},[t._v(t._s(t.productInfo.volume?t.productInfo.volume:"미입력"))])],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",{attrs:{cols:"2"}},[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"3"}},[o("strong",[t._v("용법")])]),o("b-col",[o("b-button",{attrs:{variant:"dark"}},[t._v(t._s(t.productInfo.usage?t.productInfo.usage:"미입력"))])],1)],1)],1)],1)],1),o("hr"),o("div",{staticClass:"box"},[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("판매여부")])]),o("b-col",[o("b-checkbox",{attrs:{disabled:"",size:"lg"},model:{value:t.productInfo.sale,callback:function(o){t.$set(t.productInfo,"sale",o)},expression:"productInfo.sale"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("추천상품여부")])]),o("b-col",[o("b-checkbox",{attrs:{disabled:"",size:"lg"},model:{value:t.isRecommend,callback:function(o){t.isRecommend=o},expression:"isRecommend"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("건강기능식품")])]),o("b-col",[o("b-checkbox",{attrs:{disabled:"",size:"lg"},model:{value:t.productInfo.health,callback:function(o){t.$set(t.productInfo,"health",o)},expression:"productInfo.health"}})],1)],1)],1)],1)],1),o("hr"),o("div",{staticClass:"box"},[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("할인진행여부")])]),o("b-col",[o("b-checkbox",{attrs:{disabled:"",size:"lg"},model:{value:t.productInfo.discount,callback:function(o){t.$set(t.productInfo,"discount",o)},expression:"productInfo.discount"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("할인시작일")])]),o("b-col",[o("b-form-datepicker",{attrs:{disabled:""},model:{value:t.productInfo.startdiscount,callback:function(o){t.$set(t.productInfo,"startdiscount",o)},expression:"productInfo.startdiscount"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("할인종료일")])]),o("b-col",[o("b-form-datepicker",{attrs:{disabled:""},model:{value:t.productInfo.enddiscount,callback:function(o){t.$set(t.productInfo,"enddiscount",o)},expression:"productInfo.enddiscount"}})],1)],1)],1)],1)],1),o("hr"),o("div",{staticClass:"box"},[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("정상가격(원)")])]),o("b-col",[o("b-form-input",{attrs:{disabled:"",size:"lg"},model:{value:t.originPrice,callback:function(o){t.originPrice=o},expression:"originPrice"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("할인율(%)")])]),o("b-col",[o("b-form-input",{attrs:{disabled:"",size:"lg"},model:{value:t.productInfo.discountRate,callback:function(o){t.$set(t.productInfo,"discountRate",o)},expression:"productInfo.discountRate"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("할인금액(원)")])]),o("b-col",[o("b-form-input",{attrs:{disabled:"",size:"lg"},model:{value:t.discountPrice,callback:function(o){t.discountPrice=o},expression:"discountPrice"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("총 할인금액(원)")])]),o("b-col",[o("b-form-input",{attrs:{disabled:"",size:"lg"},model:{value:t.totalDiscountPrice,callback:function(o){t.totalDiscountPrice=o},expression:"totalDiscountPrice"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("판매가격(원)")])]),o("b-col",[o("b-form-input",{attrs:{disabled:"",size:"lg"},model:{value:t.salePrice,callback:function(o){t.salePrice=o},expression:"salePrice"}})],1)],1)],1)],1)],1),o("hr"),o("h5",[t._v("제품 요약 설명")]),o("b-form-textarea",{attrs:{"max-rows":"3",readonly:""},model:{value:t.productInfo.comment,callback:function(o){t.$set(t.productInfo,"comment",o)},expression:"productInfo.comment"}}),o("hr"),o("h5",[t._v("제품 상세 설명")]),o("b-form-textarea",{attrs:{"max-rows":"3",readonly:""},model:{value:t.productInfo.detail,callback:function(o){t.$set(t.productInfo,"detail",o)},expression:"productInfo.detail"}}),o("hr"),o("h5",[t._v("섭취시 유의사항")]),o("b-form-textarea",{attrs:{"max-rows":"5",readonly:""},model:{value:t.productInfo.alert,callback:function(o){t.$set(t.productInfo,"alert",o)},expression:"productInfo.alert"}}),o("hr"),o("b-row",[o("b-col",[t._v("이미지 다운로드")]),o("b-col",[o("a",{attrs:{href:`${this.baseUrl}/image/download?fileName=${t.product.thumbnail}`}},[t._v(t._s(t.product.thumbnail))])])],1),o("hr"),o("b-row",{staticClass:"mr-0 mt-2",attrs:{"align-h":"end","align-v":"center"}},[o("b-button",{directives:[{name:"show",rawName:"v-show",value:t.userInfo,expression:"userInfo"}],staticClass:"ml-2",attrs:{variant:"danger"},on:{click:function(o){return o.preventDefault(),t.onClickRemove.apply(null,arguments)}}},[t._v("삭제")]),o("b-button",{directives:[{name:"show",rawName:"v-show",value:t.userInfo,expression:"userInfo"}],staticClass:"ml-2",attrs:{variant:"success"},on:{click:function(o){return t.move(`/products/modify/${t.productCode}`)}}},[t._v("수정")]),o("b-button",{staticClass:"ml-2",attrs:{variant:"primary"},on:{click:function(o){return t.move("/products")}}},[t._v("목록")])],1)],1),o("b-modal",{attrs:{id:"imageViewer","ok-only":"","ok-title":"닫기"}},[o("b-embed",{attrs:{type:"embed",aspect:"4by3",src:t.url,allowfullscreen:""}})],1)],1)])},c=[],s=(r(7658),r(4543)),a=r(408);const n="UserStore";var i={props:{productCode:{type:String,required:!0}},data(){return{product:{},productInfo:{},baseUrl:"http://devsu.iptime.org",url:"",visible:!1}},methods:{openModal(){this.$bvModal.show("imageViewer")},move(t){this.$router.push(t)},onClickRemove(){confirm("삭제하시겠습니까?")&&(0,s.I$)(this.product.productCode,(()=>{(0,s.Yn)(this.product.productCode,(()=>{this.move("/products")}))}))},getPriceFormat(t){const o=t.toString().split(".");return o[0]=o[0].replace(/\B(?=(\d{3})+(?!\d))/g,","),o.join(".")}},computed:{...(0,a.rn)(n,["userInfo"]),isRecommend(){return 1==this.productInfo.productLevel},originPrice(){return this.getPriceFormat(this.productInfo.price)},discountPrice(){return this.getPriceFormat(this.productInfo.discountPrice)},totalDiscountPrice(){return this.getPriceFormat(this.productInfo.price-this.productInfo.totalPrice)},salePrice(){return this.getPriceFormat(this.productInfo.totalPrice)}},created(){this.url=this.baseUrl+"/image/view/",(0,s.Z$)(this.productCode,(({data:t})=>{const{productInfo:o,...r}=t;this.product=r,this.productInfo=o,this.url+=this.product.thumbnail,this.visible=!0}))}},l=i,d=r(1001),u=(0,d.Z)(l,e,c,!1,null,"17801fd7",null),b=u.exports}}]);
//# sourceMappingURL=983.0b0b07e0.js.map