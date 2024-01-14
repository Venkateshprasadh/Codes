package com.example.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
	@Autowired
	UserRepo ur;
	@Autowired
	ProductRepo pr;
	
	
	public String register(User u)
	{
		Optional<User> result = ur.findById(u.getEmail());
		if(result.isPresent()) {
					return "user already registered";
		}
		else
		{
			ur.save(u);
			return "Registered succesfully";
		}
		
	}
	
	
	public String login(User u)
	{
		Optional<User> result = ur.findById(u.getEmail());
		if(result.isPresent()) {
			User u1 = result.get();
			if(u1.getPassword().equals(u.getPassword()))
			{
				return "Login Succesfull";
			}
			else
			{
				return "invalid Password";
			}
					
		}
		else
		{
			
			return "User Not registered";
		}
		
	}
	
//	public String placeorder(String email,int pid)
//	{
//		ur.placeorder(email, pid);
//		return "order placed";
//	}
	public String sellproduct(String email,Product product)
	{
		String sub= ur.subscribed(email);
		
		if(sub.equalsIgnoreCase("yes"))
		{
			pr.save(product);
			return "new product added";
		}
		else
		{
			return "try be Subscriber";
		}
	}
	
	public List<User> allusers()
	{
		return ur.findAll();
		
	}
	public String updateCustomer(String email)
	{
		Optional<User> result  = ur.findById(email);
	       if(result.isPresent())
	       	{	if(ur.subscribed(email).equalsIgnoreCase("yes"))
	       		{
	       		return "already subscriber";
	       		}else
	       		{
	       			User u = result.get();
	       			u.setSubscription("yes");
	       			ur.saveAndFlush(u);
	       			return  "subscribed succesfully";
	       		}
	       		
	       }
	       else
	       {
	    	   return "email not registered";
	       }
		
	}
	
	
	public String removesubscription(String email)
	{
		Optional<User> result  = ur.findById(email);
	       if(result.isPresent())
	       	{	if(ur.subscribed(email).equalsIgnoreCase("yes"))
	       		{
	       		User u = result.get();
       			u.setSubscription("no");
       			ur.saveAndFlush(u);
       			
       			return  "unsubscribed succesfully";
	       		
	       		}else
	       		{
	       			return "not a  subscriber";
	       		}
	       		
	       }
	       else
	       {
	    	   return "email not registered";
	       }
		
	}
	public String subscribed(String email)
	{
		return ur.subscribed(email);
	}
	
	
}
