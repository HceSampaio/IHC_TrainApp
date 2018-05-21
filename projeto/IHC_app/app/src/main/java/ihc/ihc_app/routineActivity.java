package ihc.ihc_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class routineActivity extends AppCompatActivity {

    private int lastExpanded;
    private ExpandableListView listView;
    private ExpandableListRoutineAdapter listAdapter;
    private List<List<String>> listHeaders;
    private HashMap<List<String>,List<List<String>>> listHash;

    private Context context;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Client c = Client.getInstance();
        List<Routine> routines = c.getRoutines();

        if(listHeaders.size() < routines.size()){

            Routine r = routines.get(routines.size()-1);//get last routine()

            List<String> tmp;
            List<String> tmp1;

            List<List<String>> zero;

            tmp = new ArrayList<>();
            tmp.add(r.getCidade_partida());
            tmp.add(r.getCidade_chegada());
            tmp.add(r.getHora_partida());
            tmp.add(r.getRepetirAsString());
            listHeaders.add(tmp);
            tmp1 = new ArrayList<>();
            tmp1.add(r.getComboio());
            tmp1.add("ANTECEDENCIA: "+r.getAntecedencia_num() + " "+ r.getAntecedencia_tempo());
            tmp1.add(r.getHora_partida());
            tmp1.add(r.getHora_chegada());
            zero = new ArrayList<>();
            zero.add(tmp1);
            listHash.put(tmp,zero);

            listAdapter = new ExpandableListRoutineAdapter(this,listHeaders,listHash);
            listView.setAdapter(listAdapter);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_routines);

        context = this;

        listView = (ExpandableListView)findViewById(R.id.lvRoutines);
        initData();
        //Log.d("AFTERPARTY",listHash.get(listHeaders.get(0)).toString() + ".....");
        listAdapter = new ExpandableListRoutineAdapter(this,listHeaders,listHash);
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
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                // DELETE DE ROTINA

                final int innerI = i;
                final int innerI1 = i1;
                new AlertDialog.Builder(context)
                        .setTitle(getResources().getString(R.string.confirm_title))
                        .setMessage(getResources().getString(R.string.deleteRoutine_text))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Client c = Client.getInstance();
                                Routine r = c.getRoutines().get(innerI);
                                c.removeRoutines(r);

                                listHash.remove(listHeaders.get(innerI));
                                listHeaders.remove(innerI);

                                listAdapter = new ExpandableListRoutineAdapter(context,listHeaders,listHash);
                                listView.setAdapter(listAdapter);
                                Toast.makeText(context, "Rotina eliminada com sucesso", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

                return false;
            }
        });
    }

    private void initData() {

        Client c = Client.getInstance();
        List<Routine> routines = c.getRoutines();

        if (listHeaders == null){
            listHeaders = new ArrayList<>();
            listHash = new HashMap<>();
        }

        List<String> tmp;
        List<String> tmp1;

        List<List<String>> zero;

        //Add client's routines
        for(int i=0;i<routines.size(); i++){
            Routine r = routines.get(i);

            tmp = new ArrayList<>();
            tmp.add(r.getCidade_partida());
            tmp.add(r.getCidade_chegada());
            tmp.add(r.getHora_partida());
            tmp.add(r.getRepetirAsString());
            listHeaders.add(tmp);
            tmp1 = new ArrayList<>();
            tmp1.add(r.getComboio());
            tmp1.add("ANTECEDENCIA: "+r.getAntecedencia_num() + " "+ r.getAntecedencia_tempo());
            tmp1.add(r.getHora_partida());
            tmp1.add(r.getHora_chegada());

            zero = new ArrayList<>();
            zero.add(tmp1);
            listHash.put(tmp,zero);

        }

    }

    public void open_new_routine (View view){
        Intent intent = new Intent (this, New_routine_Activity.class);
        startActivity(intent);
    }

    public void empty (View view){ }
}
