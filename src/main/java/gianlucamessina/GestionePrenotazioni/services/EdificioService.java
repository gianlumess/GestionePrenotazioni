package gianlucamessina.GestionePrenotazioni.services;

import gianlucamessina.GestionePrenotazioni.entities.Edificio;
import gianlucamessina.GestionePrenotazioni.exceptions.NotFoundException;
import gianlucamessina.GestionePrenotazioni.exceptions.ValidationException;
import gianlucamessina.GestionePrenotazioni.repositories.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EdificioService{
    @Autowired
    EdificioRepository edificioRepository;

    public void saveEdificio(Edificio edificio){
        if(edificioRepository.existsByNome(edificio.getNome())) throw new ValidationException("Esiste già un edificio chiamato : "+edificio.getNome());

        edificioRepository.save(edificio);

        System.out.println("L'edificio  "+edificio.getNome()+" è stato salvato correttamente!");
    }

    public Edificio findById(UUID edificioId){
        return edificioRepository.findById(edificioId).orElseThrow(() -> new NotFoundException(edificioId));
    }
}
