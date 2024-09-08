package gianlucamessina.GestionePrenotazioni.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "data_prenotazione")
    private LocalDate dataPrenotazione;
    @ManyToOne
    @JoinColumn(name = "utente_username")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "postazione_id")
    private Postazione postazione;

    public Prenotazione(LocalDate dataPrenotazione, Utente utente, Postazione postazione) {
        this.dataPrenotazione = dataPrenotazione;
        this.utente = utente;
        this.postazione = postazione;
    }

    public void printPrenotazione(Prenotazione prenotazione){
        System.out.println("PRENOTAZIONE : "+prenotazione.getId());
        System.out.println("--UTENTE: "+prenotazione.getUtente().getUserName());
        System.out.println("--POSTAZIONE :"+prenotazione.getPostazione().getId()+", TIPO: "+postazione.getTipoPostazione());
        System.out.println("--EDIFICIO: "+ postazione.getEdificio().getNome());
        System.out.println("--CITTA': "+postazione.getEdificio().getCitta());
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", dataPrenotazione=" + dataPrenotazione +

                ", postazione=" + postazione +
                '}';
    }
}
