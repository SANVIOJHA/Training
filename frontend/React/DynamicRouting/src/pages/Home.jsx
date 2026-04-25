import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'

const Home = () => {

  const[id,setId]=useState('')
  const navigate=useNavigate()
  const handleSubmit=(e)=>{
    e.preventDefault()
     if (!id) return
    navigate(`/user/${id}`)
  }




  return (
    <div>
      <h1>HomePage</h1>
      <form onSubmit={handleSubmit}>
      
      <input type='text' placeholder='enter id' value={id} onChange={(e)=>setId(e.target.value)}></input>
      <button type='submit'>Submit</button>
      </form>
<br />
      <hr />
      <Link to='/user/1'>User1</Link>
      <br />
      <hr />
      <Link to='/user/2'>User2</Link>
    </div>
  )
}

export default Home

///dynamic routing and nested routing to fetch the data 

// ----------------------------------------------------------
// nested routing 

