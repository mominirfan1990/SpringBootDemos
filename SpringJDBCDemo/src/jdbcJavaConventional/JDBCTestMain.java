package jdbcJavaConventional;

public class JDBCTestMain {

	public static void main(String[] args)
	{
		Circle circleObj= new JdbcDaoTemplate().getCircleById(1);
		System.out.println(circleObj.getName());
	}

}
