package com.creditApp.controller;

import com.creditApp.model.CreditContainer;
import com.creditApp.model.dto.CreditDto;
import com.creditApp.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/add")
    public ResponseEntity<CreditDto> addCredit(@Valid @RequestBody CreditContainer creditContainer) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(creditService.addCredit(creditContainer.getCustomerDto(), creditContainer.getProductDto()));
    }

    @GetMapping("/{creditNumber}")
    public ResponseEntity<CreditContainer> getCredit(@PathVariable String creditNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(creditService.getCredit(creditNumber));
    }

    @DeleteMapping("/{creditNumber}")
    public ResponseEntity<?> removeCredit(@PathVariable String creditNumber) {
        creditService.removeCredit(creditNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
