package com.capgemini.basicSpringBoot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.models.OpenAPI;

@RestController
public class DemoController {

    @Autowired
    private EmployeeJpaRepo jpa;

    @GetMapping("/a")
    public String getHi() {
        return "hitml";
    }

    //@GetMapping("/b/{name}/{id}")
    ////http://localhost:8080/b/Allen/10
    //public String sendData(@PathVariable String name ,@PathVariable  String id) {
    //    System.out.println(name);
    //    return "success";
    //}

    //@GetMapping("/b")
    ////http://localhost:8080/b?name=Allen
    //public String sendData(@RequestParam String name) {
    //    System.out.println(name);
    //    return "success";
    //}

    @PostMapping("/emp")
    public String createEmp(@RequestBody Employee e) {
        System.out.println(e.getId());
        System.out.println(e.getName());
        System.out.println(e.getSalary());
        jpa.save(e);
        return "success";
    }

    
    
    
    @GetMapping("/get-id/{id}")
    public Employee getById(@PathVariable int id) {
        Optional<Employee> emp = jpa.findById(id);
      if(emp.isPresent()) {
    	  return emp.get();
    }else{
    	return null;
    }
}

    @GetMapping("/get-all")
    public List<Employee> getAllEmployee() {
        List<Employee> res = jpa.findAll();
        return res;
    }
    
    
    @PutMapping("/update-id/{id}")

    public String updateEmployee(@PathVariable int id, @RequestBody Employee e) {
        Employee employee = getById(id);
        employee.setId(e.getId());
        employee.setId(e.getId());
        employee.setName(e.getName());
        employee.setSalary(e.getSalary());
        jpa.save(employee);
        return "data updated";
    }
    
    @PatchMapping("/update-field/{id}")
    public String updateEmployeeDetails(@PathVariable int id,@RequestBody Employee e) {
    	Employee emp=getById(id);
    	if(emp!=null) {
    		if(e.getName()!=null) {
    			emp.setName(e.getName());
    		}if(e.getId()!=0) {
    			emp.setId(e.getId());
    		}if(e.getSalary()!=0.0) {
    			emp.setSalary(e.getSalary());
    		}
    		jpa.save(emp);
    		return "Data Saved";
    	}else {
    		throw new EmployeeNotFoundException("EmployeeNotFoundException  by id "+id);
    	}
    }
    
    
    @DeleteMapping("/delete-emp/{id}")
    public String deleteEmployee(@PathVariable int id) {
    	Employee emp=getById(id);
    	if(emp!=null) {
    		jpa.delete(emp);
    		return "deleted";
    	}else {
    		return "data does not exists";
    	}
    }
    
    
    
    @GetMapping("find-name/{name}")
    public Employee findByName(@PathVariable String name) {
    	return jpa.getByName(name);
    }
    
    
    @GetMapping("/find-name-email/{name}/{email}")
    public Employee findByNameAndEmail(@PathVariable String name,  @PathVariable String email) {
        return jpa.findByNameAndEmail(name, email);
    }
    
public OpenAPI getOpenAPI() {
	return new OpenAPI();
	
}



@PutMapping("update-salary/{oldsalary}/{newsalary}")
public String updateBySalary(@PathVariable double oldsalary,@PathVariable double newsalary) {
	int count=jpa.updateBySalary(oldsalary, newsalary);
	if(count>0) {
		return "updated";
	}
	else {
		throw new EmployeeNotFoundException("Employee not found with salary "+oldsalary);
	}
}

////this exception is only handling exception in the  controller class only this specific class will it handle exception 
/// for exception to be handled in overall application we will create a class where this exception will be 
/// 


//@ExceptionHandler(EmployeeNotFoundException.class)
//public String handleException(EmployeeNotFoundException e) {
//	return e.getMessage();
//}
//@GetMapping("/emp-page/{page}/{size}")
//public List<Employee> getEmpData(@PathVariable int page,
//                                 @PathVariable int size) {
//
//
//    Page<Employee> empPage =jpa.getEmployees(pageable);
//
//    return empPage.getContent();
//}


}