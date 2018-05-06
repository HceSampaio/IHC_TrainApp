package ihc.ihc_app.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import ihc.ihc_app.R;

public class MyChildViewHolder extends ChildViewHolder {

    public TextView option1,option2;

    public MyChildViewHolder(View itemView) {
        super(itemView);
        option1 = (TextView)itemView.findViewById(R.id.option1);
        option2 = (TextView)itemView.findViewById(R.id.option2);
    }
}