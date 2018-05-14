package ihc.ihc_app.Models;

/**
 * Created by MSI on 14/05/2018.
 */

public class BilheteChild {

    private String carruagem;
    private String lugar;
    private String preco;

    public BilheteChild(String carruagem, String lugar, String preco) {
        this.carruagem = carruagem;
        this.lugar = lugar;
        this.preco = preco;
    }

    public String getCarruagem() {
        return carruagem;
    }

    public void setCarruagem(String carruagem) {
        this.carruagem = carruagem;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
