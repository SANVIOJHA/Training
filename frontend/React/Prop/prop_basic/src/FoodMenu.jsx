// import React from 'react'
// import Foodcart from './Foodcart';

// const FoodMenu = () => {
//   const foodItems = [
//   {
//     id: 1,
//     name: "Burger",
//     price: 120,
//     image: "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400"
//   },
//   {
//     id: 2,
//     name: "Pizza",
//     price: 250,
//     image: "https://images.unsplash.com/photo-1601924582975-7e7f0b4d1d8f?w=400"
//   },
//   {
//     id: 3,
//     name: "Pasta",
//     price: 180,
//     image: "https://images.unsplash.com/photo-1589302168068-964664d93dc0?w=400"
//   },
//   {
//     id: 4,
//     name: "French Fries",
//     price: 90,
//     image: "https://images.unsplash.com/photo-1541592106381-b31e9677c0e5?w=400"
//   },
//   {
//     id: 5,
//     name: "Sandwich",
//     price: 100,
//     image: "https://images.unsplash.com/photo-1553909489-cd47e0ef937f?w=400"
//   },
//   {
//     id: 6,
//     name: "Momos",
//     price: 80,
//     image: "https://images.unsplash.com/photo-1625943555419-56a2cb596640?w=400"
//   },
//   {
//     id: 7,
//     name: "Biryani",
//     price: 200,
//     image: "https://images.unsplash.com/photo-1604908176997-125f25cc6f3d?w=400"
//   },
//   {
//     id: 8,
//     name: "Ice Cream",
//     price: 70,
//     image: "https://images.unsplash.com/photo-1509042239860-f550ce710b93?w=400"
//   }
// ];





//   return (
//     <div><h1>Food Menu</h1>
    
   
//   {foodItems.map((item) => (
//     <Foodcart item={item} />
//   ))}
// </div>
//   )
// }

// export default FoodMenu



// with key props
import React from 'react';
import Foodcart from './Foodcart';

const FoodMenu = () => {

  const foodItems = [
    {
      id: 1,
      name: "Burger",
      price: 10,
      image: "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400"
    },
    {
      id: 2,
      name: "Pizza",
      price: 20,
      image: "https://tse4.mm.bing.net/th/id/OIP.A4oQxTVFF1YWILUViicknQHaF7?pid=Api&P=0&h=220"
    },
    {
      id: 3,
      name: "Pasta",
      price: 99999,
      image: "https://tse2.mm.bing.net/th/id/OIP.EBkDSG9XCPV0zs8A9yPbEAHaFj?pid=Api&P=0&h=220"
    },
    {
      id: 4,
      name: "French Fries",
      price: 9,
      image: "https://images.unsplash.com/photo-1541592106381-b31e9677c0e5?w=400"
    }
  ];

  return (
    <div style={{
        color: "#ae2d2d",
         textAlign: "center" ,
         border: "10px solid #c90e6b",
         borderRadius:'50px', 
         backgroundColor:'#d5dac3' 
    }}>
      <h1 id="head" >Food Menu</h1>

      {foodItems.map((item) => (
        <Foodcart key={item.id} item={item} />
      ))}
    </div>
  );
};

export default FoodMenu;