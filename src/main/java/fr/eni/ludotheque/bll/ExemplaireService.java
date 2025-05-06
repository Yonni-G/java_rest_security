package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplaire;

import java.util.Optional;

public interface ExemplaireService {
    Optional<Exemplaire> getExemplaireByCodeBarre(String codebarre);
}
