/*


/* --console.log("Hello, World!");
// This is a simple JavaScript file that logs "Hello, World!" to the console. You can add more code here to create interactive features or manipulate the DOM as needed.

//variable
//var , let, const (keywords to declare variables)

// var
console.log("Using var keyword");
var a=10;
var username="John Doe";
let b=20;
const c=30;

console.log(a);
console.log(username);
console.log(b);
console.log(c);

// variable declaration
var a;//only declaration
// console.log(a);//undefined
a=30;//only initialization
console.log(a);
//for var re declaraiton is possible

// let 
console.log();
console.log("Using let keyword");

let z;//only declaration  
z=89;//only initialization
console.log(z);
//for let re declaration is not possible
// let z=90;//error


console.log("Using const keyword");

// const p;//only declaration not possible
// p=10;//only initilalization not possible

const p=10;
console.log(p);
// p=19;// reassignment not possible

 -->
 */

// scope difference---global scope,function scope
// let -- local scope(block scope),script scope
// const -- local scope,script scope

/*
///local scope 
{
let a=10;
}
console.log(a); // can't be accessed outside block because its inside block and since let is local scope it can't be accessed outside block


{
    var c=10;
}
console.log(c);// can be accessed outside block and since var is local scope it can be accessed outside block


{
    const d=10;
}
console.log(d); // can't be accessed outside block and since const is local scope it can't be accessed outside block
*/

/*
// script scope
//let
let z=1;
{
    console.log(z);
}

// const
const x=19;
{
    console.log(x);
}

var f=10;
{
    console.log(f);
}


*/

/*
// function scope
//  var is following function scope its not accessesbile outside function
function add(){
    var k=10;
}
console.log(add(k));

// let

function sub(){
    let y=9;
}
console.log(y);

// const

function con(){
    const g=89;
}
console.log(g);

*/

/*

// data type

// primitive --- Number,string,boolean,null,undefined,bigint,symbol 
// non-primitive --- array ,object,function

// number - var
// string --sequence of char --- in three ways --single and double quote and backtick ``
let user='vikash';
const coll="lpu";
// string interpolation---to access variable name --- backtick is needed
let details=`i am ${user} from ${coll}`;
console.log(details);

*/

/*
let det='i am from lpu';
console.log(det);



let d='i am "from" lpu';
console.log(d);


let e="i am 'from' lpu";
console.log(e);

// backtick
let s=`backtick 'i am "from" lpu' 
vbjgxdfghjkkhgxcv
ffghjkl
jndjekwnewjknewjkn`;
console.log(s);

console.log(typeof det);
*/

// boolean
// let isMarried=true;
// console.log(isMarried);
// console.log(typeof isMarried);
/*
// undefined
let a;
console.log(a);
let b=undefined;
console.log(typeof b);

//null

let c= null;
console.log(c);type of null is object because historical bug from the language's very first version in 1995. In reality, null is a primitive value, not an object. 
console.log(typeof c);

// bigint --- at last we use n
let salary=2345678987654321336667n;
console.log(salary);

// non-primitive
// array--------
let arr=['vi',18,true];
console.log(arr);


*/
// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */


// let a=10;
// let b = 30;
// let res = a += b;
// console.log(res);




// let a=10;
// let b='10';
// console.log('output should be true ' , a==b);//this == will check only value so output will be true
// console.log("output should be false ",a===b);//this === will check type and output will be false


///if else//
//loops//


const f=10;
{
    console.log(f);
}