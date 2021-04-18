package com.example.ps4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ps4.Models.Ville;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class CitiesActivity extends AppCompatActivity {
    ListView listView;
    private static final String TAG = "FirestoreListActivity";
    ArrayList<String> cityList = new ArrayList<>();
    String mTitle[] = {"City1", "City2", "City3", "City4", "City5"};
    String mDescription[] = {"Hotel Description", "Hotel Description", "Hotel Description", "Hotel Description", "Hotel Description" , "Hotel Description"};
    int images[] = {R.drawable.golden_view_restau, R.drawable.golden_view_restau, R.drawable.golden_view_restau, R.drawable.golden_view_restau, R.drawable.golden_view_restau, R.drawable.golden_view_restau};
    TextView nom , prenom ;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore ;
    String userID ;
    private ArrayAdapter<Ville> adapter ;
    private static final String Ville = "Ville";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        listView = findViewById(R.id.listView);
        ArrayAdapter<Ville> adapter = new ArrayAdapter<Ville>(CitiesActivity.this , android.R.layout.simple_list_item_1, new ArrayList<Ville>());

        nom = findViewById(R.id.textView);
        prenom = findViewById(R.id.textView3);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference =fstore.collection("User").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@androidx.annotation.Nullable DocumentSnapshot value, @androidx.annotation.Nullable FirebaseFirestoreException error) {
                nom.setText(value.getString("nom"));
                prenom.setText(value.getString("prenom"));
            }
        });
        // now create an adapter class

      // CitiesActivity.MyAdapter adapter = new CitiesActivity.MyAdapter(this, mTitle, mDescription, images);
      //  listView.setAdapter(adapter);

      /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Toast.makeText(CitiesActivity.this, "Hotel Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(CitiesActivity.this, "Hotel Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(CitiesActivity.this, "Hotel Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(CitiesActivity.this, "Hotel Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(CitiesActivity.this, "Hotel Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(CitiesActivity.this, "Hotel Description", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        ArrayList<Ville> villes = new ArrayList<>();
        CitiesActivity.MyAdapter adapter1 = new CitiesActivity.MyAdapter(this , villes ) ;
        listView.setAdapter(adapter);

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext() , LoginActivity.class));
        finish();
    }

   /* public void onRefreshClick(View view) {
        fstore.collection(Ville)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<Ville> villes = new ArrayList<>();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Ville p = document.toObject(Ville.class);
                            villes.add(p);
                            Log.d(TAG, p.getNom_pays() + " " + p.getNom_ville());
                        }

                        adapter.clear();
                        adapter.addAll(villes);

                    }
                });
    }
*/

    class MyAdapter extends ArrayAdapter<Ville> {
        ArrayList<Ville> villes ;
        Context context;

        int rImgs[];

        MyAdapter (Context c, ArrayList<Ville> villes) {
            super(c, R.layout.activity_row_hotels, R.id.textView1,villes);
            this.context = c;
            this.villes = villes;


        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.activity_row_city, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView cityname = row.findViewById(R.id.textView1);
            TextView country = row.findViewById(R.id.textView2);

            // now set our resources on views
            Ville ville = villes.get(position);
            images.setImageResource(rImgs[position]);
           cityname.setText(ville.getNom_ville());
           country.setText(ville.getNom_pays());

            return row;
        }
    }
}