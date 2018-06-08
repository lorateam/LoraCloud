import request from '@/utils/request';

export function fetchOneCurrentInfo(data) {
  return request({
    url: '/baseInfo/address/boxes',
    method: 'post',
    data,
  });
}

export function fetchAllAddressInfo() {
  return request({
    url: '/baseInfo/address',
    method: 'post',
  });
}
