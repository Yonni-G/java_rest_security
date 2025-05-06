package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="UTILISATEURS")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer noUtilisateur;

    @NonNull
    @Column(unique=true)
    private String username;

    @NonNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UTILISATEURS_ROLES",
            joinColumns = @JoinColumn(name="no_utilisateur"),
            inverseJoinColumns = @JoinColumn(name="no_role"))
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role r) {
        roles.add(r);
    }

}
