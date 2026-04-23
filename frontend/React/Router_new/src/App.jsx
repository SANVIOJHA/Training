// /*import React from 'react'
// import { BrowserRouter, Link, NavLink, Route, Routes } from 'react-router'
// import Home from './pages/Home'
// import About from './pages/About'
// import ContactUs from './pages/ContactUs'
// import PageNotFound from './pages/PageNotFound'
// import Dashboard from './pages/Dashboard'

// const App = () => {

//   return (
//     <BrowserRouter>

//     {/* navlink by default provide active class so we can use that direclty to target the active link and apply css to it. we can also use isActive function to target the active link and apply css to it. */}
//     <nav>
//         <NavLink to='/'>Home  |</NavLink>
//         <NavLink to='/about'>About  |</NavLink>
//         <NavLink to='/contact'>Contact Us  |</NavLink>
//         {/* <NavLink to='/dashboard'>Dashboard</NavLink> */}
//     </nav>  
    
//     <hr/>
    
    
//     {/* <Link to ='/' element={<Home />} >Home  |</Link>
//     <Link to ='/about' element={<About />} >About  |</Link>
//     <Link to ='/contact' element={<ContactUs />} >Contact Us  |</Link> */}
//     <Routes>
//         <Route path='/' element={<Home />} />
//         <Route path='/about' element={<About />} />
//         <Route path='/contact' element={<ContactUs />} />
//         <Route path='/dashboard' element={<Dashboard />} />
//         <Route path='*' element={<PageNotFound />} />

//     </Routes>
//     <button>Home</button>
//     </BrowserRouter>

    
//   )
// }

// export default App




// */
// ---------------------------------------------------------------------

// import React from 'react'
// import { Routes, Route } from 'react-router-dom'

//import Home from './nestedPages/Home'
// import Dashboard from './nestedPages/Dashboard'
// import Profile from './nestedPages/Profile'
// import Settings from './nestedPages/Settings'
// import About from './pages/About'
// import ContactUs from './pages/ContactUs'
// import PageNotFound from './pages/PageNotFound'
// import './App.css';

// const App = () => {
//   return (
//     <Routes>
//       <Route path='/' element={<Home />} />

//       <Route path='/dashboard' element={<Dashboard />}>
//         <Route path='profile' element={<Profile />} />
//         <Route path='settings' element={<Settings />} />
//       </Route>

//       <Route path='/about' element={<About />} />
//       <Route path='/contact' element={<ContactUs />} />

//       <Route path='*' element={<PageNotFound />} />
//     </Routes>
//   )
// }

// export default App

// import React from 'react'
// import { Routes } from 'react-router-dom'

// const App = () => {
//   return (
//     <Routes>
//       <Route path='/' element={<Home/>} />
//       <Route path='/dashboard' element {<Dashboard />} />
//     </Routes> 
//   )
// }

// export default App
