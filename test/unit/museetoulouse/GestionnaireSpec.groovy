package museetoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Gestionnaire)
class GestionnaireSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test la validite d'un gestionnaire valide"(String unNom, Musee unMusee) {

        given: "un gestionnaire initialise avec nom, et un musee ou non "
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom, listeMusees: unMusee)

        expect: "le gestionnaire est valide"
        gestionnaire.validate() == true

        where:
        unNom           | unMusee
        "Association"   | Mock(Musee)
        "Association"   | null
    }

    @Unroll
    void "test l'invalidite d'un gestionnaire non valide"(String unNom, def _) {

        given: "un gestionnaire initialise sans nom"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire est invalide"
        gestionnaire.validate() == false

        where:
        unNom   | _
        null    | _
    }
}
