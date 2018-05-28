package ihc.ihc_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Globals global;
    public static Activity mActivity;
    @Override
    protected void onResume(){
        super.onResume();
        Client.resetClient();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;
    }

    public void open_login (View view){
        Intent intent = new Intent (this, LoginActivity.class);
        startActivity(intent);
    }

    public void open_routines (View view){
        Toast.makeText(MainActivity.this, "Esta funcionalidade requer início de sessão.", Toast.LENGTH_SHORT).show();
    }

    public void open_maps (View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void open_procura (View view){
        Intent intent = new Intent (this, procurar_comboio.class);
        startActivity(intent);
    }

    public void open_bilhetes (View view) {
        Toast.makeText(MainActivity.this, "Esta funcionalidade requer início de sessão.", Toast.LENGTH_SHORT).show();
    }

}
