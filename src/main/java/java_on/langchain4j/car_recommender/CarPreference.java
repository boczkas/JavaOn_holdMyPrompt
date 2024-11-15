package java_on.langchain4j.car_recommender;

import lombok.Builder;
import lombok.ToString;

import java.util.List;

@ToString
@Builder
public class CarPreference {
    int budget;
    String carType;
    FuelType fuelType;
    List<String> brands;
    List<String> additionalFeatures;
}
