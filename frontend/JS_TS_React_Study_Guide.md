# JavaScript, TypeScript, and React Study Guide

## 1. Introduction

This guide is written in a beginner-friendly and detailed manner so that you can study for class, exams, interviews, and viva questions.

These three topics are strongly connected:

- JavaScript is the programming language used to make websites interactive.
- TypeScript is an improved version of JavaScript that adds type safety.
- React is a library built using JavaScript to create user interfaces.

If you understand these three together, you understand a big part of modern frontend development.

---

## 2. Historical Background

## 2.1 History of JavaScript

JavaScript was created in 1995 by Brendan Eich while working at Netscape. It was developed in a very short time, around 10 days, to add behavior and interactivity to web pages. At that time, websites were mostly static, meaning they could only show information but could not react much to user actions.

JavaScript was first named `Mocha`. Then it was renamed `LiveScript`. Later, it was renamed `JavaScript` because Java was very popular at that time, and the new name helped with marketing. Even though the names are similar, Java and JavaScript are very different languages.

### Important timeline

- `1995`: JavaScript was created by Brendan Eich.
- `1996`: Microsoft released JScript for Internet Explorer.
- `1997`: JavaScript was standardized as `ECMAScript`.
- `2009`: ECMAScript 5 introduced strict mode and useful array methods.
- `2015`: ECMAScript 6 or ES6 introduced many modern features.
- `2015 onwards`: yearly updates continued to improve the language.

### Why JavaScript became so important

- It made websites interactive.
- It allowed developers to change content on a page without reloading.
- It became supported by all major browsers.
- It later expanded to servers through Node.js.
- It became the base for frameworks and libraries like React, Angular, and Vue.

### Real-life examples of JavaScript use

- Validating login forms
- Creating image sliders
- Building calculators
- Handling button clicks
- Loading data from APIs
- Building complete frontend and backend apps

---

## 2.2 History of TypeScript

TypeScript was created by Microsoft and officially released in 2012. As JavaScript projects became larger, developers started facing many problems such as runtime errors, confusing code, and difficulty in maintaining big applications. TypeScript was introduced to solve these problems by adding static typing and better development tools.

TypeScript is not a completely different language. It is a superset of JavaScript. That means any valid JavaScript code can also be used in TypeScript. TypeScript simply adds extra features to make coding safer and more structured.

### Why TypeScript was needed

- JavaScript allows too much flexibility, which can create errors.
- Large applications need better structure.
- Teams need readable and maintainable code.
- Developers need stronger editor support and autocomplete.
- It helps catch mistakes before the code runs.

### Important timeline

- `2012`: TypeScript launched.
- `2014 to 2016`: adoption began to grow in enterprise development.
- `2016 onwards`: more React, Angular, and Node.js projects started using TypeScript.
- Today: TypeScript is one of the most preferred choices for large-scale applications.

### Why TypeScript is popular today

- It improves code quality.
- It helps beginners understand data structures better.
- It makes big projects easier to manage.
- It reduces bugs in production.

---

## 2.3 History of React

React was developed by Facebook and released in 2013. It was created to solve the problem of building fast, interactive, and scalable user interfaces. As websites became more complex, managing the UI using plain JavaScript became harder. React introduced a component-based approach that made UI development much easier.

### Why React became popular

- It breaks the UI into smaller reusable components.
- It uses a Virtual DOM for efficient updates.
- It encourages one-way data flow.
- It helps build single-page applications.
- It has a huge community and ecosystem.

### Important timeline

- `2013`: React was open-sourced by Facebook.
- `2015`: React Native was introduced for mobile app development.
- `2019`: Hooks became very important in React development.
- Today: React is one of the most used libraries for frontend development.

### Why developers like React

- Easier UI reuse
- Faster development
- Better code organization
- Strong ecosystem
- Good job market demand

---

## 3. What is JavaScript, TypeScript, and React Together?

To understand them clearly, think of them like this:

- JavaScript is the base language.
- TypeScript makes JavaScript safer and easier to maintain.
- React uses JavaScript or TypeScript to build UI components.

A simple real-world analogy:

- JavaScript is like basic spoken language.
- TypeScript is like speaking with grammar rules and structure.
- React is like using that language to build a presentation or project model.

---

## 4. JavaScript Fundamentals in Detail

## 4.1 What is JavaScript?

JavaScript is a high-level, interpreted, dynamically typed programming language. It is mainly used to make web pages interactive. It can run inside browsers and also outside browsers using environments like Node.js.

### Beginner meaning

If HTML creates the structure of the web page and CSS adds styling, then JavaScript adds behavior.

Example:

- HTML creates a button
- CSS styles the button
- JavaScript makes the button do something when clicked

### Example

```js
document.getElementById("btn").addEventListener("click", function () {
  alert("Button clicked");
});
```

---

## 4.2 Features of JavaScript

### 1. Dynamic typing

In JavaScript, you do not have to declare the data type in advance.

```js
let value = 10;
value = "hello";
```

This flexibility is useful, but it can also create mistakes.

### 2. Interpreted or JIT compiled

JavaScript does not need traditional compilation before running in the browser. Modern engines compile and optimize it internally while executing.

### 3. Object-oriented and functional support

