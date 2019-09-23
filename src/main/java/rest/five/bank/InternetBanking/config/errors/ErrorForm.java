package rest.five.bank.InternetBanking.config.errors;

public class ErrorForm {
    private String campo;
    private String erro;

    public ErrorForm(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public ErrorForm() {
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
