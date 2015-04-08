package museetoulouse

class Gestionnaire {

    String nom

    static hasMany = [
        listeMusees: Musee
    ]

    static constraints = {
        nom nullable: false
    }
}
