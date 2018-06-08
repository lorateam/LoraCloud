import axios from 'axios';

const service = axios.create({
  timeout: 5000,
});

service.interceptors.request.use((e) => {
  e.url = `/action${e.url}`;
  return e;
});

export default service;
