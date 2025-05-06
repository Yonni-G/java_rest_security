package fr.eni.ludotheque.bo;

import fr.eni.ludotheque.enumeration.RoleName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer noRole;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique=true, columnDefinition = "varchar(50)")
    private RoleName roleName;

}
