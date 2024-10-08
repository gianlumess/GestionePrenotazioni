package gianlucamessina.GestionePrenotazioni.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "utenti")
@NoArgsConstructor
@Getter
@Setter
public class Utente {
    @Id
    @Column(name = "user_name")
    private String userName;
    private String nome;
    private String cogonome;
    private String email;
    @OneToMany(mappedBy = "utente",fetch = FetchType.EAGER) //utilizzato fetchType.EAGER per caricare la collezione associata immediatamente insieme all'entità padre.
    private List<Prenotazione> prenotazioni;                //implementato perchè non ricevevo i dati della lista (null)

    public Utente(String userName, String nome, String cogonome, String email) {
        this.userName = userName;
        this.nome = nome;
        this.cogonome = cogonome;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "userName='" + userName + '\'' +
                ", nome='" + nome + '\'' +
                ", cogonome='" + cogonome + '\'' +
                ", email='" + email + '\'' +
                ", prenotazioni=" + prenotazioni +
                '}';
    }
}
