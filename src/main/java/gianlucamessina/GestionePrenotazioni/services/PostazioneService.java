package gianlucamessina.GestionePrenotazioni.services;

import gianlucamessina.GestionePrenotazioni.entities.Postazione;
import gianlucamessina.GestionePrenotazioni.enums.TipoPostazione;
import gianlucamessina.GestionePrenotazioni.exceptions.NotFoundException;
import gianlucamessina.GestionePrenotazioni.exceptions.ValidationException;
import gianlucamessina.GestionePrenotazioni.repositories.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostazioneService {

    @Autowired
    PostazioneRepository postazioneRepository;

    public void savePostazione(Postazione postazione){
        if(postazioneRepository.existsByDescrizioneAndEdificio(postazione.getDescrizione(),postazione.getEdificio())) throw new ValidationException("Esiste già una postazione : "+postazione.getDescrizione());
        postazioneRepository.save(postazione);

        System.out.println("La postazione nell'edificio: "+postazione.getEdificio().getNome()+" , di tipo: "+postazione.getTipoPostazione()+  " è stata salvata correttamente!");
    }

    public Postazione findById(UUID postazioneId){
        return postazioneRepository.findById(postazioneId).orElseThrow(() -> new NotFoundException(postazioneId));
    }

    public List<Postazione> findByTipoAndCitta(TipoPostazione tipoPostazione,String citta){
        return postazioneRepository.findByTipoPostazioneAndEdificio_Citta(tipoPostazione,citta);
    }
}
