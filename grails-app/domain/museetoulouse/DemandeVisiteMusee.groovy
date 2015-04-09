package museetoulouse

class DemandeVisiteMusee {

    Date dateDemande;

    static belongsTo = [
            musee : Musee,
            demandeVisite : DemandeVisite
    ]

    static constraints = {
        musee nullable: false
        demandeVisite nullable: false
        dateDemande nullable: false
    }
}
