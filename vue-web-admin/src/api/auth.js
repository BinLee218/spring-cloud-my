import request from '@/utils/request'

export function AuthTree(data) {
  return request({
    url: '/api/auth/byRole',
    method: 'post',
    data
  })
}
