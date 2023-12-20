# 在Android Studio 連接Firebase即時資料庫

### 打開Tools 並點選Firebase選項
<img src="https://i.imgur.com/7SOmqFv.png" width="80%">
####點選要的Realtime Database
<img src="https://i.imgur.com/Cx3j8uP.png" width="80%">
#### 系統會跳出每個步驟，點選connect to Firebase
<img src="https://i.imgur.com/icOHChm.png" width="80%">

#### 選擇要連接的專案

<img src="https://i.imgur.com/LlYKwel.png" width="80%">

#### 接著系統會跳出google帳號下所有的專案，這邊以新增專案為例
<img src="https://i.imgur.com/YLA0kOx.png" width="80%">

<img src="https://i.imgur.com/nJXdK35.png" width="80%">

<img src="https://i.imgur.com/JcPTtQs.png" width="80%">

<img src="https://i.imgur.com/c4liV6l.png" width="80%">

<img src="https://i.imgur.com/tV1q0vT.png" width="80%">

<img src="https://i.imgur.com/ctTe8PU.png" width="80%">

<img src="https://i.imgur.com/TNKLaHV.png" width="80%">

#### 到這邊代表已經連接成功

### 加入SDK
<img src="https://i.imgur.com/TiufvmI.png" width="80%">

### 選擇專案
<img src="https://i.imgur.com/792keY9.png" width="80%">

### 點選Realtime Database
<img src="https://i.imgur.com/Rn29QPi.png" width="50%">

<img src="https://i.imgur.com/hdzqPJK.png" width="80%">

<img src="https://i.imgur.com/64F4Bd9.png" width="80%">

### 注意 必須使用測試模式
<img src="https://i.imgur.com/S3E0QVY.png" width="80%">

<img src="https://i.imgur.com/x3HSYwL.png" width="80%">

### 恭喜 到這邊即時資料庫就已經設定完成，接下來開始撰寫程式碼

前端介面

<img src="https://i.imgur.com/AggL6d5.png" width="40%">

UI XML

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textShow"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
#### 讀取資料及寫入資料程式碼
```java
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference("message"
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

```
完整JAVA程式碼(將上面程式碼放入以下MainActivity中)
```java
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
```
### 執行

成功寫入你好，並讀取後顯示至螢幕上

<img src="https://i.imgur.com/6B7yYaq.png" width="50%">

<img src="https://i.imgur.com/PVkN94j.png" width="40%">

