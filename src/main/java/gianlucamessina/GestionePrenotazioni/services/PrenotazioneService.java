package gianlucamessina.GestionePrenotazioni.services;

import gianlucamessina.GestionePrenotazioni.entities.Postazione;
import gianlucamessina.GestionePrenotazioni.entities.Prenotazione;
import gianlucamessina.GestionePrenotazioni.exceptions.ValidationException;
import gianlucamessina.GestionePrenotazioni.repositories.PostazioneRepository;
import gianlucamessina.GestionePrenotazioni.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    PostazioneRepository postazioneRepository;
    @Autowired
    UtenteService utenteService;


    public void savePrenotazione(Prenotazione prenotazione){
        //se si prova ad effettuare una prenotazione per una postazione che risulta "OCCUPATA" lancerà un errore
        if(Objects.equals(prenotazione.getPostazione().getStatoPostazione(), "OCCUPATA")) throw new ValidationException("La postazione che hai provato a prenotare risulta: "+prenotazione.getPostazione().getStatoPostazione());
        //mi creo il riferimento alla postazione che l'utente vuole prenotare
        Postazione postazione = prenotazione.getPostazione();
        //cambio lo stato della postazione
        postazione.setStatoPostazione("OCCUPATA");
        //salvo la prenotazione
        prenotazioneRepository.save(prenotazione);
        //aggiorno lo stato della postazione nel Database
        postazioneRepository.save(postazione);

        System.out.println("La prenotazione della postazione con ID: "+prenotazione.getId()+ " fatta dall'utente: "+prenotazione.getUtente().getUserName()+" , è stata salvata correttamente!");
    }

    public List<Prenotazione>getPrenotazioneListByUserId(String userId){
        //passo alla query findByUtente la query findById dell'UtenteService che mi restituisce l'utente, che passo come parametro a findByUtente
        return prenotazioneRepository.findByUtente(utenteService.findById(userId));
    }
}
