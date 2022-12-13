package FFSSM;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FFSSMJUnitTest {

    Moniteur moniteur;
    Club club;
    Plongee plongee;
    Licence licence;
    Plongeur plongeur;
    Embauche embauche;

    @BeforeEach
    public void setUp (){
        licence=new Licence(moniteur,"1",LocalDate.of(2022,7,25),club);
        moniteur=new Moniteur("1","Le Blanc","Juste","3 rue de la rue","0011223344", LocalDate.of(2002,05,29),5,1,licence);
        club=new Club(moniteur,"Les petit dauphin","0123456789");
        plongee=new Plongee(new Site("Le gouffre de feu","Temperature minimal : 68°C , Présence animal : Aucune , Présence végétal : Aucune"),moniteur,LocalDate.of(2022,7,26),3528,8);
        plongeur=new Plongeur("2","nom","prenom","adresse","telephone",LocalDate.of(1802,04,23),2,new Licence(null,"2",LocalDate.of(2021,7,25),club));
    }

    @Test
    public void testLicenceValid (){
        assertTrue(licence.estValide(LocalDate.of(2023,07,25)),"La licence devrait être valide au bout de 1 an exactement");

        assertFalse(licence.estValide(LocalDate.of(2023,07,26)),"La licence ne devrait pas être valide au bout de 1 an et 1 jour");
    }

    @Test
    public void testPlongeeConformeFalse (){
        assertTrue(plongee.estConforme(),"La plongee devrait être conforme");
        plongee.ajouteParticipant(plongeur);
        assertFalse(plongee.estConforme(),"La plongee ne devrait pas être conforme");
    }

    @Test
    public void testEmbaucheMoniteur (){
        moniteur.nouvelleEmbauche(club,LocalDate.of(2022,9,13));
        assertEquals(Optional.of(club),moniteur.employeurActuel(),"L'employeur actuel devrait être club");

        ArrayList<Embauche> embauches=moniteur.emplois();
        for (Embauche e : embauches){
            e.terminer(LocalDate.of(2022,10,24));
        }
        assertEquals(Optional.empty(),moniteur.employeurActuel(),"Le moniteur ne devrait pas être employé");
    }

    @Test
    public void testPlongeeConforme (){
        club.organisePlongee(plongee);
        assertEquals(new HashSet<Plongee>(),club.plongeesNonConformes(),"Le club devrait avoir aucune plongee non conforme");
    }

    @Test
    public void testPlongeeNonConforme (){
        plongee.ajouteParticipant(plongeur);
        club.organisePlongee(plongee);
        HashSet<Plongee> H=new HashSet<Plongee>();
        H.add(plongee);
        assertEquals(H,club.plongeesNonConformes(),"Le club devrait avoir plongee comme plongee non conforme");
    }
}
