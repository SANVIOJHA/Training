/*

import React from 'react'
import { useState } from 'react'
const App = () => {

    let [state, setState] = useState(0)
    console.log(state);
   
    let increase = () => {
        setState(state + 1)
    }
     let decrease = () => {
        setState(state -1)
    }
    
     let reset = () => {
        setState(0)
    }
    
    if(state<0){
        setState(0)
    }
  return (

    <div>

        
     <button style={{padding:'20px', margin: '10px',backgroundColor: 'lightblue',borderRadius:'10px'}} onClick={increase}>count increase</button>
     <button style={{padding:'20px', margin: '10px',backgroundColor: 'lightcoral',borderRadius:'10px'}} onClick={decrease}>count decrease</button>
     <button style={{padding:'20px', margin: '10px',backgroundColor: 'lightgreen',borderRadius:'10px'}} onClick={reset}>count reset</button>

     <h1>count : {state}</h1>
    </div>
  )
}

export default App


*/
/*
import React, { useState } from 'react';

const App = () => {
  // 1. Initialize state with your starting array
  const [users, setUsers] = useState(['subodh', 'abc', 'xyz']);

  const addUser = () => {
    // 2. Use the spread operator to create a NEW array 
    // including the old users plus the new one
    setUsers([...users, 'Saurabh']);
  };

  return (
    <div>
      <h1>User List</h1>
      <ul>
        {/* 3. Map through the state variable 'users' }
        {users.map((item, index) => (
          <li key={index}>{item}</li>
        ))}
      </ul>
      
      {/* 4. Call the function to update state }
      <button onClick={addUser}>Add User</button>
    </div>
  );
};

export default App;
*/

/*
import React, { useState } from 'react';

const App = () => {
  // 1. Initialize state with an object
  let [users, setUsers] = useState({ username: 'sushant', marks: 45 });

  let updatemarks = () => {
    // 2. Corrected typo: user.mark -> users.marks
    // 3. Use spread operator (...) to merge with existing state
    setUsers({ ...users, marks: users.marks + 90 });
    console.log("Updated marks");
  };

  return (
    <div>
      <h2>uname: {users.username} , marks: {users.marks}</h2>
      {/* 4. Corrected log typo: console.log(user) -> console.log(users) }
      <button onClick={updatemarks}>Increase marks</button>
    </div>
  );
};

export default App;
*/



import React, { useState } from 'react';

const App = () => {
    let [data,setdata]=useState(null);;
    console.log(data);


    
  return (
    <div>{
      data?<h3>{data}</h3>:<h3>no data</h3>
    }
     <button onClick={()=>setdata('data loaded...')} style={{padding:'20px', margin: '10px',backgroundColor: 'lightblue',borderRadius:'10px'}}>change data</button>
     <button onClick={()=>setdata(null)} style={{padding:'20px', margin: '10px',backgroundColor: 'lightcoral',borderRadius:'10px'}}>clear data</button>
    </div>
  )
}

export default App

//4 button ---backgroun color change --- according to mood emoji --- happy, sad, angry, neutral
//