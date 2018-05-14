package ihc.ihc_app.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import ihc.ihc_app.R;


public class MyParentViewHolder extends ParentViewHolder {
    public ImageButton _imageButton;
    public TextView _textViewDest;
    public TextView _textViewOrig;
    public TextView _textViewHour;
    public TextView _textViewDays;
    public Switch _switch;


    public MyParentViewHolder(View itemView) {
        super(itemView);
        _textViewDest = (TextView)itemView.findViewById(R.id.Destination);
        _textViewOrig = (TextView)itemView.findViewById(R.id.Origem);
        _textViewHour = (TextView)itemView.findViewById(R.id.Hour);
        _textViewDays = (TextView)itemView.findViewById(R.id.Days);
        _switch = (Switch) itemView.findViewById(R.id.switch1);
        //_imageButton = (ImageButton) itemView.findViewById(R.id.expandArrow);

    }
}