// export{}; ----------------scriptscope-----used in case js file is open as well as ts file
/*
console.log("good ts ");
let a=10;
//type inference
//automatically ts detects which type of variable based on value stored inside variable
// a='23'//won't work
a=78;//it will work
console.log(a);
// let s='san';
// s=43;

let mark=undefined;
mark='a';
console.log(mark);
console.log(typeof mark);

*/

//type annotation

// let un:string='sh';
// un=21;//Type 'number' is not assignable to type 'string'.
/*
let a:number=7;
let b:boolean=true;
console.log(a);
console.log(b);
console.log(a,b);

*/


/*
//-----
let a=undefined;
a=21;
console.log(a);





let c:undefined=undefined;
// c=21;//Type '21' is not assignable to type 'undefined'.
let b:null=null;

console.log(c ," ",b);


*/




// --- function
/*
///any
// function add(a,b){//Parameter 'a' implicitly has an 'any' type.
///number
function add(a:number,b:number){
    console.log(a+b);
    
}
add(10,20);
add(30,40);

*/
/*
//return type

function add(a:number,b:number):number{
    // console.log(a+b);
    return a+b;
    
}
let res=add(10,20);
console.log(res);


///void
function sub(a:number,b:number):void{
    console.log(a+b);
    // return a+b;
    
}
let resu=add(10,40);
console.log(resu);

///undefined
function mul(a:number,b:number):undefined{
    console.log(a*b);
    // return a*b;
    
}
let r=add(10,40);
console.log(r);

*/


//
/*

function add(a:number,b:number,c:number):number{
    
    return a+b;
    
}
// let resu=add(10,40);//Expected 3 arguments, but got 2.
let resu=add(100,10,20)//will work
console.log(resu);

*/

/*
//optional --- ?
function ad(a:number,b:number,c?:number):number{
    
    return a+b;
    
}

let r=ad(100,10,20)//will work
console.log(r);


*/


/*
/// | union

function ad(a:number|string){ //a can accept either number or string
    
    console.log(a);
    
    
}
ad(2);
ad('shushant');
// ad(ad.toUpperCase());//Property 'toUpperCase' does not exist on type '(a: string | number) 


function str(a:number|string){ //a can accept either number or string
    
    
    if(typeof a==='string'){
        //narrowing
      console.log(a.toUpperCase()); 
    }
    else{
        console.log(a);
    }
    
    
}
str(2);
str('shushant');


*/

/*
// array--- not fixed length + no sequence 
let arr=[10,'shushant',true];
console.log(arr);

///only string
// let ar:string=[10,'shushant',true];///..Type '(string | number | boolean)[]' is not assignable to type 'string'
let ar:string[]=['ss','gdg']
console.log(ar);
ar.push('z');//to add 
console.log(ar);

//mix datatype

let mixarr:(string|number)[]=[10,'ss',898,90,'guy',"shhwh"]
console.log(mixarr);


//array -- read only property
let tpl:readonly string[]=['a','b','c'];//array
console.log(tpl);//
// tpl.push('z');//Property 'push' does not exist on type 'readonly string[]'.



// tuple ---fixed length+must have sequence

// let per:[string,number,boolean]=[67,'fghh',true];//Type 'number' is not assignable to type 'string'

let per:[string,number,boolean]=['hjhj',67,true];
console.log(per);


'



//object

let person={
    name:'shushant',
    mark:4
}

console.log(person);
*/

/*

let person:{
    username:string;
    mark:number;
    address:{
        pin?:number;
        city:string;
    }

}={
    username:'shushant',
    mark:90,
    address:{
        pin:1212,
        city:'poiuytd'
    }
}

console.log(person);
// tsc script.ts 
// tsc script.ts -w    

// node script.js 
//one way 
// readonly' type modifier is only permitted on array and tuple literal types.ts(1354)

let person1:readonly{
    username:string;
    mark:number;
    address:{
        pin?:number;
        city:string;
    }

}[]=[{
    username:'shushant',
    mark:90,
    address:{
        pin:1212,
        city:'poiuytd'
    }
}]

console.log(person1);
// console.log(person1.username='aka');//wont work
person1[0].username = 'aka';
console.log(person1);


//other way --for readonly
let person1: {
    readonly username: string;
    mark: number;
    address: {
        pin?: number;
        city: string;
    }
} = 
    {
        username: 'shushant',
        mark: 90,
        address: {
            pin: 1212,
            city: 'poiuytd'
        }
    }


console.log(person1);

// ❌ This will give error (readonly)
person1[0].username = 'aka';





// -----------------------------
//---type alias
type id=number;
let aadhar:id=987654;
console.log(aadhar);

////union alias

type order='pending' | 'accepted' | 'rejected';
// let myOrder:order='f'//Type '"f"' is not assignable to type 'order'.
let myOrder:order='accepted';
console.log(myOrder);

///in array
type mark=number[];
let final:mark=[0,987,456789,98]
console.log(final);


//in an object

type stu={
    username:string;
    age:number;
    ispass:boolean
}

let vaibhav:stu={
    username:'vvvv',
    age:900,
    ispass:true

}


let shus:stu={
    username:'ssss',
    age:7600,
    ispass:false

}

console.log(vaibhav);
console.log(shus);



///--intersection with type alias  

type stu={
    uname:string;
}

type intern={
    salary:number;
}

type emp = stu & intern;

let newemp:emp = {
    uname:'shushant',
    salary:567899

}

console.log(newemp);






// --- unknown

let a:unknown;
a=10;
a='shu';
// a=true;

if(typeof a==='string'){
    console.log(a.toUpperCase());
    
}




//interface

interface person{
    username:string;
    mark:number;
    address:{
        pin?:number;
        city:string;
    }

}

let subodh:person={
    username:'shushant',
    mark:90,
    address:{
        pin:1212,
        city:'poiuytd'
    }
}

console.log(subodh);




//when we dont want infinite ----loop
function infinite():never{
    // return 4;//type '4' is not assignable to type 'never'.
}


*/