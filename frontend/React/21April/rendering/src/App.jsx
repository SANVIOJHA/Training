import React, { useState } from "react"

const App = () => {
    const [role, setRole] = useState('guest')

    return (
        <div>

            <button onClick={() => setRole("guest")}>Guest</button>
            <button onClick={() => setRole('user')}>user</button>
            <button onClick={() => setRole('admin')}>admin</button>
            {
                role==='guest' &&<h1>you are guest</h1>
                //if left side is true the only execute
            }
            {
                role==='user' &&<h1>you are user</h1>
            }
            {
                role==='admin' &&<h1>you are admin</h1>
            }
        </div>
    )
}
export default App 