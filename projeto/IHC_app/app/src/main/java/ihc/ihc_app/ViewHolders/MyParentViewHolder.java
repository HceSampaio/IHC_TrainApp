package ihc.ihc_app.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import ihc.ihc_app.R;


public class MyParentViewHolder extends ParentViewHolder {
    public TextView _textView;
    public ImageButton _imageButton;

    public MyParentViewHolder(View itemView) {
        super(itemView);
        _textView = (TextView)itemView.findViewById(R.id.parentTitle);
        _imageButton = (ImageButton) itemView.findViewById(R.id.expandArrow);
    }
}