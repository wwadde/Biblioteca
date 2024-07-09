package com.william.Biblioteca;

import com.william.Biblioteca.model.DatosBusquedaAPI;
import com.william.Biblioteca.model.dtos.AutorDTO;
import com.william.Biblioteca.model.dtos.LibroDTO;
import com.william.Biblioteca.model.entities.AutorEntity;
import com.william.Biblioteca.model.entities.LibroEntity;
import com.william.Biblioteca.repository.AutorRepository;

import com.william.Biblioteca.repository.LibroRepository;
import com.william.Biblioteca.service.ConsumoAPI;
import com.william.Biblioteca.service.MappearJson;


import javax.swing.text.html.Option;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private String url = "shakespeare%20hamlet";
    private final String urlBase = "https://gutendex.com/books/?search=";
    private static final Scanner scanner = new Scanner(System.in);

    private LibroDTO libro;
    private AutorDTO autor;

    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;

    public Main(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    public void mostrarMenu() {

        int opcion = -1;

        while (opcion != 0) {

            mostrarInicio();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("Ingrese el libro a buscar");
                    String busqueda = scanner.nextLine().replace(" ", "%20");
                    System.out.println(busqueda);
                    String json = ConsumoAPI.obtenerDatos(urlBase + busqueda);
                    System.out.println(json);

                    DatosBusquedaAPI resultado = MappearJson.convertirClase(json, DatosBusquedaAPI.class);


                    // Toma el primer resultado de busqueda
                    libro = resultado.getResults().getFirst();
                    // Toma el primer autor
                    autor = libro.autores().getFirst();

                    if (!libroExiste(libro.titulo())) {

                        LibroEntity libroEntity = new LibroEntity(libro);

                        Optional<AutorEntity> autorExiste = buscarAutores(autor.nombre());
                        autorExiste.ifPresent(libroEntity::setAutor);
                        AutorEntity autorEntity = new AutorEntity(autor);
                        // Si el autor no existe se agrega a la bd
                        if (!autorExiste(autorEntity.getNombre())) {
                            autorRepository.save(autorEntity);
                            System.out.println("Autor " + autorEntity.getNombre() + " guardado.");
                            libroEntity.setAutor(autorEntity);
                        }

                        libroRepository.save(libroEntity);
                        System.out.println("Libro guardado con exito");
                    }
                    else{
                        System.out.println("Libro ya existe en la base de datos");
                    }
                    // Si el libro no existe se agrega a la bd


                    break;
                case 2:
                    List<LibroEntity> todosLibros = mostrarLibros();
                    todosLibros.forEach(e -> {
                        Optional<AutorEntity> optionalAutor = buscarAutores(e.getTitulo());
                        optionalAutor.ifPresent(e::setAutor);
                        System.out.println(e);
                    });


                    break;
                case 3:

                    List<AutorEntity> autoresEnBD = autoresRegistrados();
                    autoresEnBD.forEach(System.out::println);
                    break;
                case 4:

                    System.out.println("Escriba el año");
                    int anio = scanner.nextInt();
                    scanner.nextLine();

                    List<AutorEntity> autoresDeterminadoAnio = autorRepository.findAutoresVivosEnFecha(anio);
                    autoresDeterminadoAnio.forEach(System.out::println);

                    break;
                case 5:
                    List<LibroEntity> todosLosLibrosIdioma = mostrarLibros();
                    System.out.println("Escriba el idioma que desea buscar");
                    System.out.println("es (español) | en (english) | fr (french) | pt (portuguese)");
                    String input = scanner.nextLine().toLowerCase();
                    todosLosLibrosIdioma.stream().filter(e -> e.getIdiomasDisponibles().contains(input)).forEach(System.out::println);

                    break;
                case 0:
                    System.out.println("Programa terminado");
                    break;
                default:
                    System.out.println("Input invalido");


            }


        }


    }

    private void mostrarInicio() {
        String inicio = """
                1- Buscar libro por titulo
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                0- Salir
                """;
        System.out.println(inicio);
    }

    private boolean autorExiste(String nombre) {
        Optional<AutorEntity> autor = autorRepository.findByNombre(nombre);
        return autor.isPresent();
    }

    private boolean libroExiste(String titulo) {
        Optional<LibroEntity> libro = libroRepository.findByTitulo(titulo);
        return libro.isPresent();
    }

    private Optional<AutorEntity> buscarAutores(String nombre){
        return autorRepository.findByNombre(nombre);

    }

    private List<LibroEntity> mostrarLibros(){
        return libroRepository.findAll();
    }

    private List<AutorEntity> autoresRegistrados(){
        return autorRepository.findAll();
    }
}
