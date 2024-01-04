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
import eduv.tecazuay.tec.service.impl.CancionServiceImpl;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api")
public class CancionController {

	@Autowired
	private CancionServiceImpl cancionImpl;
	
	@GetMapping("cancion/lists")
	public ResponseEntity<?> findSongs(){
		return ResponseEntity.ok(cancionImpl.findAll());
	}
	
	@GetMapping("/cancion/{name}")
	public ResponseEntity<?> findSong(@PathVariable("name") String name){
		return ResponseEntity.ok(cancionImpl.findById(name));
	}
	
	
	@PostMapping("/crear")
	public ResponseEntity<?> saveSong(@RequestBody Cancion cancion){
		System.out.print("Ad" + cancion.getTitle());
		if (cancionImpl.findByTitle(cancion.getTitle()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
		
		return ResponseEntity.status(HttpStatus.CREATED
				).body(cancionImpl.save(cancion));
	}
	
	@PutMapping("/update/{name}")
	public ResponseEntity<?> updateSong(@PathVariable("name") String name, @RequestBody Cancion cancion){
		Cancion cancionNew = (Cancion) cancionImpl.findById(name);
		
		if(cancionNew.getTitle().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		if(!cancionNew.getTitle().equals(cancion.getTitle())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		cancionNew.setAlbum(cancion.getAlbum());
		cancionNew.setArtist(cancion.getArtist());
		cancionNew.setYear(cancion.getYear());
		
		Cancion cancionActualizada = cancionNew;
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cancionImpl.save(cancionActualizada));
		
	}
	
	@DeleteMapping("/delete-song/{name}")
	public ResponseEntity<?> deleteSong(@PathVariable("name") String name){
		Cancion cancionNew = (Cancion) cancionImpl.findById(name);
		
		if(cancionNew == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		cancionImpl.delete(name);
		
		return ResponseEntity.noContent().build();
	}
}
