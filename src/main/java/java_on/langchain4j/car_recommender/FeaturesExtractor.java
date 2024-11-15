package java_on.langchain4j.car_recommender;

import dev.langchain4j.service.UserMessage;

import java.util.List;

public interface FeaturesExtractor {
    @UserMessage("Extract information about car features {{it}}")
    List<String> extractCarFeaturesFrom(String text);
}
