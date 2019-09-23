package rest.five.bank.InternetBanking.controller.Form;

import rest.five.bank.InternetBanking.model.Cliente;

public class ClienteForm {
    private String nomeCliente;
    private String cpfCliente;
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
