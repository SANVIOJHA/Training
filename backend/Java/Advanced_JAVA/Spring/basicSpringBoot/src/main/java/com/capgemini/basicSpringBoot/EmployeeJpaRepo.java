package com.capgemini.basicSpringBoot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeJpaRepo extends JpaRepository<Employee, Integer> {
	public Employee getByName(String name);
	public Employee getBySalary(double salary);
	public Employee findByNameAndEmail(String name, String email);
	
	@Modifying
	@Transactional
	@Query(value="update Employee set salary=:newsalary where salary=:oldsalary",nativeQuery=true)
	public int updateBySalary(@Param("oldsalary") double oldsalary,@Param("newsalary") double newsalary);
	
	

}