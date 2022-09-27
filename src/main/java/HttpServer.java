import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    private final ServerSocket serverSocket;

private HttpServer(int port) throws IOException {
    serverSocket= new ServerSocket(port);

}
    public static void main(String[] args) throws IOException {
     new HttpServer(9080).start();

    }

  private void start(){
     new Thread(() -> {
         try {
          var clientSockets =   serverSocket.accept();
          handleReguestSockets(clientSockets);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }).start();

    }

    private void handleReguestSockets(Socket clientSockets) throws IOException {
    var res = "<h1>hallæ verden</h1>";
    clientSockets.getOutputStream().write(("HTTP/1.1 200 ok\r\n" + "Content-Length:" + res.length() +"\r\r" +
            "Content-type:text/plain: charset=utf-8\r\n" +
            "\r\n" + res).getBytes(StandardCharsets.UTF_8));
    }

}
