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

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;

import ihc.ihc_app.MyAdapter;
import ihc.ihc_app.Models.TitleChild;
import ihc.ihc_app.Models.TitleParent;


public class routineActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((MyAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_routines);

        recyclerView = (RecyclerView)findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        MyAdapter adapter = new MyAdapter(this,initData()); //meter conteudo dos pais e meter os filhos dentros dos pais
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(adapter);
    }

    private List<ParentObject> initData() {

        int number_of_routines = 3;
        //get arrayList<> of TitleParent(String s)
        List<ParentObject> parent_array = new ArrayList<>();

        for(int i=0;i<number_of_routines; i++){

            String destin,hour;
            String[] days = {"ter","sex"};
            destin = "Aveiro";
            hour = "16:19";


            TitleParent parent = new TitleParent(destin,hour,days);
            //gerar parent

            List<Object> childList = new ArrayList<>();
            String comboio="IC127",carruagem="7",lugar="20";
            int before = 15;

            TitleChild child = new TitleChild(comboio,carruagem,lugar,before);
            //new child (option1, option2)
            childList.add(child);
            //lista dos filhos do parent

            parent.setChildObjectList(childList);
            //lista de filhos de parent = childlist

            parent_array.add(parent);
        }

        return parent_array;

    }

    public void open_new_routine (View view){
        Intent intent = new Intent (this, New_routine_Activity.class);
        startActivity(intent);
    }
}
