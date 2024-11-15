package java_on.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;

import static java_on.utils.Printer.print;

public class LangChain4j_7 {
    private static final String API_KEY = "<Your API Key>"; // check how to get one here: https://www.merge.dev/blog/chatgpt-api-key
    private static final String PROJECT_ID = "<Your Project ID>"; // check how to get one here: https://cloud.google.com/vertex-ai/docs/tutorials/tabular-bq-prediction/prerequisites
    private static final String LOCATION = "<Chosen location>"; // check how to chose one here: https://cloud.google.com/vertex-ai/docs/general/locations
    private static final String MODEL = "<Model to be used>"; // check how to set up it here: https://cloud.google.com/vertex-ai/docs/start/cloud-environment

    public static void main(String[] args) {
        ChatLanguageModel model
                = OpenAiChatModel.builder()
                .apiKey(API_KEY)
                .modelName(OpenAiChatModelName.GPT_4_O)
                .temperature(Double.valueOf(0))
                .build();

//
//        ChatLanguageModel model = VertexAiGeminiChatModel.builder()
//                .project(PROJECT_ID)
//                .location(LOCATION)
//                .modelName(MODEL)
//                .build();



        String question = model.generate("Tell me a joke");

        print(question);

//        Amazon Bedrock
//        Anthropic
//        Azure OpenAI
//        ChatGLM
//        DashScope
//        Google AI Gemin
//        Google Vertex AI Gemini
//        Google Vertex AI PaLM 2
//        Hugging Face
//        Jlama
//        LocalAI
//        Mistral AI
//        Ollama
//        OpenAI
//        Qianfan
//        Cloudflare Workers AI
//        Zhipu AI

    }
}

// More details and examples here:
// https://docs.langchain4j.dev/
// https://github.com/langchain4j/langchain4j-examples