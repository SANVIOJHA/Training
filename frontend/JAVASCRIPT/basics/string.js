// let uname='vikash';
// console.log(uname);

// // properties represent what an object has (data), while methods represent what an object does (behavior)


// // length is property
// console.log(uname.length);

// //toUpperCase() is method
// console.log("upper case ",uname.toUpperCase());

// //toLowerCase()

// let upper =uname.toUpperCase();
// console.log("lower case ",upper.toLowerCase());

// /* some window.name 
// let name='vikash';
// console.log(name);
// */

/*
//replace ----will only replace first ocurrence
// location is identifier
let loc="punjab";
let rep=loc.replace('p','q');
console.log("replace ",rep);

let l="inin";
console.log("replace ",l.replace('i','k'));


// replaceAll

let lo="punjabpppppppp";
let re=lo.replaceAll('p','q');
console.log("replace all ",re);

let r="inin";
console.log("replace all ",r.replaceAll('i','k'));
*/
// --------till 8 th april---end 




/*
let info='i am from lpu '
console.log(info.length);

// trimEnd()-- to remove extra space at end

let end= info.trimEnd();
console.log("after trim ",end.length);
console.log(end);


// // trimStart()-- to remove extra space at start

let i='   i am from lpu lpu    '
console.log(i);
console.log(i," total length ",i.length);

console.log(i.length);

let start=i.trimStart();
console.log(start.length);
console.log(start);

let final=i.trim();
console.log(final.length);

*/

/*

// substring(start,end)---- part of string
// if we pass negative it will return whole string

let str="i am from punjab india";
console.log(str);
let full=str.substring(0);
console.log("full substring--" ,full);

let fro=str.substring(5,8);
console.log(fro);

let punjab=str.substring(10,16);
console.log(punjab);

*/

/*
While slice() and substring() both extract parts of a string without modifying the original,
 they differ primarily in how they handle negative indices and cases where 
 the start index is larger than the end index
*/
/*
// slice(start,end) -----

let str="i am from punjab india";
console.log(str)

let part=str.slice(2,9);
console.log(part);



let part1=str.slice(-2);
console.log(part1);

let part3=str.slice(-3);
console.log(part3);

let part4=str.slice(-7,-3); 
console.log(part4);

let part5=str.slice(-9,-2);
console.log(part5);

*/

/*
// split() -----
let str="i am from punjab_india";
console.log(str)

let sp=str.split();//if its empty it will convert string to array
console.log(sp);

let sp1=str.split("");//it will separate everything char
console.log(sp1);


let sp2=str.split("f");
console.log(sp2);

let sp3=str.split("from");
console.log(sp3);

let sp5=str.split("_");
console.log(sp5);

*/

/*
//  concat()
let first="shushant";
let last="rabade";
let fullname=first+" "+last;
console.log(fullname);

let fn=first.concat(" ",last);
console.log(fn);

*/
