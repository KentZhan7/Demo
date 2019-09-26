package com.demo.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <b>MyBatis基礎配置</b>
 * 
 * @author tp1310
 */
@Configuration // spring-boot自動config
@MapperScan(basePackages = {"com.demo.orm.dao"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig implements TransactionManagementConfigurer {
	private static Logger log = LoggerFactory.getLogger(MybatisConfig.class);
	/**
	 * <b>SqlSessionFactory Bean</b>
	 * 
	 * @author tp1310
	 */
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
//		bean.setTypeAliasesPackage("gov.nsb.kmsapp.util");
		try {
			return bean.getObject();
		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Bean(name = "dataSource")
    public DataSource dataSource() throws IOException {
		
		ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        
		String usrnme = "";
		String paswd =  "";
		String url = "jdbc:h2:~/test";
		String driver = "org.h2.Driver";
		
        DataSource dataSource = new PooledDataSource(driver, url, usrnme, paswd);
        return dataSource;
    }

	/**
	 * <b>SqlSessionTemplate Bean</b>
	 * 
	 * @author tp1310
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() throws IOException {
        return new DataSourceTransactionManager(dataSource());
    }

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return null;
	}

}
