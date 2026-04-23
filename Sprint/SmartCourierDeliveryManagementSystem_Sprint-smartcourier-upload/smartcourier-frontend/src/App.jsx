import { Navigate, Route, Routes, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { apiFetch } from "./api";
import ProtectedRoute from "./components/ProtectedRoute";
import Shell from "./components/Shell";
import { useAuth } from "./state/AuthContext";

function LandingPage() {
  const [services, setServices] = useState([]);

  useEffect(() => {
    apiFetch("/services").then(setServices).catch(() => setServices([]));
  }, []);

  return (
    <div className="landing">
      <section className="hero">
        <div>
          <p className="eyebrow">Microservices Courier Platform</p>
          <h1>Book parcels, track every status hop, and manage delivery exceptions in one place.</h1>
          <p className="lede">
            React frontend, Spring Boot services, RabbitMQ events, Zipkin tracing, Swagger APIs, and Sonar-ready code quality flow.
          </p>
        </div>
      </section>
      <section className="grid">
        {services.map((service) => (
          <article key={service.id} className="panel">
            <h3>{service.name}</h3>
            <p>{service.description}</p>
          </article>
        ))}
      </section>
    </div>
  );
}

function AuthPage({ mode }) {
  const { login, signup } = useAuth();
  const navigate = useNavigate();
  const [form, setForm] = useState({ username: "", password: "", role: "CUSTOMER" });
  const [error, setError] = useState("");

  async function handleSubmit(event) {
    event.preventDefault();
    setError("");
    try {
      if (mode === "signup") {
        await signup(form);
      }
      const user = await login(form);
      navigate(user.role === "ADMIN" ? "/admin/dashboard" : "/customer/dashboard");
    } catch (err) {
      setError(err.message);
    }
  }

  return (
    <div className="auth-page">
      <form className="panel auth-form" onSubmit={handleSubmit}>
        <h2>{mode === "signup" ? "Create account" : "Sign in"}</h2>
        <input placeholder="Username" value={form.username} onChange={(e) => setForm({ ...form, username: e.target.value })} />
        <input type="password" placeholder="Password" value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} />
        {mode === "signup" && (
          <select value={form.role} onChange={(e) => setForm({ ...form, role: e.target.value })}>
            <option value="CUSTOMER">Customer</option>
            <option value="ADMIN">Admin</option>
          </select>
        )}
        {error ? <p className="error">{error}</p> : null}
        <button type="submit">{mode === "signup" ? "Sign up" : "Login"}</button>
      </form>
    </div>
  );
}

