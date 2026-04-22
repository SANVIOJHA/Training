package com.cap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/// for multiple entries of datas we cant use more values in beans  its like 1 bean ==i object
///  for multiple values it will create problems thats why we are using @ Component
/// this is class based configuration
/// and using bean.xml is xml based configuration
///
@Component
@Scope("prototype")
public class Employee {
    @Value("sanvi")
    private String name;
    @Value("1")
    private int id;
    @Value("22")
    private float age;

    public Employee(){

    }

    public Employee(String name,int id,float age) {
        this.name = name;
        this.id=id;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    float getAge(){
        return age;
    }
    public void setAge(float age){
        this.age=age;
    }


}
