package com.example.dmorales.silabusprueba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmorales.silabusprueba.model.Curso;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CursosActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button btnCerrarSesion;
    private Query query;
    private RecyclerView recyclerView;
    private FirestoreRecyclerAdapter adapter;
    FirestoreRecyclerOptions<Curso> options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        btnCerrarSesion = (Button) findViewById(R.id.cursos_btnCerrarSesion);
        recyclerView = (RecyclerView) findViewById(R.id.cursos_recycler);

        query = FirebaseFirestore.getInstance().collection("Cursos").orderBy("tipo");
        options = new FirestoreRecyclerOptions.Builder<Curso>().setQuery(query, Curso.class).build();
        adapter = new FirestoreRecyclerAdapter<Curso, CursoHolder>(options) {
            @Override
            public void onBindViewHolder(CursoHolder holder, int position, Curso curso) {
                // Bind the Chat object to the ChatHolder
                holder.txtNombre.setText(curso.getNombre());
                holder.txtTipo.setText(curso.getTipo() + "");
                holder.txtProfesor.setText(curso.getProfesor());
            }

            @Override
            public CursoHolder onCreateViewHolder(ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(group.getContext()).inflate(R.layout.item_curso, group, false);
                return new CursoHolder(view);
            }
        };

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getInstance().signOut();
                Intent intent = new Intent(CursosActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
