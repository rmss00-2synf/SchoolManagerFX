package com.ensat.schoolmanagerfx.entity.asset;

import java.util.List;
import java.util.Optional;

public abstract interface CRUD {
//	Optional<List<CRUD>> findBy[attributes]([attrbuteType] attribute);
	boolean save(CRUD crud);
	boolean update(CRUD crud);
	boolean delete(CRUD crud);
	List<CRUD> findAll();

}
