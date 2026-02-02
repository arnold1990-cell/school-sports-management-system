import './pages.css';

export default function DashboardPage() {
  return (
    <div>
      <h2>Admin Dashboard</h2>
      <div className="page-grid">
        <div className="tile">
          <h4>Total Learners</h4>
          <p>--</p>
        </div>
        <div className="tile">
          <h4>Upcoming Fixtures</h4>
          <p>--</p>
        </div>
        <div className="tile">
          <h4>Upcoming Events</h4>
          <p>--</p>
        </div>
        <div className="tile">
          <h4>Unpaid Club Fees</h4>
          <p>--</p>
        </div>
      </div>
    </div>
  );
}
