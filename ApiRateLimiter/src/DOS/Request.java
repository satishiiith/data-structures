package DOS;

public class Request {
    int id;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    String clientId ;

    public Request(int id, String clientId) {
        this.id = id;
        this.clientId = clientId;
    }
}
