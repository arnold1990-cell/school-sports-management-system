import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useAuth } from '../state/auth';
import './pages.css';

interface LoginForm {
  email: string;
  password: string;
}

export default function LoginPage() {
  const { login } = useAuth();
  const { register, handleSubmit } = useForm<LoginForm>();
  const [error, setError] = useState<string | null>(null);

  const onSubmit = async (data: LoginForm) => {
    setError(null);
    try {
      await login(data.email, data.password);
    } catch (err) {
      setError('Unable to sign in. Check your credentials.');
    }
  };

  return (
    <div className="login-page">
      <form className="card" onSubmit={handleSubmit(onSubmit)}>
        <h2>School Sports & Clubs</h2>
        <p>Sign in to manage sports, clubs, and learner data.</p>
        <label>
          Email
          <input type="email" {...register('email', { required: true })} />
        </label>
        <label>
          Password
          <input type="password" {...register('password', { required: true })} />
        </label>
        {error && <div className="error">{error}</div>}
        <button type="submit">Sign in</button>
      </form>
    </div>
  );
}
