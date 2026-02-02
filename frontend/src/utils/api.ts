import axios from 'axios';

export const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/v1'
});

apiClient.interceptors.response.use(
  (response) => response,
  async (error) => {
    if (error.response?.status === 401) {
      const refreshToken = localStorage.getItem('auth:refresh');
      if (refreshToken) {
        try {
          const refreshResponse = await axios.post('http://localhost:8080/api/v1/auth/refresh', {
            refreshToken
          });
          const { accessToken } = refreshResponse.data.data;
          apiClient.defaults.headers.common.Authorization = `Bearer ${accessToken}`;
          error.config.headers.Authorization = `Bearer ${accessToken}`;
          return apiClient.request(error.config);
        } catch (_error) {
          localStorage.removeItem('auth:refresh');
          localStorage.removeItem('auth:user');
        }
      }
    }
    return Promise.reject(error);
  }
);
