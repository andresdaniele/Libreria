package libreria.services;

import java.time.LocalDate;
import java.util.List;
import libreria.entities.Autor;
import libreria.entities.Editorial;
import libreria.entities.Libro;
import libreria.persistence.LibroDAO;

public class LibroService {

    LibroDAO lDao = new LibroDAO();

    public void crearLibro(Libro libro) throws Exception {

        int anioActual = LocalDate.now().getYear();

        if (libro.getIsbn() == null || libro.getIsbn() < 0) {
            throw new Exception("Ingresaste un ISBN nulo o invalido");
        }

        if (libro.getAnio() == null || libro.getAnio() > anioActual) {
            throw new Exception("Ingresaste un año nulo o mayor al año actual");
        }
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new Exception("Ingresaste un titulo nulo o vacio");
        }

        if (libro.getEjemplares() == null || libro.getEjemplares() < 0 || libro.getEjemplares() != (libro.getEjemplaresPrestados() + libro.getEjemplaresRestantes())) {
            throw new Exception("Ingresaste un numero de ejemplares invalido o "
                    + "la suma de ejemplares prestados y restantes es distinta del total de ejemplares");
        }

        if (libro.getAlta() == null) {
            throw new Exception("Ingresaste un estado de alta invalidos");
        }

        if (libro.getEjemplaresPrestados() == null || libro.getEjemplares() < 0) {
            throw new Exception("Ingresaste un numero de ejemplares restantes invalido");
        }

        if (libro.getEjemplaresRestantes() == null || libro.getEjemplares() < 0) {
            throw new Exception("Ingresaste un numero de ejemplares restantes invalido");
        }

        if (libro.getAutor() == null) {
            throw new Exception("Ingresaste un autor nulo");
        }

        if (libro.getEditorial() == null) {
            throw new Exception("Ingresaste una editorial nula");
        }

        lDao.crear(libro);
      
    }

    public Libro buscarISNB(Long isbn) throws Exception {

        Libro libro;

        try {
            if(isbn == null || isbn < 0) {
                throw new Exception("El isnb ingresado es nulo o invalido");
            }
            libro = lDao.buscarPorISNB(isbn);

        } catch (Exception e) {
            throw e;
        }

        return libro;
    }

    public Libro buscarPorTitulo(String titulo) throws Exception {

        Libro libro;

        try {
            libro = lDao.buscarPorTitulo(titulo);
        } catch (Exception e) {
            throw e;
        }

        return libro;
    }

    public List<Libro> buscarPorAutor(Autor autor) throws Exception {

        try {
            if (autor.getNombre() == null || autor.getNombre().trim().isEmpty()) {
                throw new Exception("Ingresaste nombre nulo o vacio");
            }

            if (autor.getAlta() == null) {
                throw new Exception("Ingresaste un estado de alta invalidos");
            }

            List<Libro> libros = lDao.buscarPorAutor(autor);
            return libros;

        } catch (Exception e) {
            throw e;
        }

    }

    public List<Libro> buscarPorEditorial(Editorial editorial) throws Exception {

        try {
            if (editorial.getNombre() == null || editorial.getNombre().trim().isEmpty()) {
                throw new Exception("Ingresaste nombre nulo o vacio");
            }

            if (editorial.getAlta() == null) {
                throw new Exception("Ingresaste un estado de alta invalidos");
            }

            List<Libro> libros = lDao.buscarPorEditorial(editorial);
            return libros;

        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarLibro(Libro libro) throws Exception {

        try {
            lDao.modificar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarLibro(Libro libro) throws Exception {
        try {
            if (libro == null) {
                throw new Exception("El libro ingresado es nulo o no existe");
            }
                lDao.eliminar(libro);
        } catch (Exception e) {
        }
    }
}
