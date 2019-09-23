package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.Business.ClienteBusiness;
import rest.five.bank.InternetBanking.controller.Form.ClienteForm;
import rest.five.bank.InternetBanking.controller.dto.ClienteDTO;
import rest.five.bank.InternetBanking.model.Cliente;

import java.util.List;

/*import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;*/

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteBusiness clBusiness;

    //==================================================================
    // Adicionar nova conta
    //==================================================================
    @PostMapping("/clienteAdd")
    public ResponseEntity<ClienteDTO> addLogin(@RequestBody ClienteForm clForm) {
        return ResponseEntity.ok(clBusiness.save(clForm));
    }

    //==================================================================
    // Verifica conta Existente
    //==================================================================
    @PostMapping("/verificaCliente")
    public boolean verificaLogin(@RequestBody ClienteForm clForm) {
        return clBusiness.verifLogin(clForm);
    }

    //==================================================================
    // Busca todos os clientes existentes
    //==================================================================
    @GetMapping("/buscarTodos")
    public List<Cliente> buscarClientes() {
        return clBusiness.buscarClientes();
    }

    //==================================================================
    // Buscar cliente para login
    //==================================================================

    @GetMapping("/buscarCpf")
    public ClienteDTO buscarCpf(@RequestParam String cpf) {
        return clBusiness.findCPF(cpf);
    }

    @GetMapping("/fixedCliente")
    public ClienteDTO getFixed() {
        return new ClienteDTO();
    }

    //==================================================================
    // Verifica Token
    //==================================================================
/*    @GetMapping("/logado")
    public Principal logado(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }*/
}
