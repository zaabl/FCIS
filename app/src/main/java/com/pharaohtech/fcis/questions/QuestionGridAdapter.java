package com.pharaohtech.fcis.questions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.pharaohtech.fcis.R;

class QuestionGridAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private String[] words;
    private int[] imagesId;

    public QuestionGridAdapter(Context context, String[] subjects, int[] subjectImages) {
        this.context = context;
        words = subjects;
        imagesId = subjectImages;
    }


    @Override
    public int getCount() {
        return words.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.row_item, null);
        }

        CircularImageView imageView = convertView.findViewById(R.id.subjectImageView);
        TextView textView = convertView.findViewById(R.id.subjectTextView);

        imageView.setImageResource(imagesId[position]);
        textView.setText(words[position]);

        return convertView;
    }
}
