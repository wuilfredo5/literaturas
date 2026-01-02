package com.gutendex.literaturas.console;

import com.gutendex.literaturas.model.dto.AuthorDTO;
import com.gutendex.literaturas.model.dto.BookDTO;
import com.gutendex.literaturas.service.AuthorService;
import com.gutendex.literaturas.service.BookService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ConsoleMenu {

    private final BookService bookService;
    private final AuthorService authorService;
    private final Scanner scanner;

    public ConsoleMenu(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            displayMainMenu();
            int option = readOption();

            switch (option) {
                case 1:
                    searchBooksByTitle();
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listAuthorsAliveInYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 6:
                    displayStatistics();
                    break;
                case 0:
                    exit = true;
                    System.out.println("\n¡Gracias por usar el sistema de Literaturas! ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("\n❌ Opción no válida. Por favor, seleccione una opción del menú.");
            }

            if (!exit) {
                pressEnterToContinue();
            }
        }

        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📚 SISTEMA DE GESTIÓN DE LITERATURAS - GUTENDEX");
        System.out.println("=".repeat(60));
        System.out.println("1. 🔍 Buscar libros por título (y guardar en base de datos)");
        System.out.println("2. 📖 Listar libros registrados");
        System.out.println("3. 👥 Listar autores registrados");
        System.out.println("4. 🎂 Listar autores vivos en un año determinado");
        System.out.println("5. 🌐 Listar libros por idioma");
        System.out.println("6. 📊 Mostrar estadísticas");
        System.out.println("0. ❌ Salir");
        System.out.println("=".repeat(60));
        System.out.print("Seleccione una opción (0-6): ");
    }

    private int readOption() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void searchBooksByTitle() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔍 BUSCAR LIBROS POR TÍTULO");
        System.out.println("=".repeat(60));
        System.out.print("Ingrese el título a buscar: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("\n❌ Debe ingresar un título para buscar.");
            return;
        }

        System.out.println("\n⏳ Buscando libros en Gutendex API...");

        List<BookDTO> books = bookService.searchAndSaveBooksByTitle(title);

        if (books.isEmpty()) {
            System.out.println("\n📭 No se encontraron libros nuevos para guardar.");
            System.out.println("   Los libros pueden ya estar registrados o no existir en Gutendex.");
        } else {
            System.out.println("\n✅ ¡" + books.size() + " libros guardados exitosamente!");
            System.out.println("\n📚 Libros guardados:");
            displayBooks(books);
        }
    }

    private void listRegisteredBooks() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📖 LIBROS REGISTRADOS EN LA BASE DE DATOS");
        System.out.println("=".repeat(60));

        try {
            List<BookDTO> books = bookService.getAllBooks();

            if (books.isEmpty()) {
                System.out.println("\n📭 No hay libros registrados en la base de datos.");
                System.out.println("   Use la opción 1 para buscar y guardar libros.");
            } else {
                System.out.println("\n📊 Total de libros: " + books.size());
                displayBooks(books);
            }
        } catch (Exception e) {
            System.err.println("\n❌ Error al listar libros: " + e.getMessage());
        }
    }

    private void listRegisteredAuthors() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👥 AUTORES REGISTRADOS EN LA BASE DE DATOS");
        System.out.println("=".repeat(60));

        try {
            List<AuthorDTO> authors = authorService.getUniqueAuthors();

            if (authors.isEmpty()) {
                System.out.println("\n📭 No hay autores registrados en la base de datos.");
            } else {
                System.out.println("\n📊 Total de autores únicos: " + authors.size());
                displayAuthors(authors);
            }
        } catch (Exception e) {
            System.err.println("\n❌ Error al listar autores: " + e.getMessage());
            // Fallback a lista normal
            List<AuthorDTO> authors = authorService.getAllAuthors();
            System.out.println("\n📊 Total de autores (puede incluir duplicados): " + authors.size());
            displayAuthors(authors);
        }
    }

    private void listAuthorsAliveInYear() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🎂 AUTORES VIVOS EN UN AÑO DETERMINADO");
        System.out.println("=".repeat(60));
        System.out.println("⚠️  Nota: Solo se consideran autores con fechas de nacimiento Y muerte conocidas.");

        System.out.print("Ingrese el año: ");
        try {
            int year = Integer.parseInt(scanner.nextLine().trim());

            List<AuthorDTO> authors = authorService.getAuthorsAliveInYear(year);

            if (authors.isEmpty()) {
                System.out.println("\n📭 No se encontraron autores con fechas conocidas vivas en el año " + year + ".");
                System.out.println("   Muchos autores antiguos no tienen fecha de muerte registrada.");
            } else {
                System.out.println("\n👥 Autores vivos en " + year + ": " + authors.size());
                displayAuthors(authors);
            }
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Debe ingresar un año válido (número entero).");
        } catch (Exception e) {
            System.err.println("\n❌ Error al buscar autores: " + e.getMessage());
        }
    }
    private void listBooksByLanguage() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🌐 LISTAR LIBROS POR IDIOMA");
        System.out.println("=".repeat(60));

        System.out.println("Idiomas comunes: en (inglés), es (español), fr (francés), de (alemán)");
        System.out.print("Ingrese el código del idioma (ej: 'es' para español): ");
        String language = scanner.nextLine().trim().toLowerCase();

        if (language.isEmpty()) {
            System.out.println("\n❌ Debe ingresar un código de idioma.");
            return;
        }

        try {
            List<BookDTO> books = bookService.getBooksByLanguage(language);

            if (books.isEmpty()) {
                System.out.println("\n📭 No hay libros en el idioma '" + language + "'.");
            } else {
                System.out.println("\n📚 Libros en " + language + ": " + books.size());
                displayBooks(books);
            }
        } catch (Exception e) {
            System.err.println("\n❌ Error al listar libros por idioma: " + e.getMessage());
        }
    }

    private void displayStatistics() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📊 ESTADÍSTICAS DEL SISTEMA");
        System.out.println("=".repeat(60));

        try {
            List<BookDTO> books = bookService.getAllBooks();
            List<AuthorDTO> authors = authorService.getUniqueAuthors();

            long totalBooks = books.size();
            long totalAuthors = authors.size();

            System.out.println("📚 Total de libros registrados: " + totalBooks);
            System.out.println("👥 Total de autores registrados: " + totalAuthors);

            if (totalBooks > 0 && totalAuthors > 0) {
                System.out.println("📈 Promedio de autores por libro: " +
                        String.format("%.2f", (double) totalAuthors / totalBooks));
            }

            // Estadísticas por idioma
            System.out.println("\n🌐 Libros por idioma:");
            Map<String, Long> languageCount = books.stream()
                    .flatMap(book -> book.getLanguages().stream())
                    .collect(Collectors.groupingBy(lang -> lang, Collectors.counting()));

            if (languageCount.isEmpty()) {
                System.out.println("   No hay datos de idiomas disponibles.");
            } else {
                languageCount.entrySet().stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .forEach(entry -> {
                            System.out.println("   " + entry.getKey() + ": " + entry.getValue() + " libros");
                        });
            }

            // Top 5 libros más descargados
            if (!books.isEmpty()) {
                System.out.println("\n🏆 Top 5 libros más descargados:");
                books.stream()
                        .sorted((b1, b2) -> Long.compare(b2.getDownloadCount(), b1.getDownloadCount()))
                        .limit(5)
                        .forEach(book -> {
                            System.out.println("   📖 " + book.getTitle() +
                                    " (" + book.getDownloadCount() + " descargas)");
                        });
            }

            // Autores con más libros
            System.out.println("\n👑 Autores con más libros:");
            Map<String, Long> authorBookCount = books.stream()
                    .flatMap(book -> book.getAuthors().stream())
                    .collect(Collectors.groupingBy(author -> author, Collectors.counting()));

            if (authorBookCount.isEmpty()) {
                System.out.println("   No hay datos de autores disponibles.");
            } else {
                authorBookCount.entrySet().stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .limit(5)
                        .forEach(entry -> {
                            System.out.println("   " + entry.getKey() + ": " + entry.getValue() + " libros");
                        });
            }

        } catch (Exception e) {
            System.err.println("\n❌ Error al calcular estadísticas: " + e.getMessage());
        }
    }

    private void displayBooks(List<BookDTO> books) {
        for (int i = 0; i < books.size(); i++) {
            BookDTO book = books.get(i);
            System.out.println("\n" + (i + 1) + ". " + book.getTitle());
            System.out.println("   ID Gutenberg: " + book.getGutenbergId());
            System.out.println("   Autores: " + String.join(", ", book.getAuthors()));
            System.out.println("   Idiomas: " + String.join(", ", book.getLanguages()));
            System.out.println("   Descargas: " + book.getDownloadCount());

            if (book.getSubjects() != null && !book.getSubjects().isEmpty() && book.getSubjects().size() <= 3) {
                System.out.println("   Temas: " + String.join(", ",
                        book.getSubjects().stream().limit(3).toList()));
            }
        }
    }

    private void displayAuthors(List<AuthorDTO> authors) {
        for (int i = 0; i < authors.size(); i++) {
            AuthorDTO author = authors.get(i);
            System.out.println("\n" + (i + 1) + ". " + author.getName());

            if (author.getBirthYear() != null || author.getDeathYear() != null) {
                String years = "";
                if (author.getBirthYear() != null) {
                    years += "Nacimiento: " + author.getBirthYear();
                }
                if (author.getDeathYear() != null) {
                    if (!years.isEmpty()) years += " - ";
                    years += "Fallecimiento: " + author.getDeathYear();
                }
                System.out.println("   " + years);
            }
        }
    }

    private void pressEnterToContinue() {
        System.out.println("\n" + "-".repeat(40));
        System.out.print("Presione Enter para continuar...");
        scanner.nextLine();
    }
}