package com.example.demo;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService 
{
	@Autowired
	ProductRepo pr;
	
	public String newproduct(Product p)
	{
		pr.save(p);
		return "product added succesfully";
	}
	
	
	public String deleteproduct(Product p)
	{
		pr.delete(p);
		return "product deleted succesfully";
	}
	
	public List<Product> viewallproducts() {
		List<Product> result = pr.findAll();
		return result;
	}


	
	 public List<Product> searchProducts(String query) {
		        List<Product> products = pr.searchProducts(query);
		        return products;
		    }
	}


	
	

		
	
	
	
	

