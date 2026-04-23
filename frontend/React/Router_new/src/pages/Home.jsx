import React from 'react'
import { Link } from 'react-router'

const Home = () => {
  return (
    <div>
      <h1> Home Page </h1>
      <Link to='/dashboard' > GO to Dashboard</Link>
    </div>
  )
}

export default Home