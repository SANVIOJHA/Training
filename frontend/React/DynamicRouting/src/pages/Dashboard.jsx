// Layout.jsx
import React from 'react'
import { Outlet, Link } from 'react-router-dom'

const Dashboard = () => {
  return (
    <div>
      <h1>Dashboard</h1>
      <Link to="/">Home</Link>
      <hr />

      <Outlet /> {/* renders child routes */}
    </div>
  )
}

export default Dashboard