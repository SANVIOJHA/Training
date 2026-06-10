import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import { Package, Eye, EyeOff } from 'lucide-react';
import api from '../../services/api';

const Login = () => {
  const [formData, setFormData] = useState({ username: '', password: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();
  const { login } = useAuth();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      const response = await api.post('/auth/login', formData);
      const data = response.data;

      // New structure: { accessToken, refreshToken, username, role }
      const token = data.accessToken || data.token;
      const username = data.username;
      const role = data.role;

      // Store tokens
      localStorage.setItem('token', token);
      if (data.refreshToken) {
        localStorage.setItem('refreshToken', data.refreshToken);
      }

      // Build user object
      const user = { username, role };
      login(user, token);

      if (role === 'ADMIN') {
        navigate('/admin/dashboard');
      } else {
        navigate('/customer/dashboard');
      }
    } catch (err) {
      console.error(err);
      // Error message from ApiResponse envelope
      const errorMsg = err.response?.data?.message
        || err.response?.data?.data?.message
        || 'Invalid username or password';
      setError(errorMsg);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="app-layout items-center justify-center" style={{ background: 'linear-gradient(135deg, var(--primary-light) 0%, var(--bg-color) 100%)' }}>
      <div className="card glass-panel" style={{ width: '100%', maxWidth: '400px', padding: '2rem' }}>
        <div style={{ textAlign: 'center', marginBottom: '2rem' }}>
          <div style={{ display: 'inline-flex', alignItems: 'center', justifyContent: 'center', width: '64px', height: '64px', borderRadius: '50%', backgroundColor: 'var(--primary-color)', color: 'white', marginBottom: '1rem' }}>
            <Package size={32} />
          </div>
          <h1 className="page-title">Welcome Back</h1>
          <p className="page-subtitle">Sign in to SmartCourier</p>
        </div>

        {error && (
          <div style={{ padding: '0.75rem', backgroundColor: 'var(--danger-color)', color: 'white', borderRadius: 'var(--radius-md)', marginBottom: '1.5rem', fontSize: '0.875rem' }}>
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label className="form-label">Username <span style={{color: 'var(--danger-color)'}}>*</span></label>
            <input 
              type="text" 
              name="username" 
              className="form-control" 
              placeholder="Enter your username"
              value={formData.username} 
              onChange={handleChange} 
              required 
            />
          </div>
          
          <div className="form-group">
            <label className="form-label">Password <span style={{color: 'var(--danger-color)'}}>*</span></label>
            <div style={{ position: 'relative' }}>
              <input 
                type={showPassword ? "text" : "password"} 
                name="password" 
                className="form-control" 
                placeholder="Enter your password"
                value={formData.password} 
                onChange={handleChange} 
                required 
                style={{ paddingRight: '2.5rem' }}
              />
              <button 
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                style={{ position: 'absolute', right: '0.75rem', top: '50%', transform: 'translateY(-50%)', background: 'none', border: 'none', color: 'var(--text-muted)', cursor: 'pointer' }}
              >
                {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
              </button>
            </div>
          </div>

          <button type="submit" className="btn btn-primary" style={{ width: '100%', marginTop: '1rem' }} disabled={loading || !formData.username || !formData.password}>
            {loading ? <div className="spinner" style={{ borderColor: 'rgba(255,255,255,0.3)', borderTopColor: 'white' }}></div> : 'Sign In'}
          </button>
        </form>

        <div style={{ marginTop: '2rem', textAlign: 'center', fontSize: '0.875rem' }}>
          Don't have an account? <Link to="/auth/signup" style={{ fontWeight: 600 }}>Sign up here</Link>
        </div>
        <div style={{ textAlign: 'center', marginTop: '0.75rem' }}>
          <Link to="/" style={{ fontSize: '0.8rem', color: 'var(--text-muted)' }}>← Back to Home</Link>
        </div>
      </div>
    </div>
  );
};

export default Login;
