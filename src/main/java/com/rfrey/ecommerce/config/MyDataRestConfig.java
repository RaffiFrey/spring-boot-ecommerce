package com.rfrey.ecommerce.config;

import com.rfrey.ecommerce.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedActions = { HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        disableUnsupportedHttpMethods(Product.class, config, unsupportedActions);
        disableUnsupportedHttpMethods(ProductCategory.class, config, unsupportedActions);
        disableUnsupportedHttpMethods(Country.class, config, unsupportedActions);
        disableUnsupportedHttpMethods(State.class, config, unsupportedActions);
        disableUnsupportedHttpMethods(Order.class, config, unsupportedActions);
        // call an internal helper method
        exposeIds(config);
    }

    private static void disableUnsupportedHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }


    private void exposeIds(RepositoryRestConfiguration config) {
        Class[] domainTypes = getEntityClasses().toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

    private List<Class> getEntityClasses() {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        return entities.stream().map(EntityType::getJavaType).collect(Collectors.toList());
    }
}
