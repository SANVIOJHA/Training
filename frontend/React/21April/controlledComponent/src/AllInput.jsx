import React from 'react'

const AllInput = () => {

let[state,setState]=useState({
    user:"",
    pass:""
    
});
 












  return (
    <div>
    <form action=''>
      <input type='text' name='username' value='' placeholder='user'></input>
        <input type='password' name='pass' value='' placeholder='password'></input>
    
    </form>
    

    <h1>user: </h1>
    </div>
  )
}

export default AllInput
