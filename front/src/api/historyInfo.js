import request from '@/utils/request';

export function fetchAddressAllHistoryInfo(data) {
  const { addressId } = data;
  return request({
    url: `/historyData/address/boxes?address_id=${addressId}`,
    method: 'get',
    data,
  });
}

export function fetchOneAddressOneHistorySensorInfo(data) {
  const { addressId, sensorName } = data;
  return request({
    url: `/historyData/address/boxes/sensor?address_id=${addressId}&sensor_name=${sensorName}`,
    method: 'get',
  });
}
