package com.example.unitestapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.unitestapplication.view.Subject.CreateSubject.CreateSubjectActivity
import com.example.unitestapplication.view.Subject.Monitor.ListsubjectsActivity
import com.example.unitestapplication.view.Subject.Search.SubjectSearchActivity
import com.example.unitestapplication.view.Subject.UpdateSubject.SubjectUpdateActivity
import com.example.unitestapplication.view.log_in.LogInActivity


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val professorid = intent.getIntExtra("professorid", -1)
        Log.e("Professor id in main activity ","Professor id in main activity "+professorid);
        // Βρες το κουμπί και πρόσθεσε τη λειτουργικότητα
        val buttonCreation = findViewById<Button>(R.id.button_creation)
        buttonCreation.setOnClickListener {
            val intent = Intent(this, CreateSubjectActivity::class.java)
            intent.putExtra("professorid",professorid);
            startActivity(intent)
        }

        val buttondelete= findViewById<Button>(R.id.button_delete)
        buttondelete.setOnClickListener {
            val intent = Intent(this, SubjectSearchActivity::class.java)
            intent.putExtra("professorid",professorid);
            startActivity(intent)
        }

        // Κώδικας για την προσθήκη της λειτουργικότητας του κουμπιού "Ενημέρωση Μαθήματος"
        val buttonUpdate = findViewById<Button>(R.id.button_update)
        buttonUpdate.setOnClickListener {
            val intent = Intent(this, SubjectUpdateActivity::class.java) // Δημιουργία Intent για την δραστηριότητα ενημέρωσης
            intent.putExtra("professorid",professorid);
            startActivity(intent)  // Εκκίνηση της δραστηριότητας ενημέρωσης

        }
        val buttonmonitor = findViewById<Button>(R.id.button_success)
        buttonmonitor.setOnClickListener {
            val intent = Intent(this, ListsubjectsActivity::class.java) // Δημιουργία Intent για την δραστηριότητα ενημέρωσης
            intent.putExtra("professorid",professorid);
            startActivity(intent)  // Εκκίνηση της δραστηριότητας ενημέρωσης

        }


        val buttonexit = findViewById<Button>(R.id.button_prof_exit)
        buttonexit.setOnClickListener {
            val intent1 = Intent(this, LogInActivity::class.java) // Δημιουργία Intent για την δραστηριότητα εισόδου
            intent.putExtra("professorid",professorid);
            startActivity(intent1)  // Εκκίνηση της δραστηριότητας εισόδου

        }




    }
}



