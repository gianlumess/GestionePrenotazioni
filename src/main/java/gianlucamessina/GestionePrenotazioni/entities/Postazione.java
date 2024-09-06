package gianlucamessina.GestionePrenotazioni.entities;

import gianlucamessina.GestionePrenotazioni.enums.TipoPostazione;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "postazioni")
@Getter
@Setter
@NoArgsConstructor
public class Postazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String descrizione;
    @Column(name = "tipo_postazione")
    @Enumerated(EnumType.STRING)
    private TipoPostazione tipoPostazione;
    @Column(name = "num_max_occupanti")
    private int numMaxOccupanti;
    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;


    public Postazione(String descrizione, TipoPostazione tipoPostazione, int numMaxOccupanti, Edificio edificio) {
        this.descrizione = descrizione;
        this.tipoPostazione = tipoPostazione;
        this.numMaxOccupanti = numMaxOccupanti;
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Postazione{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", tipoPostazione=" + tipoPostazione +
                ", numMaxOccupanti=" + numMaxOccupanti +
                ", edificio=" + edificio +
                '}';
    }
}
