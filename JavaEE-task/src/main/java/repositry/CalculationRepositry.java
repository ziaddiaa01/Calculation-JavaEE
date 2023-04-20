package repositry;

import java.util.List;
import model.CalculationEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
public class CalculationRepositry {
    @PersistenceContext
    private EntityManager entityManager;

    public void insert(CalculationEntity calc) {
        entityManager.persist(calc);
    }

    public List<CalculationEntity> selectAllCalculations() {
        return entityManager.createQuery("select c from Calculation c", CalculationEntity.class).getResultList(); // select all columns 
    }
}
