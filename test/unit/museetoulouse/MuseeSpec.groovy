package museetoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Musee)
class MuseeSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test la validite d'un musee valide"(String unNom, String unHorairesOuverture, String unTelephone, String unAccesMetro, String unAccesBus, Adresse uneAdresse, Gestionnaire unGestionnaire,Boolean unFavoris) {

        given: "un musee initialise avec nom, horaires d'ouverture, telephone (ou non), un acces métro (ou non), un acces bus (ou non)"
        Musee musee = new Musee(nom: unNom, horairesOuverture: unHorairesOuverture, telephone: unTelephone, accesMetro: unAccesMetro, accesBus: unAccesBus, adresse: uneAdresse, gestionnaire: unGestionnaire,favoris:unFavoris)

        expect: "adresse est valide"
        musee.validate() == true

        where:
        unNom       | unHorairesOuverture   | unTelephone       | unAccesMetro      | unAccesBus    | uneAdresse    | unGestionnaire     | unFavoris
        "Augustins" | "14h-18h"             | "05 61 61 63 33"  | null              | null          | Mock(Adresse) | Mock(Gestionnaire) | true
        "Augustins" | "14h-18h"             | null              | "Roseraie (A)"    | "36, 38"      | Mock(Adresse) | Mock(Gestionnaire) | false
    }

    @Unroll
    void "test l'invalidite d'un musee non valide"(String unNom, String unHorairesOuverture, Adresse uneAdresse, Gestionnaire unGestionnaire,Boolean unFavoris) {

        given: "un musee initialise sans nom, ou avec rue vide, ou sans codePostal ou avec ville vide"
        Musee musee = new Musee(nom: unNom, horairesOuverture: unHorairesOuverture, telephone: null, accesMetro: null, accesBus: null, adresse: uneAdresse, gestionnaire: unGestionnaire,favoris: unFavoris)

        expect: "l'adresse est invalide"
        musee.validate() == false

        where:
        unNom       | unHorairesOuverture   | uneAdresse    | unGestionnaire     | unFavoris
        null        | "14h-18h"             | Mock(Adresse) | Mock(Gestionnaire) | true
        "Augustins" | null                  | Mock(Adresse) | Mock(Gestionnaire) | true
        "Augustins" | null                  | null          | Mock(Gestionnaire) | true
        "Augustins" | "14h-18h"             | Mock(Adresse) | null               | true
        "Augustins" | "14h-18h"             | Mock(Adresse) | Mock(Gestionnaire) | null
    }

    void "test toString"(String unNom, def _) {
        given: "un musee initialise avec nom"
        Musee musee = new Musee(nom: unNom)

        musee.toString() == unNom

        where:
        unNom           | _
        "Augustins"     | _
    }

    void "test Constructeur"(String unNom, String unHorairesOuverture, String unTelephone, String unAccesMetro, String unAccesBus, Boolean unFavoris) {
        given: "un gestionnaire initialise avec nom"
        Musee musee = new Musee(unNom, unHorairesOuverture, unTelephone, unAccesMetro, unAccesBus, unFavoris)

        musee.nom == unNom
        musee.horairesOuverture == unHorairesOuverture
        musee.telephone == unTelephone
        musee.accesMetro == unAccesMetro
        musee.accesBus == unAccesBus
        musee.favoris == unFavoris

        where:
        unNom       | unHorairesOuverture   | unTelephone       | unAccesMetro      | unAccesBus    | unFavoris
        "Augustins" | "14h-18h"             | "05 61 61 63 33"  | "Roseraie (A)"    | "36, 38"      | true
    }
}
