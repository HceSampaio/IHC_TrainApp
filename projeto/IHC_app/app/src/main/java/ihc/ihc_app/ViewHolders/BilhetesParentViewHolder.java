package ihc.ihc_app.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import ihc.ihc_app.R;

/**
 * Created by MSI on 14/05/2018.
 */

public class BilhetesParentViewHolder extends ParentViewHolder {


    public TextView _textViewComboio;
    public TextView _textViewHora;


    public BilhetesParentViewHolder(View itemView) {
        super(itemView);
        _textViewComboio = (TextView)itemView.findViewById(R.id.comboio);
        _textViewHora = (TextView)itemView.findViewById(R.id.hora);


    }
}
