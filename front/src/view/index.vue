<template>
  <div>
    <!-- <small-box /> -->
    Hello World
  </div>
</template>

<script>
import { fetchOneCurrentInfo } from '@/api/baseInfo';
// import smallBox from '@/components/smallBox';

export default {
  name: 'name',
  data() {
    return {
      userList: [],
    };
  },
  mounted() {
    this.getAddressAllHistoryInfo();
  },
  methods: {
    getAddressAllHistoryInfo() {
      const data = 1;
      fetchOneCurrentInfo(data).then((response) => {
        this.userList = response.userList;
      });
    },
  },
  components: {
    // smallBox,
  },
};
</script>

<style>

</style>

