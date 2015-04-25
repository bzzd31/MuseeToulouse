package museetoulouse

import grails.transaction.Transactional

@Transactional
class DemandeVisiteService {
    int getLastCode(){
        def latestConfig = DemandeVisite.listOrderById(max:1, order: "desc")[0]
        int code
        if(latestConfig != null){
            code = latestConfig.code
        } else {
            code = 0
        }
        code = code+1
        code
    }
    DemandeVisite getDemandeVisiteMusee(int codeI){
        List<DemandeVisite> de = DemandeVisite.findAll()
        def demandeVisite = DemandeVisite.find("from DemandeVisite as d where d.code=? ",[codeI])
        demandeVisite
    }
}
