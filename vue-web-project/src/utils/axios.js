import axios from 'axios'
import qs from 'qs'
let base = '/api';
let baseUrl = 'http://localhost:9800';
export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${baseUrl}${base}${url}`,
        data: qs.stringify(params),
        headers: {
            // 'Content-Type': 'application/json;charset=UTF-8'
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    });
}

export const getRequest = (url) => {
    return axios({
        method: 'get',
        url: `${baseUrl}${base}${url}`
    });
}

export const putRequest = (url, params) => {
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params,
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
}

export const deleteRequest = (url) => {
    return axios({
        method: 'delete',
        url: `${base}${url}`
    })
}
  