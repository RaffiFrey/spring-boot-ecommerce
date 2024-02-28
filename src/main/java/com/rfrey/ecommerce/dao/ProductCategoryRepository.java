package com.rfrey.ecommerce.dao;

import com.rfrey.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("https://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "productCategory" /* name of JSON-Field */, path = "product-category" /* API path */)
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
