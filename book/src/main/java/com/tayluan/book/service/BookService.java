package com.tayluan.book.service;

import com.tayluan.book.dto.BookFormDTO;
import com.tayluan.book.dto.BookResponseDTO;
import com.tayluan.book.exception.BookNotFoundException;
import com.tayluan.book.mapper.BookMapper;
import com.tayluan.book.model.Book;
import com.tayluan.book.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<BookResponseDTO> findAll() {
        List<Book> bookList = bookRepository.findAll();
        return BookMapper.parseListObject(bookList,BookResponseDTO.class);
    }
    public BookResponseDTO findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException("Book not found: " + id));
        return BookMapper.parseObject(book,BookResponseDTO.class);
    }
    @Transactional
    public BookResponseDTO save(BookFormDTO bookFormDTO){
        Book book = BookMapper.parseObject(bookFormDTO,Book.class);
        return BookMapper.parseObject(bookRepository.save(book),BookResponseDTO.class);
    }
    @Transactional
    public BookResponseDTO update(Long id, BookFormDTO bookFormDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found: " + id));
        BeanUtils.copyProperties(bookFormDTO, book);
        return BookMapper.parseObject(bookRepository.save(book),BookResponseDTO.class);
    }
    @Transactional
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found: " + id));
        bookRepository.delete(book);
    }
}
