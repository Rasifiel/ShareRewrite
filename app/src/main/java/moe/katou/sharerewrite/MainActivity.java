package moe.katou.sharerewrite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleTextRewrite(intent); // Handle text being sent
            }
        }
        setContentView(R.layout.activity_main);
    }

    private void handleTextRewrite(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            String rewrittenText = sharedText.replace("twitter", "fxtwitter");
            Intent newIntent = new Intent();
            newIntent.setAction(Intent.ACTION_SEND);
            newIntent.putExtra(Intent.EXTRA_TEXT, rewrittenText);
            newIntent.setType("text/plain");
            startActivity(Intent.createChooser(newIntent, getResources().getText(R.string.send_to)));
        }
    }
}