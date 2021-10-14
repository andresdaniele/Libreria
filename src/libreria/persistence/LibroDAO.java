package libreria.persistence;

import java.util.List;
import libreria.entities.Autor;
import libreria.entities.Editorial;
import libreria.entities.Libro;

public final class LibroDAO extends DAO {

    public void crear(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(libro);
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

    public void modificar(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar una persona");
        }
    }

    public void eliminar(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar un libro");
        }
    }

    public Libro buscarPorISNB(Long isnb) throws Exception {
        try {
            return em.find(Libro.class, isnb);
        } catch (Exception e) {
            throw e;
        }

    }

    public Libro buscarPorTitulo(String titulo) throws Exception {

        try {
            return (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo").setParameter("titulo", titulo).getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Libro> buscarPorAutor(Autor autor) throws Exception {

        try {
            List<Libro> libros = (List<Libro>) em.createQuery("SELECT l FROM Libro l WHERE l.autor = :autor").setParameter("autor", autor).getResultList();
            
            return libros;
            
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Libro> buscarPorEditorial(Editorial editorial) throws Exception {

        try {

            List<Libro> libros = (List<Libro>) em.createQuery("SELECT l FROM Libro l WHERE l.editorial = :editorial").setParameter("editorial", editorial).getResultList();

            return libros;
        } catch (Exception e) {
            throw e;
        }
    }

}
