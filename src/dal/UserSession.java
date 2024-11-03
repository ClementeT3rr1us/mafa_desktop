
package dal;


public class UserSession {
    
    private static UserSession instance;
    
    private String username;
    private int id;
    private String tipo;
    
    private UserSession(){
        
    }
    
    public static UserSession getInstance(){
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void clearSssion(){
        instance = null;
    }
}
