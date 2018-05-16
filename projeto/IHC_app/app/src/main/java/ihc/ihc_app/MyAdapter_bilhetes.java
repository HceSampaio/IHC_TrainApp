package ihc.ihc_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

import ihc.ihc_app.Models.BilheteParent;
import ihc.ihc_app.Models.BilheteChild;
import ihc.ihc_app.ViewHolders.BilhetesChildViewHolder;
import ihc.ihc_app.ViewHolders.BilhetesParentViewHolder;

/**
 * Created by MSI on 14/05/2018.
 */


public class MyAdapter_bilhetes extends ExpandableRecyclerAdapter<BilhetesParentViewHolder,BilhetesChildViewHolder> {

    LayoutInflater inflater;
    private String comboio;
    private int i1,i2;
    private String hora;
    private double preco;
    private String carruagem,lugar;

    public MyAdapter_bilhetes(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public BilhetesParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.bilhete_parent,viewGroup,false);
        return new BilhetesParentViewHolder(view);
    }

    @Override
    public BilhetesChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.bilhete_child,viewGroup,false);
        return new BilhetesChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(BilhetesParentViewHolder titleParentViewHolder, int i, Object o) {
        BilheteParent title = (BilheteParent) o;
        titleParentViewHolder._textViewComboio.setText(title.getComboio());
        titleParentViewHolder._textViewHora.setText(title.getHora());
        comboio = title.getComboio();
        hora = title.getHora();
        i1 = i;
    }

    @Override
    public void onBindChildViewHolder(BilhetesChildViewHolder titleChildViewHolder, int i, Object o) {
        BilheteChild title = (BilheteChild)o;

        titleChildViewHolder.carruagem.setText("CARRUAGEM: "+title.getCarruagem());
        titleChildViewHolder.lugar.setText("LUGAR: "+title.getLugar());
        titleChildViewHolder.preco.setText("PREÇO: "+title.getPreco());
        carruagem = title.getCarruagem();
        lugar = title.getLugar();
        preco = Double.parseDouble(title.getPreco().split(" ")[0]);
        i2 = i;
    }

    public String getAtt(){
        return comboio + " " + hora + " " + preco + " C"+carruagem + " " + lugar + " i1: "+i1+" i2: "+i2;
    }
}

