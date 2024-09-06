package gianlucamessina.GestionePrenotazioni.repositories;

import gianlucamessina.GestionePrenotazioni.entities.Edificio;
import gianlucamessina.GestionePrenotazioni.entities.Postazione;
import gianlucamessina.GestionePrenotazioni.enums.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, UUID> {

        boolean existsByDescrizioneAndEdificio(String descrizione,Edificio edificio);

        List<Postazione> findByTipoPostazioneAndEdificio_Citta(TipoPostazione tipoPostazione,String citta);
}
