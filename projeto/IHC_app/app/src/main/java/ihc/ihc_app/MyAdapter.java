package ihc.ihc_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

import ihc.ihc_app.Models.TitleChild;
import ihc.ihc_app.Models.TitleParent;
import ihc.ihc_app.R;
import ihc.ihc_app.ViewHolders.MyChildViewHolder;
import ihc.ihc_app.ViewHolders.MyParentViewHolder;


public class MyAdapter extends ExpandableRecyclerAdapter<MyParentViewHolder,MyChildViewHolder> {

    LayoutInflater inflater;

    public MyAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.parent_list,viewGroup,false);
        return new MyParentViewHolder(view);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.child_list,viewGroup,false);
        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(MyParentViewHolder titleParentViewHolder, int i, Object o) {
        TitleParent title = (TitleParent)o;
        titleParentViewHolder._textView.setText(title.getTitle());

    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder titleChildViewHolder, int i, Object o) {
        TitleChild title = (TitleChild)o;
        titleChildViewHolder.option1.setText(title.getOption1());
        titleChildViewHolder.option2.setText(title.getOption2());

    }
}