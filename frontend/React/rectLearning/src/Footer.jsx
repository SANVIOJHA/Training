/*

let Footer = () => {
  return <h1>Footer component</h1>;
};

export default Footer;

export let EndFooter =()=>{
  return(
    <p>end Footer</p>
  )
}


*/



import React from 'react'

const Footer = () => {
  let uname='hell';
  //Fragment --component ---why?==== to avoid extra dom 

  return (
    // <React.Fragment>//<></>
    <>

   <h1 className='' style={{backgroundColor:'burlywood',color:'green'}}>hello {uname}</h1>


    <form action="">
      <label htmlFor=''></label>
    </form>
      <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Architecto, facere?
      </p>
    {/* </React.Fragment> */}
    </>
  )
}

export default Footer
