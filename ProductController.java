package com.example.demo;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "product")
public class ProductController 
{
	@Autowired
	ProductService ps;
	
	

	@PostMapping(value = "newproduct" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveproduct(@RequestBody Product product) {
		return ps.newproduct(product);
	}
	
	
	@GetMapping(value="viewproducts",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getallproducts() {
		return ps.viewallproducts();
		
	}
	
	
//	@GetMapping(value="searchproduct/{category}",produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<Product> searchcategory(@PathVariable String category) {
//		return ps.searchresult(category);
//		
//	}
	
	 @GetMapping("/search")
	    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query){
	        return ResponseEntity.ok(ps.searchProducts(query));
	    }
	
	
	
	
}
