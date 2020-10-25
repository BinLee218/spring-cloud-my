import request from '@/utils/request'

export function getAllRole(data) {
  return request({
    url: '/api/roles',
    method: 'post',
    data
  })
}

export function addRole(data) {
  return request({
    url: '/api/role/add',
    method: 'post',
    data
  })
}

export function updateRole(data) {
  return request({
    url: '/api/role/update',
    method: 'post',
    data
  })
}

export function getAllNameValue() {
  return request({
    url: '/api/role/name/value',
    method: 'get'
  })
}
