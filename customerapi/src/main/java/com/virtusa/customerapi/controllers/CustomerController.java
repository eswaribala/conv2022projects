package com.virtusa.customerapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.virtusa.customerapi.models.Customer;
import com.virtusa.customerapi.services.CustomerService;

import lombok.extern.slf4j.Slf4j;
//API design
@RestController
@RequestMapping("/customers")
@RefreshScope
@Slf4j
public class CustomerController {
    @Autowired
	private CustomerService customerService;
    @Value("${message}")
    private String message;
    //add customer
    @PostMapping({"/v1.0"})
    public ResponseEntity<?> addv10Customer(@RequestBody Customer customer){
       Customer customerObj=this.customerService.addCustomer(customer);
       if(customerObj!=null)
    	   return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
       else
    	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not Created");
    
    }
    @PostMapping({"/v1.1"})
    public ResponseEntity<?> addv11Customer(@RequestBody Customer customer){
       Customer customerObj=this.customerService.addCustomer(customer);
       if(customerObj!=null)
    	   return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
       else
    	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not Created");
    
    }
    //get customers
    @GetMapping({"/v1.0", "/v1.1"})
    public List<Customer> getAllCustomers(){
    	log.info("Message"+message);
    	return this.customerService.getAllCustomers();
    }
    
  //get customers
    @GetMapping({"/v1.0/{customerId}", "/v1.1/{customerId}"})
    public ResponseEntity<?> getCustomerById(@PathVariable("customerId") long customerId){
    	  Customer customerObj=this.customerService.getCustomerById(customerId);
          if(customerObj!=null)
       	   return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
          else
       	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not found");
    }
    //filtering
    
  //get
    @GetMapping({"/v1.0/filters/{customerId}", "/v1.1/filters/{customerId}"})
  		public ResponseEntity<?> getCustomerByFields(@PathVariable("customerId") long customerId,
  				@RequestParam(name = "fields", required = false) String fields){
  			
  			Map<Object,Object> model=new HashMap<>();
  	    	
  	    	Customer customer = this.customerService.getCustomerById(customerId);
  	    	
  	    	if(customer!=null)
  	    	{
  	    		//fields refers to runtime selection
  	    		ObjectMapper mapper = Squiggly.init(new ObjectMapper(), fields);  		
  				return ResponseEntity.ok(SquigglyUtils.stringify(mapper, customer));

  	    	}
  	    	else
  	    	{
  		         model.put("message", "customer not existing");
  			        
  		         return ResponseEntity.ok(model);
  	    	}

  			
  			
  		}
}
