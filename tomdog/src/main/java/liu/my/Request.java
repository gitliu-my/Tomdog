package liu.my;

public class Request extends AbstractHttpServletRequest {
    private String requestUrl;
    private String method;
    private String protocol;

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Request(String requestUrl, String method, String protocol) {
        this.requestUrl = requestUrl;
        this.method = method;
        this.protocol = protocol;
    }
}
