package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdersRepo extends JpaRepository<Orders, Integer> 
{
	@Query("select pid from Orders o where o.email = :email and paid='no'")
	public List<Integer> viewcart(@Param("email") String email);
	
	
	@Query("select oid from Orders o where o.pid = :pid and o.email=:email and paid='no'")
	public int orderid(@Param("pid") int pid,@Param("email") String email);
	
	
	@Query("select pid from Orders o where o.email=:email and paid='yes'")
	public List<Integer> vieworders(@Param("email") String email);
}
