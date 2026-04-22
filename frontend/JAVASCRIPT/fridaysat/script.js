// Array destucting it is extracting elemnets from array anf storing inside variable
// we can  give any varibale name 

// let marks=[10,20,30,40]
// console.log(marks[2]);

// let [a,b,c]=marks;
// console.log(b);

// let [x,,,z]=marks;   //extar commas is to skip the elements
// console.log(x);
// console.log(z);



// object destructing
// extracting properties from an object and storing inside variable
// we need to gove same name for variable which we used in object properties 

// let person={
//     name:'tanisha',
//     city:'jaipur'
// }

// let {name, city}=person;  //in object destucting we have to give same name as in key present
// console.log(name);
// console.log(city);


// spread & rest
// let week1= [10,20,30];
// let week2=[30,60,70]; 
// let combine=[...week1,...week2];   //right to equal to then in it is spread
// console.log(combine);

// let marks=[10,20,30,40,50,60,70,80,90,100,110];
// let [a,b,...c]=marks;     //left to equal to then it is rest
// console.log(c);



// rest mean packing of elemnets 
//spread menas unpacking of elemnets 

// in object
// let person={
//     name:'tanisha',
//     city:'jaipur'
// }

// let updatedPerson={
//     ...person,
//     country:'india'
// }

// console.log(updatedPerson);

// let user={
//     username:'Rishabh',
//     role:'admin',
//     salary:99999
// }
// let {username,...extra}=user;
// console.log(extra);


// in function

// function add(...c){               //rest in function(inside the fucntion declaration it will be rest )
//     console.log(c);
//     let res=c.reduce((acc,cur)=>{
//         return acc+cur
//     },0);
//         console.log(res);
        
    
// }
// add(10,20,30,40,50,60,70,80,90);


//spread in function

let marks=[10,20,30,40,50,60,70];
function add(a,b,c,d,e,f){
    console.log((a+b+c+d+e+f));

}
add(...marks);








 
