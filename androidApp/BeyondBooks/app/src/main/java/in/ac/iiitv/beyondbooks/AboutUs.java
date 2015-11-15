package in.ac.iiitv.beyondbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class AboutUs extends AppCompatActivity {

    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        wv = (WebView)findViewById(R.id.wv);
        wv.loadUrl("http://10.100.5.226/BeyondBooks/web/about_us.php");
    }
}
