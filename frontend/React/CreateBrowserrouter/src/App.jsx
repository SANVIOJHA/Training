import React from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'

import Home from './pages/Home'
import About from './pages/About'
import Dashboard from './Dashboard'
import Profile from './Profile'
import Contact from './Contact'

//  Correct router structure
let router = createBrowserRouter([
  {
    path: '/',
    element: <Home />,
    children: [
      {
        path: 'dashboard',   // no "/"
        element: <Dashboard />,
        children: [
          {
            path: 'profile',
            element: <Profile />
          },
          {
            path: 'contact',
            element: <Contact />
          }
        ]
      }
    ]
  },
  {
    path: '/about',
    element: <About />
  }
])

const App = () => {
  return <RouterProvider router={router} />
}

export default App