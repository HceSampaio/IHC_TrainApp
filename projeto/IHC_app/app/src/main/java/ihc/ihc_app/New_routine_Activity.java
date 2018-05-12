package ihc.ihc_app;


import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

import ihc.ihc_app.MyAdapters.MyArrayAdapter;

public class New_routine_Activity extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener{

    Routine routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_routine_);
        //creating new routing
        routine = new Routine();

        List<String> cidades_partida = new ArrayList<>();
        cidades_partida.add("PARTIDA");
        cidades_partida.add("Aveiro");
        cidades_partida.add("Porto");
        cidades_partida.add("Coimbra");
        cidades_partida.add("Lisboa");
        cidades_partida.add("Santarem");
        cidades_partida.add("Faro");

        final List<String> cidades_chegada = new ArrayList<>();
        cidades_chegada.add("CHEGADA");
        cidades_chegada.add("Aveiro");
        cidades_chegada.add("Porto");
        cidades_chegada.add("Coimbra");
        cidades_chegada.add("Lisboa");
        cidades_chegada.add("Santarem");
        cidades_chegada.add("Faro");

        final MyArrayAdapter<String> adapter_partida = new MyArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<>(cidades_partida),new ArrayList<>(cidades_partida));
        final MyArrayAdapter<String> adapter_chegada = new MyArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<>(cidades_chegada),new ArrayList<>(cidades_chegada));

        //ESTACAO PARTIDA/CHEGADA Spinners
        Spinner dynamicSpinner = (Spinner) findViewById(R.id.cidade_partida);
        //String[] cidades_partida_array = new String[] {"Aveiro","Porto","Lisboa","Ovar","Coimbra" };


        dynamicSpinner.setAdapter(adapter_partida);
        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    String sel = (String)parent.getItemAtPosition(position);
                    routine.setCidade_partida((String)parent.getItemAtPosition(position));
                    adapter_chegada.hide((String)parent.getItemAtPosition(position));
                    Spinner tv = (Spinner) findViewById(R.id.cidade_partida);
                    int spinnerPosition = adapter_partida.getPosition(sel);
                    Log.d("My TAG",String.format("Partida %d",spinnerPosition) );
                    tv.setSelection(spinnerPosition,true);

                    Spinner tv2 = (Spinner) findViewById(R.id.cidade_chegada);
                    String chegada = routine.getCidade_chegada();
                    int spinnerPosition2 = adapter_partida.getPosition(chegada);
                    Log.d("My TAG",String.format("chegada %d ",spinnerPosition2) );
                    tv.setSelection(spinnerPosition2,true);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        Spinner spinner_chegada = (Spinner) findViewById(R.id.cidade_chegada);
        //String[] cidades_chegada = new String[] {"Aveiro","Porto","Lisboa","Ovar","Coimbra" };

        spinner_chegada.setAdapter(adapter_chegada);
        spinner_chegada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    String sel = (String)parent.getItemAtPosition(position);
                    routine.setCidade_chegada((String)parent.getItemAtPosition(position));
                    adapter_partida.hide((String)parent.getItemAtPosition(position));

                    Spinner tv = (Spinner) findViewById(R.id.cidade_chegada);
                    int spinnerPosition = adapter_chegada.getPosition(sel);
                    tv.setSelection(spinnerPosition,true);

                    Spinner tv2 = (Spinner) findViewById(R.id.cidade_partida);
                    String partida = routine.getCidade_partida();
                    int spinnerPosition2 = adapter_partida.getPosition(partida);
                    tv2.setSelection(spinnerPosition2,true);


                    /*
                    routine.setCidade_chegada((String)parent.getItemAtPosition(position));
                    adapter_partida.hide((String)parent.getItemAtPosition(position));
                    //Toast.makeText(New_routine_Activity.this, new_index, Toast.LENGTH_LONG).show();
                    Spinner tv = (Spinner) findViewById(R.id.cidade_chegada);
                    tv.setSelection(position);*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        //SPINNER compra
        List<String> unidades_tempo = new ArrayList<>();
        unidades_tempo.add("Horas");
        unidades_tempo.add("Dias");

        final List<String> num_horas = new ArrayList<>();
        for (int i=1; i<24;i++){
            num_horas.add(String.format("%d",i));
        }

        final List<String> num_dias = new ArrayList<>();
        for (int i=1; i<8;i++){
            num_dias.add(String.format("%d",i));
        }

        Spinner spinner_compra1 = (Spinner) findViewById(R.id.compra_num);
        final ArrayAdapter<String> adapter_compra1 = new ArrayAdapter<String>(this, R.layout.my_spinner_layout, new ArrayList<>(num_horas));
        spinner_compra1.setAdapter(adapter_compra1);
        spinner_compra1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                routine.setAntecedencia_num((String) parent.getItemAtPosition(position));
                Log.v("item", (String) parent.getItemAtPosition(position));
                //Toast.makeText(New_routine_Activity.this,(String) parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                Spinner tv = (Spinner) findViewById(R.id.compra_num);
                tv.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }

        });

        Spinner spinner_compra2 = (Spinner) findViewById(R.id.compra_tempo);

        ArrayAdapter<String> adapter_compra2 = new ArrayAdapter<String>(this, R.layout.my_spinner_layout, new ArrayList<>(unidades_tempo));
        spinner_compra2.setAdapter(adapter_compra2);
        spinner_compra2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolean horas;
                if((String) parent.getItemAtPosition(position)== "Horas"){
                    horas = true;
                }else{
                    horas = false;
                }
                routine.setAntecedencia_tempo((String) parent.getItemAtPosition(position));
                adapter_compra1.clear();
                if(horas) {
                    adapter_compra1.addAll(new ArrayList<String>(num_horas));
                }else{
                    adapter_compra1.addAll(new ArrayList<String>(num_dias));
                }
                Log.v("item", (String) parent.getItemAtPosition(position));
                //Toast.makeText(New_routine_Activity.this,(String) parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                Spinner tv = (Spinner) findViewById(R.id.compra_tempo);
                tv.setSelection(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //DATA FIM cardView clique event = calendar
        CardView cv = (CardView)findViewById(R.id.fim_date_card);
        cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePD = DatePickerDialog.newInstance(New_routine_Activity.this, now.get(Calendar.YEAR),now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                datePD.setTitle("Escolher Data Fim da Rotina");
                datePD.show(getFragmentManager(),"Data Fim");

            }
        });

        //COIBOIO E HORARIO

        List<String> lista_comboios = new ArrayList<>();
        lista_comboios.add("AP123");
        lista_comboios.add("IC859");

        final List<String> horas_partida1 = new ArrayList<>();
        horas_partida1.add("10:30 - 12:38");
        horas_partida1.add("12:30 - 15:38");
        horas_partida1.add("16:30 - 19:38");
        horas_partida1.add("22:30 - 01:38");

        final List<String> horas_partida2 = new ArrayList<>();
        horas_partida2.add("10:55 - 12:20");
        horas_partida2.add("12:55 - 14:20");
        horas_partida2.add("16:55 - 18:20");
        horas_partida2.add("22:55 - 23:20");

        List<String> default_hora = new ArrayList<>();
        default_hora.add("HORÁRIO");

        final ArrayAdapter<String> adapter_hora = new ArrayAdapter<String>(this, R.layout.my_spinner_layout, new ArrayList<>(default_hora));
        final ArrayAdapter<String> adapter_comboio = new ArrayAdapter<String>(this,R.layout.my_spinner_layout, new ArrayList<>(lista_comboios));

        Spinner spinner_comboio = (Spinner) findViewById(R.id.cod_comboio);
        spinner_comboio.setAdapter(adapter_comboio);
        spinner_comboio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String comboio_chosen;
                if((String) parent.getItemAtPosition(position) != null){
                    comboio_chosen = (String) parent.getItemAtPosition(position);
                }else{
                    comboio_chosen = (String) parent.getItemAtPosition(position);
                }
                routine.setComboio((String) parent.getItemAtPosition(position));

                adapter_hora.clear();

                if(comboio_chosen == "AP123") {
                    adapter_hora.addAll(new ArrayList<String>(horas_partida1));
                }else{
                    adapter_hora.addAll(new ArrayList<String>(horas_partida2));
                }
                Log.v("item", (String) parent.getItemAtPosition(position));
                //Toast.makeText(New_routine_Activity.this,(String) parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                Spinner tv = (Spinner) findViewById(R.id.cod_comboio);
                tv.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }

        });


        Spinner spinner_hora = (Spinner) findViewById(R.id.hora_comboio);
        spinner_hora.setAdapter(adapter_hora);
        spinner_hora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String horas = (String)parent.getItemAtPosition(position);
                if (!horas.equals("default_hora")) {
                    String[] h = horas.split(" - ");
                    routine.setHora_partida(h[0]);
                    routine.setHora_chegada(h[1]);
                }
                Log.v("item", (String) parent.getItemAtPosition(position));
                //Toast.makeText(New_routine_Activity.this,(String) parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                Spinner tv = (Spinner) findViewById(R.id.hora_comboio);
                tv.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }

        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Data d = new Data(dayOfMonth,monthOfYear,year);
        routine.setData_fim(d.toString());
        TextView tv = (TextView) findViewById(R.id.fim_date);
        tv.setText(d.toString());
        Toast.makeText(New_routine_Activity.this, String.format("Data de Fim da Rotina Alterada para %s)",d.toString()), Toast.LENGTH_LONG).show();
    }

    public void box_clicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.Segunda:
                if (checked){
                    routine.addRepetir("Segunda");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Segunda");
                    break;
                }
            case R.id.Terca:
                if (checked){
                    routine.addRepetir("Terça");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Terça");
                    break;
                }
            case R.id.Quarta:
                if (checked){
                    routine.addRepetir("Quarta");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Quarta");
                    break;
                }
            case R.id.Quinta:
                if (checked){
                    routine.addRepetir("Quinta");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Quinta");
                    break;
                }
            case R.id.Sexta:
                if (checked){
                    routine.addRepetir("Sexta");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Sexta");
                    break;
                }
            case R.id.Sabado:
                if (checked){
                    routine.addRepetir("Sábado");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Sábado");
                    break;
                }
            case R.id.Domingo:
                if (checked){
                    routine.addRepetir("Domingo");
                }
                // Put some meat on the sandwich
                else{
                    routine.removeRepetir("Domingo");
                    break;
                }

        }
    }

}
