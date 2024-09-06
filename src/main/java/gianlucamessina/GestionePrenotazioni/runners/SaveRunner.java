package gianlucamessina.GestionePrenotazioni.runners;

import com.github.javafaker.Faker;
import gianlucamessina.GestionePrenotazioni.GestionePrenotazioniApplication;
import gianlucamessina.GestionePrenotazioni.entities.Edificio;
import gianlucamessina.GestionePrenotazioni.entities.Postazione;
import gianlucamessina.GestionePrenotazioni.entities.Utente;
import gianlucamessina.GestionePrenotazioni.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SaveRunner implements CommandLineRunner {
    @Autowired
    UtenteService utenteService;


    @Override
    public void run(String... args) throws Exception {
        /*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GestionePrenotazioniApplication.class);*/

        Faker faker=new Faker(Locale.ITALY);

        /*Edificio google= context.getBean("google",Edificio.class);
        Edificio amazon= context.getBean("amazon",Edificio.class);
        Edificio unicredit= context.getBean("unicredit",Edificio.class);

        Postazione unicreditPrivata=context.getBean("postazioneUnicreditPrivata", Postazione.class);
        Postazione googleRiunioni=context.getBean("postazioneGoogleRiunioni", Postazione.class);
        Postazione amazonOpenSpace=context.getBean("postazioneAmazonoOpenSpace", Postazione.class);*/

        //*********************************** SAVE DI EDIFICI *************************************

        Utente utente1=new Utente("gianlumess","Gianluca","Messina",faker.internet().emailAddress());
        utenteService.saveUtente(utente1);

    }
}
