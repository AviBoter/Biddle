package com.example.biddle.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biddle.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

import Models.Customer;
import Models.Seller;
import Utils.SetDate;

public class EditProfileCustomerActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference refUser;
    private ProgressBar progressb;
   private TextView  card_tv, cvv_tv,edit_btn;
   private EditText  date_tv;
   private int CardNumber,cvvNumber;
    private String card;
    private String userId;
    private SetDate setDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_customer);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        refUser = database.getReference().child("Users").child(userId).child("CostumerDetails");

        progressb = (ProgressBar)findViewById(R.id.progressBar);
        progressb.setVisibility(View.GONE);

        card_tv  =(TextView)findViewById(R.id.cardnumber);
        cvv_tv  =(TextView)findViewById(R.id.cvv1);
        date_tv  =(EditText) findViewById(R.id.expireDate);
        edit_btn  =(TextView)findViewById(R.id.update_btn);
        setDate = new SetDate(date_tv);//need to check

        date_tv.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           if (view == date_tv) {
                                               Calendar systemCalender = Calendar.getInstance();
                                               int year = systemCalender.get(Calendar.YEAR);
                                               int month = systemCalender.get(Calendar.MONTH);
                                               int day = systemCalender.get(Calendar.DAY_OF_MONTH);
                                               DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfileCustomerActivity.this, setDate, year, month, day);
                                               datePickerDialog.show();
                                           }
                                       }
                                   });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card = card_tv.getText().toString().trim();
              String  cvV = cvv_tv.getText().toString().trim();

               //validation
                boolean flag = true;
                if (TextUtils.isEmpty(card)) {
                    ((EditText) findViewById(R.id.cardnumber)).setError(R.string.mustFill + "");
                    flag = false;
                } else {
                    CardNumber = Integer.parseInt(card);
                }

                if (TextUtils.isEmpty(cvV)  ) {
                    ((EditText) findViewById(R.id.cvv1)).setError(R.string.mustFill + "");
                    flag = false;
                }

                if(cvV.length()!=3){
                    ((EditText) findViewById(R.id.cvv1)).setError("הכנס שלוש ספרות");
                    flag = false;
                }
                else {
                    cvvNumber = Integer.parseInt(cvV);
                }



                if (TextUtils.isEmpty(date_tv.getText().toString().trim())) {
                    ((EditText) findViewById(R.id.cardnumber)).setError(R.string.mustFill + "");
                    flag = false;
                }

                if(flag) {
                    Date dateTime = new Date(setDate.getYear() ,setDate.getMonth()-1,setDate.getDay());
                   int year = setDate.getYear();// dateTime.getYear();
                   int month =setDate.getMonth();            //dateTime.getMonth();
                   ExpireDate ex = new ExpireDate(year,month);
                    Customer costomer = new Customer( userId,CardNumber, cvvNumber,ex) ;
                    WriteToDB(costomer,refUser);

                }
                }
            });

        }

    private void WriteToDB(Customer customer, DatabaseReference ref) {

        progressb.setVisibility(View.VISIBLE);

        ref.setValue(customer,new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    progressb.setVisibility(View.GONE);
                    Toast.makeText(EditProfileCustomerActivity.this, "עדכון נכשל", Toast.LENGTH_SHORT).show();
                }
                else finish();
            }
        });
    }
}
