package ya.qwester345.classifier.countries.service.api;

public interface IMapper <T,K>{
    T entityFromDto(K dto);

}
