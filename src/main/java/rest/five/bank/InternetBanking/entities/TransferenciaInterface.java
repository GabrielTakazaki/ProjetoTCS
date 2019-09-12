package rest.five.bank.InternetBanking.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.five.bank.InternetBanking.model.Transferencia;

public interface TransferenciaInterface extends JpaRepository<Transferencia, Long> {
}
