package ihc.ihc_app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 10/05/2018.
 */

public class Client {

    private List<Routine> routines = new ArrayList<>();
    private String name="";
    private String password;
    private List<Bilhete> bilhetes = new ArrayList<>();
    private static Client client = null;

    protected Client() {
        // Exists only to defeat instantiation.
    }
    public static Client getInstance() {
        if(client == null) {
            client = new Client();
        }
        return client;
    }
    public boolean logged_in(){
        return client.getName()!="";
    }
    public List<Routine> getRoutines() {
        return routines;
    }

    public void addRoutines(Routine routine) {
        this.routines.add(routine);
    }
    public void removeRoutines(Routine r){
        this.routines.remove(r);
    }

    public List<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public void addBilhetes(Bilhete b) {
        this.bilhetes.add(b);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }
}
