package museetoulouse


import spock.lang.*

/**
 *
 */
class DemandeVisiteMuseeServiceIntegrationTestSpec extends Specification {

    DemandeVisiteMuseeService demandeVisiteMuseeService
    MuseeService museeService
    void "test insertion ou mise a  jour d'une demandeDeVisiteMusee avec un musee et une demande"() {

        given:"une DemandeDeVisiteMusee"
        DemandeVisiteMusee uneDemandeMusee = new DemandeVisiteMusee(dateDemande: new Date())

        and: "un Musee existant en base et une DemandeVisite"
        Musee unMusee = new Musee(nom:"toto",horairesOuverture:"17h",telephone:"00000000",accesBus:"B",accesMetro:"V",favoris:false)
        Adresse uneAdresse = new Adresse(numero: 39,rue: "rue de truc",codePostal: 31000,ville: "Toulouse")
        Gestionnaire unGestionnaire = new Gestionnaire(nom: "Dupont")
        museeService.insertOrUpdateMuseeForGestionnaireAndAdress(unMusee,unGestionnaire,uneAdresse)



        DemandeVisite uneDemandeVisite = new DemandeVisite(code:1,dateDebutPeriode:new Date(),dateFinPeriode:new Date(),nbPersonnes:1,statut:"Valide");

        when: "on tente de répercuter en base la création ou la modification de DemandeVisiteMusee"
        DemandeVisiteMusee resultDemandeMusee = demandeVisiteMuseeService.insertOrUpdateDemandeVisiteMuseeForMuseeAndDemandeVisite(uneDemandeMusee,unMusee,uneDemandeVisite)

        then: "le musee resultant pointe sur le musee initale"
        resultDemandeMusee == uneDemandeMusee

        and:"le musee résultant n'a pas d'erreur"
        !resultDemandeMusee.hasErrors()

        and:"le musee résultant a un id"
        resultDemandeMusee.id

        and:"le musee est bien present en base"
        Musee.findById(resultDemandeMusee.id) != null

        and: "le musee a pour gestionnaire le gestionnaire passé en paramètre"
        resultDemandeMusee.musee == unMusee

        and: "le musee a pour adresse l'adresse passé en paramètre"
        resultDemandeMusee.demandeVisite == uneDemandeVisite

    }

    void "test suppression d'un musee"() {

        given: "une DemandeVisiteMusee existante en base"
        DemandeVisiteMusee uneDemandeMusee = new DemandeVisiteMusee(dateDemande: new Date())
        Musee unMusee = new Musee(nom:"toto",horairesOuverture:"17h",telephone:"00000000",accesBus:"B",accesMetro:"V",favoris:false)
        Adresse uneAdresse = new Adresse(numero: 39,rue: "rue de truc",codePostal: 31000,ville: "Toulouse")
        Gestionnaire unGestionnaire = new Gestionnaire(nom: "Dupont")
        museeService.insertOrUpdateMuseeForGestionnaireAndAdress(unMusee,unGestionnaire,uneAdresse)

        DemandeVisite uneDemandeVisite = new DemandeVisite(code:1,dateDebutPeriode:new Date(),dateFinPeriode:new Date(),nbPersonnes:1,statut:"Valide");
        uneDemandeMusee = demandeVisiteMuseeService.insertOrUpdateDemandeVisiteMuseeForMuseeAndDemandeVisite(uneDemandeMusee,unMusee,uneDemandeVisite)


        when:"on tente de supprimer le musee"
        uneDemandeMusee = demandeVisiteMuseeService.deleteDemandeVisiteMusee(uneDemandeMusee)

        then:"la demandeVisiteMusee n'existe plus en base"
        DemandeVisiteMusee.findById(uneDemandeMusee.id) == null

    }
}
