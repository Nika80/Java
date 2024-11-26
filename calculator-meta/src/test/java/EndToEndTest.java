import org.junit.jupiter.api.*;
import org.junit.Rule;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.startupcheck.IsRunningStartupCheckStrategy;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

@Testcontainers
class EndToEndTest {
    static Network network = Network.newNetwork();

    @Container
    static GenericContainer<?> adderContainer = new GenericContainer(DockerImageName.parse("adder:latest"))
            .withExposedPorts(8080)
            .withNetwork(network)
            .withNetworkAliases("adder")
            .withStartupCheckStrategy(new IsRunningStartupCheckStrategy());

    @Container
    static GenericContainer<?> subtractorContainer = new GenericContainer(DockerImageName.parse("subtractor:latest"))
            .withExposedPorts(8080)
            .withNetwork(network)
            .withNetworkAliases("subtractor")
            .withStartupCheckStrategy(new IsRunningStartupCheckStrategy());

    @Container
    static GenericContainer<?> dividerContainer = new GenericContainer(DockerImageName.parse("divider:latest"))
            .withExposedPorts(8080)
            .withNetwork(network)
            .withNetworkAliases("divider")
            .withStartupCheckStrategy(new IsRunningStartupCheckStrategy());

    @Container
    static GenericContainer<?> multiplierContainer = new GenericContainer(DockerImageName.parse("multiplier:latest"))
            .withExposedPorts(8080)
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
        String response = nginxContainer.execInContainer("curl", "http://localhost:80/adder/1/1").getStdout();
        Assertions.assertEquals("2.0", response);
    }

    @Test
    public void testSubtract() throws IOException, InterruptedException {
        String response = nginxContainer.execInContainer("curl", "http://localhost:80/subtractor/2/1").getStdout();
        Assertions.assertEquals("1.0", response);
    }

    @Test
    public void testMultiply() throws IOException, InterruptedException {
        String response = nginxContainer.execInContainer("curl", "http://localhost:80/multiplier/2/2").getStdout();
        Assertions.assertEquals("4.0", response);
    }

    @Test
    public void testDivider() throws IOException, InterruptedException {
        String response = nginxContainer.execInContainer("curl", "http://localhost:80/divider/2/2").getStdout();
        Assertions.assertEquals("1.0", response);
    }
}