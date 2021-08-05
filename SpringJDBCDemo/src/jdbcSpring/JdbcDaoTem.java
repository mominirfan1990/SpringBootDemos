package jdbcSpring;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcDaoTem 
{
	
	//private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate= new JdbcTemplate(dataSource);
		//this.dataSource=dataSource;
	}
	public int getCircalCount()
	{
		String sql="select count(*) from circle";
		int count=jdbcTemplate.queryForObject(sql,null,Integer.class);
		
		return count;
	}
	public String getCircalName(int circalId)
	{
		String sql="select name from circle where id=?";
		String cname=jdbcTemplate.queryForObject(sql,new Object[] {circalId},String.class);
		
		return cname;
	}
	
	//RowMapper
	
	public Circal getCircalObject(int circalId)
	{
		String sql="select * from circle where id=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {circalId}, new CircalMapper());
	}
	private static final class CircalMapper implements RowMapper<Circal>
	{
		
		@Override
		public Circal mapRow(ResultSet rs, int rowNum) throws SQLException {
			Circal circal=new Circal(rs.getInt(1), rs.getNString(2));
			return circal;
		}
		
	}
	
	public List<Circal> getCircalObjectList()
	{
		String sql="select * from circle";
		return jdbcTemplate.query(sql,new CircalMapperLO());
	}
	private static final class CircalMapperLO implements RowMapper<Circal>
	{

		@Override
		public Circal mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			Circal circal= new Circal(rs.getInt("id"),rs.getNString("name"));
			return circal;
		}
		
	}
	
	//insert Object used update method
	
	public void insertCircalObject(Circal circal)
	{
		String sql="insert into circle (id, name) values(?,?)";
		jdbcTemplate.update(sql, new Object[] {circal.getId(), circal.getName()});
	}
	
	//create TraingleTable
	
	public void createTraingleTable()
	{
		//String sql="create table 'Traingle'('ID' int(11) NOT NULL, 'NAME' varchar(45)"
			//	+"DEFALUT NULL, PRIMARY KEY('ID')) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		
		String sql="create table Traingle (ID int(11) NOT NULL primary Key,NAME varchar(45))";
		jdbcTemplate.execute(sql);
		System.out.println("Table creation done");
	}
	
	//insert Object By Prepared Statement
	
	public void insertObjByPreparedStatement(Circal circal)
	{
		String sql="insert into circle (id, name) values(?,?)";
		jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, circal.getId());
				ps.setString(2, circal.getName());
				return ps.execute();
			}
		});
	}
	
	//Result set extractor
	
	public List<Circal> getAllCircalByRsultSetExtractor()
	{
		String sql="select * from circle";
		
		List<Circal> listC=jdbcTemplate.query(sql, new ResultSetExtractor<List<Circal>>() {
			@Override
			public List<Circal> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Circal> list= new ArrayList<>();
				while(rs.next())
				{
					Circal circal=new Circal(rs.getInt(1), rs.getNString(2));
					list.add(circal);
				}
				return list;
			}
			
		});
		return listC;
		
	}
	
	//Update with variable args
	public void updateCircalName(int circalId, String updatedCircalName)
	{
		String sql= "update circle set name=? where id=?";
		int cnt=jdbcTemplate.update(sql, updatedCircalName, circalId);
		if(cnt>0)
			System.out.println("record updated ");
		else
			System.out.println("record Not updated ");
	}
	
}
