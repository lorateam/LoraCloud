import request from '@/utils/request';

export function fetchOneAddressOneCurrentSensorInfo(data) {
  const { addressId, sensorName } = data;
  return request({
    url: `/currentInfo/address/boxes/sensor?addressId=${addressId}&sensorName=${sensorName}`,
    method: 'post',
  });
}

export function fetchOneAddressAllCurrentSensorInfo(data) {
  const { addressId } = data;
  return request({
    url: `/currentInfor/address/boxes?addressId=${addressId}`,
    method: 'post',
  });
}