JavaScript supports multiple programming styles:

- procedural
- object-oriented
- functional

### 4. Event-driven

JavaScript responds to user actions such as click, scroll, typing, and submit.

### 5. Asynchronous

JavaScript can perform tasks like loading data from a server without freezing the page.

---

## 4.3 Variables in JavaScript

Variables are used to store data.

### `var`

- old way of declaring variables
- function-scoped
- can be redeclared
- can cause confusion

### `let`

- modern way
- block-scoped
- can be reassigned

### `const`

- block-scoped
- cannot be reassigned
- preferred when value should not change

### Example

```js
var city = "Delhi";
let age = 22;
const country = "India";
```

### Difference in simple words

- Use `let` when value may change.
- Use `const` when value should stay fixed.
- Avoid `var` in modern code unless you are studying old JavaScript behavior.

---

## 4.4 Data Types in JavaScript

Data types tell us what kind of value a variable stores.

### Primitive types

- `string`: text
- `number`: numeric values
- `boolean`: true or false
- `undefined`: variable declared but value not assigned
- `null`: intentional empty value
- `bigint`: very large integers
- `symbol`: unique identifiers

### Non-primitive types

- `object`
- `array`
- `function`

### Example

```js
let name = "Anu";
let marks = 95;
let isPresent = true;
let address = null;
let phone;
```

### Important note

In JavaScript, arrays and functions are technically special kinds of objects.

---

## 4.5 Operators

Operators are symbols used to perform operations on values.

### Arithmetic operators

- `+`
- `-`
- `*`
- `/`
- `%`

### Comparison operators

- `==`
- `===`
- `!=`
- `!==`
- `>`
- `<`
- `>=`
- `<=`

### Logical operators

- `&&` meaning and
- `||` meaning or
- `!` meaning not

### Assignment operators

- `=`
- `+=`
- `-=`
- `*=`
- `/=`

### Example

```js
let a = 10;
let b = 5;
console.log(a + b);
console.log(a > b);
console.log(a === 10 && b === 5);
```

---

## 4.6 Conditional Statements

Conditional statements help us make decisions in code.

### `if`

```js
let age = 18;
if (age >= 18) {
  console.log("Eligible to vote");
}
```

### `if else`

```js
if (age >= 18) {
  console.log("Adult");
} else {
  console.log("Minor");
}
```

### `else if`

Used when there are multiple conditions.

### `switch`

Useful when checking one value against many possible cases.

---

## 4.7 Loops

Loops are used to repeat code.

### `for` loop

```js
for (let i = 1; i <= 5; i++) {
  console.log(i);
}
```

### `while` loop

```js
let i = 1;
while (i <= 5) {
  console.log(i);
  i++;
}
```

### `do while` loop

Runs at least once even if the condition is false.

---

## 4.8 Functions

A function is a reusable block of code designed to perform a task.

### Why functions are important

- avoid repeated code
- make programs organized
- improve readability
- improve reuse

### Function declaration

```js
function greet() {
  console.log("Hello");
}
```

### Function with parameters

```js
function add(a, b) {
  return a + b;
}
```

### Function expression

```js
const sayHi = function () {
  console.log("Hi");
};
```

### Arrow function

```js
const multiply = (a, b) => a * b;
```

### Callback function

A callback is a function passed into another function.

```js
function processUser(callback) {
  callback();
}

processUser(() => console.log("User processed"));
```

---

## 4.9 Scope

Scope means where a variable can be accessed.

### Global scope

A variable declared outside all functions and blocks is global.

### Function scope

A variable declared inside a function can be used only inside that function.

### Block scope

Variables declared with `let` and `const` inside a block are available only within that block.

```js
if (true) {
  let x = 10;
}
```

Here, `x` cannot be used outside the block.

---

## 4.10 Hoisting

Hoisting is JavaScript behavior where declarations are processed before the code runs.

### Example with `var`

```js
console.log(a);
var a = 5;
```

This does not crash immediately because `var a` is hoisted and treated like:

```js
var a;
console.log(a);
a = 5;
```

Output:

```txt
undefined
```

### Example with `let`

```js
console.log(b);
let b = 10;
```

This gives an error because `let` exists in the temporal dead zone before initialization.

### Viva point

Hoisting happens with declarations, not normal assignments.

---

## 4.11 Closures

A closure is created when an inner function remembers variables of its outer function even after the outer function has finished running.

### Example

```js
function outer() {
  let count = 0;

  return function inner() {
    count++;
    return count;
  };
}

const counter = outer();
console.log(counter());
console.log(counter());
```

Output:

```txt
1
2
```

### Why closure is useful

- data hiding
- private variables
- counters
- function factories

### Beginner understanding

The inner function still remembers `count` even after `outer()` is finished.

---

## 4.12 Arrays

An array stores multiple values in a single variable.

```js
let fruits = ["apple", "banana", "mango"];
```

### Common array methods

#### `push()`

Adds item at the end.

#### `pop()`

Removes last item.

#### `map()`

Creates a new array by transforming each item.

```js
let nums = [1, 2, 3];
let doubled = nums.map((n) => n * 2);
```

#### `filter()`

Creates a new array with matching items.

```js
let even = nums.filter((n) => n % 2 === 0);
```

