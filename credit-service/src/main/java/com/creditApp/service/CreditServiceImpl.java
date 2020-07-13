package com.creditApp.service;

import com.creditApp.model.Credit;
import com.creditApp.model.dto.CreditDto;
import com.creditApp.model.dto.CustomerDto;
import com.creditApp.model.dto.ProductDto;
import com.creditApp.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final RestTemplate restTemplate;

    @Override
    public CreditDto addCredit(CustomerDto customerDto, ProductDto productDto) {
        Credit credit = new Credit();
        credit.setCreditNumber(UUID.randomUUID().toString().replace("-", ""));
        customerDto.setCreditNumber(credit.getCreditNumber());
        productDto.setCreditNumber(credit.getCreditNumber());

        ResponseEntity<ProductDto> productResult = restTemplate.postForEntity(
                "http://product-service/add", productDto, ProductDto.class);
        ResponseEntity<CustomerDto> customerResult = restTemplate.postForEntity(
                "http://customer-service/add", customerDto, CustomerDto.class);
//        if (productResult.getStatusCode() == HttpStatus.CREATED && customerResult.getStatusCode() == HttpStatus.CREATED) {
//            return
//        }
        return null;
    }
}
