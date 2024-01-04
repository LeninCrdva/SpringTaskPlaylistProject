package eduv.tecazuay.tec.service;

import java.util.List;


public interface IGenericService<T>{
	List<T> findAll();
    T findById(String id);
    T save(T entity);
    void delete(String id);
}
