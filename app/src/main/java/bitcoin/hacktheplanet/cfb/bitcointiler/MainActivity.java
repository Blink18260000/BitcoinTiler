package bitcoin.hacktheplanet.cfb.bitcointiler;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import cyanogenmod.app.CMStatusBarManager;
import cyanogenmod.app.CustomTile;

public class MainActivity extends AppCompatActivity {

    ImageView qrImage;
    Intent intent;
    TextView walletid;
    WebView qrWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        String message = intent.getStringExtra(KeySubmit.EXTRA_MESSAGE);

        //Temporary example wallet
        message = "1BmTrNt7EBcE5Wj3vBLHjvtP7L8TcXyEPS";

        //walletid = (TextView) findViewById(R.id.walletID);
        //walletid.setText(message);

        //qrImage = (ImageView) findViewById(R.id.QRimage);
        //qrImage.setImageDrawable(R.drawable.exqrcode);

        //Intent oldIntent = getIntent();
        //message = oldIntent.getStringExtra(KeySubmit.EXTRA_MESSAGE);

        // Create a remoteviews object
        RemoteViews contentView = new RemoteViews(getPackageName(),
                R.layout.remote_view);

        Uri thing = Uri.parse("https://" +
                "2.info/qr?data=1Agb153xWsbqS9vt8gP4vBFKHkAchLMdSX&size=200");

        //Bitmap bmp = getBitmapFromURL("https://blockchain.info/qr?data=1Agb153xWsbqS9vt8gP4vBFKHkAchLMdSX&size=200");
        /*
        try {
            // generate a 150x150 QR code
            Bitmap bm = encodeAsBitmap(message, BarcodeFormat.QR_CODE, 150, 150);

            if(bm != null) image_view.setImageBitmap(bm);
        } catch (WriterException e) { //eek }
        */
        // contentView.setImageViewResource(R.id.QRimage, R.drawable.exqrcode);



        contentView.setImageViewUri(R.id.QRimage,thing);


        //contentView.(R.id.QR_webview).loadUrl("https://blockchain.info/qr?data=1Agb153xWsbqS9vt8gP4vBFKHkAchLMdSX&size=200");
        //contentView.setUri(R.id.QR_webview, "loadUrl", thing);
        //qrWebview = (WebView) findViewById(R.id.QR_webview);
        //qrWebview.loadUrl("https://blockchain.info/qr?data=1Agb153xWsbqS9vt8gP4vBFKHkAchLMdSX&size=200");
        //walletid = (TextView) contentView.findViewById(R.id.walletID);
        //walletid.setText(message);



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
                .setLabel("Wallet QR Code")
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
