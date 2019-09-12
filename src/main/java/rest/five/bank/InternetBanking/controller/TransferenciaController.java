package rest.five.bank.InternetBanking.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferencia")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TransferenciaController {
}
