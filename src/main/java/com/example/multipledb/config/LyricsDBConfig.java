package com.example.multipledb.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "lyricsEntityManagerFactory",
        transactionManagerRef = "lyricsTransactionManager",
        basePackages = { "com.example.multipledb.repositories.lyrics" })
public class LyricsDBConfig {

    @Bean(name="lyricsProps")
    @ConfigurationProperties("spring.lyrics.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name="lyricsDatasource")
    @ConfigurationProperties(prefix = "spring.lyrics.datasource")
    public DataSource datasource(@Qualifier("lyricsProps") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }


    @Bean(name="lyricsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("lyricsDatasource") DataSource dataSource){
        return builder.dataSource(dataSource)
                .packages("com.example.multipledb.models.lyrics")
                .persistenceUnit("Lyrics").build();
    }


    @Bean(name = "lyricsTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("lyricsEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
