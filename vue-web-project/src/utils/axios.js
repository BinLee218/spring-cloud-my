import axios from 'axios'
import qs from 'qs'
let base = '/api';
let baseUrl = 'http://localhost:9800';

// 全局设置 axios 发送请求带上cookie
axios.defaults.withCredentials = true;

export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${baseUrl}${base}${url}`,
        data: qs.stringify(params),
        // data: params,
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

// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response;
}, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
});