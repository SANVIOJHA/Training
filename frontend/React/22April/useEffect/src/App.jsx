/*


import React, { useEffect, useState } from 'react'

const App = () => {
    let[count,setCount]=useState(0)
    console.log('component mounted...');
    // window.alert('componented mounted')


    useEffect(()=>{
        console.log('effect run ');
    });//using this will give output ----component mounted...
                                      // effect run 


                            
// so we will use 
//   dependency array   
useEffect(()=>{
        // console.log('effect run ');
        window.alert('effect run')
    },[]);//using this array it will execute only once
   


 useEffect(()=>{
        console.log('effect run ');
    },[count]);


  return (
    <div>

        <h1>count : {count}</h1>
<button  onClick={()=>setCount(count+1)}>increament by 1 </button>
<br/>
<br />

<button  onClick={()=>setCount(count-1)}>Decrement  by 1 </button>



    </div>
  )
}

export default App

// -------------------------------------------------------------------------------------------




import React, { useEffect, useState } from 'react'

const App = () => {
let[count,setCount]=useState(0);
let[users,setUsers]=useState([])
console.log(count);

useEffect(()=>{
    let fetchUsers=async()=>{
        try{
            let res=await fetch('https://jsonplaceholder.typicode.com/users');
              console.log(res)
            let data=await res.json();
            console.log(data);
            setUsers(data);
          ;

        }catch(error){
            console.log(error);
            
        }
    };
    fetchUsers();
            

},[]);



 return (
    <div>
      <h1>{count}</h1>
      <button onClick={() => setCount(count + 1)}>click</button>

      <h2>User List</h2>

      <ul>
        {users.map((user) => (
          <li key={user.id}>
            <strong>{user.name}</strong> - {user.email}
          </li>
        ))}
      </ul>

    </div>
  );
};


export default App



import React, { useEffect, useState } from 'react';

const App = () => {
  let [count, setCount] = useState(0);
  let [users, setUsers] = useState([]);

  //  move function outside
  const fetchUsers = async () => {
    try {
      let res = await fetch('https://jsonplaceholder.typicode.com/users');
      let data = await res.json();
      setUsers(data);
    } catch (error) {
      console.log(error);
    }
  };

  // initial load
  useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <div>
      <h1>{count}</h1>

      <button onClick={() => {
        setCount(prev => prev + 1); //  better practice
        fetchUsers();               // now accessible
      }}>
        click
      </button>

      <h2>User List</h2>

      <ul>
        {users.map((user) => (
          <li key={user.id}>
            <strong>{user.name}</strong> - ----  {user.email}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default App;




// ..................................................................................................................


import React, { useState } from 'react'
import Child from './Child'

const App = () => {
    let[show,setShow]=useState(true);
  return (
    <div>
        <button onClick={()=>setShow(!show)}>{show?'Hide child':'show child'}</button>

        {
            show && <Child />
        }


    </div>
  )
}

export default App

// ...............................................................................................
import React, { useState } from 'react'
import Fetch from './Fetch';

const App = () => {
        let[show,setShow]=useState(true);


  return (
    <div>
           <button onClick={()=>setShow(!show)}>{show?'Hide list':'show list'}</button>
           {
            show && <Fetch/>
           }
   
    </div>
  )
}

export default App

// ...............................................................................................................................................................................   

// axios 

import axios from 'axios';
import React, { useEffect, useState } from 'react'

const App = () => {
    let[user,setUser]=useState([]);
    useEffect(()=>{
        //Sends a GET request
// Returns a Promise
        axios.get('https://jsonplaceholder.typicode.com/users').then((res)=>setUser(res.data))
        .catch((err)=>console.log(err)
        );
    },[])
  return (
    <div>
      
        {
            user.map((u)=>(
                <p key={u.id}><strong>{u.name} ---- </strong> {u.email}</p>
            ))
        }



    </div>
  )
}

export default App
*/
// ............................................... ................................

import axios from 'axios';
import React, { useEffect, useState } from 'react'

const App = () => {


let[user,setUser ]=useState([])
useEffect(()=>{
    let fetchData=async()=>{
        try{
            let res=await axios.get('https://jsonplaceholder.typicode.com/users')
            setUser(res.data)
        }catch(err){
            console.log(err);
            
        }
    };
    fetchData();
},[])


  return (
    <div>
      App
      {
         user.map((u) => (
  <p key={u.id}>
    <strong>{u.name} ---- </strong> {u.email}
  </p>
))
      }

    </div>
  )
}

export default App