#### `reduce()`

Reduces array to one value.

```js
let total = nums.reduce((sum, n) => sum + n, 0);
```

#### `forEach()`

Runs a function for each item.

### Difference between `map()` and `forEach()`

- `map()` returns a new array
- `forEach()` does not return a useful new array

---

## 4.13 Objects

Objects store data in key-value format.

```js
let student = {
  name: "Riya",
  age: 20,
  course: "BCA"
};
```

### Accessing object properties

```js
console.log(student.name);
console.log(student["age"]);
```

### Why objects are important

They help represent real-world entities such as students, products, users, and orders.

---

## 4.14 DOM

DOM means Document Object Model. It is the object representation of an HTML document. JavaScript uses the DOM to read, change, create, and remove elements on a web page.

### Common DOM methods

- `document.getElementById()`
- `document.querySelector()`
- `document.querySelectorAll()`
- `document.createElement()`
- `element.appendChild()`
- `element.innerText`
- `element.innerHTML`

### Example

```js
document.getElementById("title").innerText = "Welcome";
```

This changes the text of an HTML element.

---

## 4.15 Events in JavaScript

An event is an action that happens in the browser.

Examples:

- clicking a button
- typing in an input box
- submitting a form
- hovering over an element

### Example

```js
document.getElementById("btn").addEventListener("click", function () {
  console.log("Button clicked");
});
```

### Common events

- `click`
- `change`
- `input`
- `submit`
- `mouseover`
- `keydown`

---

## 4.16 Asynchronous JavaScript

JavaScript is single-threaded. This means it executes one main piece of code at a time. But many tasks, such as fetching data from an API, take time. If JavaScript waited for every long task, the browser would freeze. To avoid this, JavaScript uses asynchronous programming.

### Important terms

#### Callback

A function that runs later after another task finishes.

#### Promise

A Promise represents a future value.

States of a Promise:

- pending
- fulfilled
- rejected

#### `async/await`

Modern and easier way to handle promises.

### Example of Promise

```js
const promise = new Promise((resolve, reject) => {
  resolve("Success");
});
```

### Example of `async/await`

```js
async function getUsers() {
  const response = await fetch("/users");
  const data = await response.json();
  console.log(data);
}
```

### Event loop beginner explanation

JavaScript keeps running main code in the call stack. When asynchronous tasks complete, their callbacks wait in a queue. The event loop checks whether the call stack is empty. If it is empty, it moves the waiting task into the call stack.

### Why this matters

Without asynchronous programming, user interfaces would become slow and unresponsive.

---

## 4.17 ES6 and Modern JavaScript

ES6 is one of the most important updates to JavaScript.

### Major features introduced

- `let`
- `const`
- arrow functions
- template literals
- destructuring
- spread operator
- rest operator
- classes
- modules
- promises

### Template literal

```js
let name = "Aman";
console.log(`Hello ${name}`);
```

### Destructuring

```js
let user = { name: "Asha", age: 22 };
let { name, age } = user;
```

### Spread operator

```js
let arr1 = [1, 2];
let arr2 = [...arr1, 3, 4];
```

---

## 5. TypeScript Fundamentals in Detail

## 5.1 What is TypeScript?

TypeScript is a superset of JavaScript that adds static typing and some advanced features. TypeScript code is compiled into JavaScript before it can run.

### Very simple definition

TypeScript is JavaScript plus types.

### Why this is helpful

Types help us define what kind of data a variable should store. This reduces mistakes.

Example:

```ts
let age: number = 20;
```

Here, TypeScript knows that `age` must be a number.

---

## 5.2 Why We Use TypeScript

### Problems in plain JavaScript

- wrong data passed to functions
- spelling mistakes in object properties
- confusion in large teams
- difficult debugging in big applications

### How TypeScript solves these

- catches many mistakes at compile time
- improves autocomplete
- improves documentation through types
- makes code easier to understand
- helps in refactoring

### Simple example

```ts
function add(a: number, b: number): number {
  return a + b;
}
```

If someone writes `add("5", 3)`, TypeScript can warn us before the code runs.

---

## 5.3 Basic Types in TypeScript

### `string`

```ts
let username: string = "Ravi";
```

### `number`

```ts
let marks: number = 95;
```

### `boolean`

```ts
let isPassed: boolean = true;
```

### `null` and `undefined`

Represent empty or unassigned values.

### `any`

Can store any value, but removes the safety of TypeScript.

```ts
let value: any = 10;
value = "hello";
```

### `unknown`

Also stores any value, but requires checking before use.

```ts
let data: unknown = "test";
```

### `void`

Used for functions that do not return a value.

```ts
function showMessage(): void {
  console.log("Hello");
}
```

### `never`

Used for functions that never finish normally, such as ones that always throw errors.

---

## 5.4 Type Inference

TypeScript often understands the type automatically.

```ts
let city = "Mumbai";
```

Here, TypeScript infers that `city` is a string.

### Why inference is useful

- less code
- still safe
- easier to read

---

## 5.5 Arrays and Tuples

### Arrays

```ts
let numbers: number[] = [1, 2, 3];
```

or

```ts
let names: Array<string> = ["Anu", "Rahul"];
```

### Tuples

