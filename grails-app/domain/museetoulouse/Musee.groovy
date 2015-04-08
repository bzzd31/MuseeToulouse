package museetoulouse

class Musee {

    String nom
    String horairesOuverture
    String telephone
    String accesMetro
    String accesBus

    static belongsTo = [
        adresse : Adresse
    ]

    static constraints = {
        nom nullable: false
        horairesOuverture blank: true
        telephone blank: true
        accesMetro nullable: true
        accesBus nullable: true
    }
}
