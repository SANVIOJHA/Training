/*

async function getData() {//await ? fetch?
    let resp= await fetch("https://fakestoreapi.com/products");
    console.log(resp);
    let data=await resp.json();
    console.log(data);

    console.log(data[3]);
    console.log(data[3].category);
    console.log(data[3]['title']);
    console.log(data[13]);
    console.log(data[13].description);
    
    
    
}
getData();



let person={
    username:'vaibhav',
    age:19,
    isMarried:true,
    skills:['js','ts','reactjs'],
    greet:function(){
        console.log("hello");
        
    },
    address:{
        pin:12312,
        country:'India'
    },
    kids:undefined,
    ex:null

};
console.log(person);
console.log(typeof person);//object


// json fromat
let res=JSON.stringify(person);
console.log(res);
console.log(typeof res);//string //no undefined ,no function,no bigint

// revrese json

let fi=JSON.parse(res);
console.log(fi);
console.log(typeof fi);



// 
// BOM -browser object model


let x=console.log(window)
console.log(typeof x);//undefined

var a=12;//will be stored in window object
// window .open ,window.close ---in browseer 
// prompt
let data=prompt("enter");//prompt to take input from user
console.log(data);
console.log(typeof data);


// alert
window.alert("go");
window.confirm("stay or go ");

*/


