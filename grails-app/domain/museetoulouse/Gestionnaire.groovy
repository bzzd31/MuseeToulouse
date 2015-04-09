package museetoulouse

class Gestionnaire {

    String nom

    static hasMany = [
        listeMusees: Musee
    ]

    static constraints = {
        nom nullable: false
    }


    @Override
    public String toString() {
        return nom;
    }
}
