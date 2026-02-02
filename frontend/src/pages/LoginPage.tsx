import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useAuth } from '../state/auth';
import './pages.css';

interface LoginForm {
  email: string;
  password: string;
}

interface RegisterForm {
  email: string;
  password: string;
  role: string;
}

export default function LoginPage() {
  const { login, register } = useAuth();
  const { register: registerLogin, handleSubmit: handleLoginSubmit } = useForm<LoginForm>();
  const { register: registerNewAccount, handleSubmit: handleRegisterSubmit, reset } = useForm<RegisterForm>({
    defaultValues: {
      role: 'STUDENT',
    },
  });
  const [error, setError] = useState<string | null>(null);
  const [registerError, setRegisterError] = useState<string | null>(null);

  const onSubmit = async (data: LoginForm) => {
    setError(null);
    try {
      await login(data.email, data.password);
    } catch (err) {
      setError('Unable to sign in. Check your credentials.');
    }
  };

  const onRegister = async (data: RegisterForm) => {
    setRegisterError(null);
    try {
      await register(data.email, data.password, data.role);
      reset({ email: '', password: '', role: data.role });
    } catch (err) {
      setRegisterError('Unable to create account. Try a different email.');
    }
  };

  return (
    <div className="login-page">
      <div className="auth-grid">
        <form className="card" onSubmit={handleLoginSubmit(onSubmit)}>
          <h2>School Sports & Clubs</h2>
          <p>Sign in to manage sports, clubs, and learner data.</p>
          <label>
            Email
            <input type="email" {...registerLogin('email', { required: true })} />
          </label>
          <label>
            Password
            <input type="password" {...registerLogin('password', { required: true })} />
          </label>
          {error && <div className="error">{error}</div>}
          <button type="submit">Sign in</button>
        </form>

        <form className="card" onSubmit={handleRegisterSubmit(onRegister)}>
          <h2>Create an account</h2>
          <p>Register a user or admin profile, then sign in automatically.</p>
          <label>
            Email
            <input type="email" {...registerNewAccount('email', { required: true })} />
          </label>
          <label>
            Password
            <input type="password" {...registerNewAccount('password', { required: true })} />
          </label>
          <label>
            Role
            <select {...registerNewAccount('role', { required: true })}>
              <option value="STUDENT">User</option>
              <option value="ADMIN">Admin</option>
            </select>
          </label>
          {registerError && <div className="error">{registerError}</div>}
          <button type="submit">Register &amp; sign in</button>
        </form>
      </div>
    </div>
  );
}
