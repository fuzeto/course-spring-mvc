package br.com.casadocodigo.loja.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Profile("prod")
public class JPAProductionConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean
    private DriverManagerDataSource dataSource() throws URISyntaxException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");

        URI dbUrl = new URI(environment.getProperty("DATABASE_URL"));

        dataSource.setUrl("jdbc:postgresql://ec2-52-86-73-86.compute-1.amazonaws.com:5432/d7479h91f73nnl");
//        dataSource.setUrl("jdbc:postgresql://"+dbUrl.getHost()+":"+dbUrl.getPort()+dbUrl.getPath());
        dataSource.setUsername("rcfbhyjebbmffs");
        dataSource.setPassword("79a7f67dbd30007092dde364d44c12706ffe828ab92f584d06fbe91add5c0d50");

        return dataSource;
    }
}
