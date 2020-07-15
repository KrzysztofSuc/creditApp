package com.creditApp.exception;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
