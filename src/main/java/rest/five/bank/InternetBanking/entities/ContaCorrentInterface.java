package rest.five.bank.InternetBanking.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.five.bank.InternetBanking.model.Conta;

public interface ContaCorrentInterface extends JpaRepository<Conta, Long> {

}
