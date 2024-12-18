import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import model.Operation;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.startupcheck.IsRunningStartupCheckStrategy;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import java.io.IOException;

@Testcontainers
class CalculatorIntegrationTest {
    static Network network = Network.newNetwork();

    @Container
    static GenericContainer<?> adderContainer = new GenericContainer(DockerImageName.parse("adder:latest"))
            .withExposedPorts(8081)
            .withNetwork(network)
            .withNetworkAliases("adder")
            .withStartupCheckStrategy(new IsRunningStartupCheckStrategy());

    @Container
    static GenericContainer<?> subtractorContainer = new GenericContainer(DockerImageName.parse("subtractor:latest"))
            .withExposedPorts(8082)
            .withNetwork(network)
            .withNetworkAliases("subtractor")
            .withStartupCheckStrategy(new IsRunningStartupCheckStrategy());

    @Container
    static GenericContainer<?> dividerContainer = new GenericContainer(DockerImageName.parse("divider:latest"))
            .withExposedPorts(8083)
            .withNetwork(network)
            .withNetworkAliases("divider")
            .withStartupCheckStrategy(new IsRunningStartupCheckStrategy());

    @Container
    static GenericContainer<?> multiplierContainer = new GenericContainer(DockerImageName.parse("multiplier:latest"))
            .withExposedPorts(8084)
            .withNetwork(network)
            .withNetworkAliases("multiplier")
            .withStartupCheckStrategy(new IsRunningStartupCheckStrategy());

    @Container
    static GenericContainer<?> nginxContainer = new GenericContainer(DockerImageName.parse("nginx:latest"))
            .withExposedPorts(80)
            .dependsOn(adderContainer, subtractorContainer, dividerContainer, multiplierContainer)
            .withNetwork(network)
            .withCopyFileToContainer(MountableFile.forClasspathResource("nginx.conf"), "/etc/nginx/nginx.conf")
            .withCommand("nginx", "-g", "daemon off;")
            .withStartupCheckStrategy(new IsRunningStartupCheckStrategy())
            .waitingFor(Wait.forListeningPorts(80));


    @Test
    public void testAdd() throws IOException, InterruptedException {
        String response = nginxContainer.execInContainer("curl", "http://localhost:80/adder/calculate/1/1").getStdout();

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Double.class, new JacksonJsonDoubleSerializer());
        objectMapper.registerModule(module);
        Operation o = objectMapper.readValue(response, Operation.class);

        Assertions.assertEquals(1.0, o.firstArgument);
        Assertions.assertEquals(1.0, o.secondArgument);
        Assertions.assertEquals(2.0, o.result);
    }

    @Test
    public void testSubtract() throws IOException, InterruptedException {
        String response = nginxContainer.execInContainer("curl", "http://localhost:80/subtractor/calculate/2/1").getStdout();

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Double.class, new JacksonJsonDoubleSerializer());
        objectMapper.registerModule(module);
        Operation o = objectMapper.readValue(response, Operation.class);

        Assertions.assertEquals(2.0, o.firstArgument);
        Assertions.assertEquals(1.0, o.secondArgument);
        Assertions.assertEquals(1.0, o.result);
    }

    @Test
    public void testMultiply() throws IOException, InterruptedException {
        String response = nginxContainer.execInContainer("curl", "http://localhost:80/multiplier/calculate/2/2").getStdout();

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Double.class, new JacksonJsonDoubleSerializer());
        objectMapper.registerModule(module);
        Operation o = objectMapper.readValue(response, Operation.class);

        Assertions.assertEquals(2.0, o.firstArgument);
        Assertions.assertEquals(2.0, o.secondArgument);
        Assertions.assertEquals(4.0, o.result);
    }

    @Test
    public void testDivider() throws IOException, InterruptedException {
        String response = nginxContainer.execInContainer("curl", "http://localhost:80/divider/calculate/2/2").getStdout();

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Double.class, new JacksonJsonDoubleSerializer());
        objectMapper.registerModule(module);
        Operation o = objectMapper.readValue(response, Operation.class);

        Assertions.assertEquals(2.0, o.firstArgument);
        Assertions.assertEquals(2.0, o.secondArgument);
        Assertions.assertEquals(1.0, o.result);
    }
}