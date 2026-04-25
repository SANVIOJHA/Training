import React from 'react'
import Child from './Child';

// const Parent = (prop) => {

//    console.log(prop);
//    let {name}=prop;
//    console.log(name);
//destructring
const Parent = ({name})=>{

 console.log(name);
   
   
  return (
    <div>
        <h1>Parent component </h1>
      <Child name={name}/>
    </div>
  )
}

export default Parent