Tuples store a fixed number of values in a fixed order.

```ts
let student: [string, number] = ["Ravi", 21];
```

This means:

- first value must be a string
- second value must be a number

---

## 5.6 Enums

Enums allow us to define a set of named constants.

```ts
enum Role {
  Admin,
  User,
  Guest
}
```

### Why enums are useful

Instead of using raw values like `0`, `1`, or `2`, we can use meaningful names.

---

## 5.7 Functions in TypeScript

TypeScript lets us define types for function parameters and return values.

```ts
function greet(name: string): string {
  return `Hello ${name}`;
}
```

### Optional parameter

```ts
function welcome(name: string, city?: string): string {
  return city ? `Hello ${name} from ${city}` : `Hello ${name}`;
}
```

### Default parameter

```ts
function printMessage(message: string = "Hi"): string {
  return message;
}
```

---

## 5.8 Interfaces

An interface describes the shape of an object.

```ts
interface Student {
  id: number;
  name: string;
  passed: boolean;
}
```

### Example usage

```ts
const s1: Student = {
  id: 1,
  name: "Kiran",
  passed: true
};
```

### Why interfaces are useful

- consistency
- readability
- reuse
- good for props in React

---

## 5.9 Type Aliases

Type aliases let us create our own custom type names.

```ts
type ID = string | number;
```

### Example

```ts
let userId: ID = 101;
let orderId: ID = "ORD123";
```

---

## 5.10 Union Types

Union means a value can be one of multiple types.

```ts
let result: string | number;
result = "Pass";
result = 100;
```

### Why union is useful

Sometimes data can validly come in different forms.

---

## 5.11 Intersection Types

Intersection combines multiple types into one.

```ts
type Person = {
  name: string;
};

type Employee = {
  employeeId: number;
};

type Staff = Person & Employee;
```

Now `Staff` must have both `name` and `employeeId`.

---

## 5.12 any vs unknown

This is a common viva question.

### `any`

- accepts any value
- no type checking
- risky in large code

### `unknown`

- accepts any value
- safer than `any`
- must check type before use

### Example

```ts
let value: unknown = "hello";

if (typeof value === "string") {
  console.log(value.length);
}
```

---

## 5.13 Classes in TypeScript

TypeScript supports classes and access modifiers.

```ts
class Person {
  name: string;

  constructor(name: string) {
    this.name = name;
  }
}
```

### Access modifiers

- `public`: accessible everywhere
- `private`: accessible only inside the class
- `protected`: accessible inside class and subclasses

### Example

```ts
class Account {
  private balance: number = 0;
}
```

---

## 5.14 Generics

Generics allow us to write reusable and type-safe code.

```ts
function identity<T>(value: T): T {
  return value;
}
```

### Example use

```ts
identity<string>("hello");
identity<number>(10);
```

### Beginner meaning

Instead of writing separate functions for strings, numbers, and arrays, generics let one function work with many types safely.

---

## 5.15 Type Assertion

Type assertion tells TypeScript how to treat a value.

```ts
let value: unknown = "TypeScript";
let len = (value as string).length;
```

### Important note

Type assertion does not change the actual value. It only tells the compiler how to interpret it.

---

## 5.16 Compilation in TypeScript

Browsers do not understand TypeScript directly. So TypeScript must be converted into JavaScript.

This is done by the TypeScript compiler:

```txt
tsc
```

### Flow

1. Write `.ts` or `.tsx` code
2. Compile it using TypeScript
3. JavaScript output is generated
4. Browser runs the JavaScript

---

## 6. React Fundamentals in Detail

## 6.1 What is React?

React is a JavaScript library used to build user interfaces. It is especially useful for applications where the screen changes often depending on user actions or data updates.

### In simple words

React helps us build web page sections as reusable components.

Example:

- Navbar
- Sidebar
- Login form
- Product card
- Footer

Each of these can be built as a separate React component.

---

## 6.2 Why React is Used

- to build dynamic interfaces
- to split UI into components
- to reuse code
- to update the page efficiently
- to manage large frontend apps more easily

### React is declarative

In React, we describe what the UI should look like for a given state, and React updates the DOM accordingly.

This is easier than manually changing many DOM elements using plain JavaScript.

---

## 6.3 Components

Components are the building blocks of a React application.

### Functional component

```jsx
function Welcome() {
  return <h1>Hello</h1>;
}
```

### Class component

```jsx
class Welcome extends React.Component {
  render() {
    return <h1>Hello</h1>;
  }
}
```

### Which one is preferred today?

Functional components are preferred today, especially with Hooks.

---

## 6.4 JSX

JSX stands for JavaScript XML. It looks like HTML written inside JavaScript.

```jsx
const element = <h1>Welcome</h1>;
```

### Why JSX is useful

- easier to read UI code
- mixes logic and UI in a structured way
- very common in React

### Important points

- JSX is not actual HTML
- class becomes `className`
- event names use camelCase like `onClick`

---

## 6.5 Props

Props means properties. Props are used to pass data from parent component to child component.

### Example

```jsx
function Greeting(props) {
  return <h1>Hello {props.name}</h1>;
}
```

Used as:

```jsx
<Greeting name="Anita" />
```

### Beginner point

Props are like function arguments for components.

### Important rule

Props are read-only.

---

## 6.6 State

State is data stored inside a component that can change over time.

When state changes, React re-renders the component.

### Example

```jsx
import { useState } from "react";

function Counter() {
  const [count, setCount] = useState(0);

  return (
    <button onClick={() => setCount(count + 1)}>
      {count}
    </button>
  );
}
```

### Simple meaning

- `count` is current state
- `setCount` updates the state

---

## 6.7 Props vs State

This is one of the most important viva questions.

### Props

- passed from parent
- read-only
- used to send data

### State

- managed inside component
- can change
- used for dynamic data

### Example

A parent may pass a student's name as prop, while the child component may keep a local counter as state.

---

## 6.8 Event Handling in React

React handles events similarly to JavaScript but uses JSX syntax.

```jsx
function Button() {
  function handleClick() {
    alert("Clicked");
  }

  return <button onClick={handleClick}>Click Me</button>;
}
```

### Common React events

- `onClick`
- `onChange`
- `onSubmit`
- `onMouseOver`
- `onKeyDown`

---

## 6.9 Conditional Rendering

Conditional rendering means showing different UI based on a condition.

### Example using ternary operator

```jsx
function UserStatus({ isLoggedIn }) {
  return <h1>{isLoggedIn ? "Welcome" : "Please Login"}</h1>;
}
```

### Example using `&&`

```jsx
{isAdmin && <p>Admin Panel</p>}
```

---

## 6.10 Lists and Keys

React often displays data as lists.

```jsx
const items = ["Pen", "Book", "Bag"];

function ProductList() {
  return (
    <ul>
      {items.map((item, index) => (
        <li key={index}>{item}</li>
      ))}
    </ul>
  );
}
```

### What is `key`?

`key` is a special prop used by React to identify elements in a list.

### Why key is important

- helps React update efficiently
- avoids confusion during re-rendering
- improves performance

### Best practice

Use a stable unique id when possible instead of index.

---

## 6.11 Forms in React

React forms are usually handled using state.

### Controlled component

In a controlled component, the input value is controlled by React state.

```jsx
import { useState } from "react";

function LoginForm() {
  const [name, setName] = useState("");

  return (
    <input
      value={name}
      onChange={(e) => setName(e.target.value)}
    />
  );
}
```

### Why controlled components are useful

- easier validation
- better control over data
- easier debugging

---

## 6.12 Hooks

Hooks are special functions introduced in React to use state and other React features in functional components.

### Common hooks

- `useState`
- `useEffect`
- `useContext`
- `useRef`
- `useReducer`

### Why hooks were important

Before hooks, state and lifecycle logic were mainly used in class components. Hooks made functional components much more powerful and popular.

---

## 6.13 `useEffect`

`useEffect` is used for side effects.

### Side effects mean

- API calls
- timers
- subscriptions
- updating document title

### Example

```jsx
import { useEffect } from "react";

function Example() {
  useEffect(() => {
    console.log("Component rendered");
  }, []);

  return <h1>Hello</h1>;
}
```

### What does `[]` mean?

An empty dependency array means the effect runs only once after the first render.

### Cleanup

`useEffect` can also clean up resources such as timers or subscriptions.

---

## 6.14 Lifecycle in React

In old class components, lifecycle methods were used:

- `componentDidMount`
- `componentDidUpdate`
- `componentWillUnmount`

In modern React, `useEffect` is commonly used to handle similar behavior in functional components.

---

## 6.15 Virtual DOM

The Virtual DOM is a lightweight copy of the real DOM.

### How it works

1. React creates a virtual representation of the UI.
2. When data changes, React creates a new virtual representation.
3. React compares the old and new versions.
4. It updates only the changed parts in the real DOM.

### Why this is useful

- faster updates
- better performance
- less direct DOM manipulation

### Important note

Virtual DOM does not mean the real DOM is not used. It means React updates the real DOM intelligently.

---

## 6.16 One-Way Data Flow

React follows one-way data flow.

This means data generally moves from parent component to child component.

### Why this is useful

- easier debugging
- predictable behavior
- better control of application state

---

## 6.17 Routing in React

Routing means moving between pages or views in an application without fully reloading the browser page.

In React, routing is often done using `react-router-dom`.

### Example pages

- home
- about
- contact
- login

---

## 6.18 State Management in React

As applications grow, managing data in many components becomes difficult. For this, developers use state management tools.

### Common options

- Context API
- Redux
- Zustand

### Beginner understanding

State management helps share and manage data across many components.

---

## 6.19 React with TypeScript

React and TypeScript are commonly used together.

### Why this combination is powerful

- typed props
- typed state
- safer event handling
- safer API response handling
- better editor support

### Example

```tsx
type GreetingProps = {
  name: string;
};

function Greeting({ name }: GreetingProps) {
  return <h1>Hello {name}</h1>;
}
```

### Beginner meaning

TypeScript makes React code more reliable and easier to maintain.

---

## 7. JavaScript vs TypeScript vs React

| Topic | JavaScript | TypeScript | React |
|---|---|---|---|
| Type | Programming language | Superset of JavaScript | UI library |
| Main purpose | Add logic and interactivity | Add type safety to JS | Build interfaces |
| Typing | Dynamic | Static plus dynamic | Uses JS or TS |
| Runs directly in browser | Yes | No, needs compilation | JSX or TSX needs transpiling |
| Main use | Scripting and app logic | Large maintainable applications | Component-based UI |
| Created by | Brendan Eich | Microsoft | Facebook |

### Simple summary

- JavaScript is the base.
- TypeScript improves JavaScript.
- React uses JavaScript or TypeScript for UI development.

---

## 8. Important Concepts to Prepare for Viva

- history of JavaScript
- history of TypeScript
- history of React
- `var`, `let`, `const`
- data types
- operators
- functions
- scope
- hoisting
- closure
- arrays and objects
- DOM and events
- callback, promise, async/await
- event loop
- TypeScript types
- interface and type alias
- `any` and `unknown`
- union, tuple, enum, generic
- React component
- JSX
- props and state
- hooks
- `useState` and `useEffect`
- Virtual DOM
- one-way data flow
- list keys
- controlled components
- React with TypeScript

---

## 9. Detailed Viva Questions and Answers

## Q1. What is JavaScript?

JavaScript is a high-level programming language mainly used to make web pages interactive. It helps websites respond to user actions such as clicking buttons, filling forms, or loading data dynamically. JavaScript can run inside browsers and also on servers using Node.js. It is one of the core technologies of web development along with HTML and CSS.

## Q2. Who created JavaScript and when?

JavaScript was created by Brendan Eich in 1995 while he was working at Netscape. It was developed to add interactive behavior to web pages. It became very popular because it made websites more dynamic and useful.

## Q3. Why is JavaScript important in web development?

JavaScript is important because it controls the behavior of a web page. Without JavaScript, websites would mostly be static. It helps perform validation, update content dynamically, handle events, make API calls, and create full applications.

## Q4. What is the difference between Java and JavaScript?

Java and JavaScript are completely different languages. Java is a general-purpose, object-oriented programming language often used for backend, desktop, and Android applications. JavaScript is mainly used for web interactivity and frontend development, though it can also be used for backend through Node.js. Their syntax has some similarities, but they are not the same technology.

## Q5. What is ECMAScript?

ECMAScript is the standard specification on which JavaScript is based. It defines the rules, features, and behavior of the language. JavaScript is an implementation of ECMAScript. So we can say ECMAScript is the standard, while JavaScript is the practical language developers use.

## Q6. What are variables in JavaScript?

Variables are named containers used to store data. In JavaScript, variables can be declared using `var`, `let`, and `const`. Variables help store information such as numbers, strings, and objects so that the program can use and update them when needed.

## Q7. Explain `var`, `let`, and `const`.

`var` is the older way of declaring variables. It is function-scoped and can be redeclared, which sometimes creates bugs. `let` is block-scoped and can be reassigned, so it is useful when the value may change. `const` is also block-scoped but cannot be reassigned after initialization, so it is used for values that should remain constant. In modern JavaScript, `let` and `const` are preferred.

## Q8. What are data types in JavaScript?

Data types describe the kind of value stored in a variable. Primitive data types include string, number, boolean, undefined, null, bigint, and symbol. Non-primitive data types include objects, arrays, and functions. Knowing data types helps us write correct logic and use values properly.

## Q9. What is the difference between `==` and `===`?

`==` checks equality after type conversion, while `===` checks equality without type conversion. This means `===` compares both value and type. Because it avoids unexpected conversions, `===` is safer and preferred in most cases.

## Q10. What is a function?

A function is a reusable block of code that performs a specific task. Functions reduce repetition, improve readability, and make code modular. A function may take input values called parameters and may return a result.

## Q11. What is scope in JavaScript?

Scope defines where a variable can be accessed in the program. Global scope means a variable can be accessed anywhere. Function scope means a variable can be accessed only inside a function. Block scope means a variable can be accessed only inside a block such as `if`, `for`, or `while`, especially when declared with `let` or `const`.

## Q12. What is hoisting?

Hoisting is JavaScript behavior where declarations are processed before code execution. Function declarations are hoisted completely. Variables declared with `var` are hoisted and initialized with `undefined`. Variables declared with `let` and `const` are also hoisted, but they cannot be used before initialization due to the temporal dead zone.

## Q13. What is closure?

A closure happens when an inner function remembers the variables of its outer function even after the outer function has completed. Closures are useful for maintaining private data, creating counters, and writing reusable logic. This is an important and commonly asked JavaScript concept.

## Q14. What is an array?

An array is a data structure used to store multiple values in one variable. Values in an array are ordered and accessed using index positions starting from zero. Arrays are very useful when we want to work with lists of items such as students, products, or numbers.

## Q15. What are common array methods?

Common array methods include `push()`, `pop()`, `map()`, `filter()`, `reduce()`, and `forEach()`. `push()` adds an item, `pop()` removes the last item, `map()` transforms items, `filter()` selects matching items, `reduce()` combines values into one, and `forEach()` runs a function for each item.

## Q16. What is an object in JavaScript?

An object is a collection of key-value pairs. It is used to represent real-world entities such as a student, product, or employee. Each property in an object has a name and a value. Objects are one of the most important structures in JavaScript.

## Q17. What is DOM?

