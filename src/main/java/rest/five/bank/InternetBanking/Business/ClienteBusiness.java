package rest.five.bank.InternetBanking.Business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.five.bank.InternetBanking.controller.Form.ClienteForm;
import rest.five.bank.InternetBanking.controller.dto.ClienteDTO;
import rest.five.bank.InternetBanking.controller.dto.FixedClienteDTO;
import rest.five.bank.InternetBanking.entities.ClienteInterface;
import rest.five.bank.InternetBanking.model.Cliente;

import java.util.List;

@Service
public class ClienteBusiness {

    @Autowired
    private ClienteInterface clInterface;

    public ClienteDTO save(ClienteForm clForm) {
        Cliente cl = clForm.formatar();
        clInterface.save(cl);
        return new ClienteDTO(cl);
    }

    public boolean verifLogin(ClienteForm clForm) {
        List<Cliente> listUsers = buscarClientes();
        for (Cliente user : listUsers) {
            if (clForm.getCpfCliente().equals(user.getCpfCliente())) {
                return clForm.getPassword().equals(user.getPassword());
            }
        }
        return false;
    }

    public List<Cliente> buscarClientes() {
        return clInterface.findAll();
    }

    public ClienteDTO findCPF(String cpf) {
        new FixedClienteDTO(clInterface.findByCpfCliente(cpf));
        return new ClienteDTO(clInterface.findByCpfCliente(cpf));
    }
}
