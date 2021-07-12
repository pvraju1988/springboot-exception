package com.example.exception.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.exception.model.Product;

@RestController
public class ProductController {
	private static Map<Integer, Product> productRepo = new HashMap<>();
	   static {
	      Product honey = new Product();
	      honey.setId(1);
	      honey.setName("Honey");
	      productRepo.put(honey.getId(), honey);
	      
	      Product almond = new Product();
	      almond.setId(2);
	      almond.setName("Almond");
	      productRepo.put(almond.getId(), almond);
		 
	}
	 @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	   public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Product product) throws ProductNotfoundException  { 
	      if(!productRepo.containsKey(id)) throw new ProductNotfoundException();
	      productRepo.remove(id);
	      product.setId(id); 
	      productRepo.put(id, product);
	      return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
	   }

}
