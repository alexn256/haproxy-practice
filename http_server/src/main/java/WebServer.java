import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class WebServer {

    private final String name;
    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        String serverName = "default";
        int serverPort = 8080;
        if (args.length == 2) {
            serverPort = Integer.parseInt(args[0]);
            serverName = args[1];
        }
        WebServer webServer = new WebServer(serverName, serverPort);
        webServer.startServer();
        System.out.println("Server \"" + serverName + "\" is listening on port " + serverPort);
    }

    public WebServer(String name, int port) {
        this.name = name;
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        HttpContext helloContext = server.createContext("/");
        helloContext.setHandler(this::handleHelloWorldRequest);
        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void handleHelloWorldRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }
        String responseMessage = String.format("Response from: %s", name);
        sendResponse(responseMessage.getBytes(), exchange);
    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
}
