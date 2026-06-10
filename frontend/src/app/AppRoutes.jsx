import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

// Pages
import LandingPage from '../features/landing/LandingPage';
import AboutPage from '../features/landing/AboutPage';
import Login from '../features/auth/Login';
import Signup from '../features/auth/Signup';
import CustomerDashboard from '../features/customer/Dashboard';
import DeliveryWizard from '../features/customer/DeliveryWizard';
import Tracking from '../features/tracking/Tracking';
import AdminDashboard from '../features/admin/Dashboard';
import AdminDeliveries from '../features/admin/AdminDeliveries';
import UserManagement from '../features/admin/UserManagement';
import HubManagement from '../features/admin/HubManagement';

// Layouts
import MainLayout from '../components/MainLayout';

const ProtectedRoute = ({ children, requiredRole }) => {
  const { user, isAuthenticated } = useAuth();
  
  if (!isAuthenticated) {
    return <Navigate to="/auth/login" replace />;
  }
  
  if (requiredRole && user.role !== requiredRole) {
    return <Navigate to={`/${user.role.toLowerCase()}/dashboard`} replace />;
  }
  
  return <MainLayout>{children}</MainLayout>;
};

const PublicRoute = ({ children }) => {
  const { isAuthenticated, user } = useAuth();
  
  if (isAuthenticated) {
    return <Navigate to={`/${user.role.toLowerCase()}/dashboard`} replace />;
  }
  
  return children;
};

const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        {/* Public Landing Page */}
        <Route path="/" element={<PublicRoute><LandingPage /></PublicRoute>} />
        <Route path="/about" element={<PublicRoute><AboutPage /></PublicRoute>} />

        {/* Auth Routes */}
        <Route path="/auth/login" element={<PublicRoute><Login /></PublicRoute>} />
        <Route path="/auth/signup" element={<PublicRoute><Signup /></PublicRoute>} />
        
        {/* Customer Routes */}
        <Route 
          path="/customer/dashboard" 
          element={<ProtectedRoute requiredRole="CUSTOMER"><CustomerDashboard /></ProtectedRoute>} 
        />
        <Route 
          path="/customer/deliveries/new" 
          element={<ProtectedRoute requiredRole="CUSTOMER"><DeliveryWizard /></ProtectedRoute>} 
        />
        <Route 
          path="/customer/track/:trackingNumber" 
          element={<ProtectedRoute requiredRole="CUSTOMER"><Tracking /></ProtectedRoute>} 
        />

        {/* Admin Routes */}
        <Route 
          path="/admin/dashboard" 
          element={<ProtectedRoute requiredRole="ADMIN"><AdminDashboard /></ProtectedRoute>} 
        />
        <Route 
          path="/admin/deliveries" 
          element={<ProtectedRoute requiredRole="ADMIN"><AdminDeliveries /></ProtectedRoute>} 
        />
        <Route 
          path="/admin/users" 
          element={<ProtectedRoute requiredRole="ADMIN"><UserManagement /></ProtectedRoute>} 
        />
        <Route 
          path="/admin/hubs" 
          element={<ProtectedRoute requiredRole="ADMIN"><HubManagement /></ProtectedRoute>} 
        />

        {/* Default Route — send to landing instead of login */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRoutes;
