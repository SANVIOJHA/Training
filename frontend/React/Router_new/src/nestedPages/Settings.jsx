// import React from 'react'

// const Settings = () => {
//   return (
//     <div className="container">
//       Settings
//     </div>
//   )
// }

// export default Settings
import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom'

const Settings = () => {
  const [todos, setTodos] = useState([])

  useEffect(() => {
    const fetchTodos = async () => {
      try {
        const res = await axios.get('https://jsonplaceholder.typicode.com/todos?_limit=5')
        setTodos(res.data)
      } catch (err) {
        console.log(err)
      }
    }

    fetchTodos()
  }, [])

  return (
    <div className="container">
      <h2>Settings (Todos)</h2>

      {todos.map(todo => (
        <p key={todo.id}>
          {todo.title} {todo.completed ? "----Done " : "-----not done"}
        </p>
      ))}

      <br />
      <Link to='/'>Go to Home</Link>
    </div>
  )
}

export default Settings