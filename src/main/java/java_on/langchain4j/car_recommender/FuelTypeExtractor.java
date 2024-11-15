package java_on.langchain4j.car_recommender;

import dev.langchain4j.service.UserMessage;

public interface FuelTypeExtractor {
    @UserMessage("Extract information about car fuel type from {{it}}. It should be the type only without description.")
    FuelType extractFuelTypeFrom(String text);
}
