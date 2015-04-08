package museetoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Adresse)
class AdresseSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test la validite d'une adresse valide"(int unNumero, String uneRue, int unCodePostal, String uneVille) {

        given: "une adresse initialise avec numero, rue, code postal et ville "
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille)

        expect: "adresse est valide"
        adresse.validate() == true

        where:
        unNumero    | uneRue                | unCodePostal      | uneVille
        2           | "RUE DES ARCHIVES"    | 31500             | "Toulouse"
      }

    @Unroll
    void "test l'invalidite d'une adresse non valide"(Integer unNumero, String uneRue, Integer unCodePostal, String uneVille) {

        given: "une adresse initialise sans numero, ou avec rue vide, ou sans codePostal ou avec ville vide"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille)

        expect: "l'adresse est invalide"
        adresse.validate() == false

        where:
        unNumero    | uneRue                | unCodePostal      | uneVille
        null        | "RUE DES ARCHIVES"    | 31500             | "Toulouse"
        2           | ""                    | 31500             | "Toulouse"
        2           | "RUE DES ARCHIVES"    | null              | "Toulouse"
        2           | "RUE DES ARCHIVES"    | 31500             | ""
    }
}
