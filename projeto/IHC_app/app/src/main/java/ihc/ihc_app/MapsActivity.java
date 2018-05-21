package ihc.ihc_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private HashMap<String,Double[]> estacoes;
    private ArrayList<String> nomesEstacoes;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        loadEstacoes();

        // Load spinner
        sp = (Spinner) findViewById(R.id.spinner);
        nomesEstacoes = new ArrayList<>();
        nomesEstacoes.addAll(estacoes.keySet());

        // Sorting spinner values
        Collections.sort(nomesEstacoes, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2)
            {

                return  str1.compareToIgnoreCase(str2);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_spinner_layout, nomesEstacoes);
        sp.setAdapter(adapter);
        sp.setBackgroundResource(R.color.mdtp_white);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String est = (String)sp.getItemAtPosition(i);
                if (est == null) return;
                if (est.length()==0) return;

                Double[] coords = estacoes.get(est);
                LatLng lat = new LatLng(coords[0],coords[1]);
                mMap.addMarker(new MarkerOptions().position(lat).title(est));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(lat));
                mMap.moveCamera(CameraUpdateFactory.zoomTo(16));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void loadEstacoes(){
        /*
        if (estacoes == null) {
            estacoes = new HashMap<>();
            estacoes.put("Aveiro", new Double[]{40.6452789306641, -8.63984107971191});
            estacoes.put("Coimbra", new Double[]{40.2096748352051, -8.43318653106689});
            estacoes.put("Coimbra-B", new Double[]{40.2243652343750, -8.44046783447266});
            estacoes.put("Quintans", new Double[]{40.5909996032715, -8.61276054382324});
            estacoes.put("Oia", new Double[]{40.5444869995117, -8.55060195922852});
            estacoes.put("Oliveira do Bairro", new Double[]{40.5106925964355, -8.49930953979492});
            estacoes.put("Paraimo-Sangalhos", new Double[]{40.4810981750488, -8.48033428192139});
            estacoes.put("Mogofores", new Double[]{40.4507408142090, -8.45952129364014});
            estacoes.put("Curia", new Double[]{40.4257431030273, -8.45589065551758});
            estacoes.put("Aguim", new Double[]{40.4162559509277, -8.45672607421875});
            estacoes.put("Mealhada", new Double[]{40.3762855529785, -8.45387458801270});
            estacoes.put("Pampilhosa", new Double[]{40.3361129760742, -8.42953968048096});
            estacoes.put("Souselas", new Double[]{40.2856407165527, -8.42592525482178});
            estacoes.put("Vilela-Fornos", new Double[]{40.2625350952148, -8.44158840179443});
            estacoes.put("Ademia", new Double[]{40.2503356933594, -8.45053958892822});
            estacoes.put("Vila Nova de Gaia - Devesas", new Double[]{41.1300430297852, -8.62022781372070});
            estacoes.put("Porto-Campanha", new Double[]{41.1496238708496, -8.58551406860352});
            estacoes.put("Porto-Sao Bento", new Double[]{41.1456642150879, -8.60959243774414});
            estacoes.put("Espinho", new Double[]{41.0061950683594, -8.64448547363281});
        }*/
        if(estacoes == null){
            estacoes = new HashMap<>();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(getAssets().open("trainStations.csv"), "UTF-8"));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

                String[] nextRecord;
                while ((nextRecord = csvReader.readNext()) != null) {
                    String estacao = nextRecord[2];
                    double lat = Double.parseDouble(nextRecord[4]);
                    double lon = Double.parseDouble(nextRecord[5]);
                    Log.d("ESTACOESREAD",nextRecord[2]+" "+nextRecord[4]+" "+nextRecord[5]);
                    estacoes.put(estacao, new Double[]{lat, lon});
                }
            } catch (IOException e) { }
            finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) { }
                }
            }
        }
    }

    private void loadMarkers(){
        // Add locations of stations
        for(String s : nomesEstacoes){
            Double[] coords = estacoes.get(s);
            LatLng lat = new LatLng(coords[0],coords[1]);
            mMap.addMarker(new MarkerOptions().position(lat).title(s));
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng aveiro = new LatLng(40.64527893, -8.63984108);
        loadMarkers();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(aveiro));
    }
}
