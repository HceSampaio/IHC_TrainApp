package ihc.ihc_app.Models;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;
import java.util.UUID;

public class TitleParent implements ParentObject{

    private List<Object> mChildrenList; //lista de filhos
    private UUID _id; //id
    private String destination; //Text
    private String hours;
    private String[] days;

    public TitleParent(String dest,String hours,String[] days) { //recebem texto para o seu titulo
        this.destination = dest;
        this.hours = hours;
        this.days = days;
        _id = UUID.randomUUID();
    }

    public String getDestination() {
        return destination;
    }

    public String getHours() {
        return hours;
    }

    public String[] getDays() {
        return days;
    }

    public String getDaysAsString() {
        String str = "";
        for(int i=0;i<days.length;i++){
            str += days[i] +",";
        }
        str.substring(0,str.length()-2);
        return str;
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setDays(String[] days) {
        this.days = days;
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