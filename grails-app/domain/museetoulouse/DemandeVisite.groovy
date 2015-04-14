package museetoulouse

class DemandeVisite {
    int code
    Date dateDebutPeriode
    Date dateFinPeriode
    int nbPersonnes
    String statut

    /*static hasMany = [
        listeMusee: Musee
    ]*/


    static constraints = {
        code nullable: false
        dateDebutPeriode nullable: false
        dateFinPeriode nullable: true
        nbPersonnes nullable: false
        statut nullable: false
    }
}
