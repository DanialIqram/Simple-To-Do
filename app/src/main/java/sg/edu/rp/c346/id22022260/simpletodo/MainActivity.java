package sg.edu.rp.c346.id22022260.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etToDo;
    Button btnAdd, btnDelete, btnClear;
    ListView lvTasks;
    Spinner spinnerTask;
    ArrayList<String> tasksArraylist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToDo = findViewById(R.id.editToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvTasks = findViewById(R.id.listViewTasks);
        spinnerTask = findViewById(R.id.taskSpinner);

        ArrayAdapter<String> aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasksArraylist);
        lvTasks.setAdapter(aaTasks);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etToDo.getText().toString();
                tasksArraylist.add(task);
                aaTasks.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tasksArraylist.size() < 1) {
                    Toast.makeText(MainActivity.this, R.string.noTaskWarning, Toast.LENGTH_LONG).show();
                    return;
                }

                String toDoText = etToDo.getText().toString();
                if (toDoText.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.noIndexWarning, Toast.LENGTH_LONG).show();
                    return;
                }

                int pos = Integer.parseInt(toDoText);
                if (pos < 0 || pos >= tasksArraylist.size()) {
                    Toast.makeText(MainActivity.this, R.string.invalidIndexWarning, Toast.LENGTH_LONG).show();
                    return;
                }

                tasksArraylist.remove(pos);
                aaTasks.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasksArraylist.clear();
                aaTasks.notifyDataSetChanged();
            }
        });

        spinnerTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etToDo.setText("");
                switch(position) {
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etToDo.setHint(R.string.enterTaskHint);
                        break;
                    case 1:
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etToDo.setHint(R.string.deleteTaskHint);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}