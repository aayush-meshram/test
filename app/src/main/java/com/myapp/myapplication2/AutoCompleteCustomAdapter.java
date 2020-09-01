package com.myapp.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteCustomAdapter extends ArrayAdapter<nasaInfo> {
    public List<nasaInfo> nasaList;

    public AutoCompleteCustomAdapter(@NonNull Context context, @NonNull List<nasaInfo> objects) {
        super(context, 0, objects);
        nasaList = objects;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return nasaFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.scroll_down_text, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.text_search);
        nasaInfo info = getItem(position);
        if(info != null)    {
            textView.setText(info.getTitle());
        }
        return convertView;
    }

    public Filter nasaFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            List<nasaInfo> recommends = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                nasaInfo info = new nasaInfo("Search for something", null);
                recommends.add(info);
            } else {
                String pattern = charSequence.toString();
                String URL = "https://images-api.nasa.gov/search?q=" + pattern + "&media_type=image";
                Loader loader = new Loader();
                recommends = loader.getResults(URL, getContext());
                L
            }
            filterResults.values = recommends;
            filterResults.count = recommends.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((nasaInfo) resultValue).getTitle();
        }
    };
}
