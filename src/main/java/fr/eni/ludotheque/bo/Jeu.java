package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="JEUX")
public class Jeu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="no_jeu")
	private Integer noJeu;
	
	@Column( length=50, nullable=false)
	@NonNull
	private String titre;
	
	@Column(length=13, nullable=false, unique=true)
	@NonNull private String reference;
	
	@Column(nullable=true)
	private int ageMin;
	
	@Column( nullable=true)
	private String description;

	private int duree;
	
	@Column(nullable=false)
	@NonNull
	private Float tarifJour;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "JEUX_GENRES", 
		joinColumns = @JoinColumn(name="no_jeu"),
		inverseJoinColumns = @JoinColumn(name="no_genre"))
	private List<Genre> genres = new ArrayList<>();
	
	public void addGenre(Genre g) {
		genres.add(g);
	}
}
