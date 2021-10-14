package libreria.menu;

import java.util.List;
import java.util.Scanner;
import libreria.entities.Autor;
import libreria.services.AutorService;

public class MenuAutor {

    private Scanner leer;
    private AutorService as;

    public MenuAutor() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.as = new AutorService();
    }

    public void menuPrincipal() throws Exception {
        int respuesta;

        try {
            do {
                System.out.println("Ingrese una opcion");
                System.out.println(" 1-INGRESAR UN AUTOR");
                System.out.println(" 2-BUSCAR AUTOR POR NOMBRE");
                System.out.println(" 3-LISTAR LOS AUTORES");
                System.out.println(" 4-SALIR");
                respuesta = leer.nextInt();

                switch (respuesta) {
                    case 1:
                        as.crearAutor(crearAutor());
                        break;
                    case 2: 
                        System.out.println("INGRESE EL NOMBRE");
                        String nombre = leer.next().toUpperCase();
                        System.out.println(as.buscarPorNombre(nombre).toString());
                        break;
                    case 3:
                        List<Autor> autores = as.listarAutores();
                        for (Autor aux : autores) {
                            System.out.println(aux.toString());
                        }
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("OPCION INVALIDA. INTENTE NUEVAMENTE");
                        
                }

            } while (respuesta != 4);
        } catch (Exception e) {
            throw e;
        }
    }

    public Autor crearAutor() throws Exception {
        try {
            System.out.println("Ingrese el nombre del autor");
            String nombre = leer.next().toUpperCase();
            Autor autor = new Autor(nombre, true);
            return autor;

        } catch (Exception e) {
            throw e;
        }

    }
    
    
}
