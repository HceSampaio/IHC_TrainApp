package ihc.ihc_app.Models;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;
import java.util.UUID;

/**
 * Created by MSI on 14/05/2018.
 */

public class BilheteParent implements ParentObject {

    private List<Object> mChildrenList; //lista de filhos
    private UUID _id; //id
    private String comboio; //Text
    private String hora; //Text

    public BilheteParent(String dest,String hours) { //recebem texto para o seu titulo
        this.comboio = dest;
        this.hora = hours;

        _id = UUID.randomUUID();
    }


    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public String getComboio() {
        return comboio;
    }

    public void setComboio(String comboio) {
        this.comboio = comboio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    //childList
    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }
}

