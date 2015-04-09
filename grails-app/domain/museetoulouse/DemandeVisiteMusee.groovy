package museetoulouse

class DemandeVisiteMusee {

    static belongsTo = [
            musee : Musee,
            demandeVisite : DemandeVisite
    ]

    static constraints = {
        musee nullable: false
        demandeVisite nullable: false
    }
}
