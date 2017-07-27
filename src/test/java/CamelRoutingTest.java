import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class CamelRoutingTest extends CamelTestSupport{

    @EndpointInject(uri = "mock:queue2")
    protected MockEndpoint resultEndpoint;

    @Override
    protected RouteBuilder createRouteBuilder(){
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start").to("mock:queue2");
            }
        };
    }

    @Test
    public void moveFile() throws Exception{
        String expectedBody = "{\"key\":\"test\", \"value\":1}";
        resultEndpoint.expectedBodiesReceived(expectedBody);
        template.sendBody("direct:start",expectedBody);
        resultEndpoint.assertIsSatisfied();
    }
}
