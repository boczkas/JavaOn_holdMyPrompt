package java_on.langchain4j;

import dev.langchain4j.service.UserMessage;

public interface SentimentAnalyzer {
    @UserMessage("Is {{it}} positive?")
    boolean isPositive(String text);
}
