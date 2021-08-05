package jdbcSpring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JDBCTestMain {

	public static void main(String[] args)
	{

		AbstractApplicationContext context=new ClassPathXmlApplicationContext("bean.xml");
	//	JdbcDaoTem jdbcDaoTemp=context.getBean(JdbcDaoTem.class);
		
		//Circal circlObj= jdbcDaoTemp.getCircalById(1);
	/*	
		int count= jdbcDaoTemp.getCircalCount();
		System.out.println("circle count "+count);
		
		String cName= jdbcDaoTemp.getCircalName(2);
		System.out.println("Circle name is "+cName);
		
		Circal circal= jdbcDaoTemp.getCircalObject(1);
		System.out.println("Circle id is "+ circal.getId());
		System.out.println("Circle name is "+ circal.getName());
		*/
		/*
		List<Circal> circalList= jdbcDaoTemp.getCircalObjectList();
		for(Circal lst:circalList)
		{
			System.out.println("Circal Id   :"+lst.getId());
			System.out.println("Circal Name :"+lst.getName()+"\n---------------------------\n");
		}*/
		
		//insert into table 
		//Circal circalObje= new Circal(3,"C3");
	//	jdbcDaoTemp.insertCircalObject(circalObje);
		
				
		/*
		List<Circal> circalList1= jdbcDaoTemp.getCircalObjectList();
		for(Circal lst:circalList1)
		{
			System.out.println("Circal Id   :"+lst.getId());
			System.out.println("Circal Name :"+lst.getName()+"\n---------------------------\n");
		}*/
		
	// create table 	
	//	jdbcDaoTemp.createTraingleTable();
		
	//insert into table
		/*Circal cir= new Circal(5,"C5");
		jdbcDaoTemp.insertObjByPreparedStatement(cir);*/
		
		
	/*	List<Circal> list= jdbcDaoTemp.getAllCircalByRsultSetExtractor();
		for( Circal c:list)
		{
			System.out.println("Circal Id   :"+c.getId());
			System.out.println("Circal Name :"+c.getName()+"\n---------------------------\n");
		}
		*/
		
		//update circal name by veriable args
		//jdbcDaoTemp.updateCircalName(2, "new C-2");
		
		SimpleJDBCDAOImpl simpleJDBCDAOImpl= context.getBean(SimpleJDBCDAOImpl.class);
		System.out.println(simpleJDBCDAOImpl.getCircalCount()+" in simple way !!");
		
		context.close();
	}
}
