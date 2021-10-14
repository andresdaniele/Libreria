package libreria.persistence;

import java.util.List;
import libreria.entities.Autor;

public final class AutorDAO extends DAO {    

    public void crear(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(autor);
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

    public void modificar(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar una persona");
        }
    }

    public void eliminar(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar una persona");
        }
    }

    public Autor buscarPorId(Integer id) throws Exception {
        try {
            return em.find(Autor.class, id);
        } catch (Exception e) {
            throw e;
        }

    }

    public Autor buscarPorNombre(String nombre) throws Exception {
        try {
            return (Autor) em.createQuery("SELECT a FROM Autor a WHERE "
                    + "a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
        } catch (Exception e) {
            throw e;
        }

    }

    public List<Autor> listar() throws Exception {

        try {
            List<Autor> autores = (List<Autor>) em.createQuery("SELECT a FROM Autor a").getResultList();
            return autores;
        } catch (Exception e) {
            throw e;
        }

    }
}
