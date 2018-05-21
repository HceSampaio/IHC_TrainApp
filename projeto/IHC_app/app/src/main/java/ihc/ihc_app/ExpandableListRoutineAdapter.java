package ihc.ihc_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListRoutineAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<List<String>> listHeaders;
    private HashMap<List<String>,List<List<String>>> listHashMap;

    public ExpandableListRoutineAdapter(Context context, List<List<String>> listHeaders, HashMap<List<String>, List<List<String>>> listHashMap) {
        this.context = context;
        this.listHeaders = listHeaders;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listHeaders.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listHeaders.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listHeaders.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(listHeaders.get(i));
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        List<String> headerTitle = (List<String>)getGroup(i);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.parent_list, null);
        }

        TextView origem = (TextView)view.findViewById(R.id.routine_origem);
        TextView destino = (TextView)view.findViewById(R.id.routine_destino);
        TextView hora = (TextView)view.findViewById(R.id.routine_hora);
        TextView dias = (TextView)view.findViewById(R.id.routine_dias);

        origem.setText(headerTitle.get(0));
        destino.setText(headerTitle.get(1));
        hora.setText(headerTitle.get(2));
        dias.setText(headerTitle.get(3));

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        List<List<String>> childText = (List<List<String>>)getChild(i,i1);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_list, null);
        }
        TextView comboio = (TextView)view.findViewById(R.id.routine_comboio);
        TextView antecedencia = (TextView)view.findViewById((R.id.routine_antecedencia));
        TextView hora_partida = (TextView)view.findViewById(R.id.routine_child_hora_partida);
        TextView hora_chegada = (TextView)view.findViewById(R.id.routine_child_hora_chegada);
        TextView data_fim = (TextView)view.findViewById(R.id.routine_child_data_fim);
        Log.d("ROUTINEADAPTER",childText.get(i1).toString());

        comboio.setText(childText.get(i1).get(0));
        antecedencia.setText(childText.get(i1).get(1));
        hora_partida.setText(childText.get(i1).get(2));
        hora_chegada.setText(childText.get(i1).get(3));
        data_fim.setText(childText.get(i1).get(4));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
