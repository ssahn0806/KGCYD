"use strict";(self["webpackChunkadmin_vue"]=self["webpackChunkadmin_vue"]||[]).push([[819],{819:function(t,o,e){e.r(o),e.d(o,{default:function(){return u}});var s=function(){var t=this,o=t._self._c;return o("div",[o("div",{attrs:{id:"product"}},[o("b-card",{attrs:{"bg-variant":"light"}},[o("b-card-img",{staticStyle:{"max-width":"20rem"},attrs:{src:t.url,alt:"등록된이미지가 없습니다"},on:{click:t.openModal}}),o("hr"),o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"7"}},[t._v("변경할 이미지 첨부")]),o("b-col",{attrs:{cols:"4"}},[o("b-form-file",{attrs:{accept:"image/*",placeholder:t.ph,"browse-text":"이미지 찾기"},model:{value:t.file,callback:function(o){t.file=o},expression:"file"}})],1),o("b-col",[o("b-button",{attrs:{variant:"warning"},on:{click:t.clearFileInfo}},[t._v("삭제")])],1)],1),o("hr"),o("div",{staticClass:"box"},[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"4"}},[o("strong",[t._v("대분류")])]),o("b-col",[o("b-form-select",{attrs:{options:t.mainOpts,disabled:""},on:{change:t.subReset},model:{value:t.mainCode,callback:function(o){t.mainCode=o},expression:"mainCode"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"4"}},[o("strong",[t._v("중분류")])]),o("b-col",[o("b-form-select",{attrs:{options:t.subOpts,disabled:""},model:{value:t.subCode,callback:function(o){t.subCode=o},expression:"subCode"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",{attrs:{cols:"3"}},[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"4"}},[o("strong",[t._v("제품명")])]),o("b-col",[o("b-form-input",{model:{value:t.product.productName,callback:function(o){t.$set(t.product,"productName",o)},expression:"product.productName"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"3"}},[o("strong",[t._v("구성")])]),o("b-col",[o("b-form-input",{model:{value:t.productInfo.composition,callback:function(o){t.$set(t.productInfo,"composition",o)},expression:"productInfo.composition"}},[t._v('"')])],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"3"}},[o("strong",[t._v("용량")])]),o("b-col",[o("b-form-input",{model:{value:t.productInfo.volume,callback:function(o){t.$set(t.productInfo,"volume",o)},expression:"productInfo.volume"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",{attrs:{cols:"3"}},[o("strong",[t._v("용법")])]),o("b-col",[o("b-form-input",{model:{value:t.productInfo.usage,callback:function(o){t.$set(t.productInfo,"usage",o)},expression:"productInfo.usage"}})],1)],1)],1)],1)],1),o("hr"),o("div",{staticClass:"box"},[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("판매여부")])]),o("b-col",[o("b-checkbox",{attrs:{size:"lg"},model:{value:t.productInfo.sale,callback:function(o){t.$set(t.productInfo,"sale",o)},expression:"productInfo.sale"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("추천상품여부")])]),o("b-col",[o("b-checkbox",{attrs:{size:"lg"},on:{change:t.updateRecommend},model:{value:t.isRecommend,callback:function(o){t.isRecommend=o},expression:"isRecommend"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("건강기능식품")])]),o("b-col",[o("b-checkbox",{attrs:{size:"lg"},model:{value:t.productInfo.health,callback:function(o){t.$set(t.productInfo,"health",o)},expression:"productInfo.health"}})],1)],1)],1)],1)],1),o("hr"),o("div",{staticClass:"box"},[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("할인진행여부")])]),o("b-col",[o("b-checkbox",{attrs:{size:"lg"},model:{value:t.productInfo.discount,callback:function(o){t.$set(t.productInfo,"discount",o)},expression:"productInfo.discount"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("할인시작일")])]),o("b-col",[o("b-form-datepicker",{directives:[{name:"show",rawName:"v-show",value:t.productInfo.discount,expression:"productInfo.discount"}],model:{value:t.productInfo.startdiscount,callback:function(o){t.$set(t.productInfo,"startdiscount",o)},expression:"productInfo.startdiscount"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("할인종료일")])]),o("b-col",[o("b-form-datepicker",{directives:[{name:"show",rawName:"v-show",value:t.productInfo.discount,expression:"productInfo.discount"}],model:{value:t.productInfo.enddiscount,callback:function(o){t.$set(t.productInfo,"enddiscount",o)},expression:"productInfo.enddiscount"}})],1)],1)],1)],1)],1),o("hr"),o("div",{staticClass:"box"},[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("정상가격(원)")])]),o("b-col",[o("b-form-input",{attrs:{size:"lg",type:"number"},model:{value:t.productInfo.price,callback:function(o){t.$set(t.productInfo,"price",o)},expression:"productInfo.price"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("할인율(%)")])]),o("b-col",[o("b-form-input",{attrs:{size:"lg",type:"number",disabled:!t.productInfo.discount},model:{value:t.productInfo.discountRate,callback:function(o){t.$set(t.productInfo,"discountRate",o)},expression:"productInfo.discountRate"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("할인금액(원)")])]),o("b-col",[o("b-form-input",{attrs:{size:"lg",type:"number",disabled:!t.productInfo.discount},model:{value:t.productInfo.discountPrice,callback:function(o){t.$set(t.productInfo,"discountPrice",o)},expression:"productInfo.discountPrice"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("총 할인금액(원)")])]),o("b-col",[o("b-form-input",{attrs:{disabled:"",size:"lg"},model:{value:t.totalDiscountFormat,callback:function(o){t.totalDiscountFormat=o},expression:"totalDiscountFormat"}})],1)],1)],1),o("div",{staticClass:"vl"}),o("b-col",[o("b-row",{attrs:{"align-h":"center","align-v":"center"}},[o("b-col",[o("strong",[t._v("판매가격(원)")])]),o("b-col",[o("b-form-input",{attrs:{disabled:"",size:"lg"},model:{value:t.salePriceFormat,callback:function(o){t.salePriceFormat=o},expression:"salePriceFormat"}})],1)],1)],1)],1)],1),o("hr"),o("h5",[t._v("제품 요약 설명")]),o("b-form-textarea",{attrs:{"max-rows":"3"},model:{value:t.productInfo.comment,callback:function(o){t.$set(t.productInfo,"comment",o)},expression:"productInfo.comment"}}),o("hr"),o("h5",[t._v("제품 상세 설명")]),o("b-form-textarea",{attrs:{"max-rows":"3"},model:{value:t.productInfo.detail,callback:function(o){t.$set(t.productInfo,"detail",o)},expression:"productInfo.detail"}}),o("hr"),o("h5",[t._v("섭취시 유의사항")]),o("b-form-textarea",{attrs:{"max-rows":"5"},model:{value:t.productInfo.alert,callback:function(o){t.$set(t.productInfo,"alert",o)},expression:"productInfo.alert"}}),o("hr"),o("b-row",{staticClass:"mr-0 mt-2",attrs:{"align-h":"end","align-v":"center"}},[o("b-button",{staticClass:"ml-2",attrs:{variant:"primary"},on:{click:t.onClickCancel}},[t._v("취소")]),o("b-button",{staticClass:"ml-2",attrs:{variant:"danger"},on:{click:function(o){return o.preventDefault(),t.onClickUpdate.apply(null,arguments)}}},[t._v("수정")])],1)],1),o("b-modal",{attrs:{id:"imageViewer","ok-only":"","ok-title":"닫기"}},[o("b-embed",{attrs:{type:"embed",aspect:"4by3",src:t.url,allowfullscreen:""}})],1)],1)])},n=[],c=(e(7658),e(4543)),r={props:{productCode:{type:String,required:!0}},data(){return{product:{},productInfo:{},baseUrl:"http://devsu.iptime.org",mainCode:"",subCode:"",mainOpts:[],subOpts:[],url:"",file:null,ph:""}},methods:{mainLoad(){(0,c.d5)((({data:t})=>{t.map((t=>this.mainOpts.push({value:t.mainCode,text:t.mainName})))}))},subLoad(){(0,c.$M)(this.mainCode,(({data:t})=>{t.map((t=>this.subOpts.push({value:t.subCode,text:t.subName})))}))},subReset(){this.clearOption("sub"),this.subLoad()},clearOption(t){"main"==t&&(this.mainCode=null,this.subCode=null,this.mainOpts=[{value:null,text:"대분류 선택"}],this.subOpts=[{value:null,text:"중분류 선택"}]),"sub"==t&&(this.subCode=null,this.subOpts=[{value:null,text:"중분류 선택"}])},clearFileInfo(){this.file=null,this.ph=""},onClickUpdate(){if(this.productInfo.discount&&null==this.productInfo.startdiscount&&null==this.productInfo.enddiscount)return void alert("할인 제품인 경우 할인 기간을 등록해야 합니다.");let t=new FormData;t.append("product",new Blob([JSON.stringify(this.product)],{type:"application/json"})),t.append("upfile",this.file),(0,c.ZP)(t,(()=>{(0,c.HD)(this.productInfo,(()=>{this.move(`/products/detail/${this.productCode}`)}))}))},updateRecommend(t){this.productInfo.productLevel=t?1:0},openModal(){this.$bvModal.show("imageViewer")},move(t){this.$router.push(t)},onClickCancel(){confirm("수정 내용이 반영되지 않습니다")&&this.move(`/products/detail/${this.productCode}`)},getPriceFormat(t){const o=t.toString().split(".");return o[0]=o[0].replace(/\B(?=(\d{3})+(?!\d))/g,","),o.join(".")},async convertURLtoFile(t){const o=await fetch(t),e=await o.blob(),s=t.split(".").pop(),n=t.split("/").pop(),c={type:`image/${s}`};return new File([e],n,c)}},computed:{isRecommend(){return 1==this.productInfo.productLevel},totalDiscountPrice(){return parseInt(parseInt(this.productInfo.discountPrice)+parseInt(this.productInfo.price*this.productInfo.discountRate*.01))},totalDiscountFormat(){return this.getPriceFormat(this.totalDiscountPrice)},salePrice(){return parseInt(this.productInfo.price-this.totalDiscountPrice)},salePriceFormat(){return this.getPriceFormat(this.productInfo.price-this.totalDiscountPrice)}},created(){this.url=this.baseUrl+"/image/view/",(0,c.Z$)(this.productCode,(({data:t})=>{const{productInfo:o,...e}=t;this.product={...e},this.productInfo={...o},this.url+=this.product.thumbnail,this.convertURLtoFile(this.url).then((t=>{this.file=t,this.ph=this.file.name,this.visible=!0})),this.mainCode=this.productCode.substring(0,2),this.subCode=this.productCode.substring(0,3),this.mainLoad(),this.subLoad()}))},watch:{salePrice(t){this.productInfo.price=parseInt(this.productInfo.price),this.productInfo.discountRate=parseInt(this.productInfo.discountRate),this.productInfo.discountPrice=parseInt(this.productInfo.discountPrice),this.productInfo.totalPrice=t},"productInfo.price"(t){(isNaN(t)||""==t)&&(this.productInfo.price=0)},"productInfo.discount"(t){t||(""!==this.productInfo.enddiscount&&(this.productInfo.enddiscount=null),""!==this.productInfo.startdiscount&&(this.productInfo.startdiscount=null),this.productInfo.discountRate=0,this.productInfo.discountPrice=0)},"productInfo.discountRate"(t){(isNaN(t)||""==t)&&(this.productInfo.discountRate=0)},"productInfo.discountPrice"(t){(isNaN(t)||""==t)&&(this.productInfo.discountPrice=0)},"product.productName"(t){this.productInfo.productName=t},file(t){if(null!=t){const t=new FileReader;t.onload=t=>{this.url=t.target.result},t.readAsDataURL(this.file)}else this.url=""}}},i=r,a=e(1001),l=(0,a.Z)(i,s,n,!1,null,"6a8a21d0",null),u=l.exports}}]);
//# sourceMappingURL=819.03556069.js.map