import request from '@/utils/request';

export function fetchOneCurrentInfo(data) {
  return request({
    url: '/currentData/address/boxes/sensor',
    method: 'post',
    data,
  });
}

export function fetchAllCurrentInfo(data) {
  return request({
    url: '/currentData/address/boxes/sensor',
    method: 'post',
    data,
  });
}
