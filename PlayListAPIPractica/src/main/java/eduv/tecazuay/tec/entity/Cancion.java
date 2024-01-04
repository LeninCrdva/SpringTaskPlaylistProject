package eduv.tecazuay.tec.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cancion {
	
	@Id
	private String title;
	private String artist;
	private String album;
	private Short year;
}
