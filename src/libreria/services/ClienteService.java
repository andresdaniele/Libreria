package libreria.services;

import libreria.entities.Cliente;
import libreria.persistence.ClienteDAO;

public class ClienteService {

    ClienteDAO cDao = new ClienteDAO();

    public Cliente crearCliente(Cliente cliente) throws Exception {
        if (cliente.getDocumento() == null || cliente.getDocumento() <= 0) {
            throw new Exception("El documento ingresado es invalido o nulo");
        }
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre ingresado esta vacio o nulo");
        }
        if (cliente.getApellido() == null || cliente.getApellido().trim().isEmpty()) {
            throw new Exception("El apellido ingresado esta vacio o nulo");
        }
        if (cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()) {
            throw new Exception("El numero ingresado esta vacio o nulo");
        }

        cDao.crearCliente(cliente);
        return cliente;
    }

    public Cliente buscarClienteId(Integer id) throws Exception {
        try {
            if (id == null || id < 0) {
                throw new Exception("Id ingresado nulo o invalido");
            }
            Cliente cliente = cDao.buscarClienteId(id);
            return cliente;
        } catch (Exception e) {
            throw e;
        }
    }
    
        public Cliente buscarClienteDni(Long dni) throws Exception {
        try {
            if (dni == null || dni < 0) {
                throw new Exception("DNI ingresado nulo o invalido");
            }

            Cliente cliente = cDao.buscarClienteDni(dni);
            return cliente;
        } catch (Exception e) {
            throw e;
        }
    }
    
        public void eliminarCliente(Cliente cliente) {
            try {
                if (cliente == null) {
                    throw new Exception("El cliente no se encuentra en la base de datos");
                }
                
                cDao.eliminar(cliente);
            } catch (Exception e) {
            }
        }

}
