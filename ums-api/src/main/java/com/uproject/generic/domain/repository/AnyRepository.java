package com.uproject.generic.domain.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.uproject.generic.domain.repository.entity.AnyEntity;

/**
 * Repository to perform CRUD operations over {@link AnyEntity}.
 *
 * @author Wilever Gomez [wilevergomez@gmail.com]
 *
 */
@Repository
public interface AnyRepository 
extends JpaRepository<AnyEntity, Serializable>, 
		JpaSpecificationExecutor<AnyEntity>{
}
