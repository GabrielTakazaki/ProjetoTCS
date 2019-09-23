package rest.five.bank.InternetBanking.controller.dto;

import rest.five.bank.InternetBanking.controller.Form.ClienteForm;
import rest.five.bank.InternetBanking.model.Cliente;

public class ClienteDTO {
    private Long idCliente;
    private String nomeCliente;
    private String cpfCliente;
    private String password;

    public ClienteDTO(Cliente cl) {
        idCliente = cl.getIdCliente();
        nomeCliente = cl.getNomeCliente();
        cpfCliente = cl.getCpfCliente();
        password = cl.getPassword();
    }

    public ClienteDTO(ClienteForm clForm) {
        nomeCliente = clForm.getNomeCliente();
        cpfCliente = clForm.getCpfCliente();
        password = clForm.getPassword();
    }

    public ClienteDTO() {
        idCliente = FixedClienteDTO.getIdCliente();
        nomeCliente = FixedClienteDTO.getNomeCliente();
        cpfCliente = FixedClienteDTO.getCpfCliente();
        password = FixedClienteDTO.getPassword();
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

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
}
