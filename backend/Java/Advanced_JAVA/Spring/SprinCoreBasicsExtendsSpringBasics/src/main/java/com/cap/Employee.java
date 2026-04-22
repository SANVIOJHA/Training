package com.cap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
 * @Component → Registers this class as a Spring Bean.
 *
 * @Scope("prototype") → Every time getBean() is called,
 * a NEW Employee object will be created.
 *
 * If we remove @Scope, default scope is "singleton"
 * meaning only one object will exist in the container.
 */

@Component
@Scope("prototype")
public class Employee {

    /*
     * @Value is used to inject simple values
     * directly into fields from configuration.
     *
     * In real applications, values usually come from
     * application.properties file.
     */

    @Value("Sanvi")
    private String name;

    @Value("1")
    private int id;

    @Value("22")
    private float age;

    public Employee() {
        System.out.println("Employee object created");
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + ", age=" + age + "}";
    }
}