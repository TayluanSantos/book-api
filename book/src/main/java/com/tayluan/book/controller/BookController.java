package com.tayluan.book.controller;

import com.tayluan.book.dto.BookFormDTO;
import com.tayluan.book.dto.BookResponseDTO;
import com.tayluan.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }
    @PostMapping
    public ResponseEntity<BookResponseDTO> save(@Valid @RequestBody BookFormDTO bookFormDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookFormDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> update(@PathVariable Long id,
                                                             @Valid @RequestBody BookFormDTO bookFormDTO){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(id,bookFormDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted successfully.");
    }

}
