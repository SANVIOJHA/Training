import React from 'react'
import ParentComponent from './ParentComponent'
import FoodMenu from './FoodMenu'

const App = () => {
  return (
    <div>
      App
      {/* <ParentComponent /> //uncomment this to see the output of ParentComponent and ChildComponent */}
      {/* <FoodMenu/> */}
      <ParentComponent />
      
    </div>
  )
}

export default App

// functional based component are stateless component

