import request from '@/utils/request';

export function fetchOneAddressOneCurrentSensorInfo(data) {
  return request({
    url: '/currentInfo/address/boxes/sensor',
    method: 'post',
    data,
  });
}

export function fetchOneAddressAllCurrentSensorInfo(data) {
  return request({
    url: '/currentInfor/address/boxes',
    method: 'post',
    data,
  });
}
