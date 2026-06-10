import React from 'react';
import { useAuth } from '../context/AuthContext';
import { LogOut, Package, LayoutDashboard, FileText, Users, Search, MapPin } from 'lucide-react';
import { Link, useLocation, useNavigate } from 'react-router-dom';

const MainLayout = ({ children }) => {
  const { user, logout } = useAuth();
  const location = useLocation();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  const isActive = (path) => location.pathname === path;

  const linkStyle = (path) => ({
    display: 'flex', alignItems: 'center', gap: '0.75rem', padding: '0.75rem 1rem',
    borderRadius: 'var(--radius-md)',
    backgroundColor: isActive(path) ? 'var(--primary-light)' : 'transparent',
    color: isActive(path) ? 'var(--primary-color)' : 'var(--text-main)',
    fontWeight: isActive(path) ? 600 : 400,
    transition: 'var(--transition)'
  });

  return (
    <div className="app-layout">
      {/* Sidebar */}
      <div style={{ width: '250px', backgroundColor: 'var(--surface-color)', borderRight: '1px solid var(--border-color)', display: 'flex', flexDirection: 'column', flexShrink: 0 }}>
        <div style={{ padding: '1.5rem', borderBottom: '1px solid var(--border-color)' }}>
          <h2 style={{ color: 'var(--primary-color)', margin: 0, display: 'flex', alignItems: 'center', gap: '0.5rem', fontSize: '1.25rem' }}>
            <Package /> SmartCourier
          </h2>
        </div>
        
        <nav style={{ padding: '1.5rem 1rem', flex: 1, display: 'flex', flexDirection: 'column', gap: '0.25rem' }}>
          {user?.role === 'CUSTOMER' && (
            <>
              <Link to="/customer/dashboard" style={linkStyle('/customer/dashboard')}>
                <LayoutDashboard size={20} /> Dashboard
              </Link>
              <Link to="/customer/deliveries/new" style={linkStyle('/customer/deliveries/new')}>
                <Package size={20} /> Book Delivery
              </Link>
            </>
          )}

          {user?.role === 'ADMIN' && (
            <>
              <Link to="/admin/dashboard" style={linkStyle('/admin/dashboard')}>
                <LayoutDashboard size={20} /> Dashboard
              </Link>
              <Link to="/admin/deliveries" style={linkStyle('/admin/deliveries')}>
                <FileText size={20} /> All Deliveries
              </Link>
              <Link to="/admin/users" style={linkStyle('/admin/users')}>
                <Users size={20} /> User Management
              </Link>
              <Link to="/admin/hubs" style={linkStyle('/admin/hubs')}>
                <MapPin size={20} /> Hub Management
              </Link>
            </>
          )}
        </nav>

        <div style={{ padding: '1.5rem', borderTop: '1px solid var(--border-color)' }}>
          <div style={{ marginBottom: '1rem', fontSize: '0.875rem' }}>
            <div style={{ fontWeight: 600 }}>{user?.username}</div>
            <div style={{ color: 'var(--text-muted)', fontSize: '0.75rem', marginTop: '0.125rem' }}>
              <span style={{ 
                display: 'inline-flex', alignItems: 'center', gap: '0.25rem',
                padding: '0.125rem 0.5rem', borderRadius: 'var(--radius-full)',
                backgroundColor: user?.role === 'ADMIN' ? '#fee2e2' : '#e0e7ff',
                color: user?.role === 'ADMIN' ? '#b91c1c' : '#4338ca',
                fontWeight: 600, fontSize: '0.65rem', textTransform: 'uppercase'
              }}>
                {user?.role}
              </span>
            </div>
          </div>
          <button 
            onClick={handleLogout}
            className="btn btn-ghost" 
            style={{ width: '100%', justifyContent: 'flex-start', color: 'var(--danger-color)', padding: '0.5rem 1rem' }}
          >
            <LogOut size={18} /> Logout
          </button>
        </div>
      </div>

      {/* Main Content */}
      <div className="main-content">
        <div className="container">
          {children}
        </div>
      </div>
    </div>
  );
};

export default MainLayout;
