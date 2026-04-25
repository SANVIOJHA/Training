import React from 'react'

const Grandchild = ({name}) => {
    console.log(name);
    
  return (
    <div>
      <h2>Grandchild component</h2>
      <h2>hello {name}</h2>
    </div>
  )
}

export default Grandchild
