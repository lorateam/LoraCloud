import axios from 'axios';

/* eslint-disable */

window.LOG = false;

window.shutUp = function() {
  window.LOG = !window.LOG;
};

const service = axios.create({
  timeout: 5000,
});

service.interceptors.request.use((e) => {
  e.url = `/action${e.url}`;
  if (window.LOG) {
    console.group('Request');
    console.log(e);
    console.groupEnd();
  }
  return e;
});

service.interceptors.response.use((e) => {
  if (window.LOG) {
    console.group('Response');
    console.log(e);
    console.groupEnd();
  }
  return e;
})

export default service;
