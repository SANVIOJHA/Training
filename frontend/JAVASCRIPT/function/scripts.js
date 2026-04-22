// functions---
// reusable code 


// named functions--
/*
// non parametaarized
function add(){
    console.log(10+20);
    
}
add();

// parametarized

function add1(a,b){
    console.log(a+b);
    
}
add1();///will be NaN --not a number --undefined

*/
/*
//default argument
function add1(a=10,b=20){
    console.log(a+b);
    
}
add1();

//default argument + actual argument----it will print actual argument
function add2(a=10,b=20){
    console.log(a+b);
    
}
add2(50,100);

*/

/*
// anonymous name /unnamed function
// function should be inside --- ()
// normal anonymous function
(function (a,b){
    console.log(a+b);
    
} (10,20)
)
// iife (immediate invoke function expression)
// iife anonymous function 
(function (c,d){
    console.log(c+d);
    
} )(10,20)

// function expression
let div = (function (a,b){
    console.log(a/b);
    
} 
)
div(100,20);

*/
/*
// higher order function and callback function
// higher order function--- function which accepts another function  as parameter
// callback function---- function which pass as an argument to another function 

function hof(name,cl){
    console.log(name);
    cl();
}

function callback(){
    console.log("i am from callback function ");
    
}

hof('shushant',callback)
hof('shushant 1',callback())

*/

// arrow function 
// introduced in es6 version 
// shorter way to create the function
// no need to use function keyword
/* syntax
()=>{

}
    */
/*
(a,b)=>{
    console.log(a*b);
    
}
    
// store inside variable

let mul=(a,b)=>{
    console.log(a*b);
    
};
mul(10,20);
/*
// features of arrow functio----

1.> if arrow function is accepting only one parameter no need to write to paranthesis

//syntax
let mul =a=>{
    console.log(a);
    
};
mul(10);

2.> if af contains only one statement inside it we can remove the curly brackets

let mul=a=>console.log(a*a);
mul(10);


3.> if af contains only one statement inside it we should not use  the curly brackets
then only arrow function can return values implicitly this is only possible for af

// explicit return ----
function add(a,b){
    return a+b;
}

let a=add(90,9);
console.log(a);


console.log(add(10,20));



// answer nundefined both will be output 
function add(a,b){
   console.log(a+b);
   
}
add(78,2);
let a=add(90,9);
console.log(a);


console.log(add(10,20));



// implicit return(only available for af) ---only if 1 statement and no curly bracket 
let mul=(a,b)=>console.log(a*b);
mul(10,20);//200

// with curly bracket it will give error 

// nested function
function parent(name,city){
    console.log(name);
    function child(){
        console.log(city);
        
    }
    child();
    // why error?
    // let c=child();
    // console.log("this is child function -- ",c);
    
    
}

parent('s','pun');


console.log('start')

function parent(){
    let name="shushant";
    let city="banglore";
    console.log(name);
    function child(){
        console.log(city);
        
    }
    child();
}

parent();
console.log('end');





// 
// function currying
function outer(){
    let name="abs";
  return function inner(){
        console.log(name);
    }
}
outer()();


// closer function

function outer1(a){
    let name="abs";
    return function inner1(b){
        console.log(name);
     return function innermost(c){
        console.log("i am innermost function");
        console.log(a+b+c);
        
     }
    }
}
outer1(10)(20)(30);
*/

// 

let mul=a=>b=>c=>a*b*c;
console.log(mul(10)(20)(30));

function parent(name,city){
    console.log(name);
    function child(){
       
        return `this is ${city}`
        
    }
    console.log("this ",child());
    // why error?
    // let c=child();
    // console.log("this is child function -- ",c);
    
    
}

parent('s','pun');

console.log("this ,", parent(z));