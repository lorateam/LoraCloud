import request from '@/utils/request';

export function fetchAddressAllHistoryInfo(data) {
  return request({
    url: '/historyData/address/boxes',
    method: 'post',
    data,
  });
}

export function fetchAddressOneHistoryInfo(data) {
  return request({
    url: '/historyData/address/boxes/sensor',
    method: 'post',
    data,
  });
}
