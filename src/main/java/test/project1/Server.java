package test.project1;

import io.vertx.core.*;
import io.vertx.core.http.*;
import io.vertx.core.json.Json;
import io.vertx.ext.web.*;

import java.util.TreeMap;

public class Server extends AbstractVerticle {

	private Router router;

    // TreeMap provides a nice way to implement the required specification.
    // put, lowerEntry and higherEntry and all O(log(n)) operations, where n = number of entries in the map.
    // So this is very efficient even at large scale.
    private TreeMap<String, String> lexicalMap = new TreeMap<>();
    private TreeMap<Integer, String> valueMap = new TreeMap<>();

	@Override
	public void start(Future<Void> fut) throws Exception {
		router = Router.router(vertx);
        // we listen to post request to /analyze url
		router.post("/analyze").handler(routingContext -> {
            // decode the json input as an ApiRequest
            final ApiRequest request = Json.decodeValue(routingContext.getBodyAsString(),
                    ApiRequest.class);

            // get the actual word
            String word = request.getWord();

            // Comparable<String> compares by lexicographical order so we can use the word itself as the key.
            String lexical = lexicalMap.lowerEntry(word).getValue();
            if(lexical == null)
                lexical = lexicalMap.higherEntry(word).getValue();

            // we use value(word) as the key in valueMap
            String value = valueMap.lowerEntry(value(word)).getValue();
            if(value == null)
                value = valueMap.higherEntry(value(word)).getValue();

            lexicalMap.put(word,word);
            valueMap.put(value(word),word);

            routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(new ApiResult(value,lexical)));
		});

		vertx.createHttpServer().requestHandler(router::accept)
			.listen(
				config().getInteger("http.port", 8080),
				result -> {
					if (result.succeeded()) {
						fut.complete();
					} else {
						fut.fail(result.cause());
					}
				});
	}

    private Integer value(String word) {
        Integer total = 0;
        for(int i = 0 ; i < word.length(); i++)
            total += word.charAt(i) - 'a' + 1;
        return total;
    }
}
