import React from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Home from './auth/Home'
import Login from './auth/Login'
import Protect from './auth/Protect'
import Dashboard from './auth/Dashboard'

const App = () => {


    let router=createBrowserRouter([
        {
            path:'/',
            element:<Home/>
        },
        {
            path:'/login',
            element:<Login/>
        },
        {
            path:'/dashboard',
            element:(
                <Protect>
                    <Dashboard/>
                </Protect>
                
            )
        }
    ])

  return (
    <div>
        <RouterProvider router={router}>
            <h1>App component</h1>
        </RouterProvider>
      
    </div>
  )
}

export default App
