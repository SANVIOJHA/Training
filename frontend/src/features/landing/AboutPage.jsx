import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Target, Globe, Users, ArrowRight } from 'lucide-react';
import PublicNavbar from '../../components/PublicNavbar';

const AboutPage = () => {
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    setIsVisible(true);
  }, []);

  return (
    <div style={{ minHeight: '100vh', backgroundColor: '#fafafa', fontFamily: '"Inter", sans-serif' }}>
      <PublicNavbar />

      {/* Hero Section */}
      <section style={{
        padding: '10rem 2rem 5rem',
        textAlign: 'center',
        background: 'linear-gradient(180deg, #eef2ff 0%, #fafafa 100%)',
        position: 'relative'
      }}>
        <div style={{
          maxWidth: '800px',
          margin: '0 auto',
          opacity: isVisible ? 1 : 0,
          transform: isVisible ? 'translateY(0)' : 'translateY(20px)',
          transition: 'all 0.8s cubic-bezier(0.16, 1, 0.3, 1)'
        }}>
          <h1 style={{
            fontSize: 'clamp(2.5rem, 4vw, 4rem)',
            fontWeight: 800,
            lineHeight: 1.1,
            marginBottom: '1.5rem',
            color: '#0f172a',
            letterSpacing: '-0.03em'
          }}>
            Redefining the future of <br/>
            <span style={{ color: 'var(--primary-color)' }}>logistics.</span>
          </h1>
          <p style={{
            fontSize: '1.25rem',
            color: '#475569',
            lineHeight: 1.6,
            maxWidth: '600px',
            margin: '0 auto'
          }}>
            We believe that moving physical goods should be as fast, reliable, and transparent as sending an email.
          </p>
        </div>
      </section>

      {/* Image Block Section */}
     <section style={{ padding: '2rem 2rem 5rem', maxWidth: '1200px', margin: '0 auto' }}>
       <div style={{
         width: '100%',
         height: '400px',
         borderRadius: '2rem',
         overflow: 'hidden',
         boxShadow: '0 25px 50px -12px rgba(0, 0, 0, 0.25)',
         background: 'url("/About_image.webp") center/contain',
         backgroundRepeat: 'no-repeat',
         backgroundColor: '#e5e7eb',
         position: 'relative'
       }}>
         <div style={{
           position: 'absolute',
           inset: 0,
           backgroundColor: 'rgba(15, 23, 42, 0.1)'
         }}></div>
       </div>
     </section>

      {/* Mission & Vision */}
      <section style={{ padding: '4rem 2rem', maxWidth: '1000px', margin: '0 auto' }}>
        <div className="grid grid-cols-2" style={{ gap: '4rem', alignItems: 'flex-start' }}>
          <div>
            <div style={{
              display: 'inline-flex', padding: '1rem', borderRadius: '1rem',
              backgroundColor: 'rgba(79, 70, 229, 0.1)', color: 'var(--primary-color)', marginBottom: '1.5rem'
            }}>
              <Target size={28} />
            </div>
            <h2 style={{ fontSize: '2rem', fontWeight: 800, color: '#0f172a', marginBottom: '1rem' }}>Our Mission</h2>
            <p style={{ fontSize: '1.1rem', color: '#475569', lineHeight: 1.7 }}>
              To provide businesses and individuals with a seamless, end-to-end delivery management system.
              We eliminate the black box of traditional shipping by offering real-time tracking, intelligent event-driven updates, and absolute transparency.
            </p>
          </div>
          <div>
            <div style={{
              display: 'inline-flex', padding: '1rem', borderRadius: '1rem',
              backgroundColor: 'rgba(16, 185, 129, 0.1)', color: '#10b981', marginBottom: '1.5rem'
            }}>
              <Globe size={28} />
            </div>
            <h2 style={{ fontSize: '2rem', fontWeight: 800, color: '#0f172a', marginBottom: '1rem' }}>Our Vision</h2>
            <p style={{ fontSize: '1.1rem', color: '#475569', lineHeight: 1.7 }}>
              A world where every package, regardless of size or destination, is routed with maximum efficiency and zero friction. We are building the technological infrastructure to support the next decade of global commerce.
            </p>
          </div>
        </div>
      </section>

      {/* Split Text / Image Feature */}
      <section style={{ padding: '6rem 2rem', backgroundColor: 'white', borderTop: '1px solid #f1f5f9', borderBottom: '1px solid #f1f5f9' }}>
        <div style={{ maxWidth: '1200px', margin: '0 auto', display: 'flex', alignItems: 'center', gap: '4rem', flexWrap: 'wrap' }}>
          <div style={{ flex: '1 1 400px' }}>
            <h2 style={{ fontSize: '2.5rem', fontWeight: 800, color: '#0f172a', marginBottom: '1.5rem', lineHeight: 1.2 }}>
              Built for scale. <br/>Designed for humans.
            </h2>
            <p style={{ fontSize: '1.15rem', color: '#475569', lineHeight: 1.7, marginBottom: '2rem' }}>
              SmartCourier isn't just another logistics company. We are a technology company that does logistics.
              Our entire platform is built on a resilient microservices architecture using Spring Boot, React, and RabbitMQ.
              This means our system never sleeps, never drops a message, and scales infinitely with your business.
            </p>
            <ul style={{ listStyle: 'none', padding: 0, margin: 0 }}>
              {['Independently scalable microservices', 'Real-time asynchronous processing', 'Fault-tolerant architecture'].map((item, i) => (
                <li key={i} style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '1rem', fontSize: '1.05rem', color: '#0f172a', fontWeight: 500 }}>
                  <div style={{ width: '24px', height: '24px', borderRadius: '50%', backgroundColor: '#e0e7ff', display: 'flex', alignItems: 'center', justifyContent: 'center', color: 'var(--primary-color)' }}>
                    <ArrowRight size={14} />
                  </div>
                  {item}
                </li>
              ))}
            </ul>
          </div>
          <div style={{ flex: '1 1 500px' }}>
            <div style={{
              width: '100%',
              height: '500px',
              borderRadius: '2rem',
              background: 'url("/about_img2.png") center/contain',
              backgroundRepeat: 'no-repeat',
              backgroundColor: '#f8fafc',
              boxShadow: '0 20px 40px -15px rgba(0,0,0,0.1)'
            }}></div>
          </div>
        </div>
      </section>

      {/* Stats */}
      <section style={{ padding: '6rem 2rem', textAlign: 'center', maxWidth: '1000px', margin: '0 auto' }}>
        <h2 style={{ fontSize: '2rem', fontWeight: 800, color: '#0f172a', marginBottom: '4rem' }}>SmartCourier by the numbers</h2>
        <div className="grid grid-cols-3" style={{ gap: '2rem' }}>
          {[
            { value: '50+', label: 'Cities Covered' },
            { value: '2.5M', label: 'Packages Delivered' },
            { value: '99.9%', label: 'API Uptime' }
          ].map((stat, i) => (
            <div key={i}>
              <div style={{ fontSize: '3rem', fontWeight: 800, color: 'var(--primary-color)', marginBottom: '0.5rem', letterSpacing: '-0.02em' }}>
                {stat.value}
              </div>
              <div style={{ fontSize: '1.1rem', color: '#64748b', fontWeight: 500 }}>{stat.label}</div>
            </div>
          ))}
        </div>
      </section>

      {/* CTA Section */}
      <section style={{
        margin: '0 2rem 4rem',
        padding: '5rem 2rem',
        textAlign: 'center',
        backgroundColor: '#0f172a',
        color: 'white',
        borderRadius: '2rem',
        backgroundImage: 'radial-gradient(circle at center, #1e293b 0%, #0f172a 100%)'
      }}>
        <h2 style={{ fontSize: '2.5rem', fontWeight: 800, marginBottom: '1.5rem', color: 'white' }}>Join our journey.</h2>
        <p style={{ fontSize: '1.25rem', color: '#94a3b8', marginBottom: '2.5rem', maxWidth: '600px', margin: '0 auto 2.5rem' }}>
          Experience the difference of a platform built for modern logistics.
        </p>
        <Link to="/auth/signup" className="btn btn-primary" style={{
          fontWeight: 700, padding: '1rem 3rem', fontSize: '1.1rem',
          borderRadius: 'var(--radius-full)'
        }}>
          Get Started Today
        </Link>
      </section>
    </div>
  );
};

export default AboutPage;
