import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Package, Truck, Search, AlertCircle, Plus, ChevronRight } from 'lucide-react';
import api from '../../services/api';

const CustomerDashboard = () => {
  const [deliveries, setDeliveries] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchDeliveries = async () => {
      try {
        const response = await api.get('/deliveries/my');
        setDeliveries(response.data);
      } catch (err) {
        setError('Failed to load deliveries');
      } finally {
        setLoading(false);
      }
    };

    fetchDeliveries();
  }, []);

  const getStatusBadgeClass = (status) => {
    const s = status ? status.toLowerCase().replace(/_/g, '-') : '';
    return `badge badge-${s}`;
  };

  return (
    <div className="animate-fade-in">
      <div className="page-header" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <div>
          <h1 className="page-title">My Deliveries</h1>
          <p className="page-subtitle">Track and manage your packages</p>
        </div>
        <Link to="/customer/deliveries/new" className="btn btn-primary">
          <Plus size={20} /> Book New Delivery
        </Link>
      </div>

      {error && (
        <div style={{ padding: '1rem', backgroundColor: 'var(--danger-color)', color: 'white', borderRadius: 'var(--radius-md)', marginBottom: '2rem', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <AlertCircle size={20} /> {error}
        </div>
      )}

      {loading ? (
        <div style={{ display: 'flex', justifyContent: 'center', padding: '3rem' }}>
          <div className="spinner"></div>
        </div>
      ) : deliveries.length === 0 ? (
        <div className="card" style={{ padding: '4rem 2rem', textAlign: 'center' }}>
          <Package size={48} style={{ color: 'var(--text-light)', margin: '0 auto 1rem auto' }} />
          <h3>No Deliveries Yet</h3>
          <p style={{ color: 'var(--text-muted)', marginBottom: '1.5rem' }}>You haven't booked any deliveries.</p>
          <Link to="/customer/deliveries/new" className="btn btn-primary">Book Your First Delivery</Link>
        </div>
      ) : (
        <div className="grid grid-cols-1">
          {deliveries.map(delivery => (
            <div key={delivery.id} className="card" style={{ display: 'flex', flexDirection: 'column' }}>
              <div className="card-header">
                <div>
                  <h3 className="card-title" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                    <Truck size={20} color="var(--primary-color)" /> 
                    {delivery.trackingNumber || `Delivery #${delivery.id}`}
                  </h3>
                  <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)', marginTop: '0.25rem' }}>
                    Created on {new Date(delivery.createdAt).toLocaleDateString()}
                  </div>
                </div>
                <span className={getStatusBadgeClass(delivery.status)}>
                  {delivery.status?.replace(/_/g, ' ')}
                </span>
              </div>
              <div className="card-body" style={{ flex: 1 }}>
                <div className="grid grid-cols-2" style={{ gap: '1rem' }}>
                  <div>
                    <div style={{ fontSize: '0.75rem', fontWeight: 600, color: 'var(--text-light)', textTransform: 'uppercase', letterSpacing: '0.05em' }}>From</div>
                    <div style={{ fontWeight: 500 }}>{delivery.senderName}</div>
                    <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>{delivery.source}</div>
                  </div>
                  <div>
                    <div style={{ fontSize: '0.75rem', fontWeight: 600, color: 'var(--text-light)', textTransform: 'uppercase', letterSpacing: '0.05em' }}>To</div>
                    <div style={{ fontWeight: 500 }}>{delivery.receiverName}</div>
                    <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>{delivery.destination}</div>
                  </div>
                </div>
              </div>
              <div className="card-footer" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', padding: '1rem 1.5rem' }}>
                <div style={{ fontWeight: 600 }}>RS. {delivery.price?.toFixed(2) || '0.00'}</div>
                <button 
                  className="btn btn-outline" 
                  onClick={() => navigate(`/customer/track/${delivery.trackingNumber}`)}
                  disabled={!delivery.trackingNumber}
                >
                  Track Package <ChevronRight size={16} />
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default CustomerDashboard;
