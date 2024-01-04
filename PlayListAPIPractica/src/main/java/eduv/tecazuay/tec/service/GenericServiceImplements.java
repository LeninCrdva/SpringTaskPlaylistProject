package eduv.tecazuay.tec.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import eduv.tecazuay.tec.exception.ResourceNotFoundException;
import eduv.tecazuay.tec.repository.IGenericRepository;

public class GenericServiceImplements<T> implements IGenericService{
	
	@Autowired
	private IGenericRepository<T> genericRepository;

	@Override
	public List findAll() {
		return (List) genericRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public T findById(String id) {
	    return genericRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("id", id));
	}


	@Override
	public Object save(Object entity) {
		return genericRepository.save((T)entity);
	}

	@Override
	public void delete(String id) {
		genericRepository.deleteById(id);
	}

}
