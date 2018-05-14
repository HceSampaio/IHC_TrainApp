package ihc.ihc_app;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;

import ihc.ihc_app.MyAdapter;
import ihc.ihc_app.Models.TitleChild;
import ihc.ihc_app.Models.TitleParent;


public class routineActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    List<ParentObject> parentItemList;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((MyAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
    }
    @Override
    protected void onResume(){
        super.onResume();


        Client c = Client.getInstance();
        List<Routine> routines = c.getRoutines();

        Toast.makeText(routineActivity.this, String.format("Item count : %d, routines: %d",adapter.getItemCount(),routines.size()), Toast.LENGTH_LONG).show();


        if(adapter.getItemCount()-1 < routines.size()){

            Routine r = routines.get(routines.size()-1);//get last routine()

            //parametros de uma rotina
            TitleParent parent = null;
            List<Object> childList;
            TitleChild child;

            String destin,hour;
            List<String> days = null;
            String comboio,carruagem,lugar,before_num,before_tempo;
            ////////////////////////////////////

            days = r.getRepetir();
            destin = r.getCidade_chegada();
            hour = r.getHora_partida();

            parent = new TitleParent(destin,hour,days);//gerar parent

            childList = new ArrayList<>();
            comboio=r.getComboio();
            carruagem="5";
            lugar="16";
            before_num = r.getAntecedencia_num();
            before_tempo=r.getAntecedencia_tempo();

            child = new TitleChild(comboio,carruagem,lugar,before_num,before_tempo);//new child
            childList.add(child);//lista dos filhos do parent

            parent.setChildObjectList(childList);//lista de filhos de parent = childlist

            parentItemList.add(parent);

            Toast.makeText(routineActivity.this, String.format("Item INSIDE : %d, parent: %d",adapter.getItemCount(),parentItemList.size()), Toast.LENGTH_LONG).show();

            //adapter.notifyItemInserted(adapter.getItemCount()-1);
            //adapter = new MyAdapter(this,parentItemList);

            /*
            TO REMOVE STUFF
            list.remove(position);
            recycler.removeViewAt(position);
            mAdapter.notifyItemRemoved(position);
            mAdapter.notifyItemRangeChanged(position, list.size());

             */



        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_routines);

        recyclerView = (RecyclerView)findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        parentItemList = initData();
        adapter = new MyAdapter(this,initData()); //meter conteudo dos pais e meter os filhos dentros dos pais
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(adapter);
    }

    private List<ParentObject> initData() {

        Client c = Client.getInstance();
        List<Routine> routines = c.getRoutines();

        List<ParentObject> parent_array = new ArrayList<>();

        //parametros de uma rotina
        TitleParent parent = null;
        List<Object> childList;
        TitleChild child;

        String destin,hour;
        List<String> days = null;
        String comboio,carruagem,lugar,before_num,before_tempo;

        //add dummy routine
        days = new ArrayList<String>();
        days.add("Ter");
        days.add("Sex");
        destin = "Aveiro";
        hour = "16:40";
        parent = new TitleParent(destin,hour,days);

        childList = new ArrayList<>();
        comboio="IC127";
        carruagem="7";
        lugar="20";
        before_num = "15";
        before_tempo = "Min";

        child = new TitleChild(comboio,carruagem,lugar,before_num,before_tempo);
        childList.add(child);

        parent.setChildObjectList(childList);
        parent_array.add(parent);

        //Add client's routines
        for(int i=0;i<routines.size(); i++){
            Routine r = routines.get(i);

            days = r.getRepetir();
            destin = r.getCidade_chegada();
            hour = r.getHora_partida();

            parent = new TitleParent(destin,hour,days);//gerar parent

            childList = new ArrayList<>();
            comboio=r.getComboio();
            carruagem="5";
            lugar="16";
            before_num = r.getAntecedencia_num();
            before_tempo=r.getAntecedencia_tempo();

            child = new TitleChild(comboio,carruagem,lugar,before_num,before_tempo);//new child
            childList.add(child);//lista dos filhos do parent

            parent.setChildObjectList(childList);//lista de filhos de parent = childlist

            parent_array.add(parent);
        }

        return parent_array;

    }

    public void open_new_routine (View view){
        Intent intent = new Intent (this, New_routine_Activity.class);
        startActivity(intent);
        //Client c = Client.getInstance();
        //parentItemList.add(updateParent());
        //UpdateData();
    }
}
