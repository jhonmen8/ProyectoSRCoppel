export const setToken = (token: string) => window.localStorage.setItem('api_token', token);

export const getToken = () => window.localStorage.getItem('api_token');

export const setMenusApi = (data: any) => window.localStorage.setItem('menus', JSON.stringify(data));

export const getMenusApi = () => JSON.parse(window.localStorage.getItem('menus') || '[]');