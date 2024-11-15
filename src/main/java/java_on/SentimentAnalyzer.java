package java_on;

import dev.langchain4j.service.UserMessage;

public interface SentimentAnalyzer {
    @UserMessage("Is {{it}} positive?")
    boolean isPositive(String text);
}