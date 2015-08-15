package bitcoin.hacktheplanet.cfb.bitcointiler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KeySubmit extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "bitcoin.hacktheplanet.cfb.bitcointiler.MESSAGE";

    Button submitButton;
    EditText publicKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set EditText and Button
        publicKey = (EditText) findViewById(R.id.key_enter);
        submitButton = (Button) findViewById(R.id.key_submit_button);

        //Set Button listener to submit key
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pkey = publicKey.getText().toString();
                if (pkey.matches("^[13][a-km-zA-HJ-NP-Z0-9]{26,33}$")) {

                }
            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_submit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_key_submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean buildQR() {
        String pkey = publicKey.getText().toString();
        if (pkey.matches("^[13][a-km-zA-HJ-NP-Z0-9]{26,33}$")) {

        } else {
            
        }
    }

}
