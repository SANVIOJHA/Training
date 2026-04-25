/*import React, { useEffect, useState } from 'react'
import { useParams ,Link} from 'react-router-dom'

const User = () => {
  let{id}=useParams()
  let[user,setUser]=useState(null)


  useEffect(()=>{
    fetch(`https://jsonplaceholder.typicode.com/users/${id}`)
    .then((res)=>res.json())
    .then((data)=>setUser(data));
  },[id])


  return (
    <div>
      {/* <h1>user Page</h1>
      <br>
      </br>
      <h3>User Id: {id}</h3> }


      <h1>User Data</h1>

      {
        user && <h1>{user.name}</h1>
      }

       <br>
      </br>
      <button><Link to='/'>Go to Home</Link></button>


    </div>
  )
}

export default User

*/

// ----------------------------------------------------------
// nested routing 
import React, { useEffect, useState } from 'react'
import { useParams, Link } from 'react-router-dom'

const User = () => {
  const { id } = useParams()
  const [user, setUser] = useState(null)

  useEffect(() => {
    fetch(`https://jsonplaceholder.typicode.com/users/${id}`)
      .then(res => res.json())
      .then(data => setUser(data))
  }, [id])

  return (
    <div>
      <h1>User Data</h1>

      {user ? (
        <>
          <h2>{user.name}</h2>
          <p>{user.email}</p>
        </>
      ) : (
        <p>Loading...</p>
      )}

      <br />
      <Link to="/">Go to Home</Link>
    </div>
  )
}

export default User