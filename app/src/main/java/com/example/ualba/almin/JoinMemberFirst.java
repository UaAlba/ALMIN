package com.example.ualba.almin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JoinMemberFirst extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_member_first);

        Button addressBtn = (Button)findViewById(R.id.addressbtn);
        addressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                startActivityForResult(intent, SEARCH_ADDRESS_ACTIVITY);
            }
        });

    }
}
