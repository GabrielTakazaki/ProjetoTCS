package rest.five.bank.InternetBanking.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.five.bank.InternetBanking.model.Cliente;

public interface LoginInterface extends JpaRepository<Cliente, Long> {
}
