package gianlucamessina.GestionePrenotazioni.repositories;

import gianlucamessina.GestionePrenotazioni.entities.Edificio;
import gianlucamessina.GestionePrenotazioni.entities.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, UUID> {

        boolean existsByDescrizioneAndEdificio(String descrizione,Edificio edificio);
}
