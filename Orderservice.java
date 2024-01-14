package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Orderservice 
{
	@Autowired
	OrdersRepo or;
	@Autowired
	ProductRepo pr;
	
	public String placeorder(Orders order)
	{
		int price=pr.totalprice(order.getPid());
		order.setPrice(price);
		order.setPaid("no");
		or.save(order);
		return "order placed";
	}
	
	
	public List<Product> viewcart(String email)
	{
		List<Integer> prodid=or.viewcart(email);
		 return pr.findAllById(prodid);
	}
	
	
	public String buynow(String email,int pid,int price)
	{
		int actualprice=pr.totalprice(pid);
		if(price==actualprice)
		{
			try {
				int orderid=or.orderid(pid,email);
				Optional<Orders> order = or.findById(orderid);
				if (order.isPresent()) {
					Orders order1 = order.get();
					if(order1.getPaid().equalsIgnoreCase("yes"))
					{
						return "already paid";
					}
					else
					{
						order1.setPaid("yes");
					    or.save(order1);
					    return "payment succesfull";
					}
					
				    }
				return "error occured";
			} catch (Exception e) {
				// TODO: handle exception
				return "error occured";
			}
		
		}
		else
		{
			return "payment failed";
		}
		
		
		
	}
	public List<Product> orders(String email)
	{
		List<Integer> prodid=or.vieworders(email);
		 return pr.findAllById(prodid);
	}
	
}
