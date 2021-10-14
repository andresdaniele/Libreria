package libreria.menu;

import java.util.List;
import java.util.Scanner;
import libreria.entities.Autor;
import libreria.entities.Editorial;
import libreria.entities.Libro;
import libreria.services.AutorService;
import libreria.services.EditorialService;
import libreria.services.LibroService;

public class MenuLibro {

    private Scanner leer;
    LibroService ls;

    public MenuLibro() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.ls = new LibroService();
    }

    public void menuPrincipal() throws Exception {
        int respuesta;

        try {
            do {
                System.out.println("Ingrese una opcion");
                System.out.println(" 1-INGRESAR UN LIBRO");
                System.out.println(" 2-BUSCAR LIBRO POR ISBN");
                System.out.println(" 3-BUSCAR LIBRO POR TITULO");
                System.out.println(" 4-BUSCAR LIBRO POR AUTOR");
                System.out.println(" 5-BUSCAR LIBRO POR EDITORIAL");
                System.out.println(" 6-ELIMINAR UN LIBRO");
                System.out.println(" 7- SALIR");
                respuesta = leer.nextInt();

                switch (respuesta) {
                    case 1:
                        crearLibro();
                        System.out.println("LIBRO CREADO CON EXITO");
                        break;
                    case 2:
                        System.out.println("Ingrese el ISBN del libro");
                        Long isbn = leer.nextLong();
                        Libro libro = ls.buscarISNB(isbn);
                        if (libro == null) {
                            System.out.println("El libro no fue encontrado");
                        } else {
                            System.out.println(libro.toString());
                        }
                        break;
                    case 3:
                        System.out.println("Ingrese el titulo del libro");
                        String titulo = leer.next().toUpperCase();
                        libro = ls.buscarPorTitulo(titulo);
                        if (libro == null) {
                            System.out.println("El libro no fue encontrado");
                        } else {
                            System.out.println(libro.toString());
                        }
                        break;
                    case 4:
                        System.out.println("Ingrese el titulo del libro");
                        String nombreAutor = leer.next().toUpperCase();
                        AutorService as = new AutorService();
                        Autor autor = as.buscarPorNombre(nombreAutor);
                        if (autor == null) {
                            System.out.println("El autor no fue encontrado");
                        } else {
                            List<Libro> libros = ls.buscarPorAutor(autor);
                            if (libros.isEmpty()) {
                                System.out.println("El autor no posee libros");
                            } else {
                                for (Libro aux : libros) {
                                    System.out.println(aux.toString());
                                }
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Ingrese el titulo del libro");
                        String nombreEditorial = leer.next().toUpperCase();
                        EditorialService es = new EditorialService();
                        Editorial editorial = es.buscarPorNombre(nombreEditorial);
                        if (editorial == null) {
                            System.out.println("El autor no fue encontrado");
                        } else {
                            List<Libro> libros = ls.buscarPorEditorial(editorial);
                            if (libros.isEmpty()) {
                                System.out.println("La editorial no posee libros");
                            } else {
                                for (Libro aux : libros) {
                                    System.out.println(aux.toString());
                                }
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Ingrese el isbn del libro que desea eliminar");
                        isbn = leer.nextLong();
                        libro = ls.buscarISNB(isbn);
                        ls.eliminarLibro(libro);
                        System.out.println("LIBRO ELIMINADO");
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("OPCION INGRESADA INVALIDA. INTENTE NUEVAMENTE");
                }
            } while (respuesta != 7);
        } catch (Exception e) {
            throw e;
        }
    }

    public void crearLibro() throws Exception {
        try {
            System.out.println("\nIngrese el ISBN del libro");
            Long isbn = leer.nextLong();
            System.out.println("Ingrese el titulo del libro");
            String titulo = leer.next().toUpperCase();
            System.out.println("Ingrese el año de publicacion");
            Integer anio = leer.nextInt();
            System.out.println("Ingrese el numero de ejemplares disponibles del libro");
            Integer ejemplares = leer.nextInt();
            System.out.println("Ingrese el numero de ejemplares prestados");
            Integer ejemplaresPrestados = leer.nextInt();
            Integer ejemplaresRestantes = ejemplares - ejemplaresPrestados;

            Autor autor = cargarAutor();
            Editorial editorial = cargarEditorial();

            Libro libro = new Libro(isbn, titulo, anio, ejemplares,
                    ejemplaresPrestados, ejemplaresRestantes, true, autor, editorial);

            ls.crearLibro(libro);

        } catch (Exception e) {
            throw e;
        }
    }

    public Autor cargarAutor() throws Exception {
        try {
            AutorService as = new AutorService();
            Autor autor = null;
            int respuesta = 0;
            List<Autor> autores = as.listarAutores();

            if (autores.isEmpty()) {
                System.out.println("DEBE CREAR UN AUTOR");
            } else {
                System.out.println("Selecciona un autor o crea uno");
                for (int i = 0; i < autores.size(); i++) {
                    System.out.println((i + 1) + "- " + autores.get(i).getNombre());
                    if (i == autores.size() - 1) {
                        System.out.println((i + 2) + "- CREAR AUTOR");
                    }
                }
                respuesta = leer.nextInt();
            }

            if (respuesta == (autores.size() + 1) || respuesta == 0) {
                MenuAutor ma = new MenuAutor();
                autor = ma.crearAutor();
                return autor;
            } else {
                autor = autores.get(respuesta - 1);
                return autor;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Editorial cargarEditorial() throws Exception {
        try {
            EditorialService es = new EditorialService();
            Editorial editorial = null;
            Integer respuesta = 0;

            List<Editorial> editoriales = es.listarEditorial();

            if (editoriales.isEmpty()) {
                System.out.println("DEBE CREAR UNA EDITORIAL");
            } else {
                System.out.println("Selecciona una editorial o crea una");
                for (int i = 0; i < editoriales.size(); i++) {
                    System.out.println((i + 1) + "- " + editoriales.get(i).getNombre());
                    if (i == editoriales.size() - 1) {
                        System.out.println((i + 2) + "- CREAR EDITORIAL");
                    }
                }
                respuesta = leer.nextInt();
            }

            if (respuesta == (editoriales.size() + 1) || respuesta == 0) {
                MenuEditorial me = new MenuEditorial();
                editorial = me.crearEditorial();
                return editorial;
            } else {
                editorial = editoriales.get(respuesta - 1);
                return editorial;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
