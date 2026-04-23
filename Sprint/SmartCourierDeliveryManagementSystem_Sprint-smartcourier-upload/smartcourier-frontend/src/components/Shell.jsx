import { Link, NavLink, Outlet } from "react-router-dom";
import { useAuth } from "../state/AuthContext";

const customerLinks = [
  ["/customer/dashboard", "Dashboard"],
  ["/customer/create-delivery", "Create Delivery"],
  ["/customer/track", "Track Parcel"]
];

const adminLinks = [
  ["/admin/dashboard", "Dashboard"],
  ["/admin/deliveries", "Deliveries"],
  ["/admin/reports", "Reports"],
  ["/admin/users", "Users"],
  ["/admin/hubs", "Hubs"]
];

export default function Shell() {
  const { user, logout } = useAuth();
  const links = user?.role === "ADMIN" ? adminLinks : customerLinks;

  return (
    <div className="app-shell">
      <aside className="sidebar">
        <Link className="brand" to="/">
          <span>SmartCourier</span>
          <small>Delivery Control Center</small>
        </Link>
        <nav>
          {links.map(([to, label]) => (
            <NavLink key={to} to={to} className="nav-link">
              {label}
            </NavLink>
          ))}
        </nav>
        <button className="ghost" onClick={logout}>Logout</button>
      </aside>
      <main className="content">
        <header className="topbar">
          <div>
            <h1>{user?.role === "ADMIN" ? "Admin Operations" : "Customer Workspace"}</h1>
            <p>{user?.username}</p>
          </div>
        </header>
        <Outlet />
      </main>
    </div>
  );
}
