package com.purplecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/books")
public class Main {

    private final LibRepository libRepository;

    public Main(LibRepository libRepository) {
        this.libRepository = libRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Library> getBooks() {
        return libRepository.findAll();
    }

    record NewBookRequest(
            String bookTitle,
            String authorName,
            String genre
    ) {
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody NewBookRequest request) {
        Library library = new Library();
        library.setBookTitle(request.bookTitle());
        library.setAuthorName(request.authorName());
        library.setGenre(request.genre());
        libRepository.save(library);
        return ResponseEntity.ok("Booked Added Successfully");
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") Integer id) {
        libRepository.deleteById(id);
        return ResponseEntity.ok("Book deleted Successfully");
    }

    record UpdateBookRequest(
            String bookTitle,
            String authorName,
            String genre
    ) {
    }

    @PutMapping("{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable("bookId") Integer id, @RequestBody UpdateBookRequest request) {
        Optional<Library> optionalBook = libRepository.findById(id);

        if (optionalBook.isPresent()) {
            Library library = optionalBook.get();
            if (request.bookTitle() != null) {
                library.setBookTitle(request.bookTitle());
            }
            if (request.authorName() != null) {
                library.setAuthorName(request.authorName());
            }
            if (request.genre() != null) {
                library.setGenre(request.genre());
            }
            libRepository.save(library);
            return ResponseEntity.ok("Book updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{bookId}")
    public Optional<Library> getBooksByid(@PathVariable("bookId") Integer Id) {
        return libRepository.findById(Id);
    }
}



