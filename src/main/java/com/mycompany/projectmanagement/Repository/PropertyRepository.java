package com.mycompany.projectmanagement.Repository;


import com.mycompany.projectmanagement.entity.PropertyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

//    @Query("Select p from propertyEntity p where p.userEntity.id = :userId AND p.title =: title")
//    List<PropertyEntity> findAllByUserEntityId(@Param("userID") Long userId, @Param("title") Long title);
//
    List<PropertyEntity> findByUserEntityId(Long userId);
}
