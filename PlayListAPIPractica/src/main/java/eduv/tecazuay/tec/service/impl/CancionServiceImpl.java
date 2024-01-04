package eduv.tecazuay.tec.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eduv.tecazuay.tec.entity.Cancion;
import eduv.tecazuay.tec.repository.ICancionRepository;
import eduv.tecazuay.tec.service.GenericServiceImplements;

@Service
public class CancionServiceImpl extends GenericServiceImplements<Cancion>{

	@Autowired
	private ICancionRepository cancionRepository;
	
	public Optional<Cancion> findByTitle(String title){
		return cancionRepository.findByTitle(title);
	}
}
