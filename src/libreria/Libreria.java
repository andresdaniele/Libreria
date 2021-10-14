package libreria;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import libreria.entities.Autor;
import libreria.entities.Cliente;
import libreria.entities.Editorial;
import libreria.entities.Libro;
import libreria.entities.Prestamo;
import libreria.menu.MenuAutor;
import libreria.menu.MenuCliente;
import libreria.menu.MenuEditorial;
import libreria.menu.MenuLibro;
import libreria.menu.MenuPrestamo;
import libreria.services.AutorService;
import libreria.services.ClienteService;
import libreria.services.EditorialService;
import libreria.services.LibroService;
import libreria.services.PrestamoService;

public class Libreria {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        LibroService ls = new LibroService();
        EditorialService es = new EditorialService();
        AutorService as = new AutorService();
        PrestamoService ps = new PrestamoService();
        ClienteService cs = new ClienteService();
        MenuLibro ml = new MenuLibro();
        MenuEditorial me = new MenuEditorial();
        MenuAutor ma = new MenuAutor();
        MenuPrestamo mp = new MenuPrestamo();
        MenuCliente mc = new MenuCliente();

        try {

//            Editorial ed1 = new Editorial("Santillana", true);
//            Editorial ed2 = new Editorial("Capeluz", true);
//            Editorial ed3 = new Editorial("Salamandra", true);
//            Editorial ed4 = new Editorial("Panamericana", true);
//            es.crearEditorial(ed1);
//            es.crearEditorial(ed2);
//            es.crearEditorial(ed3);
//            es.crearEditorial(ed4);
//
//            Autor autor1 = new Autor("JK ROWLING", true);
//            Autor autor2 = new Autor("GABRIEL GARCIA MARQUEZ", true);
//            Autor autor3 = new Autor("MARCO DENEVI", true);
//            Autor autor4 = new Autor("JULIO VERNE", true);
//            as.crearAutor(autor1);
//            as.crearAutor(autor2);
//            as.crearAutor(autor3);
//            as.crearAutor(autor4);
//
//            Libro libro1 = new Libro(1555L, "HARRY POTTER", 1998, 9, 6, 3, true, autor1, ed1);
//            Libro libro2 = new Libro(1565L, "CIEN ANOS DE SOLEDAD", 1981, 15, 5, 10, true, autor2, ed2);
//            Libro libro3 = new Libro(1575L, "ROSAURA A LAS DIEZ", 1981, 5, 0, 5, true, autor3, ed3);
//            Libro libro4 = new Libro(1585L, "LA VUELTA AL MUNDO EN 80 DIAS", 2020, 2, 1, 1, true, autor4, ed4);
//            ls.crearLibro(libro1);
//            ls.crearLibro(libro2);
//            ls.crearLibro(libro3);
//            ls.crearLibro(libro4);
//            Cliente cliente = cs.crearCliente(new Cliente(10L, "ANDRES", "DANIELE", "2613589784"));
//            Cliente cliente2 = cs.crearCliente(new Cliente(20L, "BELEN", "ROJO", "2613431422"));
//
//            Calendar fechaPrestamo = new GregorianCalendar(2021, Calendar.OCTOBER, 22);
//            Calendar fechaDevolucion = new GregorianCalendar(2021, Calendar.NOVEMBER, 10);
//
//            ps.crearPrestamo(new Prestamo(fechaPrestamo, fechaDevolucion, libro2, cliente2));
//            ps.crearPrestamo(new Prestamo(fechaPrestamo, fechaDevolucion, libro1, cliente));
//            ps.crearPrestamo(new Prestamo(fechaPrestamo, fechaDevolucion, libro2, cliente2));
//            ps.crearPrestamo(new Prestamo(fechaPrestamo, fechaDevolucion, libro3, cliente));
//            ps.crearPrestamo(new Prestamo(fechaPrestamo, fechaDevolucion, libro4, cliente));
//            ps.crearDevolucion(prestamo1);
//            Cliente clienteBusca = cs.buscarClienteId(2);
//
//            List<Prestamo> prestamosClientes = ps.listarPrestamosCliente(clienteBusca);
//            prestamo.toString();
//            for (Prestamo aux : prestamosClientes) {
//                System.out.println(aux.toString());
//            }
//        } catch (Exception e) {
//
//            System.out.println("prueba");
//        }
            int resp;

            do {
                System.out.println("\nIngrese una opcion:");
                System.out.println("  1- MENU LIBRO");
                System.out.println("  2- MENU AUTOR");
                System.out.println("  3- MENU EDITORIAL");
                System.out.println("  4- MENU CLIENTE");
                System.out.println("  5- MENU PRESTAMOS");
                System.out.println("  6- SALIR");

                resp = leer.nextInt();

                switch (resp) {
                    case 1:
                        ml.menuPrincipal();
                        break;
                    case 2:
                        ma.menuPrincipal();
                        break;
                    case 3:
                        me.menuPrincipal();
                        break;
                    case 4:
                        mc.menuPrincipal();
                        break;
                    case 5:
                        mp.menuPrincipal();
                        break;
                    case 6:
                        System.out.println("FINALIZADO");
                        break;
                    default:
                        System.out.println("OPCION INVALIDA. INTENTE NUEVAMENTEs");
                }
            } while (resp != 6);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
This exception occurs when you save (persist or merge) an object with a reference to another object which is new (does not exists in the database). To avoid the error, such new object should be saved in the same persistence context, i.e. in the same transaction.

For example, you have two related entities: Customer and Order, and Order can contain a reference to Customer. Then, you have an editor screen for Order with the corresponding datasource. Imagine that when you edit a new Order, you programmatically create a new instance of Customer and set it to Order’s attribute. After that, you commit the screen. DataManager on middleware receives a CommitContext with the new Order which has the new Customer as a reference. It invokes EntityManager.persist(order), commits the transaction, and here the exception occurs - the Customer is not passed to the persistence context and there is no CASCADE relationship between Order and Customer.

The solution is to pass the new Customer together with the new Order. It can be done automatically if the screen contains a datasource for Customer - all datasources are saved on screen commit. Alternatively, the new Customer instance can be added to CommitContext in a DsContext.BeforeCommitListener 376.
*/