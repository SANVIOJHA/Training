import React, { useContext } from 'react'
import { ThemeContext } from './ThemeContext'

const Home = () => {
  const { theme } = useContext(ThemeContext)

  return (
    <div className="p-5">
      <h1 className="text-xl font-semibold">HOME PAGE</h1>
      <p>Theme: {theme}</p>
    </div>
  )
}

export default Home