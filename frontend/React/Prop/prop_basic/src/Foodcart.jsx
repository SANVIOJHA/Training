// const Foodcart = ({ item }) => {
//   const { id, name, price, image } = item;

//   return (
//     <div>
//       <h2>FoodId : {id}</h2>
//       <h2>Food Name : {name}</h2>
//       <p>Price : ₹{price}</p>
//       <img src={image} alt={name} width="150" />


//     </div>

//   );
// };

// export default Foodcart;



// with key props

import React from 'react';

const Foodcart = ({ item }) => {
console.log("Foodcart component rendered for:", item);
  const { name, price, image } = item;
  

  return (
    <div style={{
      border: "10px solid #c90e6b",
      backgroundColor: "#c496bd",
      padding: "20px",
      margin: "20px",
      width: "600px",
      borderRadius: "50px",
      textAlign: "center",
      fontFamily: "Arial, sans-serif",
      color: "#fff",
      fontSize: "18px",
    }}>
      <img 
        src={image} 
        alt={name}
        style={{ width: "400px", height: "300px", objectFit: "cover", borderRadius: "30px"  }}
      />

      <h3>{name}</h3>
      <p>₹{price}</p>
    </div>
  );
};

export default Foodcart;