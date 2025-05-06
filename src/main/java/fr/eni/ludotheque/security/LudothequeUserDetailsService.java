package fr.eni.ludotheque.security;

import fr.eni.ludotheque.bo.Role;
import fr.eni.ludotheque.bo.Utilisateur;
import fr.eni.ludotheque.dal.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LudothequeUserDetailsService implements UserDetailsService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur utilisateur = this.utilisateurRepository.findByUsername(username);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
        }

        String[] authorities = utilisateur.getRoles()
                .stream()
                .map(role -> role.getRoleName().name())  // Utilisation de .name() pour obtenir la chaîne de caractères
                .toArray(String[]::new);

        User.UserBuilder builder = User.withUsername(username)
                .password(utilisateur.getPassword())
                .authorities(authorities); 

        return builder.build();
    }
}
