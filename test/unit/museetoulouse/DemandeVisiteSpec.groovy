package museetoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisite)
class DemandeVisiteSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test la validite d'une DemandeVisite valide"(int unCode, Date uneDateDeDebut, Date uneDateDeFin, int unNbPersonnes, String unStatut) {

        given: "une DemandeVisite initialise avec un code, une date de début, une date de fin ou non, un nombre de personnes ou non, un statut "
        DemandeVisite demandeVisite = new DemandeVisite(code: unCode, dateDebutPeriode: uneDateDeDebut, dateFinPeriode: uneDateDeFin, nbPersonnes: unNbPersonnes, statut: unStatut)

        expect: "le gestionnaire est valide"
        demandeVisite.validate() == true

        where:
        unCode  | uneDateDeDebut    | uneDateDeFin  | unNbPersonnes | unStatut
        1       | new Date()        | new Date()    | 20            | "Valide"
        1       | new Date()        | null          | 20            | "En Attente"
    }

    @Unroll
    void "test l'invalidite d'une DemandeVisite non valide"(Integer unCode, Date uneDateDeDebut, Integer unNbPersonnes, String unStatut) {

        given: "une DemandeVisite initialise sans code ou date de début ou statut"
        DemandeVisite demandeVisite = new DemandeVisite(code: unCode, dateDebutPeriode: uneDateDeDebut, nbPersonnes: unNbPersonnes,statut: unStatut)

        expect: "la DemandeVisite est invalide"
        demandeVisite.validate() == false

        where:
        unCode  | uneDateDeDebut    | unNbPersonnes | unStatut
        null    | new Date()        | 20            | "Valide"
        1       | null              | 20            | "Valide"
        1       | new Date()        | null          | "Valide"
        1       | new Date()        | 20            | null
    }
}
