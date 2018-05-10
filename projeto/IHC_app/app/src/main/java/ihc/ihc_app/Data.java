package ihc.ihc_app;

/**
 * Created by MSI on 10/05/2018.
 */

public class Data {

    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano){
        if (!check(dia,mes,ano)) throw new IllegalArgumentException("Data inválida!");
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public Data(String d){
        String data[] = d.split("-");
        int dia = Integer.parseInt(data[0]);
        int mes = Integer.parseInt(data[1]);
        int ano = Integer.parseInt(data[2]);
        if (!check(dia,mes,ano)) throw new IllegalArgumentException("Data inválida!");
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public static boolean check(int dia, int mes, int ano){
        //if(ano<1800)return false;
        if(ano<0)return false;
        if(mes>12 || mes <1)return false;
        if(dia<1 || dia>31)return false;
        if(mes==2 && dia>29)return false;
        if((mes==4 || mes==6 || mes==9 || mes==11)&& dia>30)return false;

        return true;
    }

    @Override
    public String toString() {
        return dia+"-"+mes+"-"+ano;
    }

    public int getDia(){
        return dia;
    }

    public int getMes(){
        return mes;
    }

    public int getAno(){
        return ano;
    }
}
