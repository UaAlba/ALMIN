package com.example.ualba.almin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JoinMemberFirst extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private EditText et_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_member_first);


        et_address = (EditText)findViewById(R.id.et_address);

        Button addressBtn = (Button)findViewById(R.id.addressbtn);

        addressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinMemberFirst.this, JoinMemberWebview.class);
                startActivityForResult(intent, SEARCH_ADDRESS_ACTIVITY);
            }
        });

        Intent intent = getIntent();
        String addr  =  intent.getStringExtra("addr");

        if("" != addr){
            Log.d("현주", "ifififififi");
            et_address.setText(addr);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        super.onActivityResult(requestCode, resultCode, intent);

        switch(requestCode){

            case SEARCH_ADDRESS_ACTIVITY:

                if(resultCode == RESULT_OK){

                    String data = intent.getExtras().getString("data");
                    if (data != null)
                        et_address.setText(data);

                }
                break;

        }

    }


}
