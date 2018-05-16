package ihc.ihc_app;

/**
 * Created by MSI on 10/05/2018.
 */

public class Bilhete {

    private String comboio;
    private String carruagem;
    private int lugar;
    private int linha;
    private String estacao_chegada;
    private String estacao_partida;
    private String hora_chegada;
    private String hora_partida;
    private Data data;
    private double preco;

    public Bilhete(String comboio, String carruagem, String estacao_chegada, String estacao_partida, String hora_chegada, String hora_partida, Data d, double preco) {
        this.comboio = comboio;
        this.carruagem = carruagem;
        this.estacao_chegada = estacao_chegada;
        this.estacao_partida = estacao_partida;
        this.hora_chegada = hora_chegada;
        this.hora_partida = hora_partida;
        this.preco = preco;
        this.data = d;
    }

    public Bilhete(String comboio, String carruagem, String hora_chegada, String hora_partida, Data data, double preco, String lugar) {
        this.comboio = comboio;
        this.carruagem = carruagem;
        this.linha = linha;
        this.hora_chegada = hora_chegada;
        this.hora_partida = hora_partida;
        this.data = data;
        this.preco = preco;
        this.lugar = Integer.parseInt(lugar);
    }

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

    @Override
    public String toString() {
        return "Bilhete{" +
                "comboio='" + comboio + '\'' +
                ", carruagem='" + carruagem + '\'' +
                ", lugar=" + lugar +
                ", linha=" + linha +
                ", estacao_chegada='" + estacao_chegada + '\'' +
                ", estacao_partida='" + estacao_partida + '\'' +
                ", hora_chegada='" + hora_chegada + '\'' +
                ", hora_partida='" + hora_partida + '\'' +
                ", data=" + data +
                ", preco=" + preco +
                '}';
    }
}
