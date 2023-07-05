package liu.my;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tomdog {
    public void start(){
        //接受Socket连接 TCP
        try {
            //创建线程池
            ExecutorService executorService = Executors.newFixedThreadPool(20);
            ServerSocket serverSocket = new ServerSocket(8081);
            while (true){
                Socket socket = serverSocket.accept();
                SocketProcessor socketProcessor = new SocketProcessor(socket);
                executorService.execute(socketProcessor);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        Tomdog tomdog = new Tomdog();
        tomdog.start();
    }
}
