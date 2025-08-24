package com.HaberesMonolitico.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class LiquidacionDAO {

	@Autowired
    private EntityManager entityManager;

    public void ejecutarSPGenerarTabulados(Long idLiquidacion) {
        entityManager
            .createNativeQuery("EXEC PRC_GEN_TABULADOS :idLiquidacion")
            .setParameter("idLiquidacion", idLiquidacion.intValue())
            .executeUpdate();
    }
    
    public void ejecutarSPGenerarBasico(Long idLiquidacion) {
        entityManager
            .createNativeQuery("EXEC PRC_GEN_BASICO :idLiquidacion")
            .setParameter("idLiquidacion", idLiquidacion.intValue())
            .executeUpdate();
    }
    
    public void ejecutarSPGenerarTitulo(Long idLiquidacion) {
        entityManager
            .createNativeQuery("EXEC PRC_GEN_TITULO :idLiquidacion")
            .setParameter("idLiquidacion", idLiquidacion.intValue())
            .executeUpdate();
    }
}
