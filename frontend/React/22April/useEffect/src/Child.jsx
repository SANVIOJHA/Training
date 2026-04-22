//mounting ,updating/render,unmounting

import React, { useEffect } from 'react'

const Child = () => {
    useEffect(()=>{
        console.log('efefct started...');
        let interval=setInterval(()=>{
            console.log('running....');
            
        },1000);


        return ()=>{
            console.log('cleanup called... ');
            clearInterval(interval);//when unmounting 
            
            
        };
        
    },[])

  return (
    <div>
      Child Component
    </div>
  )
}

export default Child
