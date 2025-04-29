package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "LOCATIONS")
public class Location {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer noLocation;
	@Basic(optional = false)
	@NonNull private LocalDateTime dateDebut;
	@Basic(optional = true)
	private LocalDateTime dateRetour;
	@Basic(optional = false)
	private float tarifJour;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="no_client")
	@NonNull private Client client;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="no_exemplaire")
	@NonNull private Exemplaire exemplaire;
	

}
