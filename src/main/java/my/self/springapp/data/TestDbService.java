package my.self.springapp.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class TestDbService {

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public void example1() {
		String sql = "SELECT COUNT(*) FROM car_model";
		
//		int rowNum = jdbcTemplate.queryForObject(sql, Integer.class);
		
//		System.out.println("Row number=" + rowNum);
	}
	
	public void example2() {
		String sql = "SELECT * FROM car_model where model LIKE :model_pattern";
		
		RowMapper<String> mapper = (rs, rn) -> {
			StringBuilder sb = new StringBuilder();
			
			sb.append("ID:").append(rs.getString("id"))
			.append(" Model:").append(rs.getString("model"))
			.append(" Production:").append(rs.getString("production"));
			
			
			return sb.toString();
		};
		
		Map<String, String> par = new HashMap<String, String>();
		
		par.put("model_pattern", "%GL%");
		
		List<String> cars = jdbcTemplate.query(sql, par, mapper);
		
		for (String string : cars) {
			System.out.println(string);
		}
		
	}
	
}
