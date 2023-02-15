package com.tayluan.book.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "bookId")
public class BookResponseDTO {
    private Long bookId;
    @NotNull
    private String bookName;
    @NotNull
    private String author;
    @NotNull
    private BigDecimal price;
}

