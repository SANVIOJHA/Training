import React, { useState, useEffect } from 'react';
import { Users, Trash2, ShieldCheck, ShieldOff, AlertTriangle, RefreshCw, Search, Shield } from 'lucide-react';
import api from '../../services/api';
import { useAuth } from '../../context/AuthContext';

const UserManagement = () => {
  const { user: currentUser } = useAuth();
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [actionLoading, setActionLoading] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');

  const fetchUsers = async () => {
    try {
      setLoading(true);
      setError('');
      const response = await api.get('/auth/users');
      setUsers(response.data);
    } catch (err) {
      console.error("User fetch error:", err);
      setError('Unable to retrieve user management data right now. Please verify service availability.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleDeleteUser = async (userId, username) => {
    if (!window.confirm(`Are you sure you want to delete user "${username}"? This action cannot be undone.`)) return;
    
    setActionLoading(userId);
    try {
      await api.delete(`/auth/users/${userId}`);
      setUsers(users.filter(u => u.id !== userId));
    } catch (err) {
      alert('Failed to delete user: ' + (err.response?.data?.message || err.message));
    } finally {
      setActionLoading(null);
    }
  };

  const filteredUsers = users.filter(u => 
    u.username?.toLowerCase().includes(searchTerm.toLowerCase()) || 
    u.phoneNumber?.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const adminCount = users.filter(u => u.role === 'ADMIN').length;

  const TableSkeleton = () => (
    <div className="animate-pulse">
      {[1, 2, 3, 4].map(i => (
        <div key={i} style={{ display: 'flex', borderBottom: '1px solid var(--border-color)', padding: '1rem 1.5rem', gap: '2rem' }}>
          <div style={{ backgroundColor: 'var(--border-color)', height: '16px', width: '5%', borderRadius: '4px' }}></div>
          <div style={{ backgroundColor: 'var(--border-color)', height: '16px', width: '25%', borderRadius: '4px' }}></div>
          <div style={{ backgroundColor: 'var(--border-color)', height: '16px', width: '25%', borderRadius: '4px' }}></div>
          <div style={{ backgroundColor: 'var(--border-color)', height: '24px', width: '15%', borderRadius: '12px' }}></div>
          <div style={{ backgroundColor: 'var(--border-color)', height: '32px', width: '15%', borderRadius: '4px' }}></div>
        </div>
      ))}
    </div>
  );

  return (
    <div className="animate-fade-in">
      <div className="page-header" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '2rem' }}>
        <div>
          <h1 className="page-title">User Management</h1>
          <p className="page-subtitle">Manage system users, roles, and permissions</p>
        </div>
        <button className="btn btn-outline" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }} onClick={fetchUsers} disabled={loading}>
          <RefreshCw size={16} className={loading ? "animate-spin" : ""} /> Refresh Data
        </button>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-3" style={{ marginBottom: '2rem' }}>
        <div className="card hover-lift" style={{ padding: '1.5rem', display: 'flex', alignItems: 'center', gap: '1rem' }}>
          <div style={{ backgroundColor: 'var(--primary-light)', padding: '0.75rem', borderRadius: 'var(--radius-md)', color: 'var(--primary-color)' }}>
            <Users size={22} />
          </div>
          <div>
            <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>Total Users</div>
            <div style={{ fontSize: '1.5rem', fontWeight: 700 }}>{users.length}</div>
          </div>
        </div>
        <div className="card hover-lift" style={{ padding: '1.5rem', display: 'flex', alignItems: 'center', gap: '1rem' }}>
          <div style={{ backgroundColor: '#d1fae5', padding: '0.75rem', borderRadius: 'var(--radius-md)', color: '#047857' }}>
            <ShieldCheck size={22} />
          </div>
          <div>
            <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>Admins</div>
            <div style={{ fontSize: '1.5rem', fontWeight: 700 }}>{adminCount}</div>
          </div>
        </div>
        <div className="card hover-lift" style={{ padding: '1.5rem', display: 'flex', alignItems: 'center', gap: '1rem' }}>
          <div style={{ backgroundColor: '#dbeafe', padding: '0.75rem', borderRadius: 'var(--radius-md)', color: '#1d4ed8' }}>
            <Users size={22} />
          </div>
          <div>
            <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>Customers</div>
            <div style={{ fontSize: '1.5rem', fontWeight: 700 }}>{users.filter(u => u.role === 'CUSTOMER').length}</div>
          </div>
        </div>
      </div>

      {/* Users Table */}
      <div className="card">
        <div className="card-header" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <h3 className="card-title" style={{ margin: 0 }}>System Users Directory</h3>
          <div style={{ position: 'relative' }}>
            <Search size={16} style={{ position: 'absolute', left: '10px', top: '10px', color: 'var(--text-muted)' }} />
            <input 
              type="text" 
              className="form-control" 
              placeholder="Search username or phone..." 
              style={{ paddingLeft: '2rem', width: '250px' }}
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
        </div>

        {error ? (
          <div style={{ padding: '4rem 2rem', textAlign: 'center', backgroundColor: '#fff5f5' }}>
            <AlertTriangle size={48} style={{ margin: '0 auto 1rem', color: '#f56565' }} />
            <h3 style={{ margin: '0 0 0.5rem 0', color: '#c53030' }}>Connection Error</h3>
            <p style={{ color: '#e53e3e', marginBottom: '1.5rem' }}>{error}</p>
            <button className="btn btn-primary" onClick={fetchUsers}>Try Again</button>
          </div>
        ) : loading ? (
          <TableSkeleton />
        ) : (
          <div className="card-body" style={{ padding: 0 }}>
            <div style={{ overflowX: 'auto' }}>
              <table style={{ width: '100%', borderCollapse: 'collapse', textAlign: 'left' }}>
                <thead>
                  <tr style={{ backgroundColor: 'var(--surface-hover)', borderBottom: '1px solid var(--border-color)' }}>
                    <th style={{ padding: '1rem 1.5rem', fontWeight: 600, color: 'var(--text-light)', fontSize: '0.875rem', textTransform: 'uppercase' }}>ID</th>
                    <th style={{ padding: '1rem 1.5rem', fontWeight: 600, color: 'var(--text-light)', fontSize: '0.875rem', textTransform: 'uppercase' }}>Username</th>
                    <th style={{ padding: '1rem 1.5rem', fontWeight: 600, color: 'var(--text-light)', fontSize: '0.875rem', textTransform: 'uppercase' }}>Phone</th>
                    <th style={{ padding: '1rem 1.5rem', fontWeight: 600, color: 'var(--text-light)', fontSize: '0.875rem', textTransform: 'uppercase' }}>Role</th>
                    <th style={{ padding: '1rem 1.5rem', fontWeight: 600, color: 'var(--text-light)', fontSize: '0.875rem', textTransform: 'uppercase' }}>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {filteredUsers.length === 0 ? (
                    <tr>
                      <td colSpan="5" style={{ padding: '4rem 2rem', textAlign: 'center', color: 'var(--text-muted)' }}>
                        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '1rem' }}>
                          <Search size={48} style={{ color: 'var(--border-color)' }} />
                          <div>
                            <h4 style={{ margin: '0 0 0.25rem 0', color: 'var(--text-color)' }}>No Users Found</h4>
                            <p style={{ margin: 0, fontSize: '0.875rem' }}>Try adjusting your search terms.</p>
                          </div>
                        </div>
                      </td>
                    </tr>
                  ) : (
                    filteredUsers.map(user => {
                      const isSelf = currentUser?.username === user.username;
                      const isLastAdmin = user.role === 'ADMIN' && adminCount <= 1;
                      const deleteDisabled = isSelf || isLastAdmin || actionLoading === user.id;

                      let deleteTooltip = "Delete user";
                      if (isSelf) deleteTooltip = "You cannot delete your own active account";
                      else if (isLastAdmin) deleteTooltip = "Cannot delete the last remaining system admin";

                      return (
                        <tr key={user.id} style={{ borderBottom: '1px solid var(--border-color)', backgroundColor: isSelf ? '#f8fafc' : 'transparent' }}>
                          <td style={{ padding: '1rem 1.5rem', fontWeight: 500 }}>#{user.id}</td>
                          <td style={{ padding: '1rem 1.5rem', fontWeight: 600 }}>
                            <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                              {user.username}
                              {isSelf && <span style={{ fontSize: '0.7rem', padding: '0.1rem 0.4rem', backgroundColor: 'var(--primary-color)', color: 'white', borderRadius: '4px' }}>YOU</span>}
                            </div>
                          </td>
                          <td style={{ padding: '1rem 1.5rem', color: 'var(--text-muted)' }}>{user.phoneNumber || '—'}</td>
                          <td style={{ padding: '1rem 1.5rem' }}>
                            <span style={{
                              display: 'inline-flex', alignItems: 'center', gap: '0.375rem',
                              padding: '0.25rem 0.75rem', borderRadius: 'var(--radius-full)',
                              fontSize: '0.75rem', fontWeight: 600, textTransform: 'uppercase',
                              backgroundColor: user.role === 'ADMIN' ? '#fee2e2' : '#e0e7ff',
                              color: user.role === 'ADMIN' ? '#b91c1c' : '#4338ca'
                            }}>
                              {user.role === 'ADMIN' ? <ShieldCheck size={14} /> : <Users size={14} />}
                              {user.role}
                            </span>
                          </td>
                          <td style={{ padding: '1rem 1.5rem' }}>
                            <button
                              className="btn"
                              style={{ 
                                padding: '0.375rem 0.75rem', fontSize: '0.8rem',
                                backgroundColor: deleteDisabled ? '#e2e8f0' : 'var(--danger-color)', 
                                color: deleteDisabled ? '#94a3b8' : 'white', 
                                border: 'none',
                                cursor: deleteDisabled ? 'not-allowed' : 'pointer'
                              }}
                              onClick={() => handleDeleteUser(user.id, user.username)}
                              disabled={deleteDisabled}
                              title={deleteTooltip}
                            >
                              <Trash2 size={14} /> Delete
                            </button>
                          </td>
                        </tr>
                      );
                    })
                  )}
                </tbody>
              </table>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default UserManagement;
