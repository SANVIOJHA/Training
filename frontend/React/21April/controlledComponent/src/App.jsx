import React, { useState } from 'react'
import Filtered from './Filtered';
import AllInput from './AllInput';

// 1st for username and password after that for radio 
const App = () => {

    let [data, setData] = useState({
        username: "",
        password: "",
        gender: "",
        skills: []
    });

    let handlechange = (e) => {
        let { name, value } = e.target;
        setData({ ...data, [name]: value });
    }

    let [check, setcheck] = useState([])

    let handlecheck = (e) => {
        let { value, checked } = e.target;

        if (checked) {
            setcheck([...check, value])
        } else {
            setcheck(check.filter((item) => item !== value))
        }



// setData({...data,skills:checked?[...data.skills,value]:data.skills.filter((i)=>i!==value)})
//    <h2>Skills: {data.skills.join(", ")}</h2>




    }

    return (
        <div>
            <form>

                
                <input type="text" name="username" value={data.username} placeholder='username' onChange={handlechange} />
                <br /><br />

                <input type="text" name="password" value={data.password} placeholder='password' onChange={handlechange} />

                <br /><hr />

            
                Male <input type="radio" name="gender" onChange={handlechange} value='Male' />
                Female <input type="radio" name="gender" onChange={handlechange} value='Female' />

                <br /><hr />

              
                CSE <input type="checkbox" onChange={handlecheck} value='CSE' /><br />
                ECE <input type="checkbox" onChange={handlecheck} value='ECE' /><br />
                E <input type="checkbox" onChange={handlecheck} value='E' /><br />
                CE <input type="checkbox" onChange={handlecheck} value='CE' /><br />


                <textarea name='message' id='' value={data.message} onChange={handlechange}></textarea>

            </form>

            <br />
            <hr />

            <h1>Username: {data.username}</h1>
            <h2>Password: {data.password}</h2>
            <h2>Gender: {data.gender}</h2>
            <h2>Skills: {check.join(", ")}</h2>

            <br/><br/>
            <p>Message: {data.message}</p>
             <br/><br/><hr/>
            <Filtered />
            

        </div>
    )
}

export default App





/*


import React, { useState } from 'react'

// 1st for username and password after that for radio 
const App = () => {

    let [data, setData] = useState({
        username: "",
        password: "",
        gender: "",
        option:""
       
    });

    let handlechange = (e) => {
        let { name, value } = e.target;
        setData({ ...data, [name]: value });
    }

    return (
        <div>
            <form>

                {/* text and password }
                <input type="text" name="username" value={data.username} placeholder='username' onChange={handlechange} />
                <br /><br />

                <input type="text" name="password" value={data.password} placeholder='password' onChange={handlechange} />

                <br /><hr />

                {/* radio }
                Male <input type="radio" name="gender" onChange={handlechange} value='Male' />
                Female <input type="radio" name="gender" onChange={handlechange} value='Female' />

                <br /><hr />


                <select name='option' id='' onChange={handlechange} value={data.option}>
                    <option value="Delhi">Delhi</option>
                    <option value="Mumbai">Mumbai</option>
                    <option value="Noida">Noida</option>
                </select>

            </form>

            <br />
            <hr />

            <h1>Username: {data.username}</h1>
            <h2>Password: {data.password}</h2>
            <h2>Gender: {data.gender}</h2>
            <h2>Location: {data.option}</h2>




            

        </div>
    )
}

export default App

*/