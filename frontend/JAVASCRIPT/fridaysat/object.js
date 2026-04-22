let person={
    name:'Tanisha',
    city:'YNR',
    age:20,
    isMarried:false,
    skills: ['Springboot', 'html','css','Mysql'],
    greet:()=>{
    console.log('hello everyone');
    }
}
console.log(person);
console.log(person.name);
console.log(person['city']);
person.city='Punjab';
console.log(person);

delete person.age;
console.log(person);


// seal  to modify the property
Object.seal(person)
person.name='Rishbah';
person.city='Jaipur';
person.country='India';
delete person.name;
console.log(person);

// frezze the object  so that no modify can be done
Object.freeze(person)
person.name='tanisha';
person.age='23';
console.log(person);

// object .keys
let keys=Object.keys(person);
console.log(keys);

// object values 
let value =Object.values(person);
console.log(value);

// object enteries
let entries= Object.entries(person)
console.log(entries);









