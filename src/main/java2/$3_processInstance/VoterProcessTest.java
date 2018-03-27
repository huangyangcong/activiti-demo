package $3_processInstance;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.pool.DruidDataSource;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * @author: jhs
 * @desc:
 * @date: Create in 2018/2/28  17:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class VoterProcessTest {
	
	@BeforeClass
	public static void setupJNDI() throws NamingException, SQLException {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("oracle.jdbc.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		SimpleNamingContextBuilder builder =new SimpleNamingContextBuilder();
		builder.bind("java:comp/env/jdbc/jhs_orcl", ds);
		/*
/*		ds.setUrl("jdbc:oracle:thin:@192.168.129.75:1521:ORCL");
		ds.setUsername("hurcd_credit");
		ds.setPassword("hurcd_credit");
		SimpleNamingContextBuilder builder =new SimpleNamingContextBuilder();
		builder.bind("java:comp/env/jdbc/hurcd_credit", ds);
		
		ds.setUrl("jdbc:oracle:thin:@61.190.70.122:8023:ORCL");
		ds.setUsername("hurcd_credit");
		ds.setPassword("123");
		SimpleNamingContextBuilder builder =new SimpleNamingContextBuilder();
		builder.bind("java:comp/env/jdbc/hurcd_credit_bh", ds);
		*/
		builder.activate();
	}
}
