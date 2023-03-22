<template>
  <div id="chart">
    <Pie :data="chartData" :options="chartOption"></Pie>
  </div>
</template>

<script>
import { Chart, registerables } from "chart.js";
import { Pie } from "vue-chartjs";
Chart.register(...registerables);

export default {
  components: {
    Pie,
  },

  props: {
    chartData: {
      type: Object,
      required: true,
    },
    chartOption: {
      type: Object,
    },
  },
};
</script>

<style scoped>
#chart {
  width: 450px;
  height: 450px;
  background-color: white;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  padding-bottom: 10px;
}
</style>
