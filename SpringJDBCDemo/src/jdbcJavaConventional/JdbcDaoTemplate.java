package jdbcJavaConventional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDaoTemplate 
{
	public static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	public static final String DB_URL="jdbc:mysql://localhost/myfirstworkbase";
	
	public static final String USER="root";
	public static final String PASS="root";
	
	public Circle getCircleById(int inputCirlceId)
	{
		Circle circle=null;
		Connection con= null;
		PreparedStatement pst= null;
		ResultSet rst= null;
		
		try 
		{
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			
			con=DriverManager.getConnection(DB_URL,USER,PASS);
			
			System.out.println("creating statemenet");
			String sql="select * from circle where id=1";
			pst=con.prepareStatement(sql);
		
			rst= pst.executeQuery(sql);
			System.out.println(pst.toString());
			
			// for dynamic value pass
			/*
			String sql="select * from circle where id=?";
				pst=con.prepareStatement(sql);
				pst.setInt(1, inputCirlceId);
				rst=pst.executeQuery();
				System.out.println(pst.toString());
			*/
			while(rst.next())
			{
				int id=rst.getInt("id");
				String name= rst.getNString("name");
				circle= new Circle(id,name);
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(rst!=null)
			{
				try {
					rst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pst!=null)
			{
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				con=null;
			}
		}
		System.out.println("GoodBye !!");
		return circle;
	}
}
