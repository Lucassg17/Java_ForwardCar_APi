package MetodosTestes;

public class DadosParaCriarLogin {
    private String userName;
    private String password;

    public DadosParaCriarLogin(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }
}
