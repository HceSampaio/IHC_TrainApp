package ihc.ihc_app;

/**
 * Created by MSI on 10/05/2018.
 */

public class Routine {

    private String cidade_partida;
    private String cidade_chegada;
    private String hora_partida;
    private String hora_chegada;
    private String[] repetir;//dias da semana que repete
    private String data_fim;
    private int antecedencia;

    public String getCidade_partida() {
        return cidade_partida;
    }

    public void setCidade_partida(String cidade_partida) {
        this.cidade_partida = cidade_partida;
    }

    public String getCidade_chegada() {
        return cidade_chegada;
    }

    public void setCidade_chegada(String cidade_chegada) {
        this.cidade_chegada = cidade_chegada;
    }

    public String getHora_partida() {
        return hora_partida;
    }

    public void setHora_partida(String hora_partida) {
        this.hora_partida = hora_partida;
    }

    public String getHora_chegada() {
        return hora_chegada;
    }

    public void setHora_chegada(String hora_chegada) {
        this.hora_chegada = hora_chegada;
    }

    public String[] getRepetir() {
        return repetir;
    }

    public void setRepetir(String[] repetir) {
        this.repetir = repetir;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    public int getAntecedencia() {
        return antecedencia;
    }

    public void setAntecedencia(int antecedencia) {
        this.antecedencia = antecedencia;
    }
}
