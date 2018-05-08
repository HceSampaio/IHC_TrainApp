package ihc.ihc_app.Models;

public class TitleChild {

    public String comboio;
    public String carruagem;
    public String lugar;
    public int before;


    public TitleChild(String comboio, String carruagem, String lugar, int before) {
        this.comboio = comboio;
        this.carruagem = carruagem;
        this.lugar = lugar;
        this.before = before;
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

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setBefore(int before) {
        this.before = before;
    }

    public int getBefore() {
        return before;
    }
}