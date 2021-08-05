package com.irfan.springjpa;
import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.irfan.springjpa.configuration.AppConfig;
import com.irfan.springjpa.model.Product;
import com.irfan.springjpa.service.ProductService;




/**
 * Simple tester for Spring-Data-JPA.
 **/
public class SpringDataJPAMain {
    public static void main(String[] args) {

//        //Create Spring application context
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");

    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //Get service from context.
        ProductService productService = (ProductService) context.getBean("productService");

        //Add some items
        productService.add(new Product(1, "Television"));
        productService.add(new Product(2, "Phone"));
        productService.addAll(Arrays.asList(
                new Product(3, "Peach"),
                new Product(4, "Strawberry"),
                new Product(5, "Melone"),
                new Product(6, "Onion")
                ));

        //Test entity listing
        System.out.println("findAll=" + productService.findAll());

        //Test specified find methods
        System.out.println("findByName is 'Peach': " + productService.findByNameIs("Peach"));
        System.out.println("findByNameContainingIgnoreCase 'on': " + productService.findByNameContainingIgnoreCase("on"));

        context.close();
    }
}