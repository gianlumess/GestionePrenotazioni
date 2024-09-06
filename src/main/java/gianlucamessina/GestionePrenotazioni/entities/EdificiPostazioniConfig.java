package gianlucamessina.GestionePrenotazioni.entities;

import gianlucamessina.GestionePrenotazioni.enums.TipoPostazione;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EdificiPostazioniConfig {

    @Bean
    public Edificio google(){
        return new Edificio("Googleplex","1600 Amphitheatre Parkway","Mountain View");
    }

    @Bean
    public Edificio amazon(){
        return new Edificio("Amazon","Viale Monte Grappa","Milano");
    }

    @Bean
    public Edificio unicredit(){
        return  new Edificio("Unicredit","Piazza Gae Aulenti,3","Milano");
    }

    @Bean
    public Postazione postazioneUnicreditPrivata(){
        return new Postazione("Sala privata per teamWork", TipoPostazione.PRIVATO,10,unicredit());
    }

    @Bean
    public Postazione postazioneGoogleRiunioni(){
        return new Postazione("Sala riunioni con tutti i comfort",TipoPostazione.SALA_RIUNIONI,20,google());
    }

    @Bean
    public Postazione postazioneAmazonoOpenSpace(){
        return  new Postazione("Sala Open-Space per lavorare in gruppo con vista eccellente",TipoPostazione.OPEN_SPACE,12,amazon());
    }

}
