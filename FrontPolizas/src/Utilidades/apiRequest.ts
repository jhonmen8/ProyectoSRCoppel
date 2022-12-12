import axios, { AxiosRequestHeaders } from "axios";
import { getToken } from './session';
import env from '../env.json';
import { RequestProps } from "../interfaces/apiInterfaces";


const apiRequestToken = ({
    endpoint, body, method = 'GET', token = true, multi_part
}: RequestProps) => {
    const url = `${env.API_URL}/${endpoint}`;
    let headers: any = {
        'Access-Control-Allow-Origin': window.location.origin
    }
    if (token) headers['Authorization'] = 'Bearer ' + (getToken() || '');
    if (multi_part) headers['Content-Type'] = 'multipart/form-data';
    return axios({
        url,
        method,
        data: body || null,
        headers
    });
}

const apiRequestGetPublic = (url: string, headers?: AxiosRequestHeaders) => {
    return axios({ url, method: 'GET', headers });
}

export {
    apiRequestToken, apiRequestGetPublic
}