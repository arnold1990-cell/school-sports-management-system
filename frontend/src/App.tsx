import { Navigate, Route, Routes } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import DashboardPage from './pages/DashboardPage';
import LearnersPage from './pages/LearnersPage';
import HousesPage from './pages/HousesPage';
import SportsPage from './pages/SportsPage';
import ClubsPage from './pages/ClubsPage';
import EventsPage from './pages/EventsPage';
import AppLayout from './components/AppLayout';
import { AuthProvider, useAuth } from './state/auth';

const ProtectedRoute = ({ children }: { children: JSX.Element }) => {
  const { isAuthenticated } = useAuth();
  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }
  return children;
};

export default function App() {
  return (
    <AuthProvider>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route
          path="/"
          element={
            <ProtectedRoute>
              <AppLayout />
            </ProtectedRoute>
          }
        >
          <Route index element={<DashboardPage />} />
          <Route path="learners" element={<LearnersPage />} />
          <Route path="houses" element={<HousesPage />} />
          <Route path="sports" element={<SportsPage />} />
          <Route path="clubs" element={<ClubsPage />} />
          <Route path="events" element={<EventsPage />} />
        </Route>
      </Routes>
    </AuthProvider>
  );
}
