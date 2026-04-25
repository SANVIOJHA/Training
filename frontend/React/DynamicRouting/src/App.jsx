// import React from 'react'
// import { createBrowserRouter, RouterProvider } from 'react-router-dom'
// import Home from './pages/Home'
// import User from './pages/User'
// const App = () => {
//     let userrouter=createBrowserRouter([
//         {
//         path:'/',
//         element:<Home />
//         }
//         ,
//          {
//         path:'/user/:id',
//         element:<User />
//         }
//     ])
//   return (
//     <div>
//       <RouterProvider router={userrouter} > App</RouterProvider>
//     </div>
//   )
// }

// export default App
// ----------------------------------------------------------
// nested routing 

// App.jsx
import React from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Home from './pages/Home'
import User from './pages/User'
import Dashboard from './pages/Dashboard'

const router = createBrowserRouter([
  {
    path: '/',
    element: <Dashboard />,   // parent
    children: [
      {
        index: true,       // default route "/"
        element: <Home />
      },
      {
        path: 'user/:id',  //  nested route
        element: <User />
      }
    ]
  }
])

const App = () => {
  return <RouterProvider router={router} />
}

export default App