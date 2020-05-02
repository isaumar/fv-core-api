package com.bw.foodvendor.configuration;


import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
@EnableAsync
@ComponentScan({"com.bw"})
@EnableJpaRepositories({"com.bw"})
public class ServiceLayerConfiguration {

    @Profile({"dev", "test"})
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDev(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPersistenceUnitName("dev_unit");
        HibernateJpaDialect hibernateJpaDialect = new HibernateJpaDialect();
        factoryBean.setJpaDialect(hibernateJpaDialect);
        return factoryBean;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPersistenceUnitName("prod_unit");
        HibernateJpaDialect hibernateJpaDialect = new HibernateJpaDialect();
        factoryBean.setJpaDialect(hibernateJpaDialect);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public ConstraintValidatorFactory constraintValidatorFactory(final AutowireCapableBeanFactory beanFactory) {
        return new ConstraintValidatorFactory() {

            @Override
            public void releaseInstance(
                    ConstraintValidator<?, ?> arg0) {
                beanFactory.destroyBean(arg0);
            }

            @Override
            public <T extends ConstraintValidator<?, ?>> T getInstance(
                    Class<T> arg0) {
                try {
                    return beanFactory.getBean(arg0);
                } catch (NoSuchBeanDefinitionException e) {
                    if (arg0.isInterface()) {
                        throw e;
                    }
                    return beanFactory.createBean(arg0);
                }
            }
        };
    }

    @Bean
    public Validator validator(ConstraintValidatorFactory factory) {
        return Validation.byDefaultProvider()
                .configure()
                .constraintValidatorFactory(factory)
                .buildValidatorFactory()
                .getValidator();
    }

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster
                = new SimpleApplicationEventMulticaster();

        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }

}

