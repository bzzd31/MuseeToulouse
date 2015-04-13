package museetoulouse

import grails.transaction.Transactional

@Transactional
class RemplirBaseDeDonneesService {

    Adresse adresse1
    Adresse adresse2
    Adresse adresse3
    Adresse adresse4
    Adresse adresse5
    Adresse adresse6
    Adresse adresse7
    Adresse adresse8
    Adresse adresse9
    Adresse adresse10
    Adresse adresse11
    Adresse adresse12

    Gestionnaire gestionnaire1
    Gestionnaire gestionnaire2
    Gestionnaire gestionnaire3
    Gestionnaire gestionnaire4

    Musee musee1
    Musee musee2
    Musee musee3
    Musee musee4
    Musee musee5
    Musee musee6
    Musee musee7
    Musee musee8
    Musee musee9
    Musee musee10
    Musee musee11
    Musee musee12

    MuseeService museeService


    def creationDesAdresses() {
        if(Adresse.count == 0) {
            adresse1 = new Adresse(numero: 2, rue: "RUE DES ARCHIVES", codePostal: 31500, ville: "Toulouse")
            adresse2 = new Adresse(numero: 5, rue: "RUE SAINT PANTALEON", codePostal: 31000, ville: "Toulouse")
            adresse3 = new Adresse(numero: 69, rue: "RUE PARGAMINIERES", codePostal: 31000, ville: "Toulouse")
            adresse4 = new Adresse(numero: 31, rue: "RUE DE LA FONDERIE", codePostal: 31000, ville: "Toulouse")
            adresse5 = new Adresse(numero: 1, rue: "RUE MONTMORENCY", codePostal: 31200, ville: "Toulouse")
            adresse6 = new Adresse(numero: 2, rue: "RUE VIGUERIE", codePostal: 31300, ville: "Toulouse")
            adresse7 = new Adresse(numero: 21, rue: "RUE DE METZ", codePostal: 31000, ville: "Toulouse")
            adresse8 = new Adresse(numero: 2, rue: "RUE TRIPIERE", codePostal: 31000, ville: "Toulouse")
            adresse9 = new Adresse(numero: 2, rue: "RUE VIGUERIE", codePostal: 31300, ville: "Toulouse")
            adresse10 = new Adresse(numero: 7, rue: "RUE DU MAY", codePostal: 31000, ville: "Toulouse")
            adresse11 = new Adresse(numero: 1, rue: "RUE DU JAPON", codePostal: 31400, ville: "Toulouse")
            adresse12 = new Adresse(numero: 13, rue: "RUE DE LA PLEAU", codePostal: 31000, ville: "Toulouse")
        }
    }

    def creationDesGestionnaires() {
        if (Gestionnaire.count() == 0) {
            gestionnaire1 = new Gestionnaire(nom: "Mairie de Toulouse - DGA Culture")
            gestionnaire2 = new Gestionnaire(nom: "Association")
            gestionnaire3 = new Gestionnaire(nom: "Autre institution publique")
            gestionnaire4 = new Gestionnaire(nom: "Structure commerciale")
        }
    }

    def creationDesMusees() {
        if (Musee.count() == 0) {
            musee1 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "ARCHIVES MUNICIPALES TOULOUSE",
                    "Ouvert du lundi au vendredi de 9h à 17h. Fermeture de 12h à 13h30 pendant toutes les vacances scolaires. Fermeture annuelle la dernière quinzaine de juillet.",
                    "05 61 61 63 33", "Roseraie (A)", "36, 38",true),
                    gestionnaire1, adresse1)

            musee2 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "CMAV - CENTRE MERIDIONAL DE L'ARCHITECTURE DE LA VILLE",
                    "Ouvert du mardi au samedi de 13h à 19h fermé les dimanches, jours fériés et du 1er au 15 août",
                    "05 61 23 30 49", "Capitole (A), Jean Jaurès (B)", "ncv, 2, 10, 12, 14, 38, 78 et 80",false),
                    gestionnaire2, adresse2)

            musee3 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "ENSEMBLE CONVENTUEL DES JACOBINS",
                    "Ouvert tous les jours de 9h à 19h.",
                    "05 61 22 21 92", "Esquirol, Capitole (A)", "NCV, 2, 10, 12, 14, 38, 78, 80",false),
                    gestionnaire1, adresse3)

            musee4 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "INSTITUT CATHOLIQUE DE TOULOUSE - ESPACE MUSEOGRAPHIQUE BACCRABERE - SALLE TOLOSA",
                    "Ouvert le premier vendredi de chaque mois de 18h à 20h.",
                    "05 61 36 81 12", "Carmes (B)", "2, 38",false),
                    gestionnaire3, adresse4)

            musee5 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "L'AEROTHEQUE",
                    "Ouvert le lundi et le mercredi de 14h à 17h et le mardi de 9h à 12h",
                    "05 61 93 93 57", "", "",false),
                    gestionnaire4, adresse5)

            musee6 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE",
                    "Ouvert tous les jeudi et vendredi de 11h à 17h, et le dimande de 10h à 18h.Visites guidées sur demande.",
                    "05 61 77 84 25", "Saint-Cyprien-République, Esquirol (A)", "2, 10, 12, 14, 78, 80",false),
                    gestionnaire2, adresse6)

            musee7 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "MUSEE DES AUGUSTINS - MUSEE DES BEAUX ARTS DE TOULOUSE",
                    "Tous les jours : 10h - 18h /  nocturne le mercredi jusqu'à 21h.",
                    "05 61 22 21 82", "Esquirol (A) ou Carmes (B)", "2, 10, 12, 14, 78, 80",false),
                    gestionnaire1, adresse7)

            musee8 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "MUSEE DES COMPAGNONS",
                    "Le Mercredi et le 1er dimanche de chaque mois de 14h à 17h",
                    "05 62 47 41 77", "Esquirol, Capitole (A)", "2, 10, 12, 14, 38, 78, 80",false),
                    gestionnaire2, adresse8)

            musee9 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "MUSEE DES INSTRUMENTS DE MEDECINE DES HOPITAUX DE TOULOUSE",
                    "Ouvert tous les jeudi et vendredi de 13h à 17h. Ouvert le premier dimanche du mois.Visites guidées sur demande.",
                    "05 61 77 82 72", "Saint-Cyprien-République, Esquirol (A)", "2, 10, 12, 14, 78, 80",false),
                    gestionnaire4, adresse9)

            musee10 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "MUSEE DU VIEUX TOULOUSE",
                    "Ouvert tous les jours du 2 mai au 31 octobre de 14h00 à 18h00.Fermé le dimanche et jours fériés.",
                    "05 62 27 11 50", "Esquirol, Capitole (A)", "2, 10, 12, 14, 38, 78, 80",false),
                    gestionnaire2, adresse10)

            musee11 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "MUSEE GEORGES-LABIT",
                    "Musée ouvert de 10h à 17h en hiver et de 10h à 18h en été (du 1er juin au 30 septembre). Fermeture hebdomadaire le mardi.Bibliothèque ouverte le lundi de 14h à 17h, le mardi et le mercredi : 9h-12h et 14h-17h.",
                    "05 61 14 65 50", "François Verdier (B)", "10, 2, 27",false),
                    gestionnaire1, adresse11)

            musee12 = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(new Musee(
                    "MUSEE PAUL-DUPUY - ARTS GRAPHIQUES ET ARTS DECORATIFS",
                    "Ouvert de 10h - 17h en hiver et de 10h à 18h en été (du 1er juin au 30 septembre). Fermé le mardi et jours fériés.",
                    "05 61 14 65 50", "Esquirol (A) Carmes (B)", "ncv, 2, 12, 52",false),
                    gestionnaire1, adresse12)
        }
    }
}
