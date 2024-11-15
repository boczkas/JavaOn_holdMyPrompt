package java_on.langchain4j.car_recommender;

import dev.langchain4j.service.UserMessage;

import java.util.List;

public interface BrandExtractor {

    @UserMessage("Extract name of the car brands from {{it}}")
    List<String> extractCarBrandFrom(String text);

}
