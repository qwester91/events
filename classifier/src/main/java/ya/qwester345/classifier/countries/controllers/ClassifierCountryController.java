package ya.qwester345.classifier.countries.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ya.qwester345.classifier.countries.dao.entity.Country;
import ya.qwester345.classifier.countries.dto.CountryDto;
import ya.qwester345.classifier.countries.dto.ListOfCountries;
import ya.qwester345.classifier.countries.service.api.IClassifierService;

@RestController
@RequestMapping("/api/v1/classifier/country")
public class ClassifierCountryController {
    private IClassifierService<Country> service;

    public ClassifierCountryController(IClassifierService<Country> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody CountryDto country){
        return new ResponseEntity<>(service.add(country), HttpStatus.CREATED);

    }

    @GetMapping
    public ListOfCountries<Country> getListCountries(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size){

        Pageable pageable = PageRequest.of(page-1, size);

        return service.get(pageable);
    }

}
