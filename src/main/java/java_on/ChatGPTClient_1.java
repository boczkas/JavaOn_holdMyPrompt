package java_on;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPTClient_1 {
    private static final String URL = "https://api.openai.com/v1/chat/completions";
    private static final String MODEL = "gpt-3.5-turbo"; // model to be used
    private static final String API_KEY = "<Your API Key>"; // check how to get one here: https://www.merge.dev/blog/chatgpt-api-key

    public static void main(String[] args) throws Exception {
        URL obj = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");


        String joke = "Tell me a joke about Java";

        // create and send message to the model
        String body = "{\"model\": \"" + MODEL + "\", \"messages\": [{\"role\": \"user\", " + "\"content\": \"" + joke + "\"}]}";
        connection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(body);
        writer.flush();
        writer.close();

        // read the response
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;

        StringBuilder response = new StringBuilder();

        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        // print message
        System.out.println(response);

    }
}

// Inspired by:
// https://rollbar.com/blog/how-to-use-chatgpt-api-with-java/