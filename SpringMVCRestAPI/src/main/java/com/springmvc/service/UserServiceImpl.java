package com.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService
{
	private static final AtomicLong counter= new AtomicLong();
	
	private static List<User> users;
	
	static {
		users=populateDummyUsers();
	}
	
	public User findById(long id) {
		for(User user:users)
		{
			if(user.getId()==id)
				return user;
		}
		return null;
	}

	public User findByName(String name) {
		for(User user:users)
		{
			if(user.getName().equals(name))
				return user;
		}
		return null;
	}

	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
		
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.add(index,user);		
	}

	public void deleteUserById(long id) {
		Iterator<User> iterator= users.iterator(); 
		while(iterator.hasNext())
		{
			User user = iterator.next();
			if(user.getId()==id)
			{
				iterator.remove();
			}
		}
		
	}

	public List<User> findAllUsers() {
		return users;
	}

	public void deleteAllUsers() {
		users.clear();
		
	}
	
	public boolean isUserExist(User user) 
	{
		/*boolean flag= false;
		for(User users:users)
		{
			if(users.getName().equals(user.getName()))
			{
				flag=true;
				break;
			}
		
		}
		if(!flag)
			return false;
		else
			return true;*/
		
		return findByName(user.getName())!=null;
	}
	
	private static List<User> populateDummyUsers()
	{
		List<User> users= new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"sam",30,70000));
		users.add(new User(counter.incrementAndGet(),"tom",40,50000));
		users.add(new User(counter.incrementAndGet(),"Jerome",45,30000));
		users.add(new User(counter.incrementAndGet(),"Silvia",50,40000));
		return users;
		
	}

}
