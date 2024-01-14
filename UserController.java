package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "user")
public class UserController 
{
	@Autowired
	UserService us;
	@Autowired
	Orderservice os;
//	@Autowired
//	BillService bs;
	
	
	@PostMapping(value = "SignUp" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public String Registeruser(@RequestBody User user) {
		return us.register(user);
	}
	
 
	@PostMapping(value = "SignIn",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String Loginuser(@RequestBody User user)
	{
		return us.login(user);
	}
	@PostMapping(value = "PlaceOrder",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String placeorder(@RequestBody Orders order)
	{
		return os.placeorder(order);
	}
//	@GetMapping(value = "cart",consumes = MediaType.APPLICATION_JSON_VALUE)
//	public List<Product> cart(@RequestParam("email") String email)
//	{
//		return os.viewcart(email);
//	}
	@GetMapping("/cart")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("email") String email){
        return ResponseEntity.ok(os.viewcart(email));
    }
	
	@PostMapping(value = "subscribed")
	public ResponseEntity<String> subscribed(@RequestParam("email") String email)
	{
 		return  ResponseEntity.ok(us.subscribed(email));
	}
	@GetMapping(value = "buynow/{email}/{pid}/{price}")
	public  ResponseEntity<String> buy(@PathVariable("email")  String email,@PathVariable("pid") int pid,@PathVariable("price")  int price)
	{
		return  ResponseEntity.ok(os.buynow(email, pid, price));
	}
	@GetMapping(value = "previousorders/{email}")
	public ResponseEntity<List<Product>> orders(@PathVariable("email") String email)
	{
		return ResponseEntity.ok(os.orders(email));
	}
	
	
//	@PostMapping(value = "subscribed/{email}")
//	public ResponseEntity<String> subscribed(@PathVariable("email") String email)
//	{
// 		return  ResponseEntity.ok(us.subscribed(email));
//	}
	
	@PostMapping(value = "subscribed/{email}/newproduct",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String sellproduct(@PathVariable("email") String email,@RequestBody Product product)
	{
		return us.sellproduct(email,product);
	}
	
	
}
