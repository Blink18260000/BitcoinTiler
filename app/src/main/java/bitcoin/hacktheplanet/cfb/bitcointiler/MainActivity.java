package bitcoin.hacktheplanet.cfb.bitcointiler;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RemoteViews;

import cyanogenmod.app.CMStatusBarManager;
import cyanogenmod.app.CustomTile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent oldIntent = getIntent();
        String message = oldIntent.getStringExtra(KeySubmit.EXTRA_MESSAGE);

        // Create a remoteviews object
        RemoteViews contentView = new RemoteViews(getPackageName(),
                R.layout.remote_view);

        // Create intent for the onclick button
        Intent cyngnIntent = new Intent(Intent.ACTION_VIEW)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .setData(Uri.parse("http://www.cyngn.com"));

        // Get a pending intent for the defined intent
        PendingIntent intent = PendingIntent.getActivity(this, 0,
                cyngnIntent, 0);

        // Set the pending intent on the button in our layout
        contentView.setOnClickPendingIntent(R.id.whats_hot_click, intent);

        // Create the new RemoteExpandedStyle
        CustomTile.RemoteExpandedStyle remoteExpandedStyle =
                new CustomTile.RemoteExpandedStyle();
        remoteExpandedStyle.setRemoteViews(contentView);

        // Build the custom tile
        CustomTile customTile = new CustomTile.Builder(this)
                .setLabel("Remote Style From SDK")
                .setIcon(R.drawable.testimage)
                .setExpandedStyle(remoteExpandedStyle)
                .setOnSettingsClickIntent(new Intent(this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                .setContentDescription("Description of content for expanded style")
                .build();

        // Publish the custom tile
        CMStatusBarManager.getInstance(this)
                .publishTile(3, customTile);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
