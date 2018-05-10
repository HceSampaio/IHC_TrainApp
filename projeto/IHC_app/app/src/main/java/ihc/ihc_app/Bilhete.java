package ihc.ihc_app;

/**
 * Created by MSI on 10/05/2018.
 */

public class Bilhete {

    private String comboio;
    private String carruagem;
    private int linha;
    private String estacao_chegada;
    private String estacao_partida;
    private String hora_chegada;
    private String hora_partida;
    private double preco;

    public String getComboio() {
        return comboio;
    }

    public void setComboio(String comboio) {
        this.comboio = comboio;
    }

    public String getCarruagem() {
        return carruagem;
    }

    public void setCarruagem(String carruagem) {
        this.carruagem = carruagem;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public String getEstacao_chegada() {
        return estacao_chegada;
    }

    public void setEstacao_chegada(String estacao_chegada) {
        this.estacao_chegada = estacao_chegada;
    }

    public String getEstacao_partida() {
        return estacao_partida;
    }

    public void setEstacao_partida(String estacao_partida) {
        this.estacao_partida = estacao_partida;
    }

    public String getHora_chegada() {
        return hora_chegada;
    }

    public void setHora_chegada(String hora_chegada) {
        this.hora_chegada = hora_chegada;
    }

    public String getHora_partida() {
        return hora_partida;
    }

    public void setHora_partida(String hora_partida) {
        this.hora_partida = hora_partida;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
