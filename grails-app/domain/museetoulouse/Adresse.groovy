package museetoulouse

class Adresse {

    int numero
    String rue
    int codePostal
    String ville

    static constraints = {
        numero nullable: false
        rue blank: false
        codePostal nullable: false
        ville blank: false
    }
}
