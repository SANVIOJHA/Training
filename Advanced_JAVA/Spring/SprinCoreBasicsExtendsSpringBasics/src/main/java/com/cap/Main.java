package com.cap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        // ❌ We cannot instantiate ApplicationContext directly
        // ApplicationContext context = new ApplicationContext();  // WRONG

        /*
         * Previously we used XML configuration like this:
         *
         * ApplicationContext context =
         *      new ClassPathXmlApplicationContext("bean.xml");
         *
         * And then:
         * Employee emp = (Employee) context.getBean("employee");
         *
         * That is XML-based configuration.
         */


        // ✅ Now we are using Annotation-based configuration
        ApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);


        // Getting Employee bean from container
        Employee emp = context.getBean(Employee.class);

        /*
         * Since Employee is marked with @Scope("prototype"),
         * every time we call getBean(), a new object will be created.
         */

        System.out.println(emp.getName());
        System.out.println(emp.getAge());
        System.out.println(emp.getId());


        System.out.println("\n---- Prototype Scope Test ----");

        Employee e1 = context.getBean(Employee.class);
        Employee e2 = context.getBean(Employee.class);

        // These two objects will NOT be same because of prototype scope
        System.out.println(e1);
        System.out.println(e2);
        System.out.println("Are both same object? " + (e1 == e2));


        /*
         * If Employee was singleton (default scope),
         * then e1 == e2 would return true.
         */


        System.out.println("\n---- Singleton Bean Test ----");

        // Person and Mobile are singleton beans (default scope)
        Person person1 = context.getBean(Person.class);
        Person person2 = context.getBean(Person.class);

        System.out.println("Are both Person objects same? " + (person1 == person2));
    }
}