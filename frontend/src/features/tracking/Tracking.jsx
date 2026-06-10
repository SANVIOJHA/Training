import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { MapPin, Package, Truck, CheckCircle, ChevronLeft, AlertCircle, Clock, Navigation } from 'lucide-react';
import api from '../../services/api';

const Tracking = () => {
  const { trackingNumber } = useParams();
  const [delivery, setDelivery] = useState(null);
  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchTracking = async () => {
      try {
        const deliveryRes = await api.get(`/deliveries/tracking/${trackingNumber}`);
        const deliveryData = deliveryRes.data;
        setDelivery(deliveryData);

        try {
          const trackingRes = await api.get(`/tracking/${deliveryData.id}`);
          setEvents(Array.isArray(trackingRes.data) ? trackingRes.data : []);
        } catch (trackErr) {
          setEvents([]);
        }
      } catch (err) {
        setError('Tracking information not found for this tracking number.');
      } finally {
        setLoading(false);
      }
    };

    fetchTracking();
    const interval = setInterval(fetchTracking, 15000);
    return () => clearInterval(interval);
  }, [trackingNumber]);

  if (loading && !delivery) {
    return (
      <div className="flex justify-center flex-col items-center gap-4" style={{ padding: '8rem' }}>
        <div className="spinner" style={{ width: '40px', height: '40px', borderWidth: '4px', borderTopColor: 'var(--primary-color)' }}></div>
        <div style={{ color: 'var(--text-muted)' }}>Retrieving live shipment data...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="card animate-fade-in" style={{ padding: '4rem 2rem', textAlign: 'center', maxWidth: '600px', margin: '2rem auto' }}>
        <AlertCircle size={48} style={{ margin: '0 auto 1rem', color: 'var(--danger-color)' }} />
        <h2 style={{ color: 'var(--text-main)', marginBottom: '0.5rem' }}>Shipment Not Found</h2>
        <p style={{ color: 'var(--text-muted)', marginBottom: '2rem' }}>{error}</p>
        <Link to="/customer/dashboard" className="btn btn-primary">Return to Dashboard</Link>
      </div>
    );
  }

  // Helper to extract city from address string
  const extractCity = (address) => {
    if (!address) return 'Hub';
    const parts = address.split(/[,|-]/);
    return parts[0].trim();
  };

  // Simulated Hub Journey Mapping
  const processEvents = (rawEvents, currentDelivery) => {
    if (!rawEvents || rawEvents.length === 0) return [];
    
    const sourceCity = extractCity(currentDelivery?.source);
    const destCity = extractCity(currentDelivery?.destination);
    
    // Sort events ascending to simulate journey chronologically
    const sorted = [...rawEvents].sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp));
    let processed = [];
    
    sorted.forEach((event, index) => {
      let title = event.status;
      let desc = event.description;
      let loc = event.location;
      let icon = Clock;
      
      switch(event.status) {
        case 'CREATED':
        case 'BOOKED':
        case 'DRAFT':
          title = 'Shipment Data Received';
          desc = 'Booking confirmed. Pending pickup scheduling.';
          loc = sourceCity;
          icon = Package;
          break;
        case 'PICKED_UP':
          title = 'Package Picked Up';
          desc = `Shipment picked up from sender in ${sourceCity}.`;
          loc = `${sourceCity} Origin Center`;
          icon = MapPin;
          break;
        case 'ARRIVED_AT_HUB':
        case 'REACHED_DESTINATION_HUB':
          title = `Arrived at ${event.location || 'Hub'}`;
          desc = `Shipment arrived at ${event.location || 'intermediate'} routing facility.`;
          loc = event.location || 'Sorting Center';
          icon = Truck;
          break;
        case 'DISPATCHED_FROM_HUB':
        case 'IN_TRANSIT':
          // Simulate an artificial departure event for better realism if needed
          if (index > 0 && processed.length > 0) {
              const lastEvent = processed[processed.length - 1];
              if (!lastEvent.status.includes('TRANSIT') && !lastEvent.status.includes('DISPATCHED')) {
                 processed.push({
                   ...event,
                   id: event.id + '_departure',
                   title: `Departed from ${lastEvent.location || 'Hub'}`,
                   description: `Shipment departed and is in transit.`,
                   location: lastEvent.location || 'Sorting Hub',
                   timestamp: new Date(new Date(event.timestamp).getTime() - 1800000).toISOString(), // 30 mins earlier
                   status: 'IN_TRANSIT_DEPARTURE',
                   icon: Navigation
                 });
              }
          }
          title = 'In Transit';
          desc = 'Shipment is currently in transit to the next facility.';
          loc = event.location || 'In Transit';
          icon = Truck;
          break;
        case 'OUT_FOR_DELIVERY':
          title = 'Out for Delivery';
          desc = `Package is out for delivery with courier in ${destCity}.`;
          loc = event.location || `${destCity} Distribution Center`;
          icon = Navigation;
          break;
        case 'DELIVERED':
          title = 'Successfully Delivered';
          desc = `Package delivered safely to receiver in ${destCity}.`;
          loc = event.location || destCity;
          icon = CheckCircle;
          break;
        case 'DELIVERY_FAILED':
        case 'DELAYED':
        case 'FAILED':
        case 'RETURNED':
        case 'CANCELLED':
          title = `Exception: ${event.status.replace(/_/g, ' ')}`;
          desc = 'Delivery is experiencing an operational delay or exception.';
          loc = event.location || 'Exception Handling Hub';
          icon = AlertCircle;
          break;
        default:
          title = event.status.replace(/_/g, ' ');
          loc = event.location || loc;
      }
      
      processed.push({ ...event, title, description: desc, location: loc, icon });
    });
    
    // Sort descending for the UI timeline display (newest first)
    return processed.sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp));
  };

  const formattedEvents = processEvents(events, delivery);

  const timelineSteps = [
    { status: 'CREATED', label: 'Created', icon: Package },
    { status: 'PICKED_UP', label: 'Picked Up', icon: MapPin },
    { status: 'ARRIVED_AT_HUB', label: 'Hub Transit', icon: Truck },
    { status: 'OUT_FOR_DELIVERY', label: 'Out for Delivery', icon: Navigation },
    { status: 'DELIVERED', label: 'Delivered', icon: CheckCircle }
  ];

  const currentStatus = delivery?.status || '';
  
  // Mapping currentStatus to the visual progress bar step
  let visualStatusIndex = -1;
  if (['CREATED', 'DRAFT', 'BOOKED', 'PENDING_DISPATCH'].includes(currentStatus)) visualStatusIndex = 0;
  else if (['PICKED_UP'].includes(currentStatus)) visualStatusIndex = 1;
  else if (['ARRIVED_AT_HUB', 'DISPATCHED_FROM_HUB', 'IN_TRANSIT', 'REACHED_DESTINATION_HUB'].includes(currentStatus)) visualStatusIndex = 2;
  else if (['OUT_FOR_DELIVERY'].includes(currentStatus)) visualStatusIndex = 3;
  else if (['DELIVERED'].includes(currentStatus)) visualStatusIndex = 4;
  
  const currentStatusIndex = visualStatusIndex;
  const isException = ['DELIVERY_FAILED', 'DELAYED', 'FAILED', 'RETURNED', 'CANCELLED'].includes(currentStatus);

  const calculateEstimatedDelivery = (del) => {
    if (!del || !del.createdAt) return 'Calculating...';
    if (['DELIVERED'].includes(del.status)) return 'Delivered';
    if (['RETURNED', 'CANCELLED'].includes(del.status)) return 'Terminated';
    if (['DELAYED', 'FAILED', 'DELIVERY_FAILED'].includes(del.status)) return 'Delayed / Pending Update';
    
    const createdDate = new Date(del.createdAt);
    // Add 3 days for standard delivery estimate
    createdDate.setDate(createdDate.getDate() + 3);
    return `Est. ${createdDate.toLocaleDateString('en-US', { weekday: 'short', month: 'short', day: 'numeric' })}`;
  };

  return (
    <div className="animate-fade-in" style={{ maxWidth: '1000px', margin: '0 auto' }}>
      <Link to="/customer/dashboard" style={{ display: 'inline-flex', alignItems: 'center', gap: '0.5rem', marginBottom: '1.5rem', fontWeight: 500, color: 'var(--text-muted)', textDecoration: 'none', transition: 'color 0.2s' }} className="hover:text-primary">
        <ChevronLeft size={20} /> Back to Dashboard
      </Link>
      
      <div className="page-header" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start' }}>
        <div>
          <h1 className="page-title" style={{ fontSize: '1.75rem', marginBottom: '0.25rem' }}>Live Tracking</h1>
          <p className="page-subtitle" style={{ fontSize: '1rem' }}>
            Waybill: <strong style={{ color: 'var(--text-main)', letterSpacing: '0.5px' }}>{trackingNumber}</strong>
          </p>
        </div>
        <div style={{ textAlign: 'right' }}>
           <span className={`badge badge-${isException ? 'danger' : currentStatus.toLowerCase().replace(/_/g, '-')}`} style={{ fontSize: '0.875rem', padding: '0.5rem 1rem' }}>
             {currentStatus.replace(/_/g, ' ')}
           </span>
           <div style={{ fontSize: '0.75rem', color: 'var(--text-muted)', marginTop: '0.5rem', display: 'flex', alignItems: 'center', gap: '0.25rem', justifyContent: 'flex-end' }}>
             <div className="animate-pulse" style={{ width: '8px', height: '8px', borderRadius: '50%', backgroundColor: isException ? 'var(--danger-color)' : 'var(--success-color)' }}></div>
             Live Updates Active
           </div>
        </div>
      </div>

      {/* Delivery Routing Summary */}
      <div className="card" style={{ marginBottom: '2rem', borderTop: '4px solid var(--primary-color)', boxShadow: 'var(--shadow-md)' }}>
        <div className="card-body" style={{ padding: '2rem' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.5rem', paddingBottom: '1rem', borderBottom: '1px solid var(--border-color)' }}>
             <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                <Clock size={18} color="var(--primary-color)" /> 
                <span style={{ fontWeight: 600, color: 'var(--text-main)' }}>Estimated Delivery</span>
             </div>
             <div style={{ fontSize: '1.125rem', fontWeight: 700, color: isException ? 'var(--danger-color)' : (currentStatus === 'DELIVERED' ? 'var(--success-color)' : 'var(--text-main)') }}>
                {calculateEstimatedDelivery(delivery)}
             </div>
          </div>
          
          <div className="grid grid-cols-2" style={{ gap: '2rem' }}>
            <div style={{ position: 'relative' }}>
              <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '0.5rem' }}>
                <div style={{ backgroundColor: 'var(--surface-hover)', padding: '0.5rem', borderRadius: '50%' }}>
                  <MapPin size={18} color="var(--primary-color)" />
                </div>
                <div style={{ fontSize: '0.875rem', fontWeight: 600, color: 'var(--text-light)', textTransform: 'uppercase', letterSpacing: '0.5px' }}>Origin</div>
              </div>
              <div style={{ paddingLeft: '3rem' }}>
                <div style={{ fontWeight: 600, fontSize: '1.125rem', marginBottom: '0.25rem' }}>{extractCity(delivery?.source)}</div>
                <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>{delivery?.senderName}</div>
              </div>
            </div>
            
            <div style={{ position: 'relative' }}>
               {/* Decorative connector arrow for larger screens */}
               <div style={{ position: 'absolute', left: '-2rem', top: '2rem', color: 'var(--border-color)', display: 'none' }} className="sm:block">
                  <Navigation size={24} style={{ transform: 'rotate(90deg)' }} />
               </div>
              <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '0.5rem' }}>
                <div style={{ backgroundColor: 'var(--surface-hover)', padding: '0.5rem', borderRadius: '50%' }}>
                  <CheckCircle size={18} color="var(--primary-color)" />
                </div>
                <div style={{ fontSize: '0.875rem', fontWeight: 600, color: 'var(--text-light)', textTransform: 'uppercase', letterSpacing: '0.5px' }}>Destination</div>
              </div>
              <div style={{ paddingLeft: '3rem' }}>
                <div style={{ fontWeight: 600, fontSize: '1.125rem', marginBottom: '0.25rem' }}>{extractCity(delivery?.destination)}</div>
                <div style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>{delivery?.receiverName}</div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="card" style={{ marginBottom: '2rem', boxShadow: 'var(--shadow-md)' }}>
        <div className="card-body" style={{ padding: '3rem 2rem' }}>
          
          {/* Top Visual Progress Bar */}
          <div style={{ display: 'flex', justifyContent: 'space-between', position: 'relative', marginBottom: '5rem' }}>
            <div style={{ position: 'absolute', top: '24px', left: '10%', right: '10%', height: '4px', backgroundColor: 'var(--surface-hover)', zIndex: 0, borderRadius: '2px' }}></div>
            
            {/* Active Progress Fill */}
            {currentStatusIndex >= 0 && (
              <div style={{ 
                position: 'absolute', top: '24px', left: '10%', 
                width: `${Math.max(0, (currentStatusIndex / (timelineSteps.length - 1)) * 80)}%`, 
                height: '4px', backgroundColor: isException ? 'var(--danger-color)' : 'var(--primary-color)', zIndex: 0, borderRadius: '2px',
                transition: 'width 1s ease-in-out'
              }}></div>
            )}

            {timelineSteps.map((step, index) => {
              const isCompleted = index <= currentStatusIndex && !isException;
              const isCurrent = index === currentStatusIndex;
              const Icon = step.icon;
              
              let bgColor = 'var(--surface-color)';
              let borderColor = 'var(--border-color)';
              let iconColor = 'var(--text-light)';
              
              if (isCompleted) {
                 bgColor = 'var(--primary-color)';
                 borderColor = 'var(--primary-color)';
                 iconColor = 'white';
              }
              if (isException && isCurrent) {
                 bgColor = 'var(--danger-color)';
                 borderColor = 'var(--danger-color)';
                 iconColor = 'white';
              }
              
              return (
                <div key={step.status} style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', zIndex: 1, width: '20%' }}>
                  <div style={{ 
                    width: '48px', height: '48px', borderRadius: '50%', display: 'flex', alignItems: 'center', justifyContent: 'center',
                    backgroundColor: bgColor,
                    border: `3px solid ${borderColor}`,
                    color: iconColor,
                    transition: 'all 0.4s ease',
                    boxShadow: isCurrent && !isException ? '0 0 0 6px var(--primary-light)' : (isCurrent && isException ? '0 0 0 6px #fee2e2' : 'none'),
                    position: 'relative'
                  }}>
                    <Icon size={22} />
                  </div>
                  <div style={{ 
                    marginTop: '1rem', 
                    fontWeight: isCurrent ? 700 : 500, 
                    color: isCurrent && !isException ? 'var(--primary-color)' : (isCurrent && isException ? 'var(--danger-color)' : 'var(--text-muted)'), 
                    textAlign: 'center', 
                    fontSize: '0.875rem' 
                  }}>
                    {step.label}
                  </div>
                </div>
              );
            })}
          </div>

          <hr style={{ border: 'none', borderTop: '1px solid var(--border-color)', margin: '2.5rem 0' }} />

          {/* Detailed Timeline Events */}
          <h3 style={{ marginBottom: '2rem', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
            <Clock size={20} color="var(--primary-color)" /> Shipment Activity
          </h3>
          
          <div style={{ position: 'relative', paddingLeft: '2rem' }}>
            {/* Vertical line connecting events */}
            <div style={{ position: 'absolute', top: '10px', bottom: '10px', left: '11px', width: '2px', backgroundColor: 'var(--border-color)', zIndex: 0 }}></div>
            
            {formattedEvents.map((event, i) => {
              const EventIcon = event.icon || Clock;
              const isLatest = i === 0;
              
              return (
                <div key={event.id || i} style={{ position: 'relative', marginBottom: i === formattedEvents.length - 1 ? '0' : '2.5rem', display: 'flex', gap: '1.5rem', opacity: isLatest ? 1 : 0.7, transition: 'opacity 0.2s' }} className="hover:opacity-100">
                  
                  {/* Timeline Node */}
                  <div style={{ 
                    position: 'absolute', left: '-2rem', top: '0', zIndex: 1,
                    width: '24px', height: '24px', borderRadius: '50%', 
                    backgroundColor: isLatest && !isException ? 'var(--primary-color)' : (isLatest && isException ? 'var(--danger-color)' : 'var(--surface-color)'), 
                    border: `2px solid ${isLatest ? 'white' : 'var(--border-color)'}`, 
                    boxShadow: isLatest ? '0 0 0 3px ' + (!isException ? 'var(--primary-color)' : 'var(--danger-color)') : 'none',
                    display: 'flex', alignItems: 'center', justifyContent: 'center'
                  }}>
                     {!isLatest && <div style={{ width: '8px', height: '8px', borderRadius: '50%', backgroundColor: 'var(--border-color)' }}></div>}
                  </div>
                  
                  {/* Timestamp Sidebar */}
                  <div style={{ width: '100px', flexShrink: 0, textAlign: 'right', paddingTop: '2px' }}>
                    <div style={{ fontWeight: 600, color: 'var(--text-main)', fontSize: '0.875rem' }}>
                      {event.timestamp ? new Date(event.timestamp).toLocaleDateString('en-US', { month: 'short', day: 'numeric' }) : 'Pending'}
                    </div>
                    <div style={{ color: 'var(--text-muted)', fontSize: '0.75rem', marginTop: '0.125rem' }}>
                      {event.timestamp ? new Date(event.timestamp).toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' }) : ''}
                    </div>
                  </div>

                  {/* Content Card */}
                  <div style={{ 
                    flex: 1, backgroundColor: 'var(--surface-hover)', padding: '1.25rem', borderRadius: 'var(--radius-md)', 
                    borderLeft: `3px solid ${isLatest && !isException ? 'var(--primary-color)' : (isLatest && isException ? 'var(--danger-color)' : 'transparent')}` 
                  }}>
                    <h4 style={{ margin: '0 0 0.375rem 0', fontSize: '1rem', color: isLatest ? 'var(--text-main)' : 'var(--text-color)', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                      <EventIcon size={16} style={{ color: isLatest && !isException ? 'var(--primary-color)' : (isLatest && isException ? 'var(--danger-color)' : 'var(--text-muted)') }} />
                      {event.title}
                    </h4>
                    <p style={{ margin: '0 0 0.5rem 0', fontSize: '0.875rem', color: 'var(--text-muted)', lineHeight: 1.5 }}>
                      {event.description}
                    </p>
                    <div style={{ display: 'inline-flex', alignItems: 'center', gap: '0.25rem', fontSize: '0.75rem', color: 'var(--text-light)', backgroundColor: 'var(--bg-color)', padding: '0.25rem 0.5rem', borderRadius: 'var(--radius-sm)' }}>
                      <MapPin size={12} /> {event.location}
                    </div>
                  </div>
                  
                </div>
              );
            })}
            
            {formattedEvents.length === 0 && (
              <div style={{ padding: '2rem', textAlign: 'center', color: 'var(--text-muted)', border: '1px dashed var(--border-color)', borderRadius: 'var(--radius-md)', marginLeft: '1rem' }}>
                <Clock size={32} style={{ margin: '0 auto 1rem', opacity: 0.5 }} />
                <p>Tracking history will appear here once your package is processed.</p>
              </div>
            )}
          </div>

        </div>
      </div>
    </div>
  );
};

export default Tracking;
