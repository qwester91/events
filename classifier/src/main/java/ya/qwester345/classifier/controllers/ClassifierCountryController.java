package ya.qwester345.classifier.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ya.qwester345.classifier.dao.entity.Country;
import ya.qwester345.classifier.dto.CountryDto;
import ya.qwester345.classifier.dto.ListOfEntity;
import ya.qwester345.classifier.service.api.IClassifierService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classifier/country")
public class ClassifierCountryController {
    private IClassifierService<Country, CountryDto> service;

    public ClassifierCountryController(IClassifierService<Country,CountryDto> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody CountryDto country){
        return new ResponseEntity<>(service.add(country), HttpStatus.CREATED);

    }

    @GetMapping("/{uuid}")
    public ResponseEntity<HttpStatus> getCountry(@PathVariable UUID uuid){
        if(service.isExist(uuid)){
            return new ResponseEntity<>(HttpStatus.OK);}
        else {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}

    }

    @GetMapping
    public ListOfEntity<Country> getListCountries(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size){

        Pageable pageable = PageRequest.of(page-1, size);

        return service.get(pageable);
    }

}
