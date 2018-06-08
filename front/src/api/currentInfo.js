import request from '@/utils/request';

export function fetchAddressOneCurrentInfo(data) {
  return request({
    url: '/currentData/address/boxes/sensor',
    method: 'post',
    data,
  });
}

export function fetchAddressAllCurrentInfo(data) {
  return request({
    url: '/currentData/address/boxes',
    method: 'post',
    data,
  });
}
