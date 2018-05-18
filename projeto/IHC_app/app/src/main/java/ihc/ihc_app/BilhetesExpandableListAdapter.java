package ihc.ihc_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class BilhetesExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<List<String>> listHeaders;
    private HashMap<List<String>,List<List<String>>> listHashMap;

    public BilhetesExpandableListAdapter(Context context, List<List<String>> listHeaders, HashMap<List<String>, List<List<String>>> listHashMap) {
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
            view = inflater.inflate(R.layout.mybilhete_parent, null);
        }
        TextView comboio = (TextView)view.findViewById(R.id.bilhete_comboio);
        TextView cidade_partida = (TextView)view.findViewById(R.id.bilhete_cidade);
        TextView cidade_chegada = (TextView)view.findViewById(R.id.bilhete_cidadechegada);
        TextView hora_partida = (TextView)view.findViewById(R.id.bilhete_hora);
        TextView hora_chegada = (TextView)view.findViewById(R.id.bilhete_horachegada);

        comboio.setText(headerTitle.get(0));
        cidade_partida.setText(headerTitle.get(1));
        cidade_chegada.setText(headerTitle.get(2));
        hora_partida.setText(headerTitle.get(3));
        hora_chegada.setText(headerTitle.get(4));

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        List<List<String>> childText = (List<List<String>>)getChild(i,i1);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mybilhete_child, null);
        }
        TextView carruagem = (TextView)view.findViewById(R.id.bilhete_carruagem);
        TextView lugar = (TextView)view.findViewById(R.id.bilhete_lugar);
        TextView preco = (TextView)view.findViewById(R.id.bilhete_preco);
        carruagem.setText(childText.get(i1).get(0));
        lugar.setText(childText.get(i1).get(1));
        preco.setText(childText.get(i1).get(2));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
