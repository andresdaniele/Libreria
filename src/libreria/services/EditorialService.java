package libreria.services;

import java.util.List;
import libreria.entities.Editorial;
import libreria.persistence.EditorialDAO;

public class EditorialService {

    EditorialDAO eDao = new EditorialDAO();

    public void crearEditorial(Editorial editorial) throws Exception {

        try {
            if (editorial.getNombre() == null || editorial.getNombre().isEmpty()) {
                throw new Exception("Ingresaste nombre nulo o vacio");
            }

            if (editorial.getAlta() == null) {
                throw new Exception("Ingresaste un estado de alta invalidos");
            }

            eDao.crear(editorial);

        } catch (Exception e) {
            throw e;
        }

    }
    
        public Editorial buscarPorNombre(String nombreEditorial) throws Exception {

        try {
            if (nombreEditorial == null || nombreEditorial.trim().isEmpty()) {
                throw new Exception("Nombre de editorial vacio o nulo");
            }
            Editorial editorial = eDao.buscarPorNombre(nombreEditorial);
            return editorial;

        } catch (Exception e) {
            throw e;
        }

    }
        
        public List<Editorial> listarEditorial() throws Exception{
            
            try {
                List<Editorial> editoriales = eDao.listar();
                return editoriales;
            } catch (Exception e) {
                throw e;
            }
        }

}
