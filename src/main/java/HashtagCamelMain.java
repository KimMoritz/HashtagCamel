import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class HashtagCamelMain {
    CamelContext camelContext;

    private HashtagCamelMain() throws Exception {
        camelContext = new DefaultCamelContext();
            camelContext.addComponent("activemq", ActiveMQComponent.activeMQComponent("tcp://localhost:61616"));
            camelContext.addRoutes(new HashtagRouteBuilder());
            camelContext.start();
    }

    public static void main(String[] args) throws Exception {
        HashtagCamelMain hashtagCamelMain = new HashtagCamelMain();
        System.out.println("Camel started!");
    }

}
