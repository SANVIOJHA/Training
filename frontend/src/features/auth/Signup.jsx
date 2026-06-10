import React, { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { Package, Eye, EyeOff, CheckCircle2, Circle } from 'lucide-react';
import api from '../../services/api';

const Signup = () => {
  const [formData, setFormData] = useState({ 
    username: '', 
    password: '', 
    phoneNumber: ''
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  // Password Validation States
  const [pwdReqs, setPwdReqs] = useState({
    length: false,
    upper: false,
    lower: false,
    number: false,
    special: false
  });
  const [pwdStrength, setPwdStrength] = useState(0);

  useEffect(() => {
    const pwd = formData.password;
    const reqs = {
      length: pwd.length >= 8,
      upper: /[A-Z]/.test(pwd),
      lower: /[a-z]/.test(pwd),
      number: /[0-9]/.test(pwd),
      special: /[@$!%*?&]/.test(pwd)
    };
    setPwdReqs(reqs);

    const validCount = Object.values(reqs).filter(Boolean).length;
    let strength = 0; // 0=Empty/Very Weak, 1=Weak, 2=Medium, 3=Strong
    if (pwd.length > 0) {
      if (validCount <= 2) strength = 1;
      else if (validCount <= 4) strength = 2;
      else strength = 3;
    }
    setPwdStrength(strength);
  }, [formData.password]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const isFormValid = () => {
    return formData.username.trim() !== '' && 
           formData.phoneNumber.trim() !== '' && 
           Object.values(pwdReqs).every(Boolean);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!isFormValid()) return;
    
    setError('');
    setLoading(true);

    try {
      await api.post('/auth/signup', formData);
      navigate('/auth/login');
    } catch (err) {
      const errData = err.response?.data;
      if (errData?.data && Array.isArray(errData.data)) {
        // Backend validation errors
        setError(errData.data.join(', '));
      } else {
        setError(errData?.message || 'Failed to register');
      }
    } finally {
      setLoading(false);
    }
  };

  const getStrengthConfig = () => {
    switch(pwdStrength) {
      case 1: return { label: 'Weak', color: '#ef4444', width: '33%' };
      case 2: return { label: 'Medium', color: '#f59e0b', width: '66%' };
      case 3: return { label: 'Strong', color: '#10b981', width: '100%' };
      default: return { label: '', color: '#e5e7eb', width: '0%' };
    }
  };

  const strengthConfig = getStrengthConfig();

  const ReqItem = ({ label, met }) => (
    <div style={{ display: 'flex', alignItems: 'center', gap: '0.375rem', fontSize: '0.75rem', color: met ? 'var(--success-color)' : 'var(--text-muted)' }}>
      {met ? <CheckCircle2 size={14} /> : <Circle size={14} />}
      <span>{label}</span>
    </div>
  );

  return (
    <div className="app-layout items-center justify-center" style={{ background: 'linear-gradient(135deg, var(--primary-light) 0%, var(--bg-color) 100%)' }}>
      <div className="card glass-panel" style={{ width: '100%', maxWidth: '450px', padding: '2rem' }}>
        <div style={{ textAlign: 'center', marginBottom: '2rem' }}>
          <div style={{ display: 'inline-flex', alignItems: 'center', justifyContent: 'center', width: '64px', height: '64px', borderRadius: '50%', backgroundColor: 'var(--primary-color)', color: 'white', marginBottom: '1rem' }}>
            <Package size={32} />
          </div>
          <h1 className="page-title">Create Account</h1>
          <p className="page-subtitle">Join SmartCourier today</p>
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
              placeholder="e.g. john_doe"
              value={formData.username} 
              onChange={handleChange} 
              required 
            />
          </div>

          <div className="form-group">
            <label className="form-label">Phone Number <span style={{color: 'var(--danger-color)'}}>*</span></label>
            <input 
              type="tel" 
              name="phoneNumber" 
              className="form-control" 
              placeholder="e.g. 9876543210"
              value={formData.phoneNumber} 
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
                placeholder="Create a strong password"
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
            
            {/* Password Validation UI */}
            <div style={{ marginTop: '0.75rem', padding: '0.75rem', backgroundColor: 'var(--surface-hover)', borderRadius: 'var(--radius-md)' }}>
              <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '0.5rem', fontSize: '0.75rem', fontWeight: 600 }}>
                <span style={{ color: 'var(--text-light)' }}>Password Strength</span>
                <span style={{ color: strengthConfig.color }}>{strengthConfig.label}</span>
              </div>
              <div style={{ height: '4px', backgroundColor: '#e5e7eb', borderRadius: '2px', overflow: 'hidden', marginBottom: '0.75rem' }}>
                <div style={{ height: '100%', width: strengthConfig.width, backgroundColor: strengthConfig.color, transition: 'var(--transition)' }}></div>
              </div>
              <div className="grid grid-cols-2" style={{ gap: '0.25rem' }}>
                <ReqItem label="8+ characters" met={pwdReqs.length} />
                <ReqItem label="1 Uppercase" met={pwdReqs.upper} />
                <ReqItem label="1 Lowercase" met={pwdReqs.lower} />
                <ReqItem label="1 Number" met={pwdReqs.number} />
                <ReqItem label="1 Special (@$!%*?&)" met={pwdReqs.special} />
              </div>
            </div>
          </div>

          <button 
            type="submit" 
            className="btn btn-primary" 
            style={{ width: '100%', marginTop: '1.5rem' }} 
            disabled={loading || !isFormValid()}
          >
            {loading ? <div className="spinner" style={{ borderColor: 'rgba(255,255,255,0.3)', borderTopColor: 'white' }}></div> : 'Sign Up'}
          </button>
        </form>

        <div style={{ marginTop: '2rem', textAlign: 'center', fontSize: '0.875rem' }}>
          Already have an account? <Link to="/auth/login" style={{ fontWeight: 600 }}>Sign in here</Link>
        </div>
        <div style={{ textAlign: 'center', marginTop: '0.75rem' }}>
          <Link to="/" style={{ fontSize: '0.8rem', color: 'var(--text-muted)' }}>← Back to Home</Link>
        </div>
      </div>
    </div>
  );
};

export default Signup;
