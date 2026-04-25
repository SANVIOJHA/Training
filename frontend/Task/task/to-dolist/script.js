/* first question adding list 
let form=document.getElementById('form');
let input=document.getElementById('list');
let ul=document.getElementById('li');
let display=document.getElementById('display');


form.addEventListener('submit',(e)=>{
    e.preventDefault();
    let value= input.value;
   
    if (value.trim() === "") return;
    display.innerText = value;

    let newLi = document.createElement('li');
    newLi.innerText = value;
    ul.appendChild(newLi);

    input.value="";


});


ul.addEventListener('click',()=>{
    ul.style.textDecoration='line-through';
})



*/


/* second adding and deleting both */
let form = document.getElementById('form');
let addInput = document.getElementById('list');
let deleteInput = document.getElementById('del');

let addedList = document.getElementById('addedList');
let deletedList = document.getElementById('deletedList');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    let action = e.submitter.value;

    // ADD ITEM
    if (action === "Add") {
        let value = addInput.value;

        if (value.trim() === "") return;

        let li = document.createElement('li');
        li.innerText = value;

        addedList.appendChild(li);

        addInput.value = "";
    }

    // DELETE ITEM
    if (action === "Delete") {
        let value = deleteInput.value;

        if (value.trim() === "") return;

        let items = document.querySelectorAll('#addedList li');

        items.forEach((item) => {
            if (item.innerText === value) {

                let deletedItem = document.createElement('li');
                deletedItem.innerText = value;
                deletedList.appendChild(deletedItem);

                item.remove();
            }
        });

        deleteInput.value = "";
    }
});

let h2=document.getElementById('addedList')
h2.addEventListener('click',()=>{
    h2.style.textDecoration='line-through';
})

