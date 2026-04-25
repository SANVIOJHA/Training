import React from 'react'
import Parent from './pages/Parent'

const App = () => {
     let username='shushant'
  return (
    <div>
      <Parent name={username} ></Parent>
    </div>
  )
}

export default App
