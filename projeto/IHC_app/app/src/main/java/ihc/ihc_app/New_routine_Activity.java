package ihc.ihc_app;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ihc.ihc_app.MyAdapters.MyArrayAdapter;

public class New_routine_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    Routine routine;
    List<String> horas;
    ArrayAdapter<String> adapter_hora;

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

        final MyArrayAdapter<String> adapter_partida = new MyArrayAdapter<String>(this, R.layout.my_spinner_layout, new ArrayList<>(cidades_partida),new ArrayList<>(cidades_partida));
        final MyArrayAdapter<String> adapter_chegada = new MyArrayAdapter<String>(this, R.layout.my_spinner_layout, new ArrayList<>(cidades_chegada),new ArrayList<>(cidades_chegada));

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
                datePD.setTitle(getResources().getString(R.string.newRoutine_calendarTitle));
                datePD.show(getFragmentManager(),"Data Fim");

            }
        });

        CardView hora = (CardView)findViewById(R.id.hora_card);
        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog timePD = TimePickerDialog.newInstance(New_routine_Activity.this, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), true);
                timePD.setTitle(getResources().getString(R.string.newRoutine_hourTitle));
                timePD.show(getFragmentManager(), "Hora");
            }
        });

        //COIBOIO E HORARIO

        List<String> lista_comboios = new ArrayList<>();
        lista_comboios.add("AP123");
        lista_comboios.add("IC859");
        lista_comboios.add("IC324");

        horas = new ArrayList<>(); // lista com elementos no formato IC324 16:32 - 17:19

        for(int h = 0; h<23; h++){
            int i = 0;
            for(int m = 21; m < 45; m+=9){
                String hora_partida = h+":"+m;
                String hora_chegada = (h+1)+":"+(m-8);
                horas.add(lista_comboios.get(i)+" "+hora_partida+" - " +hora_chegada);
                i+=1;
            }
        }

        List<String> default_hora = new ArrayList<>();
        default_hora.add("COMBOIOS");

        adapter_hora = new ArrayAdapter<String>(this, R.layout.my_spinner_layout, new ArrayList<>(default_hora));
        final ArrayAdapter<String> adapter_comboio = new ArrayAdapter<String>(this,R.layout.my_spinner_layout, new ArrayList<>(lista_comboios));
/*
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

                if(comboio_chosen.equals("AP123")) {
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
*/


        Spinner spinner_hora = (Spinner) findViewById(R.id.hora_comboio);
        spinner_hora.setAdapter(adapter_hora);
        spinner_hora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner tv = (Spinner) findViewById(R.id.hora_comboio);
                if(tv.getAdapter().getCount()>1) {

                    String s = tv.getAdapter().getItem(position).toString();
                    String comboio = s.split(" ")[0];
                    String hora_partida = s.split(" ")[1];
                    String hora_chegada = s.split(" ")[3];

                    routine.setHora_partida(hora_partida);
                    routine.setHora_chegada(hora_chegada);
                    routine.setComboio(comboio);

                    tv.setSelection(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }

        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Data d = new Data(dayOfMonth,monthOfYear+1,year);
        Calendar now = Calendar.getInstance();
        Data today = new Data(now.get(Calendar.DAY_OF_MONTH),now.get(Calendar.MONTH),now.get(Calendar.YEAR));
        if(d.compareTo(today)>=0){
            routine.setData_fim(d.toString());
            TextView tv = (TextView) findViewById(R.id.fim_date);
            tv.setText(d.toString());
        }
        else{
            Toast.makeText(New_routine_Activity.this, "Data de fim inválida! Escolha data futura!", Toast.LENGTH_LONG).show();
        }
    }

    public void box_clicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.Segunda:
                if (checked){
                    routine.addRepetir("Seg");
                }
                else{
                    routine.removeRepetir("Seg");
                }
                break;
            case R.id.Terca:
                if (checked){
                    routine.addRepetir("Ter");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Ter");

                }
                break;
            case R.id.Quarta:
                if (checked){
                    routine.addRepetir("Qua");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Qua");

                }
                break;
            case R.id.Quinta:
                if (checked){
                    routine.addRepetir("Qui");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Qui");
                }
                break;
            case R.id.Sexta:
                if (checked){
                    routine.addRepetir("Sex");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Sex");
                }
                break;
            case R.id.Sabado:
                if (checked){
                    routine.addRepetir("Sáb");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Sáb");

                }
                break;
            case R.id.Domingo:
                if (checked){
                    routine.addRepetir("Dom");
                }
                // Put some meat on the sandwich
                else{
                    routine.removeRepetir("Dom");
                }
                break;

        }
    }

    public void confirm_routine(View view){
        if(routine.isComplete()) {

            Client c = Client.getInstance();
            c.addRoutines(routine);
            this.finish();
        }else{
            Toast.makeText(New_routine_Activity.this, "Por favor Preencher todos os campos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        TextView tv = (TextView) findViewById(R.id.hora_pretendida);
        String hour = String.format("%02d:%02d",hourOfDay,minute);
        tv.setText(hour);

        adapter_hora.clear();
        adapter_hora.addAll(filterHour(hour));

        Spinner sp = (Spinner) findViewById(R.id.hora_comboio);
        String s = sp.getAdapter().getItem(0).toString();
        String comboio = s.split(" ")[0];
        String hora_partida = s.split(" ")[1];
        String hora_chegada = s.split(" ")[3];

        routine.setHora_partida(hora_partida);
        routine.setHora_chegada(hora_chegada);
        routine.setComboio(comboio);
    }

    private List<String> filterHour(String hour){ //hh:mm from clock
        int h = Integer.parseInt(hour.split(":")[0]);
        int m = Integer.parseInt(hour.split(":")[1]);
        int absHour = (h*60+m)%(24*60+60);

        List<String> filtered = new ArrayList<>();

        for(int i = 0; i<horas.size(); i++){
            String s = horas.get(i);
            String hora_partida = s.split(" ")[1];
            int h_partida = Integer.parseInt(hora_partida.split(":")[0]);
            int m_partida = Integer.parseInt(hora_partida.split(":")[1]);
            int abs_partida = (h_partida*60+m_partida)%(24*60+60);
            if(abs_partida > absHour ){
                filtered.add(s);
                filtered.add(horas.get((i+1)%horas.size()));
                filtered.add(horas.get((i+2)%horas.size()));
                break;
            }
            if(i == horas.size()-1){
                // hora introduzida é superior ao ultimo comboio
                filtered.add(horas.get(0));
                filtered.add(horas.get(1));
                filtered.add(horas.get(2));
            }
        }

        return filtered;
    }
}
