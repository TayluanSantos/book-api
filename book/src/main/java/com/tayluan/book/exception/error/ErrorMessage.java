package com.tayluan.book.exception.error;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ErrorMessage {
    private String message;
    private String path;
}
