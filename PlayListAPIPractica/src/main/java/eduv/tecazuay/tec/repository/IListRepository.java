package eduv.tecazuay.tec.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import eduv.tecazuay.tec.entity.Lista;

@Repository
public interface IListRepository extends IGenericRepository<Lista>{
	Optional<Lista> findByName(String name);
}