function CustomerDashboard() {
  const [deliveries, setDeliveries] = useState([]);

  useEffect(() => {
    apiFetch("/deliveries/my").then(setDeliveries).catch(() => setDeliveries([]));
  }, []);

  return (
    <div className="stack">
      <div className="metrics">
        <div className="panel metric"><strong>{deliveries.length}</strong><span>My Deliveries</span></div>
      </div>
      <div className="panel">
        <h2>Recent Deliveries</h2>
        <div className="table-wrap">
          <table>
            <thead><tr><th>Tracking</th><th>Route</th><th>Status</th><th>Hub</th></tr></thead>
            <tbody>
              {deliveries.map((item) => (
                <tr key={item.id}>
                  <td>{item.trackingNumber}</td>
                  <td>{item.source} to {item.destination}</td>
                  <td>{item.status}</td>
                  <td>{item.currentHub}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

function CreateDeliveryPage() {
  const [message, setMessage] = useState("");
  const [form, setForm] = useState({
    senderName: "",
    receiverName: "",
    senderAddress: "",
    receiverAddress: "",
    source: "",
    destination: "",
    serviceType: "EXPRESS",
    packageType: "DOCUMENT",
    packageWeight: 1,
    pickupDate: "",
    packageDescription: "",
    notes: "",
    currentHub: "",
    price: 0
  });

  async function handleSubmit(event) {
    event.preventDefault();
    const payload = { ...form, packageWeight: Number(form.packageWeight), price: Number(form.price) };
    const result = await apiFetch("/deliveries", { method: "POST", body: JSON.stringify(payload) });
    setMessage(`Delivery booked with tracking ${result.trackingNumber}`);
  }

  return (
    <form className="panel form-grid" onSubmit={handleSubmit}>
      <h2>Create Delivery Wizard</h2>
      {["senderName","receiverName","senderAddress","receiverAddress","source","destination","pickupDate","packageDescription","notes","currentHub","price"].map((field) => (
        <input
          key={field}
          type={field === "pickupDate" ? "date" : field === "price" ? "number" : "text"}
          placeholder={field}
          value={form[field]}
          onChange={(e) => setForm({ ...form, [field]: e.target.value })}
        />
      ))}
      <select value={form.serviceType} onChange={(e) => setForm({ ...form, serviceType: e.target.value })}>
        <option>EXPRESS</option>
        <option>DOMESTIC</option>
        <option>INTERNATIONAL</option>
      </select>
      <select value={form.packageType} onChange={(e) => setForm({ ...form, packageType: e.target.value })}>
        <option>DOCUMENT</option>
        <option>PARCEL</option>
        <option>FRAGILE</option>
      </select>
      <input
        type="number"
        placeholder="packageWeight"
        value={form.packageWeight}
        onChange={(e) => setForm({ ...form, packageWeight: e.target.value })}
      />
      <button type="submit">Book Delivery</button>
      {message ? <p className="success">{message}</p> : null}
    </form>
  );
}

function TrackDeliveryPage() {
  const [trackingNumber, setTrackingNumber] = useState("");
  const [events, setEvents] = useState([]);
  const [documents, setDocuments] = useState([]);
  const [proof, setProof] = useState(null);

  async function search() {
    const result = await apiFetch(`/tracking/${trackingNumber}`);
    setEvents(result);
    setDocuments(await apiFetch(`/tracking/documents/${trackingNumber}`).catch(() => []));
    if (result[0]?.deliveryId) {
      setProof(await apiFetch(`/tracking/${result[0].deliveryId}/proof`).catch(() => null));
    }
  }

  async function uploadDocument(event) {
    event.preventDefault();
    const form = new FormData(event.currentTarget);
    const base64Content = btoa(form.get("content"));
    await apiFetch("/tracking/documents/upload", {
      method: "POST",
      body: JSON.stringify({
        deliveryId: Number(form.get("deliveryId")),
        trackingNumber,
        documentType: form.get("documentType"),
        fileName: form.get("fileName"),
        contentType: "text/plain",
        base64Content,
        uploadedBy: "customer"
      })
    });
    search();
  }

  return (
    <div className="stack">
      <div className="panel toolbar">
        <input value={trackingNumber} onChange={(e) => setTrackingNumber(e.target.value)} placeholder="Tracking number" />
        <button onClick={search}>Track</button>
      </div>
      <div className="panel">
        <h2>Tracking Timeline</h2>
        {events.map((event) => (
          <div key={`${event.deliveryId}-${event.timestamp}`} className="timeline-item">
            <strong>{event.status}</strong>
            <span>{event.location}</span>
            <p>{event.description}</p>
          </div>
        ))}
      </div>
      <form className="panel form-grid" onSubmit={uploadDocument}>
        <h2>Upload Parcel Document</h2>
        <input name="deliveryId" placeholder="Delivery ID" />
        <input name="documentType" placeholder="Document Type" />
        <input name="fileName" placeholder="File Name" />
        <textarea name="content" placeholder="Paste document content" rows="4" />
        <button type="submit">Upload</button>
      </form>
      <div className="panel">
        <h2>Documents</h2>
        {documents.map((doc) => <p key={doc.id}>{doc.documentType}: {doc.fileName}</p>)}
      </div>
      {proof ? (
        <div className="panel">
          <h2>Delivery Proof</h2>
          <p>{proof.confirmationMessage}</p>
          <p>{proof.recipientName}</p>
          <p>{proof.deliveredLocation}</p>
        </div>
      ) : null}
    </div>
  );
}

function AdminDashboard() {
  const [dashboard, setDashboard] = useState(null);
  useEffect(() => { apiFetch("/admin/dashboard").then(setDashboard); }, []);
  if (!dashboard) return <div className="panel">Loading dashboard...</div>;

  const metrics = [
    ["Total", dashboard.totalDeliveries],
    ["Delivered", dashboard.delivered],
    ["Shipped", dashboard.shipped],
    ["Delayed", dashboard.delayed],
    ["Users", dashboard.totalUsers],
    ["Active hubs", dashboard.activeHubs]
  ];

  return <div className="metrics">{metrics.map(([label, value]) => <div key={label} className="panel metric"><strong>{value}</strong><span>{label}</span></div>)}</div>;
}

function AdminDeliveries() {
  const [deliveries, setDeliveries] = useState([]);
  useEffect(() => { apiFetch("/admin/deliveries").then(setDeliveries); }, []);

  async function resolve(id) {
    await apiFetch(`/admin/deliveries/${id}/resolve`, {
      method: "PUT",
      body: JSON.stringify({ resolutionStatus: "IN_TRANSIT", notes: "Resolved by admin", hub: "Central Hub" })
    });
    setDeliveries(await apiFetch("/admin/deliveries"));
  }

  return (
    <div className="panel table-wrap">
      <table>
        <thead><tr><th>Tracking</th><th>Status</th><th>Hub</th><th>Action</th></tr></thead>
        <tbody>
          {deliveries.map((item) => (
            <tr key={item.id}>
              <td>{item.trackingNumber}</td>
              <td>{item.status}</td>
              <td>{item.currentHub}</td>
              <td><button onClick={() => resolve(item.id)}>Resolve</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

function AdminReports() {
  const [reports, setReports] = useState([]);
  const [form, setForm] = useState({ reportType: "DAILY", fromDate: "", toDate: "", generatedBy: "admin" });

  useEffect(() => { apiFetch("/admin/reports").then(setReports); }, []);

  async function createReport(event) {
    event.preventDefault();
    await apiFetch("/admin/reports", { method: "POST", body: JSON.stringify(form) });
    setReports(await apiFetch("/admin/reports"));
  }

  return (
    <div className="stack">
      <form className="panel form-grid" onSubmit={createReport}>
        <select value={form.reportType} onChange={(e) => setForm({ ...form, reportType: e.target.value })}>
          <option>DAILY</option>
          <option>MONTHLY</option>
          <option>EXCEPTION</option>
        </select>
        <input type="date" value={form.fromDate} onChange={(e) => setForm({ ...form, fromDate: e.target.value })} />
        <input type="date" value={form.toDate} onChange={(e) => setForm({ ...form, toDate: e.target.value })} />
        <input value={form.generatedBy} onChange={(e) => setForm({ ...form, generatedBy: e.target.value })} />
        <button type="submit">Generate report</button>
      </form>
      <div className="panel">
        {reports.map((report) => <p key={report.id}>{report.reportType} report for {report.generatedBy}</p>)}
      </div>
    </div>
  );
}

function CrudPage({ title, fetchPath, savePath, fields }) {
  const [items, setItems] = useState([]);
  const [form, setForm] = useState(Object.fromEntries(fields.map((field) => [field, ""])));

  useEffect(() => { apiFetch(fetchPath).then(setItems); }, [fetchPath]);

  async function submit(event) {
    event.preventDefault();
    await apiFetch(savePath, { method: "POST", body: JSON.stringify(form) });
    setItems(await apiFetch(fetchPath));
  }

  return (
    <div className="stack">
      <form className="panel form-grid" onSubmit={submit}>
        <h2>{title}</h2>
        {fields.map((field) => (
          <input key={field} placeholder={field} value={form[field]} onChange={(e) => setForm({ ...form, [field]: e.target.value })} />
        ))}
        <button type="submit">Save</button>
      </form>
      <div className="panel">
        {items.map((item) => <pre key={item.id}>{JSON.stringify(item, null, 2)}</pre>)}
      </div>
    </div>
  );
}

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/auth/login" element={<AuthPage mode="login" />} />
      <Route path="/auth/signup" element={<AuthPage mode="signup" />} />

      <Route path="/" element={<ProtectedRoute><Shell /></ProtectedRoute>}>
        <Route path="customer/dashboard" element={<ProtectedRoute role="CUSTOMER"><CustomerDashboard /></ProtectedRoute>} />
        <Route path="customer/create-delivery" element={<ProtectedRoute role="CUSTOMER"><CreateDeliveryPage /></ProtectedRoute>} />
        <Route path="customer/track" element={<ProtectedRoute><TrackDeliveryPage /></ProtectedRoute>} />
        <Route path="admin/dashboard" element={<ProtectedRoute role="ADMIN"><AdminDashboard /></ProtectedRoute>} />
        <Route path="admin/deliveries" element={<ProtectedRoute role="ADMIN"><AdminDeliveries /></ProtectedRoute>} />
        <Route path="admin/reports" element={<ProtectedRoute role="ADMIN"><AdminReports /></ProtectedRoute>} />
        <Route path="admin/users" element={<ProtectedRoute role="ADMIN"><CrudPage title="User Management" fetchPath="/admin/users" savePath="/auth/signup" fields={["username", "password", "role"]} /></ProtectedRoute>} />
        <Route path="admin/hubs" element={<ProtectedRoute role="ADMIN"><CrudPage title="Hub Management" fetchPath="/admin/hubs" savePath="/admin/hubs" fields={["name", "city", "state", "status"]} /></ProtectedRoute>} />
      </Route>

      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}
