
const ChildComponent = (prop) => {
    console.log('rendered', prop);
    console.log(prop.username);
   
    let {username, userage, photo,skills} = prop;

    // prop.username='abc';// Cannot assign to read only property 'username' of object '#<Object>'at ChildComponent
  return (
    <div>
      ChildComponent of child  
      <p>name : {username}</p>
      <p>age : {userage}</p>
      <img src={photo} alt="Profile"  style={{height: '400px', width: '400px',borderRadius:'80px'}}/>
      <ol><strong>skills:</strong>
        {skills.map((sk, index) => (
          <li key={index}>{sk}</li>
        ))}
      </ol>
    </div>
  )
}

export default ChildComponent

