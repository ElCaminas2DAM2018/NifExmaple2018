package org.ieselcaminas.pmdm.nifexmaple2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextName = null;
    private static int ACTIVITY_ID = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);

        Button buttonNif = (Button) findViewById(R.id.buttonNIF);
        buttonNif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EnterNifActivity.class);
                intent.putExtra("name",editTextName.getText().toString());
                startActivityForResult(intent, ACTIVITY_ID);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==ACTIVITY_ID && resultCode==RESULT_OK) {
            Bundle extras = data.getExtras();
            String nifString = extras.getString("result");

            TextView textViewNif = (TextView) findViewById(R.id.textViewNif);
            textViewNif.setText(nifString);

        }
    }
}
