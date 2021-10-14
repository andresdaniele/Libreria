package libreria.services;


import java.util.Calendar;
import java.util.List;
import libreria.entities.Cliente;
import libreria.entities.Libro;
import libreria.entities.Prestamo;
import libreria.persistence.PrestamoDAO;

public class PrestamoService {

    PrestamoDAO pDao = new PrestamoDAO();

    public void crearPrestamo(Prestamo prestamo) throws Exception {
        
        if (prestamo.getFechaPrestamo() == null || prestamo.getFechaPrestamo().before(Calendar.getInstance())) {
            throw new Exception("La fecha ingresada es nula o antecede la fecha actual");
        }

        if (prestamo.getFechaDevolucion() == null || prestamo.getFechaDevolucion().before(prestamo.getFechaPrestamo())) {
            throw new Exception("La fecha ingresada es nula o antecede la fecha de prestamo");
        }

        if (prestamo.getLibro() == null || prestamo.getLibro().getEjemplaresRestantes() < 1) {
            throw new Exception("El libro es nulo o no hay mas ejemplares disponibles");
        }

        if (prestamo.getCliente() == null) {
            throw new Exception("El cliente ingresado es nulo");
        }

        Libro libro = prestamo.getLibro();
        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
        prestamo.setLibro(libro);
        LibroService ls = new LibroService();
        ls.modificarLibro(libro);

        pDao.crearPrestamo(prestamo);
        
    }
    
    public Prestamo buscarPrestamoId(Integer id) throws Exception {
        try {
            if(id == null || id < 0){
                throw new Exception("El ID es nulo o invalido");
            }
            
            return pDao.buscarPrestamoPorId(id);
           
        } catch (Exception e) {
            throw e;
        }
    }

    public void crearDevolucion(Prestamo prestamo) throws Exception {

        try {
            Libro libro = prestamo.getLibro();
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() - 1);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() + 1);
            LibroService ls = new LibroService();
            ls.modificarLibro(libro);

            pDao.eliminar(prestamo);

        } catch (Exception e) {
            throw e;
        }

    }

    public List<Prestamo> listarPrestamosCliente(Cliente cliente) throws Exception {

        try {
            if (cliente == null) {
                throw new Exception("Cliente nulo");
            }
            List<Prestamo> prestamos = pDao.listarPorCliente(cliente);
            return prestamos;
        } catch (Exception e) {
            throw e;
        }

    }

}
