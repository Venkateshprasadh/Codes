package com.example.demo;

import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
	@Query("select u from User u where u.email = :email")
	Optional<User> findByEmail(String email);
	
	
	@Query("select subscription from User u where u.email = :email")
	public String subscribed(String email);
	
	
//	@Query("update User u set u.subscription='yes' where u.email = :email")
//	public String updatecustomer(String email);
	
	

	
//	@Query("insert into orders values (:pid,:email)")
//	  void placeorder(String email,int pid);

}
