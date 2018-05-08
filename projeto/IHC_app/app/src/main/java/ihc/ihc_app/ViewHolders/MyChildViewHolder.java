package ihc.ihc_app.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import ihc.ihc_app.R;

public class MyChildViewHolder extends ChildViewHolder {

    public TextView comboio,carruagem,lugar,before;

    public MyChildViewHolder(View itemView) {
        super(itemView);
        comboio = (TextView)itemView.findViewById(R.id.comboio);
        carruagem = (TextView)itemView.findViewById(R.id.carruagem);
        lugar = (TextView)itemView.findViewById(R.id.lugar);
        before = (TextView)itemView.findViewById(R.id.before);

    }
}