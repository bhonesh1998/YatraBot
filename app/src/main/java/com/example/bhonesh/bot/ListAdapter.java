package com.example.bhonesh.bot;

/**
 * Created by upesh on 19/3/18.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListAdapter extends ArrayAdapter<position> {

    private final Context context;
    private ArrayList<position> values;
    LayoutInflater layoutInflater = null;


    public ListAdapter(Context context, ArrayList<position> values) {
        super(context, -1, values);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public position getItem(int i) {
        return values.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder{
        TextView s;
    }
    ViewHolder viewHolder = null;

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

//        View v = convertView;
//
//        if (v == null) {
//            LayoutInflater vi;
//            vi = LayoutInflater.from(getContext());
//            v = vi.inflate(R.layout.listrow, null);
//        }
//
//        position p = getItem(i);
//
//        if (p != null) {
//            TextView tt1 = (TextView) v.findViewById(R.id.row);
//
//
//            if (tt1 != null) {
//                tt1.setText(p.getName());
//            }
//        }
//
//        return v;



        View vi=convertView;
        final int pos = i;
        if(vi == null){

            // create  viewholder object for list_rowcell View.
            viewHolder = new ViewHolder();

            vi = layoutInflater.inflate(R.layout.listrow,null);
            viewHolder.s = (TextView) vi.findViewById(R.id.row);
            vi.setTag(viewHolder);
        }else {


            viewHolder= (ViewHolder) vi.getTag();
        }


        viewHolder.s.setText(values.get(pos).getName());
        return vi;
    }
}
