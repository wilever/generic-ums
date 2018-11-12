package com.uproject.generic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.uproject.generic.domain.repository.entity.view.ViewAnyEntity;

/**
 * Repository to perform CRUD operations over {@link AnyEntity}.
 *
 * @author Wilever Gomez [wilevergomez@gmail.com]
 *
 */
@Repository
public interface ViewAnyRepository 
extends JpaRepository<ViewAnyEntity, Integer>, 
		JpaSpecificationExecutor<ViewAnyEntity>{
}
