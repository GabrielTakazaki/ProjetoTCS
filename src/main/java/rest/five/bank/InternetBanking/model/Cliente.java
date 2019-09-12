package rest.five.bank.InternetBanking.model;
import javax.persistence.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @Column
    private String nome_cliente;
    @Column
    private String sobrenome_cliente;
    @Column
    private Long cpf_cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Login login;

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getSobrenome_cliente() {
        return sobrenome_cliente;
    }

    public void setSobrenome_cliente(String sobrenome_cliente) {
        this.sobrenome_cliente = sobrenome_cliente;
    }

    public Long getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(Long cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
