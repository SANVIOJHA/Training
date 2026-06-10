import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { MapPin, Search, Plus, Edit2, Trash2, ShieldCheck, AlertTriangle, CheckCircle, RefreshCw } from 'lucide-react';
import api from '../../services/api';

const HubManagement = () => {
  const [hubs, setHubs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [actionLoading, setActionLoading] = useState(false);
  const [successMsg, setSuccessMsg] = useState('');
  const [searchTerm, setSearchTerm] = useState('');

  // Modal state
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingHub, setEditingHub] = useState(null);

  const [formData, setFormData] = useState({
    name: '',
    code: '',
    city: '',
    state: '',
    address: '',
    contactNumber: ''
  });

  const fetchHubs = async () => {
    try {
      setLoading(true);
      setError('');

      const response = await api.get('/deliveries/hubs');
      setHubs(response.data);
    } catch (err) {
      console.error('Hub fetch error:', err);
      setError('Unable to retrieve hubs right now. Please verify backend availability.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchHubs();
  }, []);

  // Prevent body scroll when modal opens
  useEffect(() => {
    if (!isModalOpen) return;

    const previousOverflow = document.body.style.overflow;
    document.body.style.overflow = 'hidden';

    return () => {
      document.body.style.overflow = previousOverflow;
    };
  }, [isModalOpen]);

  const handleInputChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const openModal = (hub = null) => {
    setError('');

    if (hub) {
      setEditingHub(hub);

      setFormData({
        name: hub.name || '',
        code: hub.code || '',
        city: hub.city || '',
        state: hub.state || '',
        address: hub.address || '',
        contactNumber: hub.contactNumber || ''
      });
    } else {
      setEditingHub(null);

      setFormData({
        name: '',
        code: '',
        city: '',
        state: '',
        address: '',
        contactNumber: ''
      });
    }

    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setEditingHub(null);
    setError('');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    setActionLoading(true);
    setError('');

    try {
      if (editingHub) {
        await api.put(`/deliveries/hubs/${editingHub.id}`, formData);

        setSuccessMsg('Hub updated successfully!');
      } else {
        await api.post('/deliveries/hubs', formData);

        setSuccessMsg('Hub created successfully!');
      }

      closeModal();
      await fetchHubs();
    } catch (err) {
      console.error('Hub save error:', err);

      const backendMessage =
        err.response?.data?.message ||
        err.response?.data?.error ||
        'Unable to save hub information.';

      setError(backendMessage);
    } finally {
      setActionLoading(false);

      setTimeout(() => {
        setSuccessMsg('');
      }, 4000);
    }
  };

  const handleDelete = async (id, name) => {
    const confirmed = window.confirm(
      `Are you sure you want to delete hub "${name}"?`
    );

    if (!confirmed) return;

    try {
      setError('');

      await api.delete(`/deliveries/hubs/${id}`);

      setSuccessMsg('Hub deleted successfully!');

      await fetchHubs();
    } catch (err) {
      console.error('Hub delete error:', err);

      const backendMessage =
        err.response?.data?.message ||
        err.response?.data?.error ||
        'Unable to delete hub.';

      setError(backendMessage);
    }
  };

  const filteredHubs = hubs.filter((hub) => {
    const search = searchTerm.toLowerCase();

    return (
      hub.name?.toLowerCase().includes(search) ||
      hub.code?.toLowerCase().includes(search) ||
      hub.city?.toLowerCase().includes(search)
    );
  });

  return (
    <div className="animate-fade-in relative">
      {/* Header */}
      <div
        className="page-header"
        style={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          marginBottom: '2rem',
          gap: '1rem',
          flexWrap: 'wrap'
        }}
      >
        <div>
          <h1 className="page-title">Hub Management</h1>
          <p className="page-subtitle">
            Configure routing facilities and distribution centers
          </p>
        </div>

        <div
          style={{
            display: 'flex',
            gap: '1rem',
            flexWrap: 'wrap'
          }}
        >
          <button
            className="btn btn-outline"
            onClick={fetchHubs}
            disabled={loading}
            style={{
              display: 'flex',
              alignItems: 'center',
              gap: '0.5rem'
            }}
          >
            <RefreshCw
              size={16}
              className={loading ? 'animate-spin' : ''}
            />
            Refresh
          </button>

          <button
            className="btn btn-primary"
            onClick={() => openModal()}
            style={{
              display: 'flex',
              alignItems: 'center',
              gap: '0.5rem'
            }}
          >
            <Plus size={16} />
            New Hub
          </button>
        </div>
      </div>

      {/* Success Message */}
      {successMsg && (
        <div
          className="animate-fade-in"
          style={{
            padding: '1rem',
            backgroundColor: 'var(--success-color)',
            color: 'white',
            borderRadius: 'var(--radius-md)',
            marginBottom: '1.5rem',
            display: 'flex',
            alignItems: 'center',
            gap: '0.75rem',
            boxShadow: 'var(--shadow-sm)'
          }}
        >
          <CheckCircle size={20} />
          {successMsg}
        </div>
      )}

      {/* Global Error */}
      {error && !isModalOpen && (
        <div
          style={{
            padding: '1rem',
            backgroundColor: '#fff5f5',
            color: '#c53030',
            border: '1px solid #feb2b2',
            borderRadius: '8px',
            marginBottom: '1.5rem',
            display: 'flex',
            alignItems: 'center',
            gap: '0.75rem'
          }}
        >
          <AlertTriangle size={20} />
          {error}
        </div>
      )}

      {/* Hubs Table */}
      <div className="card">
        <div
          className="card-header"
          style={{
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'center',
            gap: '1rem',
            flexWrap: 'wrap'
          }}
        >
          <h3 className="card-title" style={{ margin: 0 }}>
            Active Facilities ({filteredHubs.length})
          </h3>

          <div style={{ position: 'relative' }}>
            <Search
              size={16}
              style={{
                position: 'absolute',
                left: '10px',
                top: '10px',
                color: 'var(--text-muted)'
              }}
            />

            <input
              type="text"
              className="form-control"
              placeholder="Search hubs..."
              style={{
                paddingLeft: '2rem',
                width: '250px'
              }}
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
        </div>

        {loading ? (
          <div style={{ padding: '4rem', textAlign: 'center' }}>
            <div className="spinner"></div>
          </div>
        ) : (
          <div className="card-body" style={{ padding: 0 }}>
            <div style={{ overflowX: 'auto' }}>
              <table
                style={{
                  width: '100%',
                  borderCollapse: 'collapse',
                  textAlign: 'left'
                }}
              >
                <thead>
                  <tr
                    style={{
                      backgroundColor: 'var(--surface-hover)',
                      borderBottom: '1px solid var(--border-color)'
                    }}
                  >
                    <th
                      style={{
                        padding: '1rem 1.5rem',
                        fontWeight: 600,
                        color: 'var(--text-light)',
                        fontSize: '0.875rem'
                      }}
                    >
                      Hub Details
                    </th>

                    <th
                      style={{
                        padding: '1rem 1.5rem',
                        fontWeight: 600,
                        color: 'var(--text-light)',
                        fontSize: '0.875rem'
                      }}
                    >
                      Location
                    </th>

                    <th
                      style={{
                        padding: '1rem 1.5rem',
                        fontWeight: 600,
                        color: 'var(--text-light)',
                        fontSize: '0.875rem'
                      }}
                    >
                      Contact
                    </th>

                    <th
                      style={{
                        padding: '1rem 1.5rem',
                        fontWeight: 600,
                        color: 'var(--text-light)',
                        fontSize: '0.875rem'
                      }}
                    >
                      Actions
                    </th>
                  </tr>
                </thead>

                <tbody>
                  {filteredHubs.length === 0 ? (
                    <tr>
                      <td
                        colSpan="4"
                        style={{
                          padding: '4rem 2rem',
                          textAlign: 'center',
                          color: 'var(--text-muted)'
                        }}
                      >
                        <MapPin
                          size={48}
                          style={{
                            margin: '0 auto 1rem',
                            opacity: 0.5
                          }}
                        />

                        <p>No hubs configured yet.</p>
                      </td>
                    </tr>
                  ) : (
                    filteredHubs.map((hub) => (
                      <tr
                        key={hub.id}
                        style={{
                          borderBottom:
                            '1px solid var(--border-color)'
                        }}
                        className="hover:bg-slate-50"
                      >
                        <td style={{ padding: '1rem 1.5rem' }}>
                          <div
                            style={{
                              fontWeight: 600,
                              color: 'var(--text-main)'
                            }}
                          >
                            {hub.name}
                          </div>

                          <div
                            style={{
                              fontSize: '0.875rem',
                              color: 'var(--text-muted)'
                            }}
                          >
                            Code: {hub.code}
                          </div>
                        </td>

                        <td style={{ padding: '1rem 1.5rem' }}>
                          <div style={{ fontWeight: 500 }}>
                            {hub.city}, {hub.state}
                          </div>

                          <div
                            style={{
                              fontSize: '0.875rem',
                              color: 'var(--text-muted)',
                              maxWidth: '200px',
                              whiteSpace: 'nowrap',
                              overflow: 'hidden',
                              textOverflow: 'ellipsis'
                            }}
                          >
                            {hub.address}
                          </div>
                        </td>

                        <td
                          style={{
                            padding: '1rem 1.5rem',
                            color: 'var(--text-muted)'
                          }}
                        >
                          {hub.contactNumber || '—'}
                        </td>

                        <td style={{ padding: '1rem 1.5rem' }}>
                          <div
                            style={{
                              display: 'flex',
                              gap: '0.5rem'
                            }}
                          >
                            <button
                              className="btn btn-outline"
                              style={{
                                padding: '0.375rem 0.5rem'
                              }}
                              onClick={() => openModal(hub)}
                              title="Edit Hub"
                            >
                              <Edit2 size={14} />
                            </button>

                            <button
                              className="btn btn-outline"
                              style={{
                                padding: '0.375rem 0.5rem',
                                color: 'var(--danger-color)',
                                borderColor: 'var(--danger-color)'
                              }}
                              onClick={() =>
                                handleDelete(hub.id, hub.name)
                              }
                              title="Delete Hub"
                            >
                              <Trash2 size={14} />
                            </button>
                          </div>
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

      {/* Modal */}
      {isModalOpen && (
        <div
          style={{
            position: 'fixed',
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            backgroundColor: 'rgba(0,0,0,0.5)',
            zIndex: 1000,
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            padding: '1rem'
          }}
        >
          <div
            className="card animate-fade-in"
            style={{
              width: '100%',
              maxWidth: '600px',
              maxHeight: '90vh',
              backgroundColor: 'var(--surface-color)',
              borderRadius: 'var(--radius-lg)',
              boxShadow: 'var(--shadow-xl)',
              display: 'flex',
              flexDirection: 'column',
              overflowY: 'auto'
            }}
          >
            {/* Header */}
            <div
              className="card-header"
              style={{
                padding: '1.25rem 1.5rem',
                borderBottom:
                  '1px solid var(--border-color)',
                flexShrink: 0,
                display: 'flex',
                justifyContent: 'space-between',
                alignItems: 'center'
              }}
            >
              <div>
                <h3
                  style={{
                    margin: 0,
                    fontSize: '1.25rem',
                    fontWeight: 700,
                    color: 'var(--text-main)'
                  }}
                >
                  {editingHub
                    ? 'Edit Hub'
                    : 'Create New Hub'}
                </h3>

                <p
                  style={{
                    margin: '0.25rem 0 0 0',
                    fontSize: '0.875rem',
                    color: 'var(--text-muted)'
                  }}
                >
                  Configure routing and operational hub information
                </p>
              </div>

              <button
                type="button"
                onClick={closeModal}
                style={{
                  border: 'none',
                  background: 'transparent',
                  cursor: 'pointer',
                  fontSize: '1.25rem',
                  color: 'var(--text-muted)'
                }}
              >
                ✕
              </button>
            </div>

            {/* Form */}
            <form
              onSubmit={handleSubmit}
              style={{
                display: 'flex',
                flexDirection: 'column',
                flex: 1
              }}
            >
              {/* Body */}
              <div
                className="card-body"
                style={{
                  padding: '1.5rem',
                  display: 'flex',
                  flexDirection: 'column',
                  gap: '1rem'
                }}
              >
                <div>
                  <label className="form-label">
                    Hub Name *
                  </label>

                  <input
                    type="text"
                    className="form-control"
                    name="name"
                    value={formData.name}
                    onChange={handleInputChange}
                    required
                    placeholder="e.g. Delhi Regional Hub"
                  />
                </div>

                <div>
                  <label className="form-label">
                    Hub Code *
                  </label>

                  <input
                    type="text"
                    className="form-control"
                    name="code"
                    value={formData.code}
                    onChange={handleInputChange}
                    required
                    placeholder="e.g. DEL-01"
                  />
                </div>

                {/* Responsive Grid */}
                <div
                  style={{
                    display: 'grid',
                    gridTemplateColumns:
                      'repeat(auto-fit, minmax(220px, 1fr))',
                    gap: '1rem'
                  }}
                >
                  <div>
                    <label className="form-label">City *</label>

                    <input
                      type="text"
                      className="form-control"
                      name="city"
                      value={formData.city}
                      onChange={handleInputChange}
                      required
                      placeholder="e.g. Delhi"
                    />
                  </div>

                  <div>
                    <label className="form-label">State *</label>

                    <input
                      type="text"
                      className="form-control"
                      name="state"
                      value={formData.state}
                      onChange={handleInputChange}
                      required
                      placeholder="e.g. Delhi NCR"
                    />
                  </div>
                </div>

                <div>
                  <label className="form-label">Address</label>

                  <textarea
                    className="form-control"
                    name="address"
                    value={formData.address}
                    onChange={handleInputChange}
                    rows="3"
                    placeholder="Enter operational hub address..."
                    style={{ resize: 'vertical' }}
                  />
                </div>

                <div>
                  <label className="form-label">
                    Contact Number
                  </label>

                  <input
                    type="text"
                    className="form-control"
                    name="contactNumber"
                    value={formData.contactNumber}
                    onChange={handleInputChange}
                    placeholder="e.g. +91 9876543210"
                  />
                </div>

                {/* Modal Error */}
                {error && (
                  <div
                    style={{
                      padding: '0.875rem 1rem',
                      backgroundColor: '#fff5f5',
                      color: '#c53030',
                      border: '1px solid #feb2b2',
                      borderRadius: '8px',
                      fontSize: '0.875rem',
                      display: 'flex',
                      alignItems: 'center',
                      gap: '0.5rem'
                    }}
                  >
                    <AlertTriangle size={18} />
                    {error}
                  </div>
                )}
              </div>

              {/* Footer */}
              <div
                style={{
                  padding: '1rem 1.5rem',
                  borderTop:
                    '1px solid var(--border-color)',
                  display: 'flex',
                  justifyContent: 'flex-end',
                  gap: '1rem',
                  flexShrink: 0,
                  backgroundColor: 'var(--surface-color)'
                }}
              >
                <button
                  type="button"
                  className="btn btn-outline"
                  onClick={closeModal}
                  disabled={actionLoading}
                >
                  Cancel
                </button>

                <button
                  type="submit"
                  className="btn btn-primary"
                  disabled={actionLoading}
                  style={{ minWidth: '120px' }}
                >
                  {actionLoading
                    ? 'Saving...'
                    : editingHub
                    ? 'Update Hub'
                    : 'Create Hub'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default HubManagement;

