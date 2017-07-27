import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.*;
import org.apache.camel.impl.DefaultCamelContext;

public class MongoProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class); //query that needs to be processed.

        CamelContext context = new DefaultCamelContext();
        context.start();
        ProducerTemplate producerTemplate = null;
        context.addComponent("activemq", ActiveMQComponent.activeMQComponent("tcp://localhost:61616"));
        producerTemplate = context.createProducerTemplate();

        Object o = producerTemplate.sendBody("activemq:mongoServiceQueue", ExchangePattern.InOut, body);

        exchange.getOut().setBody(o.toString());//"{\"xvals\":[0,5,10,15,20], \"yvals\":[1,2,7,10,30]}");
    }
}