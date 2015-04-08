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
    void "test la validite d'un musee valide"(String unNom, String unHorairesOuverture, String unTelephone, String unAccesMetro, String unAccesBus, Adresse uneAdresse, Gestionnaire unGestionnaire) {

        given: "un musee initialise avec nom, horaires d'ouverture, telephone (ou non), un acces métro (ou non), un acces bus (ou non)"
        Musee musee = new Musee(nom: unNom, horairesOuverture: unHorairesOuverture, telephone: unTelephone, accesMetro: unAccesMetro, accesBus: unAccesBus, adresse: uneAdresse, gestionnaire: unGestionnaire)

        expect: "adresse est valide"
        musee.validate() == true

        where:
        unNom       | unHorairesOuverture   | unTelephone       | unAccesMetro      | unAccesBus    | uneAdresse    | unGestionnaire
        "Augustins" | "14h-18h"             | "05 61 61 63 33"  | null              | null          | Mock(Adresse) | Mock(Gestionnaire)
        "Augustins" | "14h-18h"             | null              | "Roseraie (A)"    | "36, 38"      | Mock(Adresse) | Mock(Gestionnaire)
    }

    @Unroll
    void "test l'invalidite d'un musee non valide"(String unNom, String unHorairesOuverture, Adresse uneAdresse, Gestionnaire unGestionnaire) {

        given: "un musee initialise sans nom, ou avec rue vide, ou sans codePostal ou avec ville vide"
        Musee musee = new Musee(nom: unNom, horairesOuverture: unHorairesOuverture, telephone: null, accesMetro: null, accesBus: null, adresse: uneAdresse, gestionnaire: unGestionnaire)

        expect: "l'adresse est invalide"
        musee.validate() == false

        where:
        unNom       | unHorairesOuverture   | uneAdresse    | unGestionnaire
        null        | "14h-18h"             | Mock(Adresse) | Mock(Gestionnaire)
        "Augustins" | null                  | Mock(Adresse) | Mock(Gestionnaire)
        "Augustins" | null                  | null          | Mock(Gestionnaire)
        "Augustins" | "14h-18h"             | Mock(Adresse) | null
    }
}
