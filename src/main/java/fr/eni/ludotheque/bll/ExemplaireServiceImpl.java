package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExemplaireServiceImpl implements ExemplaireService{

    private final ExemplaireRepository exemplaireRepository;
    ExemplaireServiceImpl(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }

    @Override
    public Optional<Exemplaire> getExemplaireByCodeBarre(String codebarre) {
        try {
            Exemplaire exemplaire = exemplaireRepository.findByCodebarre(codebarre);
            return Optional.of(exemplaire);
        } catch(Exception e) {
            return Optional.empty();
        }
    }
}
