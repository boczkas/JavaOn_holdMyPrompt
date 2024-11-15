package java_on.langchain4j.car_recommender;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.service.AiServices;
import java_on.langchain4j.SentimentAnalyzer;

import java.util.List;
import java.util.Scanner;

import static java_on.utils.Printer.debugPrint;
import static java_on.utils.Printer.print;


public class LangChain4j_10 {

//    https://www.cargpt.ai/

    private static final String API_KEY = "<Your API Key>"; // check how to get one here: https://www.merge.dev/blog/chatgpt-api-key

    public static void main(String[] args) {
        ChatLanguageModel model
                = OpenAiChatModel.builder()
                .apiKey(API_KEY)
                .modelName(OpenAiChatModelName.GPT_4_O)
                .temperature(1.0)
                .build();
        Scanner scanner = new Scanner(System.in);

        profile(model);
        welcomeUser(model);

        boolean shouldStartCarPreferenceFind = true;

        while (shouldStartCarPreferenceFind) {
            CarPreference carPreference = CarPreference.builder()
                    .budget(getBudget(model, scanner))
                    .carType(getCarType(model, scanner))
                    .fuelType(getFuelType(model, scanner))
                    .brands(getBrands(model, scanner))
                    .additionalFeatures(getFeatures(model, scanner))
                    .build();

            debugPrint(carPreference.toString());

            printCompletition(model);
            printRecommendations(model, carPreference);

            while (shouldGetMoreRecommendations(model, scanner)) {
                printMoreRecommendations(model, carPreference);
            }

            shouldStartCarPreferenceFind = shouldStartNewRecommendationProcess(model, scanner);
        }

        printThankYouMessage(model);
    }

    private static void profile(ChatLanguageModel model) {
        model.generate("You should act as car-recommender." +
                "Be very polite in answering questions.");
    }

    private static void welcomeUser(ChatLanguageModel model) {
        String welcomeMessage = model.generate("Create welcome message in which you describe to " +
                "Client that you're AI car-recommender chat assistant and that you will ask questions to get client's " +
                "preferences. Don't ask any questions in this message." +
                "Return the welcome message only, without any additional signs.");
        print(welcomeMessage);
    }

    private static int getBudget(ChatLanguageModel model, Scanner scanner) {
        BudgetExtractor budgetExtractor = AiServices.create(BudgetExtractor.class, model);
        String budgetQuestion = model.generate(
                "How would you ask what is client's budget for buying car in dollars? " +
                        "Return the question only, without any additional signs.");
        print(budgetQuestion);

        String answer = scanner.nextLine();

        int budget = budgetExtractor.extractCarBudgetFrom(answer);
        debugPrint(budget);

        return budget;
    }


    private static String getCarType(ChatLanguageModel model, Scanner scanner) {
        CarTypeExtractor carTypeExtractor = AiServices.create(CarTypeExtractor.class, model);
        String carTypeQuestion = model.generate("How would you congratulate the client for choosing the budget " +
                "and ask what type of car client prefers?" +
                "Return the text only, without any additional signs.");
        print(carTypeQuestion);

        String answer = scanner.nextLine();

        String carType = carTypeExtractor.extractCarTypeFrom(answer);
        debugPrint(carType);

        return carType;
    }

    private static FuelType getFuelType(ChatLanguageModel model, Scanner scanner) {
        FuelTypeExtractor fuelTypeExtractor = AiServices.create(FuelTypeExtractor.class, model);
        String fuelTypeQuestion = model.generate(
                "How would you congratulate the client for choosing the car type (type is important here)" +
                        "and ask what type of fuel client wants to use in car? " +
                        "Return the text only, without any additional signs.");
        print(fuelTypeQuestion);

        String answer = scanner.nextLine();

        FuelType fuelType = fuelTypeExtractor.extractFuelTypeFrom(answer);
        debugPrint(fuelType.toString());

        return fuelType;
    }

    private static List<String> getBrands(ChatLanguageModel model, Scanner scanner) {
        BrandExtractor brandExtractor = AiServices.create(BrandExtractor.class, model);
        String brandQuestion = model.generate(
                "How would you congratulate the client for choosing the fuel type and " +
                        "ask what brands of car client wants? " +
                        "Return the question only, without any additional signs.");
        print(brandQuestion);

        String answer = scanner.nextLine();

        List<String> brand = brandExtractor.extractCarBrandFrom(answer);
        debugPrint(brand);

        return brand;
    }

    private static List<String> getFeatures(ChatLanguageModel model, Scanner scanner) {
        FeaturesExtractor featuresExtractor = AiServices.create(FeaturesExtractor.class, model);
        String featuresQuestion = model.generate(
                "How would you congratulate the client for choosing the car brands and " +
                        "ask what additional car features client wants? " +
                        "Return the question only, without any additional signs.");
        print(featuresQuestion);

        String answer = scanner.nextLine();
        List<String> features = featuresExtractor.extractCarFeaturesFrom(answer);
        debugPrint(features);

        return features;
    }

    private static void printCompletition(ChatLanguageModel model) {
        String recommendationsInformation = model.generate("How would you inform client that we have" +
                " gathered all needed information?"
                + "Return the information only, without any additional signs.");
        print(recommendationsInformation);
    }

    private static void printRecommendations(ChatLanguageModel model, CarPreference carPreference) {
        print(model.generate("Create 5 car recommendations based on CarPreference Java object "
                + carPreference.toString()
                + ". Recommendation should contain short car specification, which is a list of items "
                + "which should refer to provided criteria and provide some non-criteria based additional information."));
    }

    private static void printMoreRecommendations(ChatLanguageModel model, CarPreference carPreference) {
        print(model.generate("Create 5 car recommendations based on CarPreference Java object "
                + carPreference.toString()
                + ". Avoid those provided previously."
                + " Recommendation should contain short car specification, which is a list of items"
                + " which should refer to provided criteria and provide some non-criteria based additional information."));
    }

    private static boolean shouldStartNewRecommendationProcess(ChatLanguageModel model, Scanner scanner) {
        SentimentAnalyzer sentimentAnalyzer = AiServices.create(SentimentAnalyzer.class, model);
        String newRecommendationsQuestion = model.generate("How would you ask client if new recommendation " +
                "process should be started?"
                + "Return the question only, without any additional signs.");

        print(newRecommendationsQuestion);

        String answer = scanner.nextLine();

        boolean isPositive = sentimentAnalyzer.isPositive(answer);
        debugPrint(String.valueOf(isPositive));

        return isPositive;
    }

    private static boolean shouldGetMoreRecommendations(ChatLanguageModel model, Scanner scanner) {
        SentimentAnalyzer sentimentAnalyzer = AiServices.create(SentimentAnalyzer.class, model);
        String moreRecommendationsQuestion = model.generate("How would you ask client if more " +
                "recommendations should be provided?"
                + "Return the question only, without any additional signs.");

        print(moreRecommendationsQuestion);

        String answer = scanner.nextLine();

        boolean isPositive = sentimentAnalyzer.isPositive(answer);
        debugPrint(String.valueOf(isPositive));

        return isPositive;
    }

    private static void printThankYouMessage(ChatLanguageModel model) {
        String thankYouMessage = model.generate("The conversation is over." +
                "Provide thank you message for using the AI car-recommender assistant. " +
                "Return the message only, without any additional signs.");

        print(thankYouMessage);
    }
}