DOM stands for Document Object Model. It is the object representation of an HTML document. JavaScript uses the DOM to read page content, change text, modify styles, create new elements, and respond to user actions.

## Q18. What are events in JavaScript?

Events are actions that happen in the browser, such as clicking a button, typing in an input field, or submitting a form. JavaScript listens for these events and performs the required task when they occur. This makes web pages interactive.

## Q19. What is asynchronous JavaScript?

Asynchronous JavaScript allows long-running tasks to happen without blocking the main program. For example, loading data from an API may take time. With asynchronous programming, the browser can continue responding to the user while waiting for the data. This improves performance and user experience.

## Q20. What is a callback?

A callback is a function passed as an argument to another function and executed later. Callbacks are commonly used in asynchronous programming and event handling. They are useful, but too many nested callbacks can make code difficult to read, which is called callback hell.

## Q21. What is a Promise?

A Promise is an object that represents the future result of an asynchronous operation. It can be pending, fulfilled, or rejected. Promises improve the readability of asynchronous code compared to deeply nested callbacks.

## Q22. What is `async/await`?

`async/await` is a modern syntax used to work with promises. An `async` function returns a promise, and `await` pauses execution inside that function until the promise settles. This makes asynchronous code easier to understand and write.

## Q23. What is the event loop?

The event loop is the mechanism that allows JavaScript to handle asynchronous tasks even though it is single-threaded. It checks whether the call stack is empty and then moves ready callbacks from queues into the stack. This is how JavaScript can work with timers, API calls, and browser events without stopping the main program.

## Q24. What is TypeScript?

TypeScript is a superset of JavaScript created by Microsoft. It adds static typing and other advanced features to JavaScript. TypeScript helps developers catch errors before running the code and makes large applications easier to maintain.

## Q25. Why was TypeScript introduced?

TypeScript was introduced because large JavaScript applications became hard to manage. JavaScript is flexible, but that flexibility can lead to bugs and confusion. TypeScript adds structure, catches errors earlier, and makes code easier to read and maintain.

## Q26. What is static typing?

Static typing means the type of a variable is checked during development or compilation rather than only at runtime. This helps catch mistakes early. In TypeScript, we can specify that a variable must be a string, number, boolean, or another defined type.

## Q27. What is the difference between JavaScript and TypeScript?

JavaScript is dynamically typed and runs directly in browsers. TypeScript adds static typing and must be compiled into JavaScript before running. JavaScript is simpler to start with, while TypeScript is safer and more suitable for large projects.

## Q28. What is `any` in TypeScript?

`any` is a type that allows any kind of value. It disables type checking for that variable. While it is flexible, it reduces the benefits of TypeScript and should be used carefully.

## Q29. What is `unknown` in TypeScript?

`unknown` is a safer alternative to `any`. It can hold any value, but we must check the type before using it. This prevents mistakes and makes the code more secure.

## Q30. What is an interface?

An interface defines the structure of an object. It tells TypeScript what properties and methods an object should have. Interfaces are commonly used to keep object definitions consistent across the program.

## Q31. What is a type alias?

A type alias is a custom name for a type. It can represent a primitive, union, intersection, object type, tuple, and more. Type aliases are useful for simplifying complex types and making code more readable.

## Q32. What is the difference between interface and type alias?

Interfaces are mainly used to define object structures and can be extended. Type aliases are more flexible because they can represent unions, intersections, tuples, and primitives. In many object-based cases, both can be used, but type aliases support a wider variety of type expressions.

## Q33. What are union types?

Union types allow a variable to store one of multiple possible types. For example, a value may be either a string or a number. Union types are useful when data can come in more than one valid form.

## Q34. What are generics?

Generics allow developers to write reusable code that works with different data types while preserving type safety. Instead of writing separate logic for strings and numbers, a generic function can handle both correctly.

## Q35. What is enum in TypeScript?

An enum is a group of named constants. It helps make code easier to read by replacing raw values with meaningful names. Enums are useful when there is a fixed set of options, such as user roles or days of the week.

## Q36. Why is TypeScript useful in large applications?

TypeScript is useful in large applications because it catches errors early, improves code readability, supports safe refactoring, and gives better IDE support. These benefits become very important when many developers are working on the same codebase.

## Q37. What is React?

React is a JavaScript library used to build user interfaces. It allows developers to create reusable components and update the UI efficiently when data changes. React is widely used for building single-page applications.

## Q38. Why is React called a library and not a framework?

React is called a library because it mainly focuses on the UI layer. A framework usually gives a full structure for building an entire application. React can be combined with other libraries for routing, state management, and API handling.

## Q39. What is a component in React?

A component is a reusable piece of UI. It can contain its own structure, logic, and styling. Complex applications are built by combining many small components together.

## Q40. What is JSX?

JSX is a syntax extension used in React that looks similar to HTML. It allows developers to write UI code in a more readable way. JSX is later converted into JavaScript by tools such as Babel.

## Q41. What are props in React?

Props are inputs passed from a parent component to a child component. They help send data from one component to another. Props are read-only, which means a child component should not directly change them.

## Q42. What is state in React?

State is data that belongs to a component and can change over time. When state changes, the component re-renders so that the UI reflects the latest data. State is used for dynamic values such as counters, form data, and toggle states.

