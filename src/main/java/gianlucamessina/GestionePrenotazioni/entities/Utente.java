package gianlucamessina.GestionePrenotazioni.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Utente {
    @Id
    @Column(name = "user_name")
    private String userName;
    private String nome;
    private String cogonome;

    @Override
    public String toString() {
        return "Utente{" +
                "userName='" + userName + '\'' +
                ", nome='" + nome + '\'' +
                ", cogonome='" + cogonome + '\'' +
                '}';
    }
}
