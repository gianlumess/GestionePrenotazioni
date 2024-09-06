package gianlucamessina.GestionePrenotazioni.services;

import gianlucamessina.GestionePrenotazioni.entities.Edificio;
import gianlucamessina.GestionePrenotazioni.entities.Postazione;
import gianlucamessina.GestionePrenotazioni.entities.Prenotazione;
import gianlucamessina.GestionePrenotazioni.exceptions.ValidationException;
import gianlucamessina.GestionePrenotazioni.repositories.PostazioneRepository;
import gianlucamessina.GestionePrenotazioni.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    PostazioneRepository postazioneRepository;

    public void savePrenotazione(Prenotazione prenotazione){
        if(Objects.equals(prenotazione.getPostazione().getStatoPostazione(), "OCCUPATA")) throw new ValidationException("La postazione che hai provato a prenotare risulta: "+prenotazione.getPostazione().getStatoPostazione());
        Postazione postazione = prenotazione.getPostazione();
        postazione.setStatoPostazione("OCCUPATA");
        prenotazioneRepository.save(prenotazione);
        postazioneRepository.save(postazione);

        System.out.println("La prenotazione della postazione con ID: "+prenotazione.getId()+ " fatta dall'utente: "+prenotazione.getUtente().getUserName()+" , è stata salvata correttamente!");
    }
}
