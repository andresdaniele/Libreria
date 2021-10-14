package libreria.persistence;

import libreria.entities.Cliente;

public final class ClienteDAO extends DAO {

    public void crearCliente(Cliente cliente) throws Exception {

        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error haciendo un rollback");
            }

            throw new Exception("Error al persistir un cliente");
        }
    }

    public void modificar(Cliente cliente) throws Exception {

        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar un cliente");
        }
    }

    public void eliminar(Cliente cliente) throws Exception {

        try {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar un cliente");
        }
    }

    public Cliente buscarClienteId(Integer id) throws Exception {
        try {
            return em.find(Cliente.class, id);
        } catch (Exception e) {
            throw e;
        }
    }

    public Cliente buscarClienteDni(Long dni) throws Exception {
        try {
            Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.documento = :dni").setParameter("dni", dni).getSingleResult();
            return cliente;
        } catch (Exception e) {
            throw e;
        }
    }

}
