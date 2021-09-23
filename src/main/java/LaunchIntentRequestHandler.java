import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.interfaces.alexa.presentation.apl.RenderDocumentDirective;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchIntentRequestHandler implements RequestHandler
{
    public boolean canHandle(HandlerInput input)
    {
        return input.matches(requestType(LaunchRequest.class));
    }

    public Optional<Response> handle(HandlerInput input)
    {
        String roundTitle = "Welcome!"; // this is for echo spot

        String title = "Welcome!"; // this is for echo show medium,large, extra-large

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<HashMap<String,Object>> typeReference = new TypeReference<HashMap<String, Object>>(){};

        try
        {
            Map<String,Object> document = objectMapper.readValue(new File("welcome.json"),typeReference);

            // this data source supplies the data to document to show the message

            Map<String,Object> dataSource = objectMapper.readValue(new File("welcomeTemplate.json"),typeReference);

            RenderDocumentDirective documentDirective = RenderDocumentDirective.builder().withToken("welcomeToken")
                    .withDocument(document)
                    .withDatasources(dataSource).build();

            return input.getResponseBuilder().withSpeech("Hi welcome to simple document. This is apl document demo.")
                    .withReprompt("Hi welcome to simple document. This is apl document demo.")
                    .addDirective(documentDirective)
                    .build();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
