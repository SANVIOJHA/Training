import React, { useState } from 'react';

const Fetch = () => {
  let [users, setUsers] = useState([]);

  const fetchUsers = async () => {
    try {
      let res = await fetch('https://jsonplaceholder.typicode.com/users');
      let data = await res.json();
      setUsers(data);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div>
      <button onClick={fetchUsers}>Get Users</button>

      <ol>
        {users.map(user => (
          <li key={user.id}>
           <strong> {user.name} </strong> --- {user.email}
          </li>
        ))}
      </ol>
    </div>
  );
};

export default Fetch;