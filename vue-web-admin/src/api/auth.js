import request from '@/utils/request'

export function AuthTreeWithoutRole() {
  return request({
    url: '/api/auth/without/role',
    method: 'get'
  })
}
