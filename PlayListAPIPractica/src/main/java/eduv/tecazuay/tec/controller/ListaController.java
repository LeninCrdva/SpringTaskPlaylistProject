package eduv.tecazuay.tec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eduv.tecazuay.tec.entity.Cancion;
import eduv.tecazuay.tec.entity.Lista;
import eduv.tecazuay.tec.service.impl.ListaServiceImpl;

@RestController
@RequestMapping("/list")
public class ListaController {
	
	@Autowired
	private ListaServiceImpl listaImpl;
	
	@GetMapping("/lists")
	public ResponseEntity<?> findLists(){
		return ResponseEntity.ok(listaImpl.findAll());
	}
	
	@GetMapping("/{listName}")
	public ResponseEntity<?> findList(@PathVariable("listName") String listName){
		Lista lista = (Lista) listaImpl.findById(listName);
		
		if(lista.getName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> saveSong(@RequestBody Lista lista){
		if(lista.getName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		if (listaImpl.findByName(lista.getName()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
		
		return ResponseEntity.status(HttpStatus.CREATED
				).body(listaImpl.save(lista));
	}
	
	@PutMapping("/update/{actualizar}")
	public ResponseEntity<?> updateList(@PathVariable("actualizar") String actualizar, @RequestBody Lista lista){
		Lista cancionNew = (Lista) listaImpl.findById(actualizar);
		
		if(cancionNew.getName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		if(!cancionNew.getName().equals(lista.getName())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		cancionNew.setDescription(lista.getDescription());
		cancionNew.setSongs(lista.getSongs());
		
		Lista cancionActualizada = cancionNew;
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(listaImpl.save(cancionActualizada));
		
	}
	
	@DeleteMapping("/delete-song/{name}")
	public ResponseEntity<?> deleteSong(@PathVariable("name") String name){
		Lista cancionNew = (Lista) listaImpl.findById(name);
		
		if(cancionNew == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		listaImpl.delete(name);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
