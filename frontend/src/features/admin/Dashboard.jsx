import React, { useState, useEffect } from 'react';
import { ShieldAlert, CheckCircle, Package, Truck, AlertTriangle, Search, Clock, RefreshCw } from 'lucide-react';
import api from '../../services/api';

const AdminDashboard = () => {
  const [deliveries, setDeliveries] = useState([]);
  const [stats, setStats] = useState({ total: 0, pending: 0, inTransit: 0, exceptions: 0 });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [actionLoading, setActionLoading] = useState(null);
  const [successMsg, setSuccessMsg] = useState('');

  const fetchDeliveries = async () => {
    try {
      setLoading(true);
      setError('');
      const response = await api.get('/admin/deliveries');
      const data = response.data;
      setDeliveries(data);
      
      const pending = data.filter(d => ['PENDING_DISPATCH', 'DRAFT', 'BOOKED', 'CREATED'].includes(d.status)).length;
      const inTransit = data.filter(d => ['PICKED_UP', 'ARRIVED_AT_HUB', 'DISPATCHED_FROM_HUB', 'IN_TRANSIT', 'REACHED_DESTINATION_HUB', 'OUT_FOR_DELIVERY'].includes(d.status)).length;
      const exceptions = data.filter(d => ['DELIVERY_FAILED', 'DELAYED', 'FAILED', 'RETURNED', 'CANCELLED'].includes(d.status)).length;
      
      setStats({ total: data.length, pending, inTransit, exceptions });
    } catch (err) {
      console.error("Dashboard delivery fetch error:", err);
      setError('Unable to retrieve shipment data right now. Please verify service availability.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchDeliveries();
  }, []);

  const handleStatusUpdate = async (id, newStatus) => {
    setActionLoading(id);
    setError('');
    setSuccessMsg('');
    try {
      await api.put(`/admin/deliveries/${id}/status`, {
        status: newStatus,
        location: 'Admin Operations Center',
        description: `Operational status updated to ${newStatus}`
      });
      setSuccessMsg(`Delivery ${id} successfully updated to ${newStatus.replace(/_/g, ' ')}`);
      await fetchDeliveries(); 
    } catch (err) {
      const errMsg = err.response?.data?.message || err.response?.data?.data?.message || err.message;
      setError(`Failed to update status for delivery ${id}: ${errMsg}`);
    } finally {
      setActionLoading(null);
      setTimeout(() => setSuccessMsg(''), 4000);
    }
  };

  const getStatusBadgeClass = (status) => {
    const s = status ? status.toLowerCase().replace(/_/g, '-') : '';
    return `badge badge-${s}`;
  };

  const StatSkeleton = () => (
    <div className="card animate-pulse" style={{ padding: '1.5rem', display: 'flex', alignItems: 'center', gap: '1.25rem' }}>
      <div style={{ backgroundColor: 'var(--border-color)', width: '48px', height: '48px', borderRadius: 'var(--radius-md)' }}></div>
      <div style={{ flex: 1 }}>
        <div style={{ backgroundColor: 'var(--border-color)', height: '12px', width: '50%', marginBottom: '8px', borderRadius: '4px' }}></div>
        <div style={{ backgroundColor: 'var(--border-color)', height: '24px', width: '30%', borderRadius: '4px' }}></div>
      </div>
    </div>
  );

  const TableSkeleton = () => (
    <div className="animate-pulse">
      {[1, 2, 3, 4, 5].map(i => (
        <div key={i} style={{ display: 'flex', borderBottom: '1px solid var(--border-color)', padding: '1rem 1.5rem', gap: '2rem' }}>
          <div style={{ backgroundColor: 'var(--border-color)', height: '16px', width: '15%', borderRadius: '4px' }}></div>
          <div style={{ backgroundColor: 'var(--border-color)', height: '16px', width: '20%', borderRadius: '4px' }}></div>
          <div style={{ backgroundColor: 'var(--border-color)', height: '16px', width: '25%', borderRadius: '4px' }}></div>
          <div style={{ backgroundColor: 'var(--border-color)', height: '24px', width: '10%', borderRadius: '12px' }}></div>
          <div style={{ backgroundColor: 'var(--border-color)', height: '24px', width: '15%', borderRadius: '4px' }}></div>
        </div>
      ))}
    </div>
  );

  return (
    <div className="animate-fade-in">
      <div className="page-header" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-end', marginBottom: '2rem' }}>
        <div>
          <h1 className="page-title">Operations Control Panel</h1>
          <p className="page-subtitle">Real-time logistics and delivery management</p>
        </div>
        <button className="btn btn-outline" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }} onClick={fetchDeliveries} disabled={loading}>
          <RefreshCw size={16} className={loading ? "animate-spin" : ""} /> Refresh Data
        </button>
      </div>

      {successMsg && (
        <div className="animate-fade-in" style={{ padding: '1rem', backgroundColor: 'var(--success-color)', color: 'white', borderRadius: 'var(--radius-md)', marginBottom: '1.5rem', display: 'flex', alignItems: 'center', gap: '0.75rem', boxShadow: 'var(--shadow-sm)' }}>
          <CheckCircle size={20} /> {successMsg}
        </div>
      )}

      {/* Stats Cards */}
      <div className="grid grid-cols-4" style={{ marginBottom: '2.5rem' }}>
        {loading ? (
          <><StatSkeleton /><StatSkeleton /><StatSkeleton /><StatSkeleton /></>
        ) : (
          <>
            <div className="card hover-lift" style={{ padding: '1.5rem', display: 'flex', alignItems: 'center', gap: '1.25rem', borderLeft: '4px solid var(--primary-color)' }}>
              <div style={{ backgroundColor: 'var(--primary-light)', padding: '1rem', borderRadius: 'var(--radius-md)', color: 'var(--primary-color)' }}>
                <Package size={24} />
              </div>
              <div>
                <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)', fontWeight: 600, textTransform: 'uppercase', letterSpacing: '0.5px' }}>Total Deliveries</div>
                <div style={{ fontSize: '1.75rem', fontWeight: 700, lineHeight: 1.2 }}>{stats.total}</div>
              </div>
            </div>
            <div className="card hover-lift" style={{ padding: '1.5rem', display: 'flex', alignItems: 'center', gap: '1.25rem', borderLeft: '4px solid #f59e0b' }}>
              <div style={{ backgroundColor: '#fef3c7', padding: '1rem', borderRadius: 'var(--radius-md)', color: '#b45309' }}>
                <Clock size={24} />
              </div>
              <div>
                <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)', fontWeight: 600, textTransform: 'uppercase', letterSpacing: '0.5px' }}>Pending Dispatch</div>
                <div style={{ fontSize: '1.75rem', fontWeight: 700, lineHeight: 1.2 }}>{stats.pending}</div>
              </div>
            </div>
            <div className="card hover-lift" style={{ padding: '1.5rem', display: 'flex', alignItems: 'center', gap: '1.25rem', borderLeft: '4px solid #3b82f6' }}>
              <div style={{ backgroundColor: '#dbeafe', padding: '1rem', borderRadius: 'var(--radius-md)', color: '#1d4ed8' }}>
                <Truck size={24} />
              </div>
              <div>
                <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)', fontWeight: 600, textTransform: 'uppercase', letterSpacing: '0.5px' }}>Active Transit</div>
                <div style={{ fontSize: '1.75rem', fontWeight: 700, lineHeight: 1.2 }}>{stats.inTransit}</div>
              </div>
            </div>
            <div className="card hover-lift" style={{ padding: '1.5rem', display: 'flex', alignItems: 'center', gap: '1.25rem', borderLeft: '4px solid #ef4444' }}>
              <div style={{ backgroundColor: '#fee2e2', padding: '1rem', borderRadius: 'var(--radius-md)', color: '#b91c1c' }}>
                <ShieldAlert size={24} />
              </div>
              <div>
                <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)', fontWeight: 600, textTransform: 'uppercase', letterSpacing: '0.5px' }}>Exceptions</div>
                <div style={{ fontSize: '1.75rem', fontWeight: 700, lineHeight: 1.2 }}>{stats.exceptions}</div>
              </div>
            </div>
          </>
        )}
      </div>

      {/* Operational Shipments Table */}
      <div className="card">
        <div className="card-header" style={{ padding: '1.5rem', borderBottom: '1px solid var(--border-color)', backgroundColor: 'var(--surface-color)', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <h3 className="card-title" style={{ margin: 0, fontSize: '1.125rem' }}>Active Delivery Monitor</h3>
        </div>
        
        {error ? (
          <div style={{ padding: '4rem 2rem', textAlign: 'center', backgroundColor: '#fff5f5' }}>
            <AlertTriangle size={48} style={{ margin: '0 auto 1rem', color: '#f56565' }} />
            <h3 style={{ margin: '0 0 0.5rem 0', color: '#c53030' }}>Connection Error</h3>
            <p style={{ color: '#e53e3e', marginBottom: '1.5rem' }}>{error}</p>
            <button className="btn btn-primary" onClick={fetchDeliveries}>Try Again</button>
          </div>
        ) : loading ? (
          <TableSkeleton />
        ) : (
          <div className="card-body" style={{ padding: 0 }}>
            <div style={{ overflowX: 'auto' }}>
              <table style={{ width: '100%', borderCollapse: 'collapse', textAlign: 'left' }}>
                <thead>
                  <tr style={{ backgroundColor: 'var(--bg-color)', borderBottom: '2px solid var(--border-color)' }}>
                    <th style={{ padding: '1rem 1.5rem', fontWeight: 600, color: 'var(--text-light)', fontSize: '0.75rem', textTransform: 'uppercase', letterSpacing: '0.5px' }}>Tracking #</th>
                    <th style={{ padding: '1rem 1.5rem', fontWeight: 600, color: 'var(--text-light)', fontSize: '0.75rem', textTransform: 'uppercase', letterSpacing: '0.5px' }}>Client Info</th>
                    <th style={{ padding: '1rem 1.5rem', fontWeight: 600, color: 'var(--text-light)', fontSize: '0.75rem', textTransform: 'uppercase', letterSpacing: '0.5px' }}>Routing</th>
                    <th style={{ padding: '1rem 1.5rem', fontWeight: 600, color: 'var(--text-light)', fontSize: '0.75rem', textTransform: 'uppercase', letterSpacing: '0.5px' }}>Current State</th>
                    <th style={{ padding: '1rem 1.5rem', fontWeight: 600, color: 'var(--text-light)', fontSize: '0.75rem', textTransform: 'uppercase', letterSpacing: '0.5px' }}>Operational Action</th>
                  </tr>
                </thead>
                <tbody>
                  {deliveries.length === 0 ? (
                    <tr>
                      <td colSpan="5" style={{ padding: '4rem 2rem', textAlign: 'center', color: 'var(--text-muted)' }}>
                        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '1rem' }}>
                          <Package size={48} style={{ color: 'var(--border-color)' }} />
                          <div>
                            <h4 style={{ margin: '0 0 0.25rem 0', color: 'var(--text-color)' }}>No Active Deliveries</h4>
                            <p style={{ margin: 0, fontSize: '0.875rem' }}>Delivery activity will appear here once bookings are created.</p>
                          </div>
                        </div>
                      </td>
                    </tr>
                  ) : (
                    deliveries.slice(0, 10).map(delivery => (
                      <tr key={delivery.id} style={{ borderBottom: '1px solid var(--border-color)', transition: 'var(--transition)' }} className="hover:bg-slate-50">
                        <td style={{ padding: '1rem 1.5rem', verticalAlign: 'top' }}>
                          <div style={{ fontWeight: 600, fontFamily: 'monospace', fontSize: '0.875rem', color: 'var(--primary-color)' }}>{delivery.trackingNumber}</div>
                          <div style={{ fontSize: '0.75rem', color: 'var(--text-muted)', marginTop: '0.25rem' }}>Ref ID: {delivery.id}</div>
                        </td>
                        <td style={{ padding: '1rem 1.5rem', verticalAlign: 'top' }}>
                          <div style={{ fontWeight: 600 }}>{delivery.senderName}</div>
                          <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>{delivery.senderPhone}</div>
                        </td>
                        <td style={{ padding: '1rem 1.5rem', verticalAlign: 'top' }}>
                          <div style={{ display: 'flex', flexDirection: 'column', gap: '0.375rem' }}>
                            <div style={{ fontSize: '0.875rem', display: 'flex', gap: '0.5rem' }}><span style={{ color: 'var(--text-muted)', fontSize: '0.75rem', width: '30px', fontWeight: 600 }}>SRC</span> <span className="truncate" style={{maxWidth: '150px'}} title={delivery.source}>{delivery.source?.split(',')[0]}</span></div>
                            <div style={{ fontSize: '0.875rem', display: 'flex', gap: '0.5rem' }}><span style={{ color: 'var(--text-muted)', fontSize: '0.75rem', width: '30px', fontWeight: 600 }}>DST</span> <span className="truncate" style={{maxWidth: '150px'}} title={delivery.destination}>{delivery.destination?.split(',')[0]}</span></div>
                          </div>
                        </td>
                        <td style={{ padding: '1rem 1.5rem', verticalAlign: 'top' }}>
                          <span className={getStatusBadgeClass(delivery.status)}>{delivery.status?.replace(/_/g, ' ')}</span>
                        </td>
                        <td style={{ padding: '1rem 1.5rem', verticalAlign: 'top' }}>
                          {actionLoading === delivery.id ? (
                             <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', fontSize: '0.875rem', color: 'var(--primary-color)' }}>
                               <div className="spinner" style={{ width: '16px', height: '16px', borderWidth: '2px', borderTopColor: 'var(--primary-color)' }}></div>
                               Updating...
                             </div>
                          ) : (
                            <select
                              className="form-control"
                              style={{ padding: '0.375rem 0.5rem', fontSize: '0.875rem', minWidth: '160px', cursor: 'pointer', backgroundColor: 'var(--surface-color)' }}
                              onChange={(e) => {
                                if(e.target.value) handleStatusUpdate(delivery.id, e.target.value);
                                e.target.value = '';
                              }}
                              disabled={['DELIVERED', 'CANCELLED', 'RETURNED'].includes(delivery.status)}
                            >
                              <option value="">Update State...</option>
                              <option value="PENDING_DISPATCH">Pending Dispatch</option>
                              <option value="PICKED_UP">Picked Up</option>
                              <option value="ARRIVED_AT_HUB">Arrived at Hub</option>
                              <option value="DISPATCHED_FROM_HUB">Dispatched from Hub</option>
                              <option value="IN_TRANSIT">In Transit</option>
                              <option value="REACHED_DESTINATION_HUB">Reached Destination Hub</option>
                              <option value="OUT_FOR_DELIVERY">Out For Delivery</option>
                              <option value="DELIVERED">Delivered</option>
                              <option disabled>── Exceptions ──</option>
                              <option value="DELIVERY_FAILED">Delivery Failed</option>
                            </select>
                          )}
                        </td>
                      </tr>
                    ))
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

export default AdminDashboard;
