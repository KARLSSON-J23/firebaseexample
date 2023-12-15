    package com.example.firebaseexample;

    import static android.content.ContentValues.TAG;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import android.os.Bundle;
    import android.util.Log;
    import android.widget.TextView;

    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.IgnoreExtraProperties;
    import com.google.firebase.database.ValueEventListener;

    import org.w3c.dom.Text;

    public class MainActivity extends AppCompatActivity {
        private DatabaseReference mDatabase;
        private TextView show;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            show = findViewById(R.id.textShow);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("message");

            //寫入值
            myRef.setValue("你好");

            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                //讀取資料
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // 使用初始值呼叫此方法一次，然後再次調用
                    // 每當此位置的資料更新時。
                    String value = dataSnapshot.getValue(String.class);
                    show.setText(value);
                    Log.d("TAG", "Value is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // 讀取值失敗
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });

        }

    }