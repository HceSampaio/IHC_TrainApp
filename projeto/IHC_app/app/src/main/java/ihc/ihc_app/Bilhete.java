package ihc.ihc_app;

/**
 * Created by MSI on 10/05/2018.
 */

public class Bilhete {

    private String comboio;
    private int lugar, carruagem;
    private int linha;
    private String estacao_chegada;
    private String estacao_partida;
    private String hora_chegada;
    private String hora_partida;
    private Data data;
    private double preco;

    public Bilhete(String comboio, String carruagem, String lugar, String estacao_chegada, String estacao_partida, String hora_chegada, String hora_partida, Data d, double preco) {
        this.comboio = comboio;
        this.estacao_chegada = estacao_chegada;
        this.estacao_partida = estacao_partida;
        this.hora_chegada = hora_chegada;
        this.hora_partida = hora_partida;
        this.data = d;
        if (lugar.contains(" ")){
            this.lugar = Integer.parseInt(lugar.split(" ")[1]);
        }else{
            this.lugar = Integer.parseInt(lugar);
        }

        if(carruagem.contains(" ")){
            this.carruagem = Integer.parseInt(carruagem.split(" ")[1]);
        }else{
            this.carruagem = Integer.parseInt(carruagem);
        }
        this.preco = preco;
    }

    public String getComboio() {
        return comboio;
    }

    public void setComboio(String comboio) {
        this.comboio = comboio;
    }

    public int getCarruagem() {
        return carruagem;
    }

    public void setCarruagem(int carruagem) {
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

    public int getLugar(){
        return lugar;
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
