import React, { useContext } from 'react'
import { ThemeContext } from './ThemeContext'

const Navbar = () => {
  const { theme, toggleTheme } = useContext(ThemeContext)

  return (
    <div className={`navbar ${theme === 'light' ? 'navbar-light' : 'navbar-dark'}`}>
      <h1 className="text-2xl font-bold">Navbar</h1>

      <button onClick={toggleTheme} className="navbar-btn">
        Click — Toggle Theme
      </button>
    </div>
  )
}

export default Navbar