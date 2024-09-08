package gianlucamessina.GestionePrenotazioni.runners;

import com.github.javafaker.Faker;
import gianlucamessina.GestionePrenotazioni.GestionePrenotazioniApplication;
import gianlucamessina.GestionePrenotazioni.entities.Edificio;
import gianlucamessina.GestionePrenotazioni.entities.Postazione;
import gianlucamessina.GestionePrenotazioni.entities.Prenotazione;
import gianlucamessina.GestionePrenotazioni.entities.Utente;
import gianlucamessina.GestionePrenotazioni.enums.TipoPostazione;
import gianlucamessina.GestionePrenotazioni.exceptions.NotFoundException;
import gianlucamessina.GestionePrenotazioni.exceptions.ValidationException;
import gianlucamessina.GestionePrenotazioni.services.EdificioService;
import gianlucamessina.GestionePrenotazioni.services.PostazioneService;
import gianlucamessina.GestionePrenotazioni.services.PrenotazioneService;
import gianlucamessina.GestionePrenotazioni.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


@Component
public class SaveRunner implements CommandLineRunner {
    @Autowired
    UtenteService utenteService;
    @Autowired
    EdificioService edificioService;
    @Autowired
    PostazioneService postazioneService;
    @Autowired
    PrenotazioneService prenotazioneService;


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
        utenteService.saveUtente(utente2);
        }catch (ValidationException e){
            System.out.println(e.getMessage());
        }
        //*********************************** SAVE DI POSTAZIONI *************************************
        try {
            Edificio unicreditFromDb=edificioService.findById(UUID.fromString("afca3cb3-bbe9-4328-9a90-de80379b3d33"));
            Edificio amazonFromDb=edificioService.findById(UUID.fromString("4439fce6-619f-42c8-9591-b3ce9caccdea"));
            Edificio googleFromDb=edificioService.findById(UUID.fromString("612c2b60-57a6-47ea-ba6e-cc476bf82f44"));

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

        //*********************************** SAVE DI PRENOTAZIONI *************************************
        Utente gianluFromDb=utenteService.findById("gianlumess");
        Postazione unicreditPrivataFromDb=postazioneService.findById(UUID.fromString("aff592a2-8452-4286-afe7-b208f848c00e"));
        try {
        Prenotazione prenotazione1=new Prenotazione(LocalDate.now(),gianluFromDb,unicreditPrivataFromDb);
        prenotazioneService.savePrenotazione(prenotazione1);

        }catch (ValidationException e){
            System.out.println(e.getMessage());
        }
        System.out.println("");
        System.out.println("************************* RICERCA POSTAZIONI PER CITTA' : 'MILANO' E OPEN-SPACE *************************");
        System.out.println("");
        postazioneService.findByTipoAndCitta(TipoPostazione.OPEN_SPACE,"milano").forEach(System.out::println);
        System.out.println("");
        System.out.println("STAMPA PRENOTAZIONI DI GIANLUMESS");


        System.out.println("************************* RICERCA PRENOTAZIONI PER UTENTE : GIANLUMESS *************************");
        try {
        prenotazioneService.getPrenotazioneListByUserId("gianlumess").forEach(prenotazione -> prenotazione.printPrenotazione(prenotazione));
        }catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
