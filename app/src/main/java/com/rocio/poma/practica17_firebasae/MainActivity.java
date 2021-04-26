package com.rocio.poma.practica17_firebasae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference dbRef=db.getReference("mensaje");
        //dfRef.setValue("hola modificado");

        ListView listView=findViewById(R.id.listView);
        List<Estado> estadosList=new ArrayList<>();
        ArrayAdapter<Estado> adapter= new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,estadosList);
        listView.setAdapter(adapter);


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot snap: dataSnapshot.getChildren()){
                    Estado e =snap.getValue(Estado.class);
                    adapter.add(e);
                }
                //Toast.makeText(getApplicationContext(),"Se actualizo los Datos",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Button btn = findViewById(R.id.btnadd);
        editText=findViewById(R.id.editTextAgregar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String estado=editText.getText().toString();
                String id=dbRef.push().getKey();
                Estado e=new Estado(id,estado);
                dbRef.child(id).setValue(e);
                editText.setText("");
            }
        });

    }
}