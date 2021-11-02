package i.errajraji.callphone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText phone = findViewById(R.id.number);
        final ImageButton call = findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               callAtRuntime();
            }
        });
    }
private void callAtRuntime() {
   

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {

            BreakIterator phone = null;
            String phoneNumber = phone.getText().toString();
            Intent intent = new Intent(Intent.ACTION_CALL);

            Object tel;
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 callAtRuntime();
            } else {
                Toast.makeText(this, "Oh No!!! Permission Denied. Try Again!", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
