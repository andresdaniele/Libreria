package libreria.menu;

import java.util.Scanner;
import libreria.entities.Cliente;
import libreria.entities.Cliente_;
import libreria.services.ClienteService;

public class MenuCliente {

    private Scanner leer;
    private Cliente cliente;
    private ClienteService cs;

    public MenuCliente() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.cliente = new Cliente();
        this.cs = new ClienteService();
    }

    public void menuPrincipal() throws Exception {
        try {
            int respuesta;
            do {
                System.out.println("Ingrese una opcion");
                System.out.println(" 1-INGRESAR UN CLIENTE");
                System.out.println(" 2-ELIMINAR UN CLIENTE");
                System.out.println(" 3-SALIR");
                respuesta = leer.nextInt();
                switch (respuesta) {
                    case 1:
                        crearCliente();
                        break;
                    case 2:
                        eliminarCliente();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("OPCION INGRESADA INVALIDA. INTENTE"
                                + " NUEVAMENTE");
                }
            } while (respuesta != 3);

        } catch (Exception e) {
            throw e;
        }
    }

    public void crearCliente() throws Exception {
        try {
            System.out.println("Ingrese el numero de documento del cliente");
            Long dni = leer.nextLong();
            System.out.println("Ingrese el nombre del cliente");
            String nombre = leer.next().toUpperCase();
            System.out.println("Ingrese el apellido del cliente");
            String apellido = leer.next().toUpperCase();
            System.out.println("Ingrese el telefono del cliente");
            String telefono = leer.next();

            cliente = new Cliente(dni, nombre, apellido, telefono);
            cs.crearCliente(cliente);
            System.out.println("CLIENTE CREADO EXITOSAMENTE");
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarCliente() throws Exception {
        try {
            System.out.println("Ingrese el numero de documento del cliente a eliminar");
            Long dni = leer.nextLong();
            cliente = cs.buscarClienteDni(dni);
            if (cliente == null) {
                throw new Exception("El cliente no existe en la base de datos");
            }
            cs.eliminarCliente(cliente);
            System.out.println("CLIENTE ELIMINADO EXITOSAMENTE");
        } catch (Exception e) {
            throw e;
        }
    }
}
