package com.example.douwe.todoist;

import android.content.Context;
import android.database.Cursor;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by douwe on 11/23/17.
 */

public class TodoAdapter extends ResourceCursorAdapter {
    public todoDatabase baas;
    public TodoAdapter(Context context, Cursor cursor){
        super(context, R.layout.row_xml, cursor);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int index = cursor.getColumnIndex("checked");

        CheckBox box = view.findViewById(R.id.checkBox);

        if(cursor.getInt(index) == 1){
            box.setChecked(true);
        } else{
            box.setChecked(false);
        }
        index = cursor.getColumnIndex("name");
        TextView textytexy = view.findViewById(R.id.textView);
        textytexy.setText(cursor.getString(index));
        textytexy.setTag(cursor.getString(cursor.getColumnIndex("_id")));
        todoDatabase naarHding = todoDatabase.getInstance(context);
        baas = naarHding;
    }



}
