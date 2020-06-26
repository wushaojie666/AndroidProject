package com.example.myseven;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class QueryAdapter extends ArrayAdapter<InfoBean> {
    private  int resourceId;
    public QueryAdapter(Context context, int textViewResourceId, List<InfoBean> objects) {
        super(context, textViewResourceId, objects);
        Log.v("输入","jieguo");
        resourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent ){
        InfoBean pc=getItem(position);

        Log.v("111222333211","122211");
        View view;
        if(convertView==null){
            Log.v("输入1","jieguo");
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            Log.v("输入2","jieguo");
        }
        else{
            view=convertView;
        }

        TextView name=(TextView)view.findViewById(R.id.tv_name);
        TextView id=(TextView)view.findViewById(R.id.tv_id);
        TextView sex=(TextView)view.findViewById(R.id.tv_sex);
        TextView phone=(TextView)view.findViewById(R.id.tv_phone) ;

        name.setText(String.valueOf(pc.getName()) );
        phone.setText(String.valueOf(pc.getPhone()));
        id.setText(String.valueOf(pc.getId()));
        sex.setText(String.valueOf(pc.getSex()));
        if(position==0){
            name.setBackgroundColor(Color.RED);
        }
        else if(position==1){
            name.setBackgroundColor(Color.YELLOW);
        }
        else{
            name.setBackgroundColor(Color.BLUE);
        }
        return view;
    }

}



