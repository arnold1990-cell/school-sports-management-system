import { NavLink, Outlet } from 'react-router-dom';
import { useAuth } from '../state/auth';
import './layout.css';

const navItems = [
  { to: '/', label: 'Dashboard' },
  { to: '/learners', label: 'Learners' },
  { to: '/houses', label: 'Houses' },
  { to: '/sports', label: 'Sports' },
  { to: '/clubs', label: 'Clubs' },
  { to: '/events', label: 'Events' }
];

export default function AppLayout() {
  const { logout, user } = useAuth();

  return (
    <div className="app-shell">
      <aside className="sidebar">
        <h1>Sports & Clubs</h1>
        <nav>
          {navItems.map((item) => (
            <NavLink key={item.to} to={item.to} end className="nav-link">
              {item.label}
            </NavLink>
          ))}
        </nav>
      </aside>
      <main className="content">
        <header className="top-bar">
          <div>
            <strong>{user?.email ?? 'User'}</strong>
            <span className="role-pill">{user?.roles.join(', ')}</span>
          </div>
          <button onClick={logout}>Log out</button>
        </header>
        <div className="page-body">
          <Outlet />
        </div>
      </main>
    </div>
  );
}
