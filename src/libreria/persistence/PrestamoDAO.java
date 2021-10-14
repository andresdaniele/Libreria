package libreria.persistence;

import java.util.List;
import libreria.entities.Cliente;
import libreria.entities.Prestamo;

public final class PrestamoDAO extends DAO {

    public void crearPrestamo(Prestamo prestamo) throws Exception {

        try {
            em.getTransaction().begin();
            em.persist(prestamo);
            em.getTransaction().commit();

        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error haciendo un rollback");
            }

            throw new Exception("Error al persistir un prestamo");
        }
    }

    public void modificar(Prestamo prestamo) throws Exception {

        try {
            em.getTransaction().begin();
            em.merge(prestamo);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar un prestamo");
        }
    }

    public void eliminar(Prestamo prestamo) throws Exception {

        try {
            em.getTransaction().begin();
            em.remove(prestamo);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar un prestamo");
        }
    }
    
    public Prestamo buscarPrestamoPorId(Integer id) {
        try {
            return em.find(Prestamo.class, id);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Prestamo> listarPorCliente(Cliente cliente) {

        try {
            List<Prestamo> prestamos = (List<Prestamo>) em.createQuery("SELECT p"
                    + " FROM Prestamo p WHERE p.cliente = :cliente").setParameter("cliente", cliente).getResultList();
            return prestamos;
        } catch (Exception e) {
            throw e;
        }
    }
}
