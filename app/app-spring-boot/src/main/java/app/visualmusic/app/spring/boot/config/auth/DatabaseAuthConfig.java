package app.visualmusic.app.spring.boot.config.auth;

import app.visualmusic.app.spring.boot.config.property.DatabaseProperty;
import app.visualmusic.app.spring.boot.config.property.LiquibaseProperty;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

import static app.visualmusic.app.spring.boot.config.auth.ModulesBasePackage.AUTH_DB_PACKAGE;

@EnableJpaRepositories(
        entityManagerFactoryRef = DatabaseAuthConfig.ENTITY_MANAGER_FACTORY,
        transactionManagerRef = DatabaseAuthConfig.TRANSACTION_MANAGER,
        basePackages = DatabaseAuthConfig.JPA_REPOSITORY_PACKAGE
)
@EntityScan(
        basePackages = DatabaseAuthConfig.ENTITY_PACKAGE
)
@Configuration
@EnableTransactionManagement
public class DatabaseAuthConfig {
    public static final String JPA_REPOSITORY_PACKAGE = AUTH_DB_PACKAGE + ".repository";
    public static final String ENTITY_PACKAGE = AUTH_DB_PACKAGE + ".entity";

    public static final String DATASOURCE_PROPERTY_PREFIX = "app.auth.datasource";
    public static final String TRANSACTION_MANAGER = "authTransactionManager";
    public static final String ENTITY_MANAGER_FACTORY = "authEntityManagerFactory";
    public static final String DATA_SOURCE = "authDataSource";
    public static final String DATABASE_PROPERTY = "authDatabaseProperty";

    public static final String LIQUIBASE_BEAN_NAME = "authLiquibase";
    public static final String LIQUIBASE_PROPERTY_PREFIX = "app.auth.liquibase";
    public static final String LIQUIBASE_PROPERTY = "authLiquibaseProperty";

    @Bean(DATABASE_PROPERTY)
    @ConfigurationProperties(prefix = DATASOURCE_PROPERTY_PREFIX)
    public DatabaseProperty appDatabaseProperty() {
        return new DatabaseProperty();
    }

    @Bean(DATA_SOURCE)
    public DataSource appDataSource(
            @Qualifier(DATABASE_PROPERTY) DatabaseProperty databaseProperty
    ) {
        return DataSourceBuilder
                .create()
                .username(databaseProperty.getUsername())
                .password(databaseProperty.getPassword())
                .url(databaseProperty.getUrl())
                .driverClassName(databaseProperty.getClassDriver())
                .build();
    }

    @Bean(ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean appEntityManager(
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPersistenceUnitName(ENTITY_MANAGER_FACTORY);
        em.setPackagesToScan(ENTITY_PACKAGE);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        final HashMap<String, Object> properties = new HashMap<>();

        properties.put("javax.persistence.validation.mode", "none");
        properties.put("hibernate.hbm2ddl.auto", "validate");

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(TRANSACTION_MANAGER)
    public PlatformTransactionManager sqlSessionTemplate(
            @Qualifier(ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean entityManager,
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManager.getObject());
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }

    @Bean(LIQUIBASE_PROPERTY)
    @ConfigurationProperties(prefix = LIQUIBASE_PROPERTY_PREFIX)
    public LiquibaseProperty liquibaseProperty() {
        return new LiquibaseProperty();
    }

    @Bean(name = LIQUIBASE_BEAN_NAME)
    public SpringLiquibase authLiquibase(
            @Qualifier(DATA_SOURCE) DataSource dataSource,
            @Qualifier(LIQUIBASE_PROPERTY) LiquibaseProperty liquibaseProperty
    ) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(liquibaseProperty.getChangeLog());
        liquibase.setContexts("auth");
        return liquibase;
    }
}
