package com.cap;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//Cannot Instantiate ApplicationContext Directly --- below way is wrong
    //    ApplicationContext applicationContext=new ApplicationContext("bean.xml") ;

        //ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean.xml");
       //this below line is not getting spring object;
       // Employee emp=new Employee();
       // emp.setName("s");
       // System.out.println(emp.getName());
/* here we are using configuration file


        // Load Spring container
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");

        // Get bean from container
        Employee emp = (Employee) context.getBean("employee");

        // Print values
        System.out.println("name is "+ emp.getName());
        System.out.println("id is  "+emp.getId());
        System.out.println("age is " +emp.getAge());



 */

        /////now using annotations for this

        ApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        Employee emp= (Employee) context.getBean("employee");
        /* instead of this  we can use @value in employee class where these attributes are to give the value
        emp.setName("sanvi");
        emp.setAge(32);
        emp.setId(1);
        */
        System.out.println(emp.getName());
        System.out.println(emp.getAge());
        System.out.println(emp.getId());

        //after adding @scope

        Employee e1 = context.getBean(Employee.class);
        Employee e2 = context.getBean(Employee.class);

        System.out.println(e1);
        System.out.println(e2);

//    emp.getBean(Person.class);




    }
}