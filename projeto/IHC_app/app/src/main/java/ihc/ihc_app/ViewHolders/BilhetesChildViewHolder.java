package ihc.ihc_app.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import ihc.ihc_app.R;

/**
 * Created by MSI on 14/05/2018.
 */

public class BilhetesChildViewHolder extends ChildViewHolder {

    public TextView carruagem,lugar,preco,compra_btn;
    public ImageView edit_img_view;

    public BilhetesChildViewHolder(View itemView) {
        super(itemView);
        carruagem = (TextView)itemView.findViewById(R.id.carruagem);
        lugar = (TextView)itemView.findViewById(R.id.lugar);
        preco = (TextView)itemView.findViewById(R.id.preco);
        compra_btn = (TextView)itemView.findViewById(R.id.compra_btn);
        edit_img_view = (ImageView) itemView.findViewById(R.id.imageView);

    }
}
