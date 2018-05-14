package ihc.ihc_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ihc.ihc_app.Models.BilheteParent;
import ihc.ihc_app.Models.BilheteChild;
import ihc.ihc_app.MyAdapters.MyArrayAdapter;

public class procurar_comboio extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener{

    private String partida,chegada;
    private Data data;

    RecyclerView recyclerView;
    MyAdapter_bilhetes adapter;
    List<ParentObject> parentItemList;

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((MyAdapter_bilhetes)recyclerView.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_comboio);


        recyclerView = (RecyclerView)findViewById(R.id.bilhetesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



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
                    partida = (String)parent.getItemAtPosition(position);
                    adapter_chegada.hide((String)parent.getItemAtPosition(position));
                    Spinner tv = (Spinner) findViewById(R.id.cidade_partida);
                    int spinnerPosition = adapter_partida.getPosition(partida);
                    //Log.d("My TAG",String.format("Partida %d",spinnerPosition) );
                    tv.setSelection(spinnerPosition,true);

                    Spinner tv2 = (Spinner) findViewById(R.id.cidade_chegada);
                    int spinnerPosition2 = adapter_partida.getPosition(chegada);
                    //Log.d("My TAG",String.format("chegada %d ",spinnerPosition2) );
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
                    chegada = (String)parent.getItemAtPosition(position);
                    adapter_partida.hide((String)parent.getItemAtPosition(position));

                    Spinner tv = (Spinner) findViewById(R.id.cidade_chegada);
                    int spinnerPosition = adapter_chegada.getPosition(chegada);
                    tv.setSelection(spinnerPosition,true);

                    Spinner tv2 = (Spinner) findViewById(R.id.cidade_partida);
                    int spinnerPosition2 = adapter_partida.getPosition(partida);
                    tv2.setSelection(spinnerPosition2,true);

                }
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
                DatePickerDialog datePD = DatePickerDialog.newInstance(procurar_comboio.this, now.get(Calendar.YEAR),now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                datePD.setTitle("Escolher Data Fim da Rotina");
                datePD.show(getFragmentManager(),"Data Fim");

            }
        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Data d = new Data(dayOfMonth,monthOfYear+1,year);
        //routine.setData_fim(d.toString());
        data = d;
        TextView tv = (TextView) findViewById(R.id.fim_date);
        tv.setText(d.toString());
    }

    public void search(View view){

        parentItemList = initData();
        adapter = new MyAdapter_bilhetes(this,parentItemList); //meter conteudo dos pais e meter os filhos dentros dos pais
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);
        recyclerView.setAdapter(adapter);

    }

    private List<ParentObject> initData() {

        List<ParentObject> parent_array = new ArrayList<>();

        //parametros de uma rotina
        BilheteParent parent = null;
        List<Object> childList;
        BilheteChild child;

        String comboio,hora;
        String carruagem,lugar,preço;

        if(partida == "Aveiro"){
            for (int i = 0; i < 5; i++) {

                comboio = "IC786";
                hora = "16:20";
                carruagem = "4";
                lugar = "26";
                preço = "22 Euros";
                parent = new BilheteParent(comboio, hora);

                childList = new ArrayList<>();

                child = new BilheteChild(carruagem, lugar, preço);
                childList.add(child);

                parent.setChildObjectList(childList);
                parent_array.add(parent);

            }
        }
        else {
            for (int i = 0; i < 5; i++) {

                comboio = "IC123";
                hora = "12:40";
                carruagem = "7";
                lugar = "48";
                preço = "35 Euros";
                parent = new BilheteParent(comboio, hora);

                childList = new ArrayList<>();

                child = new BilheteChild(carruagem, lugar, preço);
                childList.add(child);

                parent.setChildObjectList(childList);
                parent_array.add(parent);

            }
        }

        return parent_array;

    }
    public void comprar_bilhete(View view){

    }
}
