import request from '@/utils/request';

export function fetchOneAddressOneCurrentSensorInfo(data) {
  const { addressId, sensorName } = data;
  return request({
    url: `/currentInfo/address/boxes/sensor?address_id=${addressId}&sensorName=${sensorName}`,
    method: 'post',
  });
}

export function fetchOneAddressAllCurrentSensorInfo(data) {
  const { addressId } = data;
  return request({
    url: `/currentInfor/address/boxes?address_id=${addressId}`,
    method: 'post',
  });
}
