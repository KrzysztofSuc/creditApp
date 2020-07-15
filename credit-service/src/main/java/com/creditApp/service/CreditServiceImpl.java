package com.creditApp.service;

import com.creditApp.model.Credit;
import com.creditApp.model.CreditContainer;
import com.creditApp.model.dto.CreditDto;
import com.creditApp.model.dto.CustomerDto;
import com.creditApp.model.dto.ProductDto;
import com.creditApp.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    @Override
    public CreditDto addCredit(CustomerDto customerDto, ProductDto productDto) {
        Credit credit = new Credit();
        credit.setCreditNumber(UUID.randomUUID().toString().replace("-", ""));
        customerDto.setCreditNumber(credit.getCreditNumber());
        productDto.setCreditNumber(credit.getCreditNumber());

        try {
            restTemplate.postForEntity("http://product-service/add", productDto, ProductDto.class);
            restTemplate.postForEntity("http://customer-service/add", customerDto, CustomerDto.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NoSuchElementException();
            }
        }
        creditRepository.save(credit);
        return modelMapper.map(credit, CreditDto.class);
    }

    @Override
    public CreditContainer getCredit(String creditNumber) {
        CreditContainer creditContainer = new CreditContainer();
        if (creditRepository.getCreditByCreditNumber(creditNumber) != null) {
            try {
                creditContainer.setProductDto(restTemplate
                        .getForEntity("http://product-service/{creditNumber}", ProductDto.class, creditNumber).getBody());
                creditContainer.setCustomerDto(restTemplate
                        .getForEntity("http://customer-service/{creditNumber}", CustomerDto.class, creditNumber).getBody());
            } catch (HttpClientErrorException e) {
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new NoSuchElementException();
                }
            }
        } else throw new NoSuchElementException();

        return creditContainer;
    }
}
