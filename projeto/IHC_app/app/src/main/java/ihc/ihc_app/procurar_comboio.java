package ihc.ihc_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import ihc.ihc_app.MyAdapters.MyArrayAdapter;

public class procurar_comboio extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private String partida,chegada;
    private String comboio;
    private String hora;
    private double preco;
    private Data data;
    private int lastExpanded;

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<List<String>> listHeaders;
    private HashMap<List<String>,List<List<String>>> listHash;

    private ViewGroup bilheteEscolhido;

    int carruagemEscolhida;
    int lugarEscolhido;

    private Bilhete currentBilhete;
    private Bilhete bilheteComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_comboio);

        listView = (ExpandableListView)findViewById(R.id.lvExp);

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

    private void initData(){
        String comboio, hora, carruagem, lugar, preco;
        listHeaders = new ArrayList<>();
        listHash = new HashMap<>();

        if(partida == "Aveiro"){
            comboio = "IC786";
            hora = "16:20";
            carruagem = "4";
            lugar = "26";
            preco = "22 Euros";
            List<String> tmp = new ArrayList<>();
            tmp.add(comboio);
            tmp.add(hora);
            listHeaders.add(tmp);
            List<String> tmp1 = new ArrayList<>();
            tmp1.add(carruagem);
            tmp1.add(lugar);
            tmp1.add(preco);
            List<List<String>> zero = new ArrayList<>();
            zero.add(tmp1);
            listHash.put(tmp,zero);
        }
        else {
            comboio = "IC123";
            hora = "12:40";
            carruagem = "7";
            lugar = "48";
            preco = "35 Euros";
            List<String> tmp = new ArrayList<>();
            tmp.add(comboio);
            tmp.add(hora);
            listHeaders.add(tmp);
            List<String> tmp1 = new ArrayList<>();
            tmp1.add(carruagem);
            tmp1.add(lugar);
            tmp1.add(preco);
            List<List<String>> zero = new ArrayList<>();
            zero.add(tmp1);
            listHash.put(tmp,zero);

            comboio = "IC666";
            hora = "12:55";
            carruagem = "3";
            lugar = "2";
            preco = "69 Euros";
            tmp = new ArrayList<>();
            tmp.add(comboio);
            tmp.add(hora);
            listHeaders.add(tmp);
            tmp1 = new ArrayList<>();
            tmp1.add(carruagem);
            tmp1.add(lugar);
            tmp1.add(preco);
            zero = new ArrayList<>();
            zero.add(tmp1);
            listHash.put(tmp,zero);

            comboio = "IC111";
            hora = "11:22";
            carruagem = "3";
            lugar = "2";
            preco = "12 Euros";
            tmp = new ArrayList<>();
            tmp.add(comboio);
            tmp.add(hora);
            listHeaders.add(tmp);
            tmp1 = new ArrayList<>();
            tmp1.add(carruagem);
            tmp1.add(lugar);
            tmp1.add(preco);
            zero = new ArrayList<>();
            zero.add(tmp1);
            listHash.put(tmp,zero);
        }
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
        initData();
        listAdapter = new ExpandableListAdapter(this,listHeaders,listHash);
        listView.setAdapter(listAdapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){
                Log.d("CLICKER","group "+groupPosition+", child "+childPosition);
                List<String> group = (List<String>)listAdapter.getGroup(groupPosition);
                String comboio = group.get(0);
                String hora = group.get(1);

                List<List<String>> child = (List<List<String>>)listAdapter.getChild(groupPosition,0);
                String carruagem = child.get(0).get(0);
                String lugar = child.get(0).get(1);
                double preco = Double.parseDouble(child.get(0).get(2).split(" ")[0]);
                currentBilhete = new Bilhete(comboio, carruagem, null, hora, null, preco, lugar);
                Log.d("BILHETE",currentBilhete.toString());
                return false;
            }
        });
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                if(lastExpanded != -1 && i != lastExpanded){
                    listView.collapseGroup(lastExpanded);
                }
                lastExpanded = i;
            }
        });
    }

    public void selectSeat(View view){
        Intent intent = new Intent (this, escolherLugares.class);
        startActivityForResult(intent, 1); // if reqCode >= 0, é devolvido quando a activity termina
        bilheteEscolhido = (ViewGroup)view.getParent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            carruagemEscolhida = data.getIntExtra("carruagem", 1);
            lugarEscolhido = data.getIntExtra("lugar", 0);

            TextView carruagem = (TextView)bilheteEscolhido.getChildAt(0);
            TextView lugar = (TextView)bilheteEscolhido.getChildAt(1);
            carruagem.setText("CARRUAGEM: "+carruagemEscolhida);
            lugar.setText("LUGAR: "+lugarEscolhido);
            Toast.makeText(this, "Lugar alterado com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

}
