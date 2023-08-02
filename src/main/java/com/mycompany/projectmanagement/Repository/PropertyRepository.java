package com.mycompany.projectmanagement.Repository;

import com.mycompany.projectmanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
}
