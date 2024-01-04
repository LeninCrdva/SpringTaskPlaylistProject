package eduv.tecazuay.tec.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eduv.tecazuay.tec.entity.Lista;
import eduv.tecazuay.tec.repository.IListRepository;
import eduv.tecazuay.tec.service.GenericServiceImplements;

@Service
public class ListaServiceImpl extends GenericServiceImplements<Lista>{
	
	@Autowired
	private IListRepository listRepository;
	
	public Optional<Lista> findByName(String name){
		return listRepository.findByName(name);
	}
}
