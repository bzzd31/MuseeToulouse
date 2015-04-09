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

    static hasOne = [
        gestionnaire : Gestionnaire
    ]

    /*static hasMany = [
        listeDemandesVisite : DemandeVisite
    ]*/

    static constraints = {
        nom blank: false
        horairesOuverture blank: false
        telephone nullable: true
        accesMetro nullable: true
        accesBus nullable: true
        gestionnaire nullable: false
        adresse nullable: false
    }
}
