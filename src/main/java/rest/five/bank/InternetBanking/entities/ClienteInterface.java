package rest.five.bank.InternetBanking.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.five.bank.InternetBanking.model.Cliente;

public interface ClienteInterface extends JpaRepository<Cliente, Long> {
    Cliente findByCpfCliente(String Cpf);
}
