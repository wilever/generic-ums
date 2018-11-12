package com.uproject.generic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.uproject.generic.domain.repository.entity.AnyEntityDetail;
import com.uproject.generic.domain.repository.entity.AnyEntityDetailPK;

/**
 * Repository to perform CRUD operations over {@link AnyEntity}.
 *
 * @author Wilever Gomez [wilevergomez@gmail.com]
 *
 */
@Repository
public interface AnyDetailRepository 
extends JpaRepository<AnyEntityDetail, AnyEntityDetailPK>, 
		JpaSpecificationExecutor<AnyEntityDetail>{
}
