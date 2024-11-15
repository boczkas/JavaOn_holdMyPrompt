package java_on.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.service.AiServices;

public class LangChain4j_9 {
    private static final String API_KEY = "<Your API Key>"; // check how to get one here: https://www.merge.dev/blog/chatgpt-api-key

    public static void main(String[] args) {
        ChatLanguageModel model
                = OpenAiChatModel.builder()
                .apiKey(API_KEY)
                .modelName(OpenAiChatModelName.GPT_4_O)
                .responseFormat("json_object")
                .temperature(0.0)
                .build();
        PersonExtractor personExtractor = AiServices.create(PersonExtractor.class, model);

        String frodoDescription = """
                Frodo Baggins is a central character in J.R.R.
                Tolkien's epic fantasy novel "The Lord of the Rings".
                He is a hobbit, a small, humanoid creature with curly hair,
                slightly pointed ears, and a love for a simple, quiet life.
                Frodo is characterized by his kind-hearted and selfless nature.
                He has a fair complexion, bright eyes, and often wears comfortable, earth-toned clothing.
                Although he initially lives a peaceful life in the Shire,
                Frodo is thrust into a dangerous adventure when he inherits the One Ring,
                an immensely powerful artifact. Throughout the journey,
                Frodo demonstrates great courage and resilience as he faces numerous
                challenges in his quest to destroy the ring and save Middle-earth from the dark forces of the evil Sauron.
                """;

        String vaderDescription = """
                Lord Vader, also known as Darth Vader, is a central character in the Star Wars franchise,
                clad in imposing black armor and a mask that conceals his identity.
                Initially a heroic Jedi Knight named Anakin Skywalker, he falls to the dark side and becomes
                a Sith Lord under Emperor Palpatine. Vader is known for his deep, modulated voice, heavy, mechanical breathing,
                and mastery of dark side powers, including telekinesis and force choke. Armed with a red lightsaber,
                Vader embodies a commanding and fearsome presence, making him one of the most iconic villains in film history.
                His character's journey involves themes of power, tragedy, and eventual redemption.
                """;

        Person frodo = personExtractor.extractPersonFrom(frodoDescription);

        System.out.println(frodo);

        Person vader = personExtractor.extractPersonFrom(vaderDescription);

        System.out.println(vader);

    }
}
