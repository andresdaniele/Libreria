package libreria.persistence;

import java.util.List;
import libreria.entities.Editorial;

public final class EditorialDAO extends DAO {

    public void crear(Editorial editorial) throws Exception {
        try {

            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();

        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error haciendo un rollback");
            }

            throw new Exception("Error al persistir una persona");
        }
    }

    public void modificar(Editorial editorial) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar una persona");
        }
    }

    public void eliminar(Editorial editorial) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar una persona");
        }
    }

    public Editorial buscarPorId(Integer id) {
        return em.find(Editorial.class, id);
    }

    public Editorial buscarPorNombre(String nombre) throws Exception {
        try {
            return (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE "
                    + "e.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Editorial> listar() throws Exception {
        
        try {
            List<Editorial> editoriales = (List<Editorial>) em.createQuery("SELECT e FROM Editorial e").getResultList();           
            return editoriales;
        } catch (Exception e) {
            throw e;
        }
    }
}
