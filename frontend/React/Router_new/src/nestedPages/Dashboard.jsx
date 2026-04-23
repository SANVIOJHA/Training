import React from 'react'
import { Link, Outlet } from 'react-router-dom'

const Dashboard = () => {
  return (
    <div className="container">
      <h1>Dashboard</h1>

      <nav>
        <Link to='profile'>Profile</Link> |{" "}
        <Link to='settings'>Settings</Link> |{" "}
        <Link to='/about'>About</Link> |{" "}
        <Link to='/contact'>Contact</Link>
      </nav>

      <hr />

      <Outlet />
    </div>
  )
}

export default Dashboard