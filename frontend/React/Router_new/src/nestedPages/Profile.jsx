// import React from 'react'

// const Profile = () => {
//   return (
//     <div className="container">
//       Profile
//       <br />


//     </div>
//   )
// }

// export default Profile

import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom'

const Profile = () => {
  const [users, setUsers] = useState([])

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const res = await axios.get('https://jsonplaceholder.typicode.com/users')
        setUsers(res.data)
      } catch (err) {
        console.log(err)
      }
    }

    fetchUsers()
  }, [])

  return (
    <div className="container">
      <h2>Profile (Users)</h2>

      {users.map(user => (
        <p key={user.id}>
          {user.name} - {user.email}
        </p>
      ))}

      <br />
      <Link to='/'>Go to Home</Link>
    </div>
  )
}

export default Profile