import React from 'react'
import { Link } from 'react-router-dom'

const Home = () => {
  return (
    <div className="container">
      <h1>Home Page</h1>

      <Link to='/dashboard'>Go to Dashboard</Link>
      <br /><br />

      <Link to='/about'>About</Link>
      <br /><br />

      <Link to='/contact'>Contact</Link>
    </div>
  )
}

export default Home