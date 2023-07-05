package liu.my;

import java.util.HashMap;
import java.util.Map;

public class Response extends AbstractHttpServletResponse {
    private String message = "OK";
    private int status = 200;

    private Map<String, String> headers=new HashMap<String, String>();

    public Response() {
    }

    public Response(String message, int status, Map<String, String> headers) {
        this.message = message;
        this.status = status;
        this.headers = headers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
