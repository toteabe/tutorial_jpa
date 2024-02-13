package org.iesvdm.tutorial.config;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * CONFIG PARA EXPONER LOS IDs EN SPRING DATA REST
 * ACCESIBLE EN /data-api
 */
@Configuration
public class ExposeAllIdConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        //PARA ANIADIR A TODAS LAS ENTIDADES EL ID
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(jakarta.persistence.metamodel.Type::getJavaType).toArray(Class[]::new));

    }
}