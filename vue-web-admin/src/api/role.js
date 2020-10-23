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
    url: '/api/addRole',
    method: 'post',
    data
  })
}

export function updateRole(data) {
  return request({
    url: '/api/updateRole',
    method: 'post',
    data
  })
}

export function getRoutes() {
  return request({
    url: '/vue-element-admin/routes',
    method: 'get'
  })
}
export function getRoles() {
  return request({
    url: '/vue-element-admin/roles',
    method: 'get'
  })
}



export function deleteRole(id) {
  return request({
    url: `/vue-element-admin/role/${id}`,
    method: 'delete'
  })
}
