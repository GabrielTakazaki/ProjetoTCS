package rest.five.bank.InternetBanking.model;

import javax.persistence.*;

@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_login;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private boolean status;


    //==================================================================
    // Getters e Setters
    //==================================================================
    public Long getId_login() {
        return id_login;
    }
    public void setId_login(Long id_login) {
        this.id_login = id_login;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean validaSenha() {
        return getPassword().length() >= 6;
    }
}
