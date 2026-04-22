/*
function greet(){
        alert('welcome...')
    }


// function submit(){
//     alert('are you sure ...')
// }


//preventdefault

let form=document.getElementById('form');
form.addEventListener('submit',(e)=>{
    e.preventDefault();
    confirm('are u sure');
})

// counter event --- onclick increment counter increment ,decrement annd reset

*/

/*
///this code is for entering username and on submit its showing on the same page as USERNAME :  sanvi 

let form=document.getElementById('form');
let input=document.getElementById('username');
let h2=document.querySelector('#head2');
form.addEventListener('submit',(e)=>{
    e.preventDefault();
    console.log(input.value);
    let res=input.value;
    h2.innerText=`USERNAME : ${res}`

    
    // confirm('are u sure');
})
*/



let form=document.getElementById('form');
let input=document.getElementById('username');
let input1=document.getElementById('username');

let h2=document.querySelector('#head2');
let h2_1=document.querySelector('#head3');

form.addEventListener('submit',(e)=>{
    e.preventDefault();
    console.log(input.value);
    console.log(input1.value);
    let resuser=input.value;
    let respass=input1.value;
    h2.innerText=` ${resuser}`
    h2_1.innerText=` ${respass}`


})
//output is like -----  USERNAME : 12212400@lpu.com ----- in line through 

h2.addEventListener('click',()=>{
    h2.style.textDecoration='line-through';
})

h2_1.addEventListener('click',()=>{
    h2_1.style.textDecoration='line-through';
})


