package gianlucamessina.GestionePrenotazioni.services;

import gianlucamessina.GestionePrenotazioni.entities.Utente;
import gianlucamessina.GestionePrenotazioni.exceptions.ValidationException;
import gianlucamessina.GestionePrenotazioni.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;

    public void saveUtente(Utente utente){
        if(utenteRepository.existsByUserName(utente.getUserName())) throw new ValidationException("Esiste già un utente con l'username da te selezionato : "+utente.getUserName());

        utenteRepository.save(utente);

        System.out.println("L'utente "+utente.getUserName()+" è stato salvato correttamente!");
    }
}
