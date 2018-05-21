package ihc.ihc_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

    Context context;

    int carruagemEscolhida;
    int lugarEscolhido;

    private Bilhete currentBilhete;
    private Bilhete bilheteComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_comboio);

        context = this;

        listView = (ExpandableListView)findViewById(R.id.lvExp);

        final List<String> cidades_partida = new ArrayList<>();
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
        String comboio, hora, horachegada, carruagem, lugar, preco;
        listHeaders = new ArrayList<>();
        listHash = new HashMap<>();

        if(partida == "Aveiro"){
            comboio = "IC786";
            hora = "16:20";
            horachegada = "17:52";
            carruagem = "CARRUAGEM: 4";
            lugar = "LUGAR: 26";
            preco = "22 Euros";
            List<String> tmp = new ArrayList<>();
            tmp.add(comboio);
            tmp.add(hora);
            tmp.add(horachegada);
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
            horachegada = "13:48";
            carruagem = "CARRUAGEM: 7";
            lugar = "LUGAR: 48";
            preco = "35 Euros";
            List<String> tmp = new ArrayList<>();
            tmp.add(comboio);
            tmp.add(hora);
            tmp.add(horachegada);
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
            horachegada = "13:59";
            carruagem = "CARRUAGEM: 3";
            lugar = "LUGAR: 2";
            preco = "69 Euros";
            tmp = new ArrayList<>();
            tmp.add(comboio);
            tmp.add(hora);
            tmp.add(horachegada);
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
            horachegada = "12:40";
            carruagem = "CARRUAGEM: 3";
            lugar = "LUGAR: 2";
            preco = "12 Euros";
            tmp = new ArrayList<>();
            tmp.add(comboio);
            tmp.add(hora);
            tmp.add(horachegada);
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
        if (partida == null){
            Toast.makeText(this, "Escolha uma estação de partida.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (chegada == null){
            Toast.makeText(this, "Escolha uma estação de chegada.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (data == null){
            Toast.makeText(this, "Escolha uma data.", Toast.LENGTH_SHORT).show();
            return;
        }
        listView.setAdapter(listAdapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(final ExpandableListView parent, View v, final int groupPosition, int childPosition, long id){
                // COMPRA DE BILHETE
                List<String> group = (List<String>)listAdapter.getGroup(groupPosition);
                String comboio = group.get(0);
                String hora = group.get(1);
                String horachegada = group.get(2);

                ViewGroup linearLayout1 = (ViewGroup)((ViewGroup)v).getChildAt(0);
                ViewGroup linearLayout2 = (ViewGroup)((ViewGroup)linearLayout1).getChildAt(0);
                ViewGroup linearLayout3 = (ViewGroup)((ViewGroup)linearLayout2).getChildAt(0);
                TextView car = (TextView)linearLayout3.getChildAt(0);
                TextView pre = (TextView)linearLayout3.getChildAt(1);
                String carruagem = car.getText().toString();
                double preco = Double.parseDouble(pre.getText().toString().split(" ")[0]);

                //layout2.1.0
                TextView lug = (TextView)((ViewGroup)linearLayout2.getChildAt(1)).getChildAt(0);
                String lugar = lug.getText().toString();

                currentBilhete = new Bilhete(comboio, carruagem, lugar, chegada, partida, horachegada, hora, data, preco);
                Log.d("BILHETE",currentBilhete.toString() + "......preco:"+preco);

                final Client c = Client.getInstance();
                if (c.logged_in()){
                    new AlertDialog.Builder(context)
                            .setTitle(getResources().getString(R.string.confirm_title))
                            .setMessage("Está prestes a comprar o seguinte bilhete\n"+currentBilhete.shortToString())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    c.addBilhetes(currentBilhete);
                                    parent.collapseGroup(groupPosition);
                                    Toast.makeText(parent.getContext(), "Bilhete comprado com sucesso!", Toast.LENGTH_LONG).show();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                }else{
                    Toast.makeText(parent.getContext(), "Inicie sessão para comprar um bilhete.", Toast.LENGTH_SHORT).show();
                }
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

            TextView carruagem = (TextView)((ViewGroup)((ViewGroup)bilheteEscolhido.getParent()).getChildAt(0)).getChildAt(0);
            TextView lugar = (TextView)bilheteEscolhido.getChildAt(0);
            carruagem.setText("CARRUAGEM: "+carruagemEscolhida);
            lugar.setText("LUGAR: "+lugarEscolhido);
            Toast.makeText(this, "Lugar alterado com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    public void empty (View v){
        // does nothing
    }

}
