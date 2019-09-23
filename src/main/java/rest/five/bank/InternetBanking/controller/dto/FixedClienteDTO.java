package rest.five.bank.InternetBanking.controller.dto;

import rest.five.bank.InternetBanking.controller.Form.ClienteForm;
import rest.five.bank.InternetBanking.model.Cliente;

public class FixedClienteDTO {
    static Long idCliente;
    static String nomeCliente;
    static String cpfCliente;
    static String password;

    public FixedClienteDTO() {
    }

    public FixedClienteDTO(Cliente cl) {
        idCliente = cl.getIdCliente();
        nomeCliente = cl.getNomeCliente();
        cpfCliente = cl.getCpfCliente();
        password = cl.getPassword();
    }

    public FixedClienteDTO(ClienteForm clForm) {
        nomeCliente = clForm.getNomeCliente();
        cpfCliente = clForm.getCpfCliente();
        password = clForm.getPassword();
    }

    public static Long getIdCliente() {
        return idCliente;
    }

    public static void setIdCliente(Long idCl) {
        idCliente = idCl;
    }

    public static String getNomeCliente() {
        return nomeCliente;
    }

    public static void setNomeCliente(String nome) {
        nomeCliente = nome;
    }

    public static String getCpfCliente() {
        return cpfCliente;
    }

    public static void setCpfCliente(String cpf) {
        cpfCliente = cpf;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String passwd) {
        password = passwd;
    }
}
