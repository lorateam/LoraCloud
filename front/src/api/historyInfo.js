import request from '@/utils/request';

export function fetchAddressAllHistoryInfo(data) {
  const { addressId } = data;
  return request({
    url: `/historyData/address/boxes?addressId=${addressId}`,
    method: 'get',
    data,
  });
}

export function fetchOneAddressOneHistorySensorInfo(data) {
  const { addressId, sensorName } = data;
  return request({
    url: `/historyData/address/boxes/sensor?addressId=${addressId}&sensorName=${sensorName}`,
    method: 'get',
  });
}
