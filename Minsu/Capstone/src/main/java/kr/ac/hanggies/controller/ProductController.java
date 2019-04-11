package kr.ac.hanggies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hanggies.model.Product;
import kr.ac.hanggies.service.ProductService;;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/products")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();
		
		model.addAttribute("products", products);
		
		return "products";
	}
}
