package com.infobite.life.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import infobite.kumar.life.R;

public class ThankYouActivity extends AppCompatActivity {

    Button bt_thanku_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        bt_thanku_continue = findViewById(R.id.bt_thanku_continue);
        bt_thanku_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThankYouActivity.this, HomeNavigationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
