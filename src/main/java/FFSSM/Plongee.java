/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Plongee {

	public Site lieu;

	public Moniteur chefDePalanquee;

	public LocalDate date;

	public int profondeur;

	public int duree;

	public ArrayList<Plongeur> participants = new ArrayList<Plongeur>();

	public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
		participants.add(chefDePalanquee);
	}

	public void ajouteParticipant(Plongeur participant) {
		participants.add(participant);
	}

	/**
	 * Détermine si la plongée est conforme. 
	 * Une plongée est conforme si tous les plongeurs de la palanquée ont une
	 * licence valide à la date de la plongée
	 * @return vrai si la plongée est conforme
	 */
	public boolean estConforme() {
		for (Plongeur p : participants){
			if (!p.getLicence().estValide(date)){
				return false;
			}
		}
		return true;
	}

	/*

	public Personne getPossesseur() {
		return possesseur;
	}

	public String getNumero() {
		return numero;
	}

	public LocalDate getDelivrance() {
		return delivrance;
	}

	public Club getClub() {
		return club;
	}

	J'ai mis ça en commentaire pour avoir une couverture de 100% car ces fonctions ne me servait pas
	*/
}
