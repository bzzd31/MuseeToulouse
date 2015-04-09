package museetoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisiteMusee)
class DemandeVisiteMuseeSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test la validite d'une DemandeVisiteMusee valide"() {

        given: "un Musée et une DemandeVisite"
        Musee unMusee = Mock(Musee)
        DemandeVisite uneDemandeVisite = Mock(DemandeVisite)

        when: "une demande de visite musée est créée"
        DemandeVisiteMusee demandeVisiteMusee = new DemandeVisiteMusee(musee: unMusee, demandeVisite: uneDemandeVisite)

        then: "le gestionnaire est valide"
        demandeVisiteMusee.validate() == true

        and:
        demandeVisiteMusee.demandeVisite == uneDemandeVisite
        demandeVisiteMusee.musee == unMusee

    }

    @Unroll
    void "test l'invalidite d'une DemandeVisiteMusee non valide"() {

        given: "un Musée null ou une DemandeVisite null"
        Musee unMusee = Mock(Musee)
        DemandeVisite uneDemandeVisite = Mock(DemandeVisite)

        when: "une demande de visite musée est créée"
        DemandeVisiteMusee demandeVisiteMusee = new DemandeVisiteMusee(musee: unMusee,demandeVisite:  null)

        then: "la DemandeVisite est invalide"
        demandeVisiteMusee.validate() == false

        when: "une demande de visite musée est créée"
        demandeVisiteMusee = new DemandeVisiteMusee(musee:  null, demandeVisite: uneDemandeVisite)

        then: "la DemandeVisite est invalide"
        demandeVisiteMusee.validate() == false
    }
}
