import React from 'react'
import { Link, Outlet } from 'react-router-dom'

const Dashboard = () => {
  return (
    <div>
      <h2>Dashboard</h2>

      <nav>
        <button>
          <Link to='profile'>Profile</Link>
        </button>

        <br /><br />

        <button>
          <Link to='contact'>Contact</Link>
        </button>
      </nav>

      <hr />
      <Outlet />
    </div>
  )
}

export default Dashboard