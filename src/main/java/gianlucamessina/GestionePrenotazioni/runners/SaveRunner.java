package gianlucamessina.GestionePrenotazioni.runners;

import com.github.javafaker.Faker;
import gianlucamessina.GestionePrenotazioni.GestionePrenotazioniApplication;
import gianlucamessina.GestionePrenotazioni.entities.Edificio;
import gianlucamessina.GestionePrenotazioni.entities.Postazione;
import gianlucamessina.GestionePrenotazioni.entities.Utente;
import gianlucamessina.GestionePrenotazioni.enums.TipoPostazione;
import gianlucamessina.GestionePrenotazioni.exceptions.NotFoundException;
import gianlucamessina.GestionePrenotazioni.exceptions.ValidationException;
import gianlucamessina.GestionePrenotazioni.services.EdificioService;
import gianlucamessina.GestionePrenotazioni.services.PostazioneService;
import gianlucamessina.GestionePrenotazioni.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;
import java.util.function.Supplier;

@Component
public class SaveRunner implements CommandLineRunner {
    @Autowired
    UtenteService utenteService;
    @Autowired
    EdificioService edificioService;
    @Autowired
    PostazioneService postazioneService;


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
        Edificio amazon=new Edificio("Amazon","Viale Monte Grappa","Milano");
        Edificio google=new Edificio("Googleplex","1600 Amphitheatre Parkway","Mountain View");
        Edificio unicredit=new Edificio("Unicredit","Piazza Gae Aulenti,3","Milano");

        try {
            edificioService.saveEdificio(amazon);
            edificioService.saveEdificio(google);
            edificioService.saveEdificio(unicredit);
        }catch (ValidationException e){
            System.out.println(e.getMessage());
        }
        //*********************************** SAVE DI UTENTI *************************************
        Utente utente1=new Utente("gianlumess","Gianluca","Messina",faker.internet().emailAddress());
        Utente utente2=new Utente(faker.name().username(),faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress());
        try {
        //utenteService.saveUtente(utente1);
        //utenteService.saveUtente(utente2);
        }catch (ValidationException e){
            System.out.println(e.getMessage());
        }
        //*********************************** SAVE DI POSTAZIONI *************************************
        try {
            Edificio unicreditFromDb=edificioService.findById(UUID.fromString("8501b713-b0b7-4e08-86f8-7d46e5c3bcc8"));
            Edificio amazonFromDb=edificioService.findById(UUID.fromString("f0b950a4-052a-48ea-955d-121765097bb6"));
            Edificio googleFromDb=edificioService.findById(UUID.fromString("1d9115d8-4553-4e60-bcc4-c736b91e5377"));

            Postazione unicreditPrivata= new Postazione("Sala privata per teamWork", TipoPostazione.PRIVATO,10,unicreditFromDb);
            Postazione unicreditOpenSpace= new Postazione("Sala Open-Space per lavorare in gruppo con vista eccellente",TipoPostazione.OPEN_SPACE,22,unicreditFromDb);
            Postazione amazonOpenSpace=new Postazione("Sala Open-Space per lavorare in gruppo con vista eccellente",TipoPostazione.OPEN_SPACE,12,amazonFromDb);
            Postazione googleRiunioni=new Postazione("Sala riunioni con tutti i comfort",TipoPostazione.SALA_RIUNIONI,20,googleFromDb);

            postazioneService.savePostazione(unicreditOpenSpace);
            postazioneService.savePostazione(unicreditPrivata);
            postazioneService.savePostazione(amazonOpenSpace);
            postazioneService.savePostazione(googleRiunioni);
        }catch (NotFoundException | ValidationException e){
            System.out.println(e.getMessage());
        }




    }
}
