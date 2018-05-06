package ihc.ihc_app;

import android.app.Application;

/**
 * Created by MSI on 03/05/2018.
 */

public class Globals extends Application {

    private String user_name;
    private String password;

    public String get_user(){
        return user_name;
    }
    public String get_password(){
        return password;
    }

    public void set_user(String user){
        user_name= user;
    }
    public void set_password(String pass){
        password = pass;
    }

}
