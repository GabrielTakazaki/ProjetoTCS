package rest.five.bank.InternetBanking.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;

public interface CreditoEspecialInterface extends JpaRepository<CreditoEspecial, Long> {
    boolean existsByFkIdConta(Conta conta);
    CreditoEspecial findByFkIdConta(Conta conta);
}
