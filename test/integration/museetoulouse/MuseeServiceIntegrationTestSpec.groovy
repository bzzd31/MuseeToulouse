package museetoulouse



import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationTestSpec extends Specification {

    MuseeService museeService

    void "test insertion ou mise Ã  jour d'un musee avec une adresse et un gestionnaire"() {

        given:"un Musee"
        Musee unMusee = new Musee(nom: "act1",horairesOuverture: "18h",telephone: "05054050",accesMetro: "ligne B",accesBus: "bus 34,36")
        Adresse uneAdresse = new Adresse(numero: 39,rue: "rue de truc",codePostal: 31000,ville: "Toulouse")

        and: "un gestionnaire"
        Gestionnaire unGestionnaire = new Gestionnaire(nom: "Dupont")

        when: "on tente de répercuter en base la création ou la modification de musee"
        Musee resultMusee = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(unMusee,uneAdresse,unGestionnaire)

        then: "le musee resultant pointe sur le musee initale"
        resultMusee == unMusee

        and:"le musee résultant n'a pas d'erreur"
        !resultMusee.hasErrors()

        and:"le musee résultant a un id"
        resultMusee.id

        and:"le musee est bien present en base"
        Musee.findById(resultMusee.id) != null

        and: "le musee a pour gestionnaire le gestionnaire passé en paramètre"
        resultMusee.gestionnaire == unGestionnaire

        and: "le musee a pour adresse l'adresse passé en paramètre"
        resultMusee.adresse == uneAdresse

        and:"le gestionnaire a dans sa liste de musee le musee passé en paramètre"
        unGestionnaire.listeMusees.contains(resultMusee)
    }

    void "test suppression d'un musee"() {

        given: "un musee existante en base"
        Musee unMusee = new Musee(nom: "act1",horairesOuverture: "18h",telephone: "05054050",accesMetro: "ligne B",accesBus: "bus 34,36")
        Adresse uneAdresse = new Adresse(numero: 39,rue: "rue de truc",codePostal: 31000,ville: "Toulouse")
        Gestionnaire unGestionnaire = new Gestionnaire(nom: "Dupont")
        unMusee = museeService.insertOrUpdateMuseeForGestionnaireAndAdress(unMusee,uneAdresse,unGestionnaire)


        when:"on tente de supprimer le musee"
        museeService.deleteMusee(unMusee)

        then:"le musee n'existe plus en base"
        Musee.findById(unMusee.id) == null

        and:"le gestionnaire n'a plus le musee dans sa liste de musee"
        !unGestionnaire.listeMusees.contains(unMusee)
    }
}
