// import React from 'react'

// const Dashboard = () => {
//   return (
//     <div>
//         <h1> Dashboard Page </h1>
//         <h1> Welcome to Dashboard </h1>
//         <a href= '/Home' > Go to Home Page </a>
//     </div>
//   )
// }

// export default Dashboard



import React from 'react'
import { Link } from 'react-router-dom'

const Dashboard = () => {
  return (
    <div>
      <nav>
        <Link to='profile'>Profile</Link>
        <Link to='settings'>Settings</Link>
        <Link to='/about'>About</Link>
        <Link to='/contact'>Contact</Link>
      </nav>
    </div>
  )
}

export default Dashboard
