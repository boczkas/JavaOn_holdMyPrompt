package java_on;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.preview.ChatSession;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import com.google.cloud.vertexai.generativeai.preview.ResponseHandler;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static java_on.utils.Printer.print;

/**
 * Ongoing discussion
 */
public class Discussion_5 {
    private static final String PROJECT_ID = "<Your Project ID>"; // check how to get one here: https://cloud.google.com/vertex-ai/docs/tutorials/tabular-bq-prediction/prerequisites
    private static final String LOCATION = "<Chosen location>"; // check how to chose one here: https://cloud.google.com/vertex-ai/docs/general/locations
    private static final String MODEL = "<Model to be used>"; // check how to set up it here: https://cloud.google.com/vertex-ai/docs/start/cloud-environment

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        try (VertexAI vertexAI = new VertexAI(PROJECT_ID, LOCATION)) {
            GenerateContentResponse response;

            GenerativeModel model = new GenerativeModel(MODEL, vertexAI);
            ChatSession chatSession = new ChatSession(model);

            print("Ask you first question to AI.");
            String question = scanner.nextLine();

            while (!Objects.equals(question, "q")) {

                response = chatSession.sendMessage(question);
                print(ResponseHandler.getText(response));

                question = scanner.nextLine();
            }
        }
    }
}


// Check more details here:
// https://medium.com/google-cloud/get-started-with-gemini-in-java-923f2069ea4d