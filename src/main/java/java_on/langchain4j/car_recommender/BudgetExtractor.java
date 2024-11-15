package java_on.langchain4j.car_recommender;

import dev.langchain4j.service.UserMessage;

public interface BudgetExtractor {
    @UserMessage("Extract information about budget from {{it}}")
    int extractCarBudgetFrom(String text);
}
