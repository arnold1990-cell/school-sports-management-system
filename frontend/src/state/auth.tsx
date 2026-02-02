import React, { createContext, useContext, useMemo, useState } from 'react';
import { apiClient } from '../utils/api';

interface UserProfile {
  id: string;
  email: string;
  roles: string[];
}

interface AuthContextValue {
  user: UserProfile | null;
  isAuthenticated: boolean;
  login: (email: string, password: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [user, setUser] = useState<UserProfile | null>(() => {
    const stored = localStorage.getItem('auth:user');
    return stored ? JSON.parse(stored) : null;
  });

  const login = async (email: string, password: string) => {
    const response = await apiClient.post('/auth/login', { email, password });
    const { accessToken, refreshToken } = response.data.data;
    apiClient.defaults.headers.common.Authorization = `Bearer ${accessToken}`;
    localStorage.setItem('auth:refresh', refreshToken);
    const me = await apiClient.get('/auth/me');
    setUser(me.data.data);
    localStorage.setItem('auth:user', JSON.stringify(me.data.data));
  };

  const logout = () => {
    const refreshToken = localStorage.getItem('auth:refresh');
    if (refreshToken) {
      apiClient.post('/auth/logout', { refreshToken }).catch(() => undefined);
    }
    localStorage.removeItem('auth:refresh');
    localStorage.removeItem('auth:user');
    delete apiClient.defaults.headers.common.Authorization;
    setUser(null);
  };

  const value = useMemo(
    () => ({ user, isAuthenticated: Boolean(user), login, logout }),
    [user]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider');
  }
  return context;
};
