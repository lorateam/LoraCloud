import request from '@/utils/request';

export function fetchOneAddressVideo(data) {
  const { addressId } = data;
  return request({
    url: `/video?addressId=${addressId}`,
    method: 'get',
    data,
  });
}