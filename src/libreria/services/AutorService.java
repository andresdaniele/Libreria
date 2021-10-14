package libreria.services;

import java.util.List;
import libreria.entities.Autor;
import libreria.persistence.AutorDAO;

public class AutorService {

    AutorDAO aDao = new AutorDAO();

    public void crearAutor(Autor autor) throws Exception {

        try {

            if (autor.getNombre() == null || autor.getNombre().trim().isEmpty()) {
                throw new Exception("Ingresaste nombre nulo o vacio");
            }

            if (autor.getAlta() == null) {
                throw new Exception("Ingresaste un estado de alta invalido");
            }

            aDao.crear(autor);

        } catch (Exception e) {
            throw e;
        }

    }

    public Autor buscarPorNombre(String nombreAutor) throws Exception {

        try {
            if (nombreAutor == null || nombreAutor.trim().isEmpty()) {
                throw new Exception("Nombre de autor vacio o nulo");
            }
            Autor autor = aDao.buscarPorNombre(nombreAutor);
            return autor;

        } catch (Exception e) {
            throw e;
        }

    }
    
    public List<Autor> listarAutores() throws Exception{
        
        try {
            List<Autor> autores =  aDao.listar();
            return autores;
        } catch (Exception e) {
            throw e;
        }
        
        
    }

}
