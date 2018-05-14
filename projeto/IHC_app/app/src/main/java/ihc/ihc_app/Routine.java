package ihc.ihc_app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 10/05/2018.
 */

public class Routine {


    private String comboio;
    private String cidade_partida;
    private String cidade_chegada;
    private String hora_partida;
    private String hora_chegada;
    private List<String> repetir = new ArrayList<>();//dias da semana que repete
    private String data_fim;
    private String antecedencia_num;
    private String antecedencia_tempo;

    public boolean isComplete(){
        Routine r = this;
        return r.data_fim!=null && r.comboio!= null && r.cidade_chegada!=null && r.cidade_partida!=null && r.hora_chegada!=null && r.hora_partida!=null && repetir!=null && antecedencia_num!=null && antecedencia_tempo!=null;
    }

    public String getComboio() {
        return comboio;
    }

    public void setComboio(String comboio) {
        this.comboio = comboio;
    }

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

    public List<String> getRepetir() {
        return repetir;
    }

    public String getRepetirAsString() {
        String s = "";
        for(int i=0;i<repetir.size();i++){
            s+=repetir.get(i)+",";
        }
        return s.substring(0,s.length()-2);
    }

    public void addRepetir(String repetir) {
        this.repetir.add(repetir);
    }

    public void removeRepetir(String repetir) {
        this.repetir.remove(repetir);
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    public String getAntecedencia_num() {
        return antecedencia_num;
    }

    public void setAntecedencia_num(String antecedencia) {
        this.antecedencia_num = antecedencia;
    }

    public String getAntecedencia_tempo() {
        return antecedencia_tempo;
    }

    public void setAntecedencia_tempo(String antecedencia) {
        this.antecedencia_tempo = antecedencia;
    }
}
