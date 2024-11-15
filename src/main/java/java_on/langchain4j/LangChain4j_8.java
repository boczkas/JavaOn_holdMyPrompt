package java_on.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.service.AiServices;
import java_on.SentimentAnalyzer;

import java.util.Scanner;

import static java_on.utils.Printer.debugPrint;
import static java_on.utils.Printer.print;


public class LangChain4j_8 {
    private static final String API_KEY = "<Your API Key>"; // check how to get one here: https://www.merge.dev/blog/chatgpt-api-key

    public static void main(String[] args) {
        ChatLanguageModel model
                = OpenAiChatModel.builder()
                .apiKey(API_KEY)
                .modelName(OpenAiChatModelName.GPT_4_O)
                .temperature(1.0)
                .build();

        String answer = model.generate("Tell me a joke about Java");

        print(answer);

        String jokeLikeQuestion = model.generate(
                "How would you ask a user if the joke was funny. " +
                        "Provide the text of a question only.");
        print(jokeLikeQuestion);

        Scanner scanner = new Scanner(System.in);
        String userOpinion = scanner.nextLine();

        java_on.SentimentAnalyzer sentimentAnalyzer = AiServices.create(SentimentAnalyzer.class, model);
        boolean positive = sentimentAnalyzer.isPositive(userOpinion);
        debugPrint(String.valueOf(positive));

        if (positive) {
            String happyAnswer = model.generate(
                    "How would you say that you're glad that user likes the joke. " +
                            "Provide the text of message only.");
            print(happyAnswer);
        } else {
            String sadAnswer = model.generate(
                    "How would you say that you're are sorry for providing not so funny joke. " +
                            "Provide the text of message only.");
            print(sadAnswer);
        }
    }
}
