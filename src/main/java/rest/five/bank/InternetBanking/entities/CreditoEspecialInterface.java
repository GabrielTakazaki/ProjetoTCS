package rest.five.bank.InternetBanking.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.five.bank.InternetBanking.model.CreditoEspecial;

public interface CreditoEspecialInterface extends JpaRepository<CreditoEspecial, Long> {
}
