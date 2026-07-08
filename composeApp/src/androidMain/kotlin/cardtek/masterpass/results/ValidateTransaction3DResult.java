package cardtek.masterpass.results;

import java.io.Serializable;

public class ValidateTransaction3DResult implements Serializable {
    private String token;
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
