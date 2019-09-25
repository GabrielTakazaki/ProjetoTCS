package rest.five.bank.InternetBanking.controller.Form;

import rest.five.bank.InternetBanking.config.validations.CpfValid.ValidacaoCPF;
import rest.five.bank.InternetBanking.model.Cliente;

import javax.validation.constraints.NotNull;

public class ClienteForm {
    @NotNull(message = "O nome não pode ser nulo")
    private String nomeCliente;
    @NotNull(message = "Cpf não pode estar vazio")
    @ValidacaoCPF(message = "O cpf informado é invalido")
    private String cpfCliente;
    @NotNull(message = "A senha não pode estar vazia")
    private String password;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nome) {
        this.nomeCliente = nome;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpf) {
        this.cpfCliente = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cliente formatar() {
        Cliente cl = new Cliente();
        cl.setCpfCliente(cpfCliente);
        cl.setNomeCliente(nomeCliente);
        cl.setPassword(password);
        return cl;
    }
}
