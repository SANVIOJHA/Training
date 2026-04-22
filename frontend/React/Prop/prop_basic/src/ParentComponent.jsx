// import React from 'react'//no need for this lightweight version of react in latest versions of react

import ChildComponent from "./ChildComponent"


const ParentComponent = () => {
    let user='shushant';
    let age=78;
    let profile="https://img.freepik.com/premium-photo/digital-artwork-girl-imaage_955028-1056.jpg?w=2000" 
    let skill=['CSS', 'HTML', 'JavaScript', 'React'];

  return (
    <div>
      <h1>Parent Component</h1>
      <ChildComponent username={user} userage={age} photo={profile} skills={skill}/>
      

    </div>
  );
};

export default ParentComponent
