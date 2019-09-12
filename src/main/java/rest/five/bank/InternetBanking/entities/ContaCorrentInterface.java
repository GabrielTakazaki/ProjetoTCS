package rest.five.bank.InternetBanking.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.five.bank.InternetBanking.model.ContaCorrente;

public interface ContaCorrentInterface extends JpaRepository<ContaCorrente, Long> {

}
