package com.cap.springDto;

public class StudentNotFoundException extends RuntimeException {
   public  StudentNotFoundException(){

    }
   public  StudentNotFoundException(String msg){
        super(msg);
    }
}
