package ihc.ihc_app.Models;

public class TitleChild {

    public String comboio;

    public String getBefore_num() {
        return before_num;
    }

    public void setBefore_num(String before_num) {
        this.before_num = before_num;
    }

    public String getBefore_tempo() {
        return before_tempo;
    }

    public void setBefore_tempo(String before_tempo) {
        this.before_tempo = before_tempo;
    }

    public String before_num;
    public String before_tempo;


    public TitleChild(String comboio, String before_num, String before_tempo) {
        this.comboio = comboio;
        this.before_num = before_num;
        this.before_tempo = before_tempo;
    }

    public String getComboio() {
        return comboio;
    }

    public void setComboio(String comboio) {
        this.comboio = comboio;
    }


}