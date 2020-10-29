import request from '@/utils/request'

export function AuthTreeWithoutRole() {
  return request({
    url: '/api/auth/without/role',
    method: 'get'
  })
}

export function getAllAuths(data) {
  return request({
    url: '/api/auth/all',
    method: 'post',
    data
  })
}

export function updateAuth(data) {
  return request({
    url: '/api/auth/update',
    method: 'post',
    data
  })
}

export function addAuth(data) {
  return request({
    url: '/api/auth/add',
    method: 'post',
    data
  })
}

export function getAllApp() {
  return request({
    url: '/api/auth/app',
    method: 'get'
  })
}
