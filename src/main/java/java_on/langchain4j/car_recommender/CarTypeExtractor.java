package java_on.langchain4j.car_recommender;

import dev.langchain4j.service.UserMessage;

public interface CarTypeExtractor {
    @UserMessage("Extract information about car type from {{it}}. It should be the type only without description.")
    String extractCarTypeFrom(String text);
}
