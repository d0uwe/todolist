package com.example.douwe.todoist;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public todoDatabase baas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoDatabase db = todoDatabase.getInstance(getApplicationContext());
        baas = db;
        Cursor selectedall = db.selectAll();
        System.out.println(selectedall.toString());
        TodoAdapter adaptor = new TodoAdapter(this, selectedall);
        ListView listylistview = findViewById(R.id.listView);
        listylistview.setOnItemClickListener(new ShortHandleClick());
        listylistview.setOnItemLongClickListener(new LongHandleClick());

        listylistview.setAdapter(adaptor);
        Button butt = findViewById(R.id.button);
        butt.setOnClickListener(new onAddButton());
    }

    public void addItem(String stringy, int completed){
        todoDatabase db = todoDatabase.getInstance(this);
        db.insert(stringy, completed);
    }

    public void updateData(){
        todoDatabase db = todoDatabase.getInstance(getApplicationContext());
        Cursor selectedall = db.selectAll();
        TodoAdapter adaptor = new TodoAdapter(this, selectedall);
        ListView listylistview = findViewById(R.id.listView);
        listylistview.setAdapter(adaptor);
    }

    public void onclickButton(View view){
        EditText textediter = findViewById(R.id.editText);
        String input = textediter.getText().toString();
        addItem(input, 0);
        updateData();
    }

    private class onAddButton implements View.OnClickListener {
        @Override
        public void onClick(View view){
            EditText textediter = findViewById(R.id.editText);
            String input = textediter.getText().toString();
            addItem(input, 0);
            updateData();
        }
    }

    public class ShortHandleClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            System.out.println("CLICKED");
            CheckBox checkBox = view.findViewById(R.id.checkBox);
            TextView textView = view.findViewById(R.id.textView);

            int id = Integer.parseInt(textView.getTag().toString());
            int completed;
            if(checkBox.isChecked()){
                completed = 0;
                checkBox.setChecked(false);
            } else {
                completed = 1;
                checkBox.setChecked(true);
            }
            System.out.println("Status: " + Integer.toString(completed));
            baas.update(id, completed);
            updateData();
            System.out.println(id);
        }
    }

    public class LongHandleClick implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            System.out.println("CLICKED");
            TextView textView = view.findViewById(R.id.textView);

            int id = Integer.parseInt(textView.getTag().toString());
            baas.delete(id);
            updateData();
            System.out.println(id);
            return true;
        }
    }

}


