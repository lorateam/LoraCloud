import request from '@/utils/request';

export function fetchOneAddressVideo(data) {
  const { addressId } = data;
  return request({
    url: `/video?address_id=${addressId}`,
    method: 'get',
    data,
  });
}