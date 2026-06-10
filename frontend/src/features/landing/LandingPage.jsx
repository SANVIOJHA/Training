import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Package, Truck, MapPin, Shield, Zap, Clock, ArrowRight, CheckCircle, Search, PlayCircle } from 'lucide-react';
import PublicNavbar from '../../components/PublicNavbar';

const LandingPage = () => {
  const [trackingNumber, setTrackingNumber] = useState('');
  const [isVisible, setIsVisible] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    setIsVisible(true);
  }, []);

  const handleTrack = (e) => {
    e.preventDefault();
    if (trackingNumber.trim()) {
      // In a real app, this would be a public tracking route, but for now we route to the customer tracking if they are logged in,
      // or we can just show a message. Actually, since tracking is protected, we can redirect to login with a redirect param,
      // OR we can make tracking public? The prompt says "Optional delivery tracking input (UI only or connected if possible)".
      // Let's just make it UI only, or show an alert if they try to track.
      alert(`Tracking feature for ${trackingNumber} requires login in the current version. Redirecting to login...`);
      navigate('/auth/login');
    }
  };

  return (
    <div style={{ minHeight: '100vh', backgroundColor: '#fafafa', fontFamily: '"Inter", sans-serif' }}>
      <PublicNavbar />

      {/* Hero Section */}
      <section style={{
        padding: '10rem 2rem 6rem',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        textAlign: 'center',
        background: 'radial-gradient(circle at top, #eef2ff 0%, #fafafa 60%)',
        position: 'relative',
        overflow: 'hidden'
      }}>
        {/* Background decorative elements */}
        <div style={{ position: 'absolute', top: '15%', left: '5%', width: '300px', height: '300px', background: 'rgba(79, 70, 229, 0.05)', borderRadius: '50%', filter: 'blur(40px)' }}></div>
        <div style={{ position: 'absolute', top: '30%', right: '5%', width: '400px', height: '400px', background: 'rgba(16, 185, 129, 0.05)', borderRadius: '50%', filter: 'blur(60px)' }}></div>

        <div style={{ 
          maxWidth: '800px', 
          position: 'relative', 
          zIndex: 10,
          opacity: isVisible ? 1 : 0,
          transform: isVisible ? 'translateY(0)' : 'translateY(20px)',
          transition: 'all 0.8s cubic-bezier(0.16, 1, 0.3, 1)'
        }}>
          <div style={{
            display: 'inline-flex', alignItems: 'center', gap: '0.5rem',
            backgroundColor: 'rgba(79, 70, 229, 0.1)', padding: '0.4rem 1.2rem', borderRadius: '2rem',
            fontSize: '0.85rem', fontWeight: 600, color: 'var(--primary-color)',
            marginBottom: '1.5rem', border: '1px solid rgba(79, 70, 229, 0.2)'
          }}>
            <Zap size={14} fill="currentColor" /> SmartCourier Platform 2.0
          </div>
          
          <h1 style={{ 
            fontSize: 'clamp(3rem, 5vw, 4.5rem)', 
            fontWeight: 800, 
            lineHeight: 1.1, 
            marginBottom: '1.5rem', 
            color: '#0f172a',
            letterSpacing: '-0.03em'
          }}>
            Next-generation logistics <br/>
            <span style={{ 
              background: 'linear-gradient(135deg, var(--primary-color) 0%, #8b5cf6 100%)', 
              WebkitBackgroundClip: 'text', 
              WebkitTextFillColor: 'transparent' 
            }}>
              built for speed.
            </span>
          </h1>
          
          <p style={{ 
            fontSize: '1.25rem', 
            color: '#475569', 
            marginBottom: '2.5rem', 
            lineHeight: 1.6, 
            maxWidth: '600px', 
            margin: '0 auto 3rem' 
          }}>
            An enterprise-grade microservices platform delivering real-time tracking, intelligent routing, and automated SMS notifications.
          </p>
          
          <div style={{ display: 'flex', gap: '1rem', justifyContent: 'center', flexWrap: 'wrap' }}>
            <Link to="/auth/signup" className="btn btn-primary" style={{ 
              padding: '1rem 2.5rem', 
              fontSize: '1.1rem', 
              borderRadius: 'var(--radius-full)',
              boxShadow: '0 10px 25px -5px rgba(79, 70, 229, 0.4)',
              transition: 'all 0.3s ease'
            }}>
              Start Shipping Free <ArrowRight size={18} />
            </Link>
            <Link to="/about" className="btn btn-outline" style={{ 
              padding: '1rem 2.5rem', 
              fontSize: '1.1rem', 
              borderRadius: 'var(--radius-full)',
              backgroundColor: 'white',
              border: '1px solid #e2e8f0',
              color: '#0f172a'
            }}>
              Learn More
            </Link>
          </div>
        </div>

        {/* Tracking Interaction Section */}
        <div style={{
          marginTop: '5rem',
          width: '100%',
          maxWidth: '600px',
          backgroundColor: 'white',
          padding: '2rem',
          borderRadius: '1.5rem',
          boxShadow: '0 20px 40px -15px rgba(0,0,0,0.05), 0 0 0 1px rgba(0,0,0,0.05)',
          position: 'relative',
          zIndex: 10,
          opacity: isVisible ? 1 : 0,
          transform: isVisible ? 'translateY(0)' : 'translateY(40px)',
          transition: 'all 1s cubic-bezier(0.16, 1, 0.3, 1) 0.2s'
        }}>
          <h3 style={{ fontSize: '1.25rem', fontWeight: 700, marginBottom: '1rem', color: '#0f172a', textAlign: 'left' }}>
            Track your package
          </h3>
          <form onSubmit={handleTrack} style={{ display: 'flex', gap: '0.5rem' }}>
            <div style={{ position: 'relative', flex: 1 }}>
              <Search size={20} color="#94a3b8" style={{ position: 'absolute', left: '1rem', top: '50%', transform: 'translateY(-50%)' }} />
              <input 
                type="text" 
                placeholder="Enter tracking number (e.g. TRK-12345)" 
                value={trackingNumber}
                onChange={(e) => setTrackingNumber(e.target.value)}
                style={{
                  width: '100%', padding: '1rem 1rem 1rem 3rem',
                  fontSize: '1rem', borderRadius: '0.75rem',
                  border: '1px solid #e2e8f0', backgroundColor: '#f8fafc',
                  outline: 'none', transition: 'all 0.2s'
                }}
                onFocus={(e) => e.target.style.borderColor = 'var(--primary-color)'}
                onBlur={(e) => e.target.style.borderColor = '#e2e8f0'}
                required
              />
            </div>
            <button type="submit" className="btn btn-primary" style={{ padding: '0 1.5rem', borderRadius: '0.75rem' }}>
              Track
            </button>
          </form>
        </div>
      </section>

{/*       -----------------------------------------------------------Adding video  */}
<section style={{ padding: '2rem 2rem 6rem', maxWidth: '1200px', margin: '0 auto' }}>
  <div style={{
    position: 'relative',
    width: '100%',
    borderRadius: '2rem',
    overflow: 'hidden',
    boxShadow: '0 25px 50px -12px rgba(0, 0, 0, 0.25)'
  }}>

    <video
      width="100%"
      height="500px"
      controls
      autoPlay
      muted
      loop
      style={{ objectFit: 'cover' }}
    >
      <source src="/Video/smartcourier-demo.mp4" type="video/mp4" />
    </video>

  </div>
</section>
{/* ------------------------------------------- */}

      {/* Logo Cloud */}
      <section style={{ borderTop: '1px solid #e2e8f0', borderBottom: '1px solid #e2e8f0', padding: '3rem 2rem', backgroundColor: 'white' }}>
        <div style={{ maxWidth: '1000px', margin: '0 auto', textAlign: 'center' }}>
          <p style={{ fontSize: '0.875rem', fontWeight: 600, color: '#64748b', textTransform: 'uppercase', letterSpacing: '0.05em', marginBottom: '2rem' }}>
            Powered by enterprise-grade technologies
          </p>
          <div style={{ display: 'flex', justifyContent: 'center', gap: '4rem', flexWrap: 'wrap', opacity: 0.6 }}>
            {['Spring Boot', 'React', 'RabbitMQ', 'MySQL', 'Netflix Eureka', 'Twilio'].map((tech) => (
              <span key={tech} style={{ fontSize: '1.25rem', fontWeight: 700, color: '#0f172a' }}>{tech}</span>
            ))}
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section id="features" style={{ padding: '8rem 2rem', maxWidth: '1200px', margin: '0 auto' }}>
        <div style={{ textAlign: 'center', marginBottom: '5rem' }}>
          <h2 style={{ fontSize: 'clamp(2.5rem, 4vw, 3rem)', fontWeight: 800, marginBottom: '1rem', color: '#0f172a', letterSpacing: '-0.02em' }}>
            Everything you need to scale
          </h2>
          <p style={{ color: '#475569', fontSize: '1.25rem', maxWidth: '600px', margin: '0 auto' }}>
            Stop wrestling with outdated legacy systems. Our platform gives you full control from origin to destination.
          </p>
        </div>
        
        <div className="grid grid-cols-3" style={{ gap: '2.5rem' }}>
          {[
            { icon: Truck, title: 'Real-Time Tracking', desc: 'Track every delivery in real-time with a visual timeline showing each step from creation to delivery.', color: '#4f46e5' },
            { icon: Shield, title: 'Secure Authentication', desc: 'Role-based access control with JWT tokens ensures customers and admins each get tailored experiences.', color: '#10b981' },
            { icon: Zap, title: 'Instant Notifications', desc: 'Automated SMS alerts on registration, delivery creation, and every critical status update.', color: '#f59e0b' },
            { icon: MapPin, title: 'Route Optimization', desc: 'Intelligent source-to-destination management with robust delivery data structures.', color: '#0ea5e9' },
            { icon: Clock, title: 'Event-Driven Core', desc: 'Built on asynchronous RabbitMQ message queues for non-blocking, reliable operations.', color: '#8b5cf6' },
            { icon: CheckCircle, title: 'Admin Control Center', desc: 'Comprehensive admin panel to manage deliveries, update statuses, and control user roles.', color: '#ef4444' }
          ].map((feature, i) => (
            <div key={i} style={{ 
              padding: '2.5rem', 
              backgroundColor: 'white',
              borderRadius: '1.5rem',
              boxShadow: '0 4px 6px -1px rgba(0, 0, 0, 0.05), 0 2px 4px -1px rgba(0, 0, 0, 0.03)',
              border: '1px solid #f1f5f9',
              transition: 'transform 0.3s ease, box-shadow 0.3s ease',
              cursor: 'default'
            }}
            onMouseOver={(e) => {
              e.currentTarget.style.transform = 'translateY(-5px)';
              e.currentTarget.style.boxShadow = '0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04)';
            }}
            onMouseOut={(e) => {
              e.currentTarget.style.transform = 'translateY(0)';
              e.currentTarget.style.boxShadow = '0 4px 6px -1px rgba(0, 0, 0, 0.05), 0 2px 4px -1px rgba(0, 0, 0, 0.03)';
            }}
            >
              <div style={{ 
                display: 'inline-flex', padding: '1rem', borderRadius: '1rem',
                backgroundColor: `${feature.color}15`, color: feature.color, marginBottom: '1.5rem'
              }}>
                <feature.icon size={28} />
              </div>
              <h3 style={{ fontSize: '1.25rem', fontWeight: 700, marginBottom: '0.75rem', color: '#0f172a' }}>{feature.title}</h3>
              <p style={{ color: '#64748b', fontSize: '1rem', lineHeight: 1.6 }}>{feature.desc}</p>
            </div>
          ))}
        </div>
      </section>

      {/* CTA Section */}
      <section style={{
        margin: '0 2rem 4rem',
        padding: '5rem 2rem', 
        textAlign: 'center',
        background: 'linear-gradient(135deg, var(--primary-color) 0%, #3730a3 100%)',
        color: 'white',
        borderRadius: '2rem',
        boxShadow: '0 20px 25px -5px rgba(79, 70, 229, 0.4)'
      }}>
        <h2 style={{ fontSize: '2.5rem', fontWeight: 800, marginBottom: '1rem', color: 'white' }}>Start shipping smarter today.</h2>
        <p style={{ fontSize: '1.25rem', opacity: 0.9, marginBottom: '2.5rem', maxWidth: '600px', margin: '0 auto 2.5rem' }}>
          Join thousands of businesses managing their logistics with SmartCourier.
        </p>
        <Link to="/auth/signup" className="btn" style={{ 
          backgroundColor: 'white', color: 'var(--primary-color)', 
          fontWeight: 700, padding: '1rem 3rem', fontSize: '1.1rem',
          borderRadius: 'var(--radius-full)',
          boxShadow: '0 4px 6px rgba(0,0,0,0.1)'
        }}>
          Create your free account
        </Link>
      </section>

      {/* Footer */}
      <footer style={{ padding: '3rem 2rem', borderTop: '1px solid #e2e8f0', backgroundColor: 'white' }}>
        <div style={{ maxWidth: '1200px', margin: '0 auto', display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '2rem' }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
            <div style={{ backgroundColor: 'var(--primary-color)', borderRadius: '8px', padding: '0.3rem', display: 'flex' }}>
              <Package size={18} color="white" />
            </div>
            <span style={{ fontSize: '1.1rem', fontWeight: 700, color: '#0f172a' }}>SmartCourier</span>
          </div>
          
          <div style={{ display: 'flex', gap: '2rem', fontSize: '0.9rem', color: '#64748b', fontWeight: 500 }}>
            <Link to="/" style={{ color: '#64748b' }}>Home</Link>
            <Link to="/about" style={{ color: '#64748b' }}>About</Link>
            <Link to="/auth/login" style={{ color: '#64748b' }}>Login</Link>
            <Link to="/auth/signup" style={{ color: '#64748b' }}>Sign Up</Link>
          </div>
          
          <p style={{ color: '#94a3b8', fontSize: '0.875rem', margin: 0 }}>
            © {new Date().getFullYear()} SmartCourier. All rights reserved.
          </p>
        </div>
      </footer>
    </div>
  );
};

export default LandingPage;
