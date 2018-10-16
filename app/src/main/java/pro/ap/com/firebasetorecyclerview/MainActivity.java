package pro.ap.com.firebasetorecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ModelClass> mylist;

    private RecyclerAdapterClass recyclerAdapterClass;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mylist = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView_xml);


        LinearLayoutManager mlinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mlinearLayoutManager.setReverseLayout(true);
        mlinearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mlinearLayoutManager);

        recyclerAdapterClass = new RecyclerAdapterClass(mylist);

//        recyclerAdapterClass.setonitemclick(this);

        recyclerView.setAdapter(recyclerAdapterClass);

        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference db = firebaseDatabase.getReference();
        DatabaseReference userref = db.child("Images").child("URL");


        userref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dataDoWork(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                dataDoWork(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                dataDoWork(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                dataDoWork(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//
//// write to other firebase database
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setApplicationId("1:1053786409822:android:7ff3197c14199a3f") // Required for Analytics.
//                .setApiKey("AIzaSyA1MgaiO9Y6NyQShPms096vsvEizxMtlgw") // Required for Auth.
//                .setDatabaseUrl("https://my-complain.firebaseio.com/") // Required for RTDB.
//                .build();
//
//        // Initialize with secondary app.
//        FirebaseApp.initializeApp(this /* Context */, options, "secondary");
//
//// Retrieve secondary app.
//        FirebaseApp secondary = FirebaseApp.getInstance("secondary");
//// Get the database for the other app.
//        FirebaseDatabase secondaryDatabase = FirebaseDatabase.getInstance(secondary);
//        DatabaseReference secRef = secondaryDatabase.getReference();
//        DatabaseReference secondaryRef = secRef.child("yo");
//        secondaryRef.push().setValue("ooo").addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//                Toast.makeText(MainActivity.this, "error " + e, Toast.LENGTH_SHORT).show();
//                Log.i("tag", "Failed second" + e );
//            }
//        });
//
    }

    public void dataDoWork(DataSnapshot dataSnapshot){
        ModelClass data = dataSnapshot.getValue(ModelClass.class);
        mylist.add(data);

        recyclerAdapterClass.notifyDataSetChanged();
    }}
//
//    @Override
//    public void onitemclick(int position) {
//        Toast.makeText(this, "yoyo " + position, Toast.LENGTH_SHORT).show();
//    }
//}
