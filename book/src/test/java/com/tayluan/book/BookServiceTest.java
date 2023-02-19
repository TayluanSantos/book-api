package com.tayluan.book;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.tayluan.book.dto.BookFormDTO;
import com.tayluan.book.exception.BookNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tayluan.book.dto.BookResponseDTO;
import com.tayluan.book.model.Book;
import com.tayluan.book.repository.BookRepository;
import com.tayluan.book.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    @Test
    @DisplayName("Should return a list of books")
    void shouldReturnBookResponseDTOList_WhenFindAll() {

        // given
        List<Book> bookList = Arrays.asList(
                new Book(1L, "Book 1", "Author 1", new BigDecimal(50.00)),
                new Book(2L, "Book 2", "Author 2", new BigDecimal(70.00)));

        when(repository.findAll()).thenReturn(bookList);

        // when
        List<BookResponseDTO> result = service.findAll();

        // then
        verify(repository,times(1)).findAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(),2);
        Assertions.assertEquals(result.get(0).getBookId(),1L);
        Assertions.assertEquals(result.get(1).getBookId(),2L);
    }
    @Test
    @DisplayName("Should return a book by id")
    void shouldReturnABookResponseDTO_WhenFindById(){

        // given
        var book = new Book(1L,"Book 1","Author 1",new BigDecimal(50.00));
        when(repository.findById(1L)).thenReturn(Optional.of(book));

        // when
        BookResponseDTO result = service.findById(1L);

        // then
        verify(repository,times(1)).findById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getBookId(),1L);
        Assertions.assertEquals(result.getBookName(),"Book 1");
        Assertions.assertEquals(result.getAuthor(),"Author 1");
        Assertions.assertEquals(result.getPrice(),new BigDecimal(50.00));
    }
    @Test
    @DisplayName("Should save a book")
    void shouldReturnABookResponseDTO_WhenSave(){

        // given
        var bookFormDTO = new BookFormDTO("Book 1","Author 1",new BigDecimal(50.00));
        var book = new Book(1L,"Book 1","Author 1",new BigDecimal(50.00));

        when(repository.save(any())).thenReturn(book);

        // when
        BookResponseDTO result = service.save(bookFormDTO);

        // then
        verify(repository,times(1)).save(any());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getBookId(),1L);
        Assertions.assertEquals(result.getBookName(),"Book 1");
        Assertions.assertEquals(result.getAuthor(),"Author 1");
        Assertions.assertEquals(result.getPrice(),new BigDecimal(50.00));
    }
    @Test
    @DisplayName("Should update a book")
    void shouldReturnABookResponseDTO_WhenUpdate(){
        // given
        Long bookId = 1L;
        var bookFormDTO = new BookFormDTO("Book 1","Author 1",new BigDecimal(50.00));
        var book = new Book(1L,"Book 1","Author 1",new BigDecimal(50.00));

        when(repository.findById(bookId)).thenReturn(Optional.of(book));
        when(repository.save(any())).thenReturn(book);

        // when
        BookResponseDTO result = service.update(bookId, bookFormDTO);

        // then
        verify(repository,times(1)).findById(1L);
        verify(repository,times(1)).save(any());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getBookName(),"Book 1");
        Assertions.assertEquals(result.getBookName(),"Book 1");
        Assertions.assertEquals(result.getAuthor(),"Author 1");
        Assertions.assertEquals(result.getPrice(),new BigDecimal(50.00));
    }
    @Test
    @DisplayName("Should throws a BookNotFoundException")
    void shouldThrowBookNotFoundException_WhenUpdate(){

        // given
        Long bookId = 1L;
        var bookFormDTO = new BookFormDTO("Book 1","Author 1",new BigDecimal(50.00));
        when(repository.findById(bookId)).thenReturn(Optional.empty());


        // when/then
        BookNotFoundException exception = Assertions.assertThrows(BookNotFoundException.class, () -> {
            service.update(bookId, bookFormDTO);
        });
        String expectedMessage = "Book not found: " + bookId;
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(actualMessage,expectedMessage);
    }
    @Test
    @DisplayName("Should delete a book ")
    void shouldDeleteABook_WhenDelete(){

        // given
        Long bookId = 1L;
        var book = new Book(bookId,"Book 1","Author 1",new BigDecimal(50.00));
        when(repository.findById(bookId)).thenReturn(Optional.of(book));
        doNothing().when(repository).delete(book);

        //when
        service.delete(bookId);

        // then
        verify(repository,times(1)).delete(book);
        verify(repository,times(1)).findById(bookId);
    }
    @Test
    @DisplayName("Should throws a BookNotFoundException")
    void shouldThrowsABookNotFoundException_WhenDelete(){

        // given
        Long bookId = 1L;
        var book = new Book(bookId,"Book 1","Author 1",new BigDecimal(50.00));
        when(repository.findById(bookId)).thenReturn(Optional.empty());

        // when/then
        BookNotFoundException exception = Assertions.assertThrows(BookNotFoundException.class, () -> {
            service.delete(bookId);
        });

        String expectedMessage = "Book not found: " + bookId;
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(actualMessage,expectedMessage);
    }

}
