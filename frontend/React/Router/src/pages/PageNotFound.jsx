import React from 'react'
import { Link } from 'react-router'

const PageNotFound = () => {
  return (
    <div>
        <h1>404</h1>
        <p>Page Not Found</p>
        <Link to='/'  >Go to Home </Link>
    </div>
  )
}

export default PageNotFound