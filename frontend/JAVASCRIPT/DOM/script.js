/*
// dom tree 
// !----
// document.getElementById('')
let div1 = document.getElementById('di');
console.log(div1);//h1#di

// !----
let parag=document.getElementsByClassName('para');
console.log(parag);//HTMLCollection [pre.para]//can have multiple elements inside



// !----
let p=document.getElementsByTagName('pre');
console.log(p);//HTMLCollection(2) [pre.para, pre]//can have only tag //with multiple element

// !-----

let n=document.getElementsByName('heading');
console.log(n);  //NodeList [h1#di] //can have text also



let para=document.querySelector('.para');//returing individual first occurrence
console.log(para);//pre.para



let par=document.querySelectorAll('.para');//returing all occurrence
console.log(par);//NodeList(2) [pre.para, pre.para]

*/
/*
 let div=document.createElement('div');
 console.log(div);
 /*one way 
//  let body=document.body;
//  body.append(div);

//  document.body.append(div);//one way
//only tag--

div.innerHTML='<h1>Good Afternoon..</h1>'//Good Afternoon..//property
//text 
// div.innerText='<h2>Good Afternoon..</h2>'///<h2>Good Afternoon..</h2>//property

 let body=document.body;
 body.append(div);
*/


//  let para=document.createElement('p');
//  console.log(para);
//  let body=document.body;
//  body.append(para);
//  para.innerText='<p>heleleoeneibjbjbfjkbewriygfewjbdqwjhgqwyeh...</p>'


// //  css manipulation

// let main=document.createElement('main');


// let section=document.createElement('section');
// let article=document.createElement('article');
//in append --multiple element --main.append(section,article); but in append child it's not possible
 
// document.body.append(main);
// main.appendChild(section,article);
// main.append(section);
// // main.append(article);
// section.append(article);



// css manipulation
// let p=document.createElement('p');
// p.innerText='gogogogogooo';
// document.body.append(p);

// style writing  again and again is tedious 
// p.style.backgroundColor='red';
// p.style.color='yellow';


// classlist--property
// let container=document.querySelector('#container');
// classlist--add
// document.body.classList.add('dark');
// classlist--remove
// document.body.classList.remove('light');
/*


// // classlist--toggle
let btn=document.getElementById('btn');
btn.addEventListener('click',()=>{
    document.body.classList.toggle('dark');
    //btn.innerText='Light';//will be light only
    // contains()
console.log(document.body.classList.contains("dark"));
(document.body.classList.contains("dark")
?(btn.innerText="light")
:(btn.innerText="dark")
)
   
})

*/

// addEventListener---- event ,callback function,boolean value(optional by default its false)