## Q43. What is the difference between props and state?

Props are passed from parent to child and are read-only. State is managed inside a component and can change. Props are used to send data, while state is used to store and update dynamic information.

## Q44. What is `useState`?

`useState` is a React Hook used to add state to functional components. It returns the current state value and a function used to update that state. It is one of the most common hooks in React.

## Q45. What is `useEffect`?

`useEffect` is a React Hook used to perform side effects such as data fetching, timers, subscriptions, and manual DOM updates. It runs after rendering and can also clean up when the component unmounts or when dependencies change.

## Q46. What is Virtual DOM?

The Virtual DOM is a lightweight JavaScript copy of the real DOM. React compares the old and new Virtual DOM trees and updates only the changed parts in the real DOM. This makes UI updates more efficient.

## Q47. What is one-way data flow in React?

One-way data flow means data moves from parent to child. This makes the application easier to understand and debug because the flow of data is predictable.

## Q48. What is conditional rendering in React?

Conditional rendering means showing different UI based on a condition. For example, we may show a login message if the user is not authenticated and a welcome message if the user is logged in.

## Q49. Why is `key` important in React lists?

The `key` prop helps React uniquely identify each item in a list. This allows React to update lists efficiently and correctly when items are added, removed, or reordered.

## Q50. What is a controlled component?

A controlled component is a form element whose value is controlled by React state. This gives the developer full control over the input's value and behavior, which is useful for validation and form handling.

## Q51. What are hooks in React?

Hooks are special functions that allow functional components to use features such as state, lifecycle behavior, context, and references. Hooks made functional components much more powerful and reduced the need for class components.

## Q52. What is the relationship between JavaScript, TypeScript, and React?

JavaScript is the base language. TypeScript builds on JavaScript by adding types and extra safety. React is a library that uses JavaScript or TypeScript to build user interfaces. Together, they are used to create modern frontend applications.

## Q53. How does TypeScript help React projects?

TypeScript helps React projects by adding types for props, state, events, and API data. This catches errors early, improves autocomplete, and makes large component-based applications easier to maintain.

## Q54. Why are JavaScript, TypeScript, and React important for a frontend developer?

They are important because JavaScript is the base language of web interactivity, TypeScript improves code quality and maintainability, and React helps build scalable user interfaces. Together, they form a major part of real-world frontend development.

---

## 10. Beginner-Friendly Quick Revision Notes

### JavaScript in one line

JavaScript makes web pages interactive.

### TypeScript in one line

TypeScript is JavaScript with type safety.

### React in one line

React is a library for building reusable UI components.

### `var`, `let`, `const`

- `var` is old and function-scoped
- `let` can change
- `const` should not be reassigned

### Props vs State

- props come from parent
- state belongs to component

### Promise vs async/await

- Promise is the object
- `async/await` is the cleaner syntax to handle it

### Interface vs type

- interface is mainly for object structure
- type is more flexible

### JavaScript vs TypeScript

- JavaScript is flexible
- TypeScript is safer

---

## 11. Common Mistakes Beginners Make

### In JavaScript

- confusing `==` with `===`
- using `var` everywhere
- not understanding scope
- not returning values from functions when needed
- misunderstanding asynchronous code

### In TypeScript

- using `any` too much
- mixing types incorrectly
- not understanding interface and type alias
- assuming TypeScript runs directly in browser

### In React

- trying to change props directly
- forgetting `key` in lists
- misunderstanding state updates
- writing event names in lowercase like HTML
- not understanding when `useEffect` runs

---

## 12. How to Answer in Viva

For almost every viva question, try this method:

### Step 1. Give definition

Example:

JavaScript is a programming language used to make web pages interactive.

### Step 2. Explain why it is used

It is used to handle events, validate forms, and update content dynamically.

### Step 3. Give a simple example

Example:

It can show an alert when a button is clicked.

### Step 4. If possible, compare

Example:

TypeScript is similar to JavaScript, but it adds type checking.

This structure makes your viva answer sound clear and confident.

---

## 13. Most Important Viva Questions to Practice First

If you have less time, practice these first:

1. What is JavaScript?
2. Difference between `var`, `let`, and `const`
3. What is scope?
4. What is hoisting?
5. What is closure?
6. What is a Promise?
7. What is async/await?
8. What is TypeScript?
9. Difference between JavaScript and TypeScript
10. What is interface?
11. What is React?
12. What is JSX?
13. What is a component?
14. Props vs state
15. What is `useState`?
16. What is `useEffect`?
17. What is Virtual DOM?
18. Why use TypeScript in React?

---

## 14. Final Conclusion

JavaScript, TypeScript, and React are three very important parts of frontend development. JavaScript is the foundation that adds logic and interactivity. TypeScript improves JavaScript by making the code safer and easier to maintain. React helps developers build the user interface in a reusable and efficient way.

If you study the history, definitions, examples, differences, and viva answers from this guide, you will be able to explain these topics with much more confidence in class and in exams.

---

## 15. Final Study Tip

Do not try to memorize only definitions. Try to understand:

- what the concept means
- why it is used
- where it is used
- one simple example
- one difference from a related concept

If you do that, your viva answers will sound natural instead of memorized.
