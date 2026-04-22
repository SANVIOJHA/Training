let count=0;

function updateDisplay(){
    document.getElementById("display").innerText=count;
}

function increment(){
    count+=1;
    updateDisplay()
}

function decrement(){
    count-=1;
    updateDisplay()
}

// with popup
function reset(){
    count = 0;
    updateDisplay()
}

// without popup
function reset1(){
    count = 0;
    updateDisplay()
}

// this event code is for popup
let form=document.getElementById('form');

form.addEventListener('submit', (e) => {
    e.preventDefault();
    let result = confirm('Are you sure?');
    if (result) {
        reset();
    }
});

