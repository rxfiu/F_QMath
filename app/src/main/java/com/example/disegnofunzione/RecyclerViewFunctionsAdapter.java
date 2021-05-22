package com.example.disegnofunzione;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewFunctionsAdapter extends RecyclerView.Adapter<RecyclerViewFunctionsAdapter.ViewHolder> {

    Graph graph;
    List<FunctionToDraw> functions;
    LayoutInflater inflater;

    RecyclerViewFunctionsAdapter(Context context, Graph graph, List<FunctionToDraw> data) {
        this.graph = graph;
        this.functions = data;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RecyclerViewFunctionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_function_button, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewFunctionsAdapter.ViewHolder holder, int position) {
        final FunctionToDraw fx = functions.get(position);
        holder.textViewFunction.setText(fx.toString());
        holder.buttonDeleteFunction.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        graph.deleteFunction(fx.toString());
                    }
                }
                );
    }

    @Override
    public int getItemCount() {
        return functions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFunction;
        Button buttonDeleteFunction;

        ViewHolder(View itemView) {
            super(itemView);
            textViewFunction = itemView.findViewById(R.id.textViewFunctionText);
            buttonDeleteFunction = itemView.findViewById(R.id.buttonFunctionDelete);
        }

//        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView myTextView;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            myTextView = itemView.findViewById(R.id.tvAnimalName);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
//    }
//
//    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData.get(id);
//    }
//
//    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
    }

//    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData.get(id);
//    }
//
//    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
}