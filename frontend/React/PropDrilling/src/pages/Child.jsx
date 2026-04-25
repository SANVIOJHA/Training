import React from 'react'
import Grandchild from './Grandchild'

const Child = ({name}) => {
console.log(name);

  return (
    <div>
        <h2>Child component</h2>
      <Grandchild name={name}/>
    </div>
  )
}

export default Child
