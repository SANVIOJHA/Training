// console.log('Start');

// for( let i=0; i<=500; i++){
//     console.log(i);
// }
// console.log('end');



// set timeout
// console.log('start');
 
// setTimeout(()=>
// console.log("hello"),2000);

// console.log('end');


// set interval
// setInterval(()=>{
//     console.log('hello');
// },2000);
// console.log('end');


// !--
// let greet=setInterval(()=>{
//     console.log('hello');
    
// },1000)

// // setTimeout(()=>{
// //     clearInterval(greet)
// // },3000)

// setTimeout(()=>
// clearTimeout(greet),4000)


// aync function & await
// async function getdata(){
//     let response= await fetch("https://fakestoreapi.com/products");
//     console.log(response);
//     let data= await response.json();
//     console.log(data);
//     console.log(data[3]);
//     console.log(data[3].category);
//     console.log(data[3]['title']);
    
    
// }
// getdata();


// JSON

// datatypes not alloweedin jSon== undefined, BigInt, function 
// json stringify js object => json string
// json parse json string => js object

let person={
    username:'tanisha',
    age:19,
    isMarried:false,
    skills:['html','css','javascript'],
    greet:function(){
        console.log('hello everyone');
    },
    address:{
        pin: 135001,
        country:'india',
    },
    kids:undefined,
    ex: null,
    // salary:9999n  
};

console.log(person);

let res= JSON.stringify(person);
console.log(res);
console.log(typeof res);


let finalres= JSON.parse(res);
console.log(finalres);
console.log(typeof finalres);



