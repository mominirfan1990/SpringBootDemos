package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springmvc.model.User;
import com.springmvc.service.UserService;

@RestController
public class HelloWorldRestController 
{
	@Autowired
	UserService userService;
	
	// ----- retrive all users-----
	
	@RequestMapping(value="/user/", method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers()
	{
		List<User> users= userService.findAllUsers();
		if(users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		
	}
	
	//----retrive single user----
	
	@RequestMapping(value = "/user/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id)
	{
		System.out.println("Fetching User with id : "+id);
		User user = userService.findById(id);
		if(user==null)
		{
			System.out.println("User with id : "+id+" not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	//-------create user----
	
	@RequestMapping(value="/user/",method=RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user,UriComponentsBuilder ucBuilder)
	{
		System.out.println("Create user by user name : "+user.getName());
		if(userService.isUserExist(user))
		{
			System.out.println("A user with name :"+ user.getName()+" is already Exists ");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		userService.saveUser(user);
		
		HttpHeaders headers= new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}
	
	//--------Upadte User by PUT -----
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id")long id, @RequestBody User user){
		System.out.println("Upadating Users :"+id+" in put");
		
		User currentUser= userService.findById(id);
		if(currentUser==null)
		{
			System.out.println("User with user Id :"+id+" not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		currentUser.setAge(user.getAge());
		currentUser.setName(user.getName());
		currentUser.setSalary(user.getSalary());
		
		userService.updateUser(currentUser);
		
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	//--------Upadte User by Patch-----
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.PATCH)
	public ResponseEntity<User> updateUserPatch(@PathVariable("id")long id, @RequestBody User user){
		System.out.println("Upadating Users :"+id+" in patch");
		
		User currentUser= userService.findById(id);
		if(currentUser==null)
		{
			System.out.println("User with user Id :"+id+" not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		currentUser.setSalary(user.getSalary());
		
		userService.updateUser(currentUser);
		
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	//--------- delete user------------
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id)
	{
		System.out.println("Fetching and Deleting user with User Id : "+id);
		
		User currentUser=userService.findById(id);
		if(currentUser==null)
		{
			System.out.println("User with user Id :"+id+" not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		userService.deleteUserById(id);
		
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		
	}
	
	
	//-----------Delete all Users-------
	
	@RequestMapping(value="/user/", method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers()
	{
		System.out.println("Deleteing all Users");
		
		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		
	}
	
	
}
