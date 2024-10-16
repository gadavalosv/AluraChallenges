package gadv.starwars.main;

import gadv.starwars.models.FileGenerator;
import gadv.starwars.models.Movie;
import gadv.starwars.models.MovieConsult;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Movie> movies = new ArrayList<>();
        while (true) {
            System.out.println("Escriba el id (1-6) de la Película (o 7 para salir): ");
            var movieToSearch = scanner.nextInt();
            if (movieToSearch == 7){
                break;
            }
            try {
                Movie myMovie = MovieConsult.searchMovie(movieToSearch);
                System.out.println("Titulo ya convertido: " + myMovie);
                movies.add(myMovie);
            }catch (NumberFormatException e){
                System.out.println("Número no encontrado " + e.getMessage());
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
                System.out.println("Finalizando la aplicación");
            }catch (Exception e) {
                System.out.println("Ocurrió un error inesperado.");
                System.out.println(e.getMessage());
            }
        }
        FileGenerator.generateFile(movies);
        System.out.println("Finalizó la ejecución del Programa");
        scanner.close();
    }
}
