import React, { useState } from 'react'

const Filtered = () => {
    let[category,setCategory]=useState('all');


    let products=[
        {//here category is case sensitive --  so we have to give exact data or use to upeercase or lowercase 
            name:'laptop',category:'electronics'
        },
        {
            name:'Apple',category:'Fruits'
        },
        {
            name:'Shirt',category:'Clothes'
        }

    ]


let handleChange=(e)=>{
    setCategory(e.target.value);
}

  return (
    <div> 
        {/* {
      products.map((p,i)=>(
        <div key={i}>
        <p>{p.name}</p> */}
{/* </div> */}



<input type='text' name='' id='' placeholder='enter category' value={category} onChange={handleChange}></input>
   {
    products.filter((item)=>item.category.toLowerCase() ==category.toLowerCase()|| category=='all').map((item,index)=>(
        <ul key={index}>
            <li>{item.name}</li>
        </ul>
    ))
   }   



    
    </div>
  )
}

export default Filtered
