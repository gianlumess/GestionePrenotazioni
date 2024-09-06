package gianlucamessina.GestionePrenotazioni.repositories;

import gianlucamessina.GestionePrenotazioni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,String> {

        boolean existsByUserName(String userName);
}
