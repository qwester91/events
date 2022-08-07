package ya.qwester345.classifier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ya.qwester345.classifier.dao.entity.Category;
import ya.qwester345.classifier.dto.CategoryDto;
import ya.qwester345.classifier.dto.ListOfEntity;
import ya.qwester345.classifier.service.api.IClassifierService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classifier/concert/category")
public class ClassifierCategoryController {
    @Qualifier("category")
    private IClassifierService<Category,CategoryDto> service;

    @Autowired

    public ClassifierCategoryController(IClassifierService<Category,CategoryDto> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Category> addCountry(@RequestBody CategoryDto category){
        return new ResponseEntity<>(service.add(category), HttpStatus.CREATED);

    }

    @GetMapping("/{uuid}")
    public ResponseEntity<HttpStatus> getCountry(@PathVariable UUID uuid){
        if(service.isExist(uuid)){
            return new ResponseEntity<>(HttpStatus.OK);}
        else {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}

    }

    @GetMapping
    public ListOfEntity<Category> getListCountries(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "10") Integer size){

        Pageable pageable = PageRequest.of(page-1, size);

        return service.get(pageable);
    }

}
