import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

import java.util.Random;

public class HashtagRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:hashTagStormQueue")
                .to("activemq:hashtagMongoQueue");

        int num = new Random().nextInt(10000);
        from("activemq:hashtagServiceQueue")
                .setHeader("JMSMessageID", constant("ID : " + num))
                .setHeader("JMSReplyTo", constant("hashtagServiceQueue"))
                .setExchangePattern(ExchangePattern.InOut)
                .process(new MongoProcessor())
        ;
    }
}
