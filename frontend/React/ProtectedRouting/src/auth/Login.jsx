import React from 'react'

const Login = () => {
    let navigate=useNavigate();
    let handleLogin=()=>{
        localStorage.setItem('isauth','true');
        navigate('/dashboard')
    }
  return (

    <div>
      <button onClick={handleLogin}></button>
    </div>
  )
}

export default Login
