package java_on;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.preview.ChatSession;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import com.google.cloud.vertexai.generativeai.preview.ResponseHandler;

import java.io.IOException;

import static java_on.utils.Printer.print;
/**
 * We can communicate from Java
 */
public class Joke_2 {
    private static final String PROJECT_ID = "<Your Project ID>"; // check how to get one here: https://cloud.google.com/vertex-ai/docs/tutorials/tabular-bq-prediction/prerequisites
    private static final String LOCATION = "<Chosen location>"; // check how to chose one here: https://cloud.google.com/vertex-ai/docs/general/locations
    private static final String MODEL = "<Model to be used>"; // check how to set up it here: https://cloud.google.com/vertex-ai/docs/start/cloud-environment

    public static void main(String[] args) throws IOException {

        try (VertexAI vertexAI = new VertexAI(PROJECT_ID, LOCATION)) {

            GenerativeModel model = new GenerativeModel(MODEL, vertexAI);
            ChatSession chatSession = new ChatSession(model);

            String jokeQuestion = "Tell me a joke about Java.";
            print(jokeQuestion);

            GenerateContentResponse response = chatSession.sendMessage(jokeQuestion);
            print(ResponseHandler.getText(response));
        }
    }
}

// Check more details here:
// https://medium.com/google-cloud/get-started-with-gemini-in-java-923f2069ea4d