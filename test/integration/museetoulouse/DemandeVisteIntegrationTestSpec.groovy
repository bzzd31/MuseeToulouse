package museetoulouse

import spock.lang.*

/**
 * Created by Lucas on 25/04/2015.
 */
class DemandeVisteIntegrationTestSpec extends Specification {


    DemandeVisiteService demandeVisiteService

    void "test recherche dernier code attribué"() {

        given:"une demande visite"
        DemandeVisite uneDemandeVisite = new DemandeVisite(code: 1, dateDebutPeriode: new Date(), dateFinPeriode: new Date(), nbPersonnes: 2, statut: "En cours")

        when: "on demande le dernier code"
        int code = demandeVisiteService.getLastCode()

        then:"le code est à 1"
        code == 1

        when:"on demande le dernier code après le save"
        uneDemandeVisite.save()
        code = demandeVisiteService.getLastCode()

        then:"le code est à 2"
        code == 2

        when:"on supprime le musee ajouté"
        uneDemandeVisite.delete()
        code = demandeVisiteService.getLastCode()

        then:"le code est à 1"
        code == 1
    }

    void "test recherche visiteMusee grâce au code"() {

        given:"une demande visite"
        DemandeVisite uneDemandeVisite = new DemandeVisite(code: 1, dateDebutPeriode: new Date(), dateFinPeriode: new Date(), nbPersonnes: 2, statut: "En cours")

        when:"on recherche une demandeVisite"
        uneDemandeVisite.save()
        DemandeVisite uneAutreDemandeVisite = demandeVisiteService.getDemandeVisiteMusee(1)

        then:"la demandeVisite récupérée est la même"
        uneDemandeVisite == uneAutreDemandeVisite

        when:"on recherche avec un code inexistant"
        uneAutreDemandeVisite = demandeVisiteService.getDemandeVisiteMusee(2)

        then:"cela retourne null"
        uneAutreDemandeVisite == null
    }
}
