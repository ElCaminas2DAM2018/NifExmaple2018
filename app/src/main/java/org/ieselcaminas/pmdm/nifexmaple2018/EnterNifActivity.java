package org.ieselcaminas.pmdm.nifexmaple2018;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnterNifActivity extends AppCompatActivity {

    private Dni dni = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_nif_layout);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        TextView greetingLabel = (TextView) findViewById(R.id.greetingLabel);
        greetingLabel.setText(name);

        Button botonOk = (Button) findViewById(R.id.buttonOk);
        botonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextNif = (EditText) findViewById(R.id.editTextNif);
                try {
                    String s = editTextNif.getText().toString();
                    if (!s.isEmpty()) {
                        dni = new Dni(s);
                        Intent intent = new Intent();
                        intent.putExtra("result",dni.toFormattedString());
                        setResult(RESULT_OK,intent);
                        finish();
                    } else {
                        throw new NIFException();
                    }
                } catch (NIFException e) {
                    dni = null;
                    new AlertDialog.Builder(EnterNifActivity.this)
                            .setTitle(R.string.Error)
                            .setMessage(R.string.WrongNif)
                            .create().show();
                }
            }
        });
    }
}
