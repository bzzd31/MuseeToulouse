package museetoulouse

import grails.transaction.Transactional

@Transactional
class DemandeVisiteService {
    int getLastedCode(DemandeVisite uneDemandeVisite){
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
}
