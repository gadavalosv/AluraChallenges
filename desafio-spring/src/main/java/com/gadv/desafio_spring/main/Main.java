package com.gadv.desafio_spring.main;

import com.gadv.desafio_spring.model.BookData;
import com.gadv.desafio_spring.model.Data;
import com.gadv.desafio_spring.service.ConsultAPI;
import com.gadv.desafio_spring.service.ConvertData;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final ConsultAPI consultAPI = new ConsultAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final ConvertData convertData = new ConvertData();
    public void showMenu(Scanner scanner){
        String json = consultAPI.getData(URL_BASE);// + URLEncoder.encode(seriesName, StandardCharsets.UTF_8));
        var data = convertData.getData(json, Data.class);
        System.out.println(data);
        //Top 10 libros mas descargados
        System.out.println("Top 10 libros mas descargados: ");
        data.bookDataList().stream()
                .sorted(Comparator.comparing(BookData::numberOfDownloads).reversed())
                .limit(10)
                .map(bookData -> bookData.title().toUpperCase())
                .forEach(System.out::println);
        //Buscar libros por nombre
        System.out.println("Por favor ingrese el nombre del libro que desea buscar (en ingles):");
        String bookTitleToSearch = scanner.nextLine();
        //usando ConsultAPI
        json = consultAPI.getData(URL_BASE + "?search=" + URLEncoder.encode(bookTitleToSearch, StandardCharsets.UTF_8));
        var dataBookSearched = convertData.getData(json, Data.class);
        Optional<BookData> optionalBookData = dataBookSearched.bookDataList().stream()
                .filter(bookData -> bookData.title().toUpperCase().contains(bookTitleToSearch.toUpperCase()))
                .findFirst();
        if(optionalBookData.isPresent()){
            System.out.println("Libro Encontrado: ");
            System.out.println(optionalBookData.get());
        }else{
            System.out.println("Libro No Encontrado");
        }
        //usando stream en todos los libros
//        data.bookDataList().stream()
//                .filter(l -> l.title().toUpperCase().contains(bookTitleToSearch.toUpperCase()))
//                .forEach(System.out::println);

        //Estadisticas de descarga:
        DoubleSummaryStatistics booksDownloadsDoubleSummaryStatistics = data.bookDataList().stream()
                .filter(bookData -> bookData.numberOfDownloads() > 0.0)
                .collect(Collectors.summarizingDouble(BookData::numberOfDownloads));
        System.out.println("Cantidad media de descargas: " + booksDownloadsDoubleSummaryStatistics.getAverage());
        System.out.println("Cantidad máxima de descargas: " + booksDownloadsDoubleSummaryStatistics.getMax());
        System.out.println("Cantidad mínima de descargas: " + booksDownloadsDoubleSummaryStatistics.getMin());
    }
}
