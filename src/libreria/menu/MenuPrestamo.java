package libreria.menu;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import libreria.entities.Cliente;
import libreria.entities.Libro;
import libreria.entities.Prestamo;
import libreria.services.ClienteService;
import libreria.services.LibroService;
import libreria.services.PrestamoService;

public class MenuPrestamo {

    private Scanner leer;
    private ClienteService sc;
    private Cliente cliente;
    private Prestamo prestamo;
    private PrestamoService ps;
    private Libro libro;
    private LibroService sl;

    public MenuPrestamo() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.sc = new ClienteService();
        this.cliente = new Cliente();
        this.prestamo = new Prestamo();
        this.ps = new PrestamoService();
        this.libro = new Libro();
        this.sl = new LibroService();
    }

    public void menuPrincipal() throws Exception {
        int respuesta;
        try {
            do {
                System.out.println("Ingrese una opcion");
                System.out.println(" 1- REGISTRAR UN PRESTAMO");
                System.out.println(" 2- REGISTRAR UNA DEVOLUCION");
                System.out.println(" 3- MOSTRAR PRESTAMOS DE UN CLIENTE");
                System.out.println(" 4- SALIR");
                respuesta = leer.nextInt();

                switch (respuesta) {
                    case 1:
                        prestamo();
                        break;
                    case 2:
                        devolucion();
                        break;
                    case 3:
                        mostrarPrestamos();
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("OPCION INGRESADA INVALIDA. INTENTE"
                                + " NUEVAMENTE");
                }

            } while (respuesta != 4);
        } catch (Exception e) {
            throw e;
        }

    }

    public void prestamo() throws Exception {

        try {
            System.out.println("Ingrese el id del cliente");
            Integer idCliente = leer.nextInt();
            cliente = sc.buscarClienteId(idCliente);
            if (cliente == null) {
                throw new Exception("El ID ingresado no corresponde a un cliente existente");
            }
            System.out.println("Ingrese el ISBN del libro");
            Long isbn = leer.nextLong();
            libro = sl.buscarISNB(isbn);
            if (libro == null) {
                throw new Exception("El ISBN ingresado no corresponde a un libro existente");
            }

            System.out.println("Ingrese el dia de retiro");
            int diaRetiro = leer.nextInt();
            System.out.println("Ingrese el mes de retiro");
            int mesRetiro = leer.nextInt();
            System.out.println("Ingrese el año de retiro");
            int anioRetiro = leer.nextInt();
            Calendar fechaRetiro = new GregorianCalendar(anioRetiro, mesRetiro - 1, diaRetiro);

            System.out.println("Ingrese el dia de devolucion");
            int diaDevolucion = leer.nextInt();
            System.out.println("Ingrese el mes de devolucion");
            int mesDevolucion = leer.nextInt();
            System.out.println("Ingrese el año de devolucion");
            int anioDevolucion = leer.nextInt();
            Calendar fechaDevolucion = new GregorianCalendar(anioDevolucion, mesDevolucion - 1, diaDevolucion);
            
            prestamo = new Prestamo(fechaRetiro, fechaDevolucion, libro, cliente);
            ps.crearPrestamo(prestamo);
            System.out.println("PRESTAMO CONFIRMADO");

        } catch (Exception e) {
            throw e;
        }

    }

    public void devolucion() throws Exception {
        try {
            System.out.println("Ingrese el ID del prestamo");
            Integer id = leer.nextInt();
            Prestamo prestamo = ps.buscarPrestamoId(id);
            if (prestamo == null) {
                throw new Exception("El ID ingresado no corresponde a un prestamo existente");
            }
            ps.crearDevolucion(prestamo);
            System.out.println("DEVOLUCION EXITOSA");

        } catch (Exception e) {
            throw e;
        }

    }

    public void mostrarPrestamos() throws Exception {
        try {
            System.out.println("Ingrese el DNI del cliente");
            Long dni = leer.nextLong();
            Cliente cliente = sc.buscarClienteDni(dni);
            if (cliente == null) {
                throw new Exception("El cliente ingresado no existe");
            }
            List<Prestamo> prestamos = ps.listarPrestamosCliente(cliente);

            if (prestamos.isEmpty()) {
                throw new Exception("El cliente no posee ningun prestamo");
            }
            for (Prestamo aux : prestamos) {
                System.out.println(aux.toString());
            }
        } catch (Exception e) {
            throw e;
        }

    }
}
