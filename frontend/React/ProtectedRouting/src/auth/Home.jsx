import React from 'react'
import { Link } from 'react-router-dom'
const Home = () => {
  return (
    <div>
      <h1>HomePage</h1>
      <Link to='/dashboard'>go to dashboard</Link>
    </div>
  )
}

export default Home
