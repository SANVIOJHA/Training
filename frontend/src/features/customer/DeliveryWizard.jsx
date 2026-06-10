import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Check, ChevronRight, ChevronLeft, Package, Zap, ShieldAlert, AlertCircle } from 'lucide-react';
import api from '../../services/api';

const STEPS = [
  { id: 1, title: 'Sender Details' },
  { id: 2, title: 'Receiver Details' },
  { id: 3, title: 'Package Details' },
  { id: 4, title: 'Review' }
];

const DeliveryWizard = () => {
  const [currentStep, setCurrentStep] = useState(1);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [stepErrors, setStepErrors] = useState({});
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    senderName: '',
    senderPhone: '',
    source: '',
    receiverName: '',
    receiverPhone: '',
    destination: '',
    weight: '',
    dimensions: '',
    packageType: 'STANDARD'
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    // Clear step error when user types
    if (stepErrors[e.target.name]) {
      setStepErrors({ ...stepErrors, [e.target.name]: null });
    }
    setError('');
  };

  const handlePackageSelect = (type) => {
    setFormData({ ...formData, packageType: type });
  };

  const validateStep = (step) => {
    const errors = {};
    if (step === 1) {
      if (!formData.senderName.trim()) errors.senderName = 'Sender name is required';
      if (!formData.senderPhone.trim()) errors.senderPhone = 'Sender phone is required';
      else if (!/^\d{10}$/.test(formData.senderPhone.replace(/\D/g, ''))) errors.senderPhone = 'Enter valid 10 digit phone number';
      if (!formData.source.trim()) errors.source = 'Pickup address is required';
    } else if (step === 2) {
      if (!formData.receiverName.trim()) errors.receiverName = 'Receiver name is required';
      else if (formData.receiverName.trim().toLowerCase() === formData.senderName.trim().toLowerCase()) {
        errors.receiverName = 'Sender and receiver names cannot be identical';
      }
      
      if (!formData.receiverPhone.trim()) errors.receiverPhone = 'Receiver phone is required';
      else if (!/^\d{10}$/.test(formData.receiverPhone.replace(/\D/g, ''))) errors.receiverPhone = 'Enter valid 10 digit phone number';
      else if (formData.receiverPhone.trim() === formData.senderPhone.trim()) {
        errors.receiverPhone = 'Sender and receiver phone numbers cannot be the same';
      }
      
      if (!formData.destination.trim()) errors.destination = 'Drop-off address is required';
      else if (formData.destination.trim().toLowerCase() === formData.source.trim().toLowerCase()) {
        errors.destination = 'Pickup and delivery addresses cannot be identical';
      }
    } else if (step === 3) {
      if (!formData.weight || isNaN(formData.weight) || parseFloat(formData.weight) <= 0) {
        errors.weight = 'Valid weight is required';
      }
    }
    setStepErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const handleNext = () => {
    if (validateStep(currentStep)) {
      if (currentStep < STEPS.length) setCurrentStep(currentStep + 1);
    } else {
      setError('Please fix the highlighted errors before continuing.');
    }
  };

  const handlePrev = () => {
    if (currentStep > 1) {
      setCurrentStep(currentStep - 1);
      setError('');
      setStepErrors({});
    }
  };

  const handleSubmit = async () => {
    if (!validateStep(3)) return; // final safety check

    setLoading(true);
    setError('');
    try {
      await api.post('/deliveries', {
        senderName: formData.senderName,
        senderPhone: formData.senderPhone,
        receiverName: formData.receiverName,
        receiverPhone: formData.receiverPhone,
        source: formData.source,
        destination: formData.destination,
        weight: parseFloat(formData.weight),
        price: 15.0 // Fallback price to satisfy backend @NotNull validation, backend PricingStrategy will override this
      });
      // The backend pricing strategy factory handles pricing now, no dummy frontend logic needed
      navigate('/customer/dashboard');
    } catch (err) {
      const errData = err.response?.data;
      if (errData?.data && Array.isArray(errData.data)) {
        setError(errData.data.join(', '));
      } else {
        setError(errData?.message || 'Failed to create delivery');
      }
    } finally {
      setLoading(false);
    }
  };

  const renderStepIndicator = () => (
    <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '3rem', position: 'relative' }}>
      <div style={{ position: 'absolute', top: '16px', left: '0', right: '0', height: '2px', backgroundColor: 'var(--border-color)', zIndex: 0 }}></div>
      <div style={{ position: 'absolute', top: '16px', left: '0', width: `${((currentStep - 1) / (STEPS.length - 1)) * 100}%`, height: '2px', backgroundColor: 'var(--primary-color)', zIndex: 0, transition: 'var(--transition)' }}></div>
      
      {STEPS.map((step) => {
        const isCompleted = step.id < currentStep;
        const isCurrent = step.id === currentStep;
        
        return (
          <div key={step.id} style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', zIndex: 1, width: '80px' }}>
            <div style={{ 
              width: '32px', height: '32px', borderRadius: '50%', display: 'flex', alignItems: 'center', justifyContent: 'center',
              backgroundColor: isCompleted ? 'var(--primary-color)' : isCurrent ? 'var(--primary-color)' : 'var(--surface-color)',
              border: `2px solid ${isCompleted || isCurrent ? 'var(--primary-color)' : 'var(--border-color)'}`,
              color: isCompleted || isCurrent ? 'white' : 'var(--text-light)',
              fontWeight: 600, fontSize: '0.875rem', transition: 'var(--transition)',
              boxShadow: isCurrent ? '0 0 0 4px var(--primary-light)' : 'none'
            }}>
              {isCompleted ? <Check size={16} /> : step.id}
            </div>
            <div style={{ marginTop: '0.5rem', fontSize: '0.75rem', fontWeight: isCurrent ? 600 : 500, color: isCurrent ? 'var(--primary-color)' : 'var(--text-muted)', textAlign: 'center' }}>
              {step.title}
            </div>
          </div>
        );
      })}
    </div>
  );

  const PackageCard = ({ id, title, icon: Icon, description, info, selected }) => (
    <div 
      onClick={() => handlePackageSelect(id)}
      style={{
        border: `2px solid ${selected ? 'var(--primary-color)' : 'var(--border-color)'}`,
        borderRadius: 'var(--radius-md)',
        padding: '1.5rem',
        cursor: 'pointer',
        backgroundColor: selected ? 'var(--primary-light)' : 'transparent',
        transition: 'all 0.2s ease',
        display: 'flex',
        flexDirection: 'column',
        gap: '0.5rem'
      }}
    >
      <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', color: selected ? 'var(--primary-color)' : 'var(--text-color)' }}>
        <Icon size={24} />
        <span style={{ fontWeight: 600, fontSize: '1.1rem' }}>{title}</span>
      </div>
      <p style={{ fontSize: '0.875rem', color: 'var(--text-muted)', margin: 0 }}>{description}</p>
      <div style={{ marginTop: 'auto', paddingTop: '1rem', fontSize: '0.75rem', fontWeight: 600, color: selected ? 'var(--primary-color)' : 'var(--text-light)' }}>
        {info}
      </div>
    </div>
  );

  return (
    <div className="animate-fade-in" style={{ maxWidth: '800px', margin: '0 auto' }}>
      <h1 className="page-title" style={{ marginBottom: '2rem' }}>Book New Delivery</h1>
      
      <div className="card">
        <div className="card-body" style={{ padding: '3rem' }}>
          {renderStepIndicator()}
          
          {error && (
            <div style={{ padding: '1rem', backgroundColor: 'var(--danger-color)', color: 'white', borderRadius: 'var(--radius-md)', marginBottom: '1.5rem', fontSize: '0.875rem', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
              <AlertCircle size={18} /> {error}
            </div>
          )}

          <div style={{ minHeight: '350px' }}>
            {currentStep === 1 && (
              <div className="animate-fade-in">
                <h3 style={{ marginBottom: '1.5rem' }}>Sender Information</h3>
                <div className="grid grid-cols-2">
                  <div className="form-group">
                    <label className="form-label">Full Name <span style={{color: 'var(--danger-color)'}}>*</span></label>
                    <input type="text" name="senderName" className={`form-control ${stepErrors.senderName ? 'border-red-500' : ''}`} placeholder="John Doe" value={formData.senderName} onChange={handleChange} />
                    {stepErrors.senderName && <span style={{color: 'var(--danger-color)', fontSize: '0.75rem', marginTop: '0.25rem', display: 'block'}}>{stepErrors.senderName}</span>}
                  </div>
                  <div className="form-group">
                    <label className="form-label">Phone Number <span style={{color: 'var(--danger-color)'}}>*</span></label>
                    <input type="tel" name="senderPhone" className={`form-control ${stepErrors.senderPhone ? 'border-red-500' : ''}`} placeholder="10-digit number" value={formData.senderPhone} onChange={handleChange} />
                    {stepErrors.senderPhone && <span style={{color: 'var(--danger-color)', fontSize: '0.75rem', marginTop: '0.25rem', display: 'block'}}>{stepErrors.senderPhone}</span>}
                  </div>
                </div>
                <div className="form-group">
                  <label className="form-label">Pickup Address (Source) <span style={{color: 'var(--danger-color)'}}>*</span></label>
                  <textarea name="source" className={`form-control ${stepErrors.source ? 'border-red-500' : ''}`} rows="3" placeholder="Full pickup address, City" value={formData.source} onChange={handleChange}></textarea>
                  {stepErrors.source && <span style={{color: 'var(--danger-color)', fontSize: '0.75rem', marginTop: '0.25rem', display: 'block'}}>{stepErrors.source}</span>}
                </div>
              </div>
            )}

            {currentStep === 2 && (
              <div className="animate-fade-in">
                <h3 style={{ marginBottom: '1.5rem' }}>Receiver Information</h3>
                <div className="grid grid-cols-2">
                  <div className="form-group">
                    <label className="form-label">Full Name <span style={{color: 'var(--danger-color)'}}>*</span></label>
                    <input type="text" name="receiverName" className={`form-control ${stepErrors.receiverName ? 'border-red-500' : ''}`} placeholder="Jane Smith" value={formData.receiverName} onChange={handleChange} />
                    {stepErrors.receiverName && <span style={{color: 'var(--danger-color)', fontSize: '0.75rem', marginTop: '0.25rem', display: 'block'}}>{stepErrors.receiverName}</span>}
                  </div>
                  <div className="form-group">
                    <label className="form-label">Phone Number <span style={{color: 'var(--danger-color)'}}>*</span></label>
                    <input type="tel" name="receiverPhone" className={`form-control ${stepErrors.receiverPhone ? 'border-red-500' : ''}`} placeholder="10-digit number" value={formData.receiverPhone} onChange={handleChange} />
                    {stepErrors.receiverPhone && <span style={{color: 'var(--danger-color)', fontSize: '0.75rem', marginTop: '0.25rem', display: 'block'}}>{stepErrors.receiverPhone}</span>}
                  </div>
                </div>
                <div className="form-group">
                  <label className="form-label">Drop-off Address (Destination) <span style={{color: 'var(--danger-color)'}}>*</span></label>
                  <textarea name="destination" className={`form-control ${stepErrors.destination ? 'border-red-500' : ''}`} rows="3" placeholder="Full destination address, City" value={formData.destination} onChange={handleChange}></textarea>
                  {stepErrors.destination && <span style={{color: 'var(--danger-color)', fontSize: '0.75rem', marginTop: '0.25rem', display: 'block'}}>{stepErrors.destination}</span>}
                </div>
              </div>
            )}

            {currentStep === 3 && (
              <div className="animate-fade-in">
                <h3 style={{ marginBottom: '1.5rem' }}>Package Details</h3>
                <div className="grid grid-cols-2">
                  <div className="form-group">
                    <label className="form-label">Weight (kg) <span style={{color: 'var(--danger-color)'}}>*</span></label>
                    <input type="number" step="0.1" min="0.1" name="weight" className={`form-control ${stepErrors.weight ? 'border-red-500' : ''}`} placeholder="e.g. 2.5" value={formData.weight} onChange={handleChange} />
                    {stepErrors.weight && <span style={{color: 'var(--danger-color)', fontSize: '0.75rem', marginTop: '0.25rem', display: 'block'}}>{stepErrors.weight}</span>}
                  </div>
                  <div className="form-group">
                    <label className="form-label">Dimensions (LxWxH)</label>
                    <input type="text" name="dimensions" className="form-control" placeholder="e.g. 10x10x10 cm (Optional)" value={formData.dimensions} onChange={handleChange} />
                  </div>
                </div>
                <div className="form-group" style={{ marginTop: '1.5rem' }}>
                  <label className="form-label" style={{ marginBottom: '1rem' }}>Package Type <span style={{color: 'var(--danger-color)'}}>*</span></label>
                  <div className="grid grid-cols-3" style={{ gap: '1rem' }}>
                    <PackageCard 
                      id="STANDARD" 
                      title="Standard" 
                      icon={Package} 
                      description="Budget-friendly standard routing." 
                      info="Est. 3-5 days delivery"
                      selected={formData.packageType === 'STANDARD'} 
                    />
                    <PackageCard 
                      id="EXPRESS" 
                      title="Express" 
                      icon={Zap} 
                      description="Priority routing for faster arrival." 
                      info="Est. 1-2 days delivery"
                      selected={formData.packageType === 'EXPRESS'} 
                    />
                    <PackageCard 
                      id="FRAGILE" 
                      title="Fragile" 
                      icon={ShieldAlert} 
                      description="Special handling and protection." 
                      info="Extra care applied"
                      selected={formData.packageType === 'FRAGILE'} 
                    />
                  </div>
                </div>
              </div>
            )}

            {currentStep === 4 && (
              <div className="animate-fade-in">
                <h3 style={{ marginBottom: '1.5rem' }}>Review & Confirm</h3>
                <div className="grid grid-cols-2" style={{ gap: '2rem' }}>
                  <div>
                    <h4 style={{ fontSize: '1rem', color: 'var(--text-light)', borderBottom: '1px solid var(--border-color)', paddingBottom: '0.5rem', marginBottom: '1rem' }}>Pickup</h4>
                    <p style={{ margin: '0 0 0.25rem 0' }}><strong>{formData.senderName}</strong></p>
                    <p style={{ margin: '0 0 0.5rem 0', color: 'var(--text-muted)', fontSize: '0.875rem' }}>{formData.senderPhone}</p>
                    <p style={{ margin: 0, color: 'var(--text-muted)', fontSize: '0.875rem' }}>{formData.source}</p>
                  </div>
                  <div>
                    <h4 style={{ fontSize: '1rem', color: 'var(--text-light)', borderBottom: '1px solid var(--border-color)', paddingBottom: '0.5rem', marginBottom: '1rem' }}>Drop-off</h4>
                    <p style={{ margin: '0 0 0.25rem 0' }}><strong>{formData.receiverName}</strong></p>
                    <p style={{ margin: '0 0 0.5rem 0', color: 'var(--text-muted)', fontSize: '0.875rem' }}>{formData.receiverPhone}</p>
                    <p style={{ margin: 0, color: 'var(--text-muted)', fontSize: '0.875rem' }}>{formData.destination}</p>
                  </div>
                </div>
                <div style={{ marginTop: '2rem', padding: '1.5rem', backgroundColor: 'var(--surface-hover)', borderRadius: 'var(--radius-md)' }}>
                  <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '0.75rem', borderBottom: '1px solid var(--border-color)', paddingBottom: '0.75rem' }}>
                    <span style={{ color: 'var(--text-muted)' }}>Package Type:</span>
                    <strong style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                      {formData.packageType === 'EXPRESS' ? <Zap size={16} style={{color: 'var(--primary-color)'}}/> : 
                       formData.packageType === 'FRAGILE' ? <ShieldAlert size={16} style={{color: 'var(--primary-color)'}}/> : 
                       <Package size={16} style={{color: 'var(--primary-color)'}}/>}
                      {formData.packageType}
                    </strong>
                  </div>
                  <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                    <span style={{ color: 'var(--text-muted)' }}>Weight:</span>
                    <strong>{formData.weight} kg</strong>
                  </div>
                  <div style={{ marginTop: '1rem', fontSize: '0.75rem', color: 'var(--text-muted)', textAlign: 'center' }}>
                    Final pricing will be calculated based on the selected route and package type.
                  </div>
                </div>
              </div>
            )}
          </div>
        </div>
        
        <div className="card-footer" style={{ display: 'flex', justifyContent: 'space-between', borderTop: '1px solid var(--border-color)', padding: '1.5rem 3rem' }}>
          <button 
            className="btn btn-outline" 
            onClick={handlePrev} 
            disabled={currentStep === 1 || loading}
            style={{ minWidth: '120px' }}
          >
            <ChevronLeft size={18} style={{ marginRight: '0.5rem' }} /> Back
          </button>
          
          {currentStep < STEPS.length ? (
            <button className="btn btn-primary" onClick={handleNext} style={{ minWidth: '120px' }}>
              Next Step <ChevronRight size={18} style={{ marginLeft: '0.5rem' }} />
            </button>
          ) : (
            <button className="btn btn-primary" onClick={handleSubmit} disabled={loading} style={{ minWidth: '180px' }}>
              {loading ? <div className="spinner" style={{ borderColor: 'rgba(255,255,255,0.3)', borderTopColor: 'white' }}></div> : 'Confirm Booking'}
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default DeliveryWizard;
