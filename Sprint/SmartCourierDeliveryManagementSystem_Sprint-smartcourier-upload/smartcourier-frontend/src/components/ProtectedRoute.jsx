import { Navigate } from "react-router-dom";
import { useAuth } from "../state/AuthContext";

export default function ProtectedRoute({ children, role }) {
  const { user, loading } = useAuth();

  if (loading) {
    return <div className="panel">Loading SmartCourier...</div>;
  }

  if (!user) {
    return <Navigate to="/auth/login" replace />;
  }

  if (role && user.role !== role) {
    return <Navigate to={user.role === "ADMIN" ? "/admin/dashboard" : "/customer/dashboard"} replace />;
  }

  return children;
}
