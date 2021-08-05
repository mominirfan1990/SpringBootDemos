package jdbcSpring;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SimpleJDBCDAOImpl extends JdbcDaoSupport
{
	public int getCircalCount()
	{
		String sql= "select count(*) from circle";
		return this.getJdbcTemplate().queryForObject(sql, null, Integer.class);
	}
}
