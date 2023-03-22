<template>
  <div id="chart">
    <Bar :data="chartData" :options="chartOption"></Bar>
  </div>
</template>

<script>
import { Chart, registerables } from "chart.js";
import { Bar } from "vue-chartjs";
Chart.register(...registerables);
export default {
  components: {
    Bar,
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
  width: 51%;
  height: 450px;
  background: white;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  padding-bottom: 3px;
}
</style>
