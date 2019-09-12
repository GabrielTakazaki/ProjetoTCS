package rest.five.bank.InternetBanking.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.five.bank.InternetBanking.model.Investimento;

public interface InvestimentoInterface extends JpaRepository<Investimento, Long> {
}
