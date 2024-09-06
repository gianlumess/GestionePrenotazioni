package gianlucamessina.GestionePrenotazioni.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
	public NotFoundException(UUID id){
		super("Il record con id " + id + " non è stato trovato!");
	}
	public  NotFoundException(String userId){
		super("Il record con id " + userId + " non è stato trovato!");
	}
}


