package com.tayluan.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tayluan.book.controller.BookController;
import com.tayluan.book.dto.BookFormDTO;
import com.tayluan.book.dto.BookResponseDTO;
import com.tayluan.book.model.Book;
import com.tayluan.book.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("shouldReturnStatusCode200_WhenGetAll")
    void shouldReturnStatusCode200_WhenGetAll() throws Exception {

        // given
        List<BookResponseDTO> bookResponseDTOList = Arrays.asList(
                new BookResponseDTO(1L,"Book 1","Author 1",new BigDecimal(40.00)),
                new BookResponseDTO(2L,"Book 2","Author 2",new BigDecimal(50.00))
        );
        when(bookService.findAll()).thenReturn(bookResponseDTOList);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books")
                .accept(MEDIA_TYPE_JSON))
                .andDo(MockMvcResultHandlers.print())
        // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookId").value(2L));

    }
    @Test
    @DisplayName("shouldReturnStatusCode200_WhenGetById")
    void shouldReturnStatusCode200_WhenGetById() throws Exception {

        // given
        var bookResponseDTO = new BookResponseDTO(1L,"Book 1","Author 1",new BigDecimal(50.00));
        when(bookService.findById(1L)).thenReturn(bookResponseDTO);
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books/{id}",1L)
                        .accept(MEDIA_TYPE_JSON))
                .andDo(MockMvcResultHandlers.print())
                // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookId").value(1L));
    }
    @Test
    @DisplayName("shouldReturnStatusCode201_WhenSave")
    void shouldReturnStatusCode201_WhenSave() throws Exception {

        // given
        var bookFormDTO = new BookFormDTO("Book 1","Author 1",new BigDecimal(50.00));
        var bookResponseDTO = new BookResponseDTO(1L,"Book 1","Author 1",new BigDecimal(50.00));

        when(bookService.save(bookFormDTO)).thenReturn(bookResponseDTO);

        // when

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/books")
                        .accept(MEDIA_TYPE_JSON)
                        .contentType(MEDIA_TYPE_JSON)
                        .content(objectMapper.writeValueAsString(bookFormDTO)) )
                .andDo(MockMvcResultHandlers.print())
                // then
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName").value("Book 1"));
    }
}
