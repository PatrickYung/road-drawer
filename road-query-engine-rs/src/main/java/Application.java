/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * <b><code>Application</code></b>
 * <p>
 * Main entry of the application including datasource configuration
 * </p>
 * <b>Create Time:</b> 2017/1/22 16:12
 *
 * @author 朱贺天
 * @version 0.1.0
 * @since road-query-engine-rs 0.1.0
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.richstonedt.*"})
@MapperScan("com.richstonedt.road.query.engine.cs.dao")
public class Application {

    private static final Logger LOG = Logger.getLogger(Application.class);

    /**
     * set data source
     *
     * @return org.apache.tomcat.jdbc.pool.DataSource
     * @see DataSource
     * @since road-query-engine 0.1.0
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DataSource();
    }

    /**
     * sqlSessionFactoryBean
     *
     * @return org.apache.ibatis.session.SqlSessionFactory
     * @see SqlSessionFactory
     * @since road-query-engine 0.1.0
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        /* DateSource */
        sqlSessionFactoryBean.setDataSource(dataSource());

        /* Mapper */
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mappers/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    /**
     * transactionManager
     *
     * @return org.springframework.transaction.PlatformTransactionManager
     * @see PlatformTransactionManager
     * @since road-query-engine 0.1.0
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());
        manager.setRollbackOnCommitFailure(false);
        return manager;
    }

    /**
     * Entry of the App
     *
     * @param args  args
     * @since road-query-engine 0.1.0
     */
    public static void main(String[] args) {
        LOG.info("-- Application Start! --");
        SpringApplication.run(Application.class, args);
    }
}
