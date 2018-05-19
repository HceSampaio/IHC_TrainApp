package ihc.ihc_app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;

public class escolherLugares extends AppCompatActivity {

    private HashMap<Integer,Integer[]> lugaresOcupados;
    private int carruagemAtual;
    private int limiteCarruagens = 3;
    private int lugarEscolhido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_lugares);

        carruagemAtual = 1;
        limiteCarruagens = 3;
        lugarEscolhido = 0;
        lugaresOcupados = new HashMap<>();
        lugaresOcupados.put(1,new Integer[]{1,7,9,16,22,23,24,25,26,27,28});
        lugaresOcupados.put(2,new Integer[]{21,22,32});
        lugaresOcupados.put(3,new Integer[]{19});
        setState();
        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeLeft(){
                if(carruagemAtual<limiteCarruagens){
                    carruagemAtual += 1;
                }else{
                    return;
                }
                setState();
            }

            public void onSwipeRight(){
                if(carruagemAtual > 1){
                    carruagemAtual -= 1;
                }else{
                    return;
                }
                setState();
            }
        });
    }

    private void setState(){
        for(int i = 1; i <= 32; i++){
            Button b = (Button)findViewById(getResources().getIdentifier("seat"+i, "id", this.getPackageName()));
            if (Arrays.asList(lugaresOcupados.get(carruagemAtual)).contains(i)){
                b.setBackgroundResource(R.drawable.button_lugar_taken);
            }else{
                b.setBackgroundResource(R.drawable.button_lugar_free);
            }
        }
        TextView name = (TextView)findViewById(R.id.cartNumber);
        name.setText("Carruagem "+carruagemAtual);
    }

    public void changeColor(View v) {
        Button b = (Button)v;
        Drawable drawableSelected = getResources().getDrawable(R.drawable.button_lugar_selected);
        Drawable drawableTaken = getResources().getDrawable(R.drawable.button_lugar_taken);
        Drawable drawableFree = getResources().getDrawable(R.drawable.button_lugar_free);

        // Clicou num lugar que estava selecionado
        if(b.getBackground().getConstantState() == drawableSelected.getConstantState()){
            b.setBackgroundResource(R.drawable.button_lugar_free);
            lugarEscolhido = 0;
        }else if(b.getBackground().getConstantState() == drawableTaken.getConstantState()){
            // Clicou num lugar que estava ocupado
            Toast.makeText(this, "Lugar já ocupado!", Toast.LENGTH_SHORT).show();
        }else{
            if(lugarEscolhido != 0) {
                Button tmp = (Button) findViewById(lugarEscolhido);
                tmp.setBackgroundResource(R.drawable.button_lugar_free);
            }
            lugarEscolhido = v.getId();
            b.setBackgroundResource(R.drawable.button_lugar_selected);
        }
    }

    public void changeCart(View v){
        int id = v.getId();
        if(id == R.id.carruagem_next && carruagemAtual<limiteCarruagens){
            carruagemAtual += 1;
        }else if(id == R.id.carruagem_previous && carruagemAtual > 1){
            carruagemAtual -= 1;
        }else{
            return;
        }
        setState();
    }

    public void confirmSelection(View v){
        if(lugarEscolhido==0){
            Toast.makeText(this, "Escolha um lugar.", Toast.LENGTH_LONG).show();
            return;
        }
        String lugar = getResources().getResourceName(lugarEscolhido);
        int lugarEscolhido = Integer.parseInt(lugar.split("/seat")[1]);

        Intent out = new Intent();
        out.putExtra("carruagem", carruagemAtual);
        out.putExtra("lugar", lugarEscolhido);
        setResult(RESULT_OK, out);
        finish();
    }
}
