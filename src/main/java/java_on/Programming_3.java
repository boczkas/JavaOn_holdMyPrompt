package java_on;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.preview.ChatSession;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import com.google.cloud.vertexai.generativeai.preview.ResponseHandler;

import java.io.IOException;

import static java_on.utils.Printer.print;
import static java_on.utils.Printer.printSeparator;

/**
 * We can ask multiple questions
 */
public class Programming_3 {

    private static final String PROJECT_ID = "<Your Project ID>"; // check how to get one here: https://cloud.google.com/vertex-ai/docs/tutorials/tabular-bq-prediction/prerequisites
    private static final String LOCATION = "<Chosen location>"; // check how to chose one here: https://cloud.google.com/vertex-ai/docs/general/locations
    private static final String MODEL = "<Model to be used>"; // check how to set up it here: https://cloud.google.com/vertex-ai/docs/start/cloud-environment


    public static void main(String[] args) throws IOException {

        try (VertexAI vertexAI = new VertexAI(PROJECT_ID, LOCATION)) {
            GenerateContentResponse response;

            GenerativeModel model = new GenerativeModel(MODEL, vertexAI);
            ChatSession chatSession = new ChatSession(model);

            String hello = "Hello.";
            print(hello);
            response = chatSession.sendMessage(hello);
            print(ResponseHandler.getText(response));
            printSeparator();

            String languagesQuestion = "What are the most popular programming languages?";
            print(languagesQuestion);

            response = chatSession.sendMessage(languagesQuestion);
            print(ResponseHandler.getText(response));
            printSeparator();


            String bestQuestion = "Why the first one is the best?";
            print(bestQuestion);

            response = chatSession.sendMessage(bestQuestion);
            print(ResponseHandler.getText(response));
        }
    }
}
