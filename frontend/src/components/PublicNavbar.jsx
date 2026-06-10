import React, { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { Package } from 'lucide-react';

const PublicNavbar = () => {
  const [scrolled, setScrolled] = useState(false);
  const location = useLocation();

  useEffect(() => {
    const handleScroll = () => {
      if (window.scrollY > 20) {
        setScrolled(true);
      } else {
        setScrolled(false);
      }
    };

    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  return (
    <nav style={{
      position: 'fixed',
      top: 0,
      left: 0,
      right: 0,
      zIndex: 1000,
      display: 'flex',
//       flexDirection: 'row-reverse',----------this one is used for changing place
      justifyContent: 'space-between',
      alignItems: 'center',
      padding: scrolled ? '0.75rem 2rem' : '1.25rem 2rem',
      backgroundColor: scrolled ? 'rgba(255, 255, 255, 0.95)' : 'transparent',
      backdropFilter: scrolled ? 'blur(12px)' : 'none',
      borderBottom: scrolled ? '1px solid var(--border-color)' : '1px solid transparent',
      boxShadow: scrolled ? 'var(--shadow-sm)' : 'none',
      transition: 'all 0.3s ease'
    }}>
      <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
        <Link to="/" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', textDecoration: 'none' }}>
          <div style={{ 
            backgroundColor: 'var(--primary-color)', 
            borderRadius: '10px', 
            padding: '0.4rem', 
            display: 'flex',
            boxShadow: '0 4px 10px rgba(79, 70, 229, 0.3)'
          }}>
            <Package size={22} color="white" />
          </div>
          <span style={{ fontSize: '2.25rem', fontWeight: 800, color: 'var(--text-main)', letterSpacing: '-0.02em' }}>
            SmartCourier
          </span>
        </Link>
      </div>

{/* home */}
      <div style={{ display: 'flex', alignItems: 'center', gap: '2.5rem' }}>
        <Link to="/" style={{ 
          color: location.pathname === '/' ? 'var(--primary-color)' : 'var(--text-main)', 
          fontWeight: 600, 
          fontSize: '0.95rem',
          transition: 'color 0.2s'
        }}>
          Home
        </Link>

{/* about */}
        <Link to="/about" style={{ 
          color: location.pathname === '/about' ? 'var(--primary-color)' : 'var(--text-main)', 
          fontWeight: 600, 
          fontSize: '0.95rem',
          transition: 'color 0.2s'
        }}>
          About
        </Link>
{/*  login */}
        <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
          <Link to="/auth/login" className="btn btn-ghost" style={{ padding: '0.5rem 1rem', fontWeight: 600 }}>
            Login
          </Link>

{/* signup           */}
          <Link to="/auth/signup" className="btn btn-primary" style={{ padding: '0.6rem 1.5rem', borderRadius: 'var(--radius-full)', fontWeight: 600, boxShadow: '0 4px 12px rgba(79, 70, 229, 0.3)' }}>
            Sign Up
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default PublicNavbar;
