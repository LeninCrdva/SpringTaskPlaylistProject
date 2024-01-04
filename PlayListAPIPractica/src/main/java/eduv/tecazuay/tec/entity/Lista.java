package eduv.tecazuay.tec.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lista {
	
	@Id
	private String name;
	private String description;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "title", name="songs")
	private Cancion songs;
}
