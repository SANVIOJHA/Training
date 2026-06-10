import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { ArrowLeft, AlertTriangle, CheckCircle, Package, Search, Filter } from 'lucide-react';
import api from '../../services/api';

const AdminDeliveries = () => {
  const navigate = useNavigate();
  const [deliveries, setDeliveries] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [actionLoading, setActionLoading] = useState(null);
  const [successMsg, setSuccessMsg] = useState('');
  const [hubs, setHubs] = useState([]);
  const [selectedHubs, setSelectedHubs] = useState({});
  
  // Filtering state
  const [searchTerm, setSearchTerm] = useState('');
  const [statusFilter, setStatusFilter] = useState('ALL');

  const fetchDeliveries = async () => {
    try {
      setLoading(true);
      setError('');
      const response = await api.get('/admin/deliveries');
      setDeliveries(response.data);
    } catch (err) {
      console.error("Delivery fetch error:", err);
      setError('Unable to retrieve delivery directory data right now. Please verify service availability.');
    } finally {
      setLoading(false);
    }
  };

  const fetchHubs = async () => {
    try {
      const response = await api.get('/deliveries/hubs');
      setHubs(response.data);
    } catch (err) {
      console.error("Failed to load hubs:", err);
    }
  };

  useEffect(() => {
    fetchDeliveries();
    fetchHubs();
  }, []);

  const handleStatusUpdate = async (id, newStatus) => {
    setActionLoading(id);
    setError('');
    setSuccessMsg('');
    try {
      const hubId = selectedHubs[id] || null;
      await api.put(`/admin/deliveries/${id}/status`, {
        status: newStatus,
        location: 'Admin Operations Center',
        description: `Status updated to ${newStatus} by admin`,
        currentHubId: hubId
      });
      setSuccessMsg(`Delivery ${id} successfully updated to ${newStatus.replace(/_/g, ' ')}`);
      await fetchDeliveries();
    } catch (err) {
      const errMsg = err.response?.data?.message || err.response?.data?.data?.message || err.message;
      setError(`Invalid Status Transition

                Cannot move delivery from
                "Arrived at Hub"
                back to
                "Pending Dispatch".

                Delivery states must progress forward. ${id}: ${errMsg}`);
    } finally {
      setActionLoading(null);
      setTimeout(() => setSuccessMsg(''), 4000);
    }
  };

  const getStatusBadgeClass = (status) => {
    const s = status ? status.toLowerCase().replace(/_/g, '-') : '';
    return `badge badge-${s}`;
  };

  const filteredDeliveries = deliveries.filter(d => {
    const matchesSearch = d.trackingNumber?.toLowerCase().includes(searchTerm.toLowerCase()) || 
                          d.senderName?.toLowerCase().includes(searchTerm.toLowerCase()) ||
                          d.receiverName?.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesStatus = statusFilter === 'ALL' || d.status === statusFilter;
    return matchesSearch && matchesStatus;
  });

  const TableSkeleton = () => (
    <div className="animate-pulse">
      {[1, 2, 3, 4, 5, 6, 7].map(i => (
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
      <div className="page-header" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '2rem' }}>
        <div>
          <h1 className="page-title">All Deliveries</h1>
          <p className="page-subtitle">Complete operational view of all deliveries</p>
        </div>
        <button className="btn btn-outline" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }} onClick={() => navigate('/admin/dashboard')}>
          <ArrowLeft size={18} /> Back to Dashboard
        </button>
      </div>

      {successMsg && (
        <div className="animate-fade-in" style={{ padding: '1rem', backgroundColor: 'var(--success-color)', color: 'white', borderRadius: 'var(--radius-md)', marginBottom: '1.5rem', display: 'flex', alignItems: 'center', gap: '0.75rem', boxShadow: 'var(--shadow-sm)' }}>
          <CheckCircle size={20} /> {successMsg}
        </div>
      )}

      <div className="card">
        <div className="card-header" style={{ padding: '1.5rem', borderBottom: '1px solid var(--border-color)', backgroundColor: 'var(--surface-color)', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <h3 className="card-title" style={{ margin: 0, fontSize: '1.125rem' }}>Delivery Directory ({filteredDeliveries.length})</h3>
          <div style={{ display: 'flex', gap: '1rem' }}>
            <div style={{ position: 'relative' }}>
              <Search size={16} style={{ position: 'absolute', left: '10px', top: '10px', color: 'var(--text-muted)' }} />
              <input 
                type="text" 
                className="form-control" 
                placeholder="Search tracking or names..." 
                style={{ paddingLeft: '2rem', width: '250px' }}
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
            </div>
            <div style={{ position: 'relative' }}>
              <Filter size={16} style={{ position: 'absolute', left: '10px', top: '10px', color: 'var(--text-muted)' }} />
              <select 
                className="form-control" 
                style={{ paddingLeft: '2rem', width: '150px' }}
                value={statusFilter}
                onChange={(e) => setStatusFilter(e.target.value)}
              >
                <option value="ALL">All Statuses</option>
                <option value="PENDING_DISPATCH">Pending Dispatch</option>
                <option value="PICKED_UP">Picked Up</option>
                <option value="ARRIVED_AT_HUB">Arrived at Hub</option>
                <option value="DISPATCHED_FROM_HUB">Dispatched from Hub</option>
                <option value="IN_TRANSIT">In Transit</option>
                <option value="REACHED_DESTINATION_HUB">Reached Dest Hub</option>
                <option value="OUT_FOR_DELIVERY">Out for Delivery</option>
                <option value="DELIVERED">Delivered</option>
                <option value="DELIVERY_FAILED">Delivery Failed</option>
              </select>
            </div>
          </div>
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
                  {filteredDeliveries.length === 0 ? (
                    <tr>
                      <td colSpan="5" style={{ padding: '4rem 2rem', textAlign: 'center', color: 'var(--text-muted)' }}>
                        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '1rem' }}>
                          <Search size={48} style={{ color: 'var(--border-color)' }} />
                          <div>
                            <h4 style={{ margin: '0 0 0.25rem 0', color: 'var(--text-color)' }}>No Results Found</h4>
                            <p style={{ margin: 0, fontSize: '0.875rem' }}>Try adjusting your search terms or filters.</p>
                          </div>
                        </div>
                      </td>
                    </tr>
                  ) : (
                    filteredDeliveries.map(delivery => (
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
                            <div style={{ display: 'flex', flexDirection: 'column', gap: '0.5rem' }}>
                              <select
                                className="form-control"
                                style={{ padding: '0.375rem 0.5rem', fontSize: '0.75rem', minWidth: '160px', backgroundColor: 'var(--surface-color)' }}
                                value={selectedHubs[delivery.id] || delivery.currentHubId || ''}
                                onChange={(e) => setSelectedHubs({...selectedHubs, [delivery.id]: e.target.value})}
                                disabled={['DELIVERED', 'CANCELLED', 'RETURNED'].includes(delivery.status)}
                              >
                                <option value="">-- No Hub Assigned --</option>
                                {hubs.map(hub => (
                                  <option key={hub.id} value={hub.id}>{hub.name}</option>
                                ))}
                              </select>
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
                            </div>
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

export default AdminDeliveries;
