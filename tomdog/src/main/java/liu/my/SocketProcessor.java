package liu.my;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class SocketProcessor implements Runnable{
    private Socket socket;
    public SocketProcessor() {
    }
    public SocketProcessor(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            System.out.println("处理连接");
            processSocket();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket() {
        try {
            // 处理Socket  读数据  写数据

            //此处设定每次读取1kb的数据
            byte[] bytes = new byte[1024];
            InputStream inputStream = this.socket.getInputStream();
            int read = inputStream.read(bytes);

            //解析数据
            Request request = parse(bytes);
            Response response = new Response();
            //先假设匹配到是这个servlet
            LiumyServlet servlet = new LiumyServlet();
            servlet.doGet(request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Request parse(byte[] bytes) {
        /* http协议
         请求行：  请求方法 空格 url 空格 协议版本 回车符 换行符
         请求头：  头部字段名  冒号  值   回车符 换行符
                  回车符 换行符
         请求体：  请求数据
        */
        //寻找第一个空格之前的位置
        int pos = 0;
        for (byte aByte : bytes) {
            if(aByte == ' '){
                break;
            }
            pos++;
        }
        //获取方法
        StringBuilder method = new StringBuilder();
        for (int i = 0; i < pos; i++) {
            method.append((char)bytes[i]);
        }
        pos+=2;
        //获取url
        StringBuilder url = new StringBuilder();
        while (pos<bytes.length&&bytes[pos]!=' '){
            url.append((char)bytes[pos++]);
        }
        pos++;
        //获取协议版本
        StringBuilder protocol = new StringBuilder();
        while (pos<bytes.length&&bytes[pos]!='\r'){
            protocol.append((char)bytes[pos++]);
        }
        //后面先不解析了
        return new Request(url.toString(),method.toString(),protocol.toString());
    }
}
