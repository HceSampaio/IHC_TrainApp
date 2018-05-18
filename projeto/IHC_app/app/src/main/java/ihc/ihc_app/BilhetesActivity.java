package ihc.ihc_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BilhetesActivity extends AppCompatActivity {

    private int lastExpanded;

    private ExpandableListView listView;
    private BilhetesExpandableListAdapter listAdapter;
    private List<List<String>> listHeaders;
    private HashMap<List<String>,List<List<String>>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilhetes);

        listView = (ExpandableListView)findViewById(R.id.lvBilhetes);
        initData();
        listAdapter = new BilhetesExpandableListAdapter(this,listHeaders,listHash);
        listView.setAdapter(listAdapter);
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

    private void initData(){
        Client c = Client.getInstance();
        List<Bilhete> list = c.getBilhetes();

        if (listHeaders == null){
            listHeaders = new ArrayList<>();
            listHash = new HashMap<>();
        }

        List<String> tmp = new ArrayList<>();
        tmp.add("R14132");
        tmp.add("Aveiro");
        tmp.add("Coimbra");
        tmp.add("17:24");
        tmp.add("18:06");
        listHeaders.add(tmp);
        List<String> tmp1 = new ArrayList<>();
        tmp1.add("CARRUAGEM: 1");
        tmp1.add("LUGAR: 5");
        tmp1.add("12.43 €");
        List<List<String>> zero = new ArrayList<>();
        zero.add(tmp1);
        listHash.put(tmp,zero);

        for(Bilhete b : list){
            Log.d("MYBILHETES",b.toString());
            tmp = new ArrayList<>();
            tmp.add(b.getComboio());
            tmp.add(b.getEstacao_partida());
            tmp.add(b.getEstacao_chegada());
            tmp.add(b.getHora_partida());
            tmp.add(b.getHora_chegada());
            listHeaders.add(tmp);
            tmp1 = new ArrayList<>();
            tmp1.add("CARRUAGEM: " + b.getCarruagem());
            tmp1.add("LUGAR: " + b.getLugar());
            tmp1.add(b.getPreco() + "€");
            zero = new ArrayList<>();
            zero.add(tmp1);
            listHash.put(tmp,zero);
        }

    }
}
