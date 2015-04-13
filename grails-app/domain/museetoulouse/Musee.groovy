package museetoulouse

class Musee {

    String nom
    String horairesOuverture
    String telephone
    String accesMetro
    String accesBus

    boolean favoris;

    Musee(String nom, String horairesOuverture, String telephone, String accesMetro, String accesBus,boolean favoris) {
        this.nom = nom
        this.telephone = telephone
        this.horairesOuverture = horairesOuverture
        this.accesMetro = accesMetro
        this.accesBus = accesBus
        this.favoris = favoris;
    }

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
        favoris blank:false
    }


    @Override
    public String toString() {
        return nom;
    }
}
