import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class HashtagCamelMain {
    CamelContext camelContext;

    private HashtagCamelMain(){
        camelContext = new DefaultCamelContext();
        try {
            camelContext.addComponent("activemq", ActiveMQComponent.activeMQComponent("tcp://localhost:61616"));
            camelContext.addRoutes(new HashtagRouteBuilder());
            camelContext.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HashtagCamelMain hashtagCamelMain = new HashtagCamelMain();
        System.out.println("Camel started!");
    }

}
