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

    private Graph graph;
    private List<FunctionToDraw> functions;
    private LayoutInflater inflater;

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
    public void onBindViewHolder(@NonNull RecyclerViewFunctionsAdapter.ViewHolder holder, final int position) {
        final FunctionToDraw fx = functions.get(position);
        holder.textViewFunction.setText(fx.toString());
        holder.buttonDeleteFunction.setBackgroundColor(fx.getColor());
        final RecyclerViewFunctionsAdapter adapter = this;
        holder.buttonDeleteFunction.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FunctionManager.deleteFunction(position, functions, graph, adapter);
                    }
                }
                );
    }

    @Override
    public int getItemCount() {
        return functions.size();
    }

    public void add() {
        notifyItemInserted(functions.size() - 1);
    }
    public void removeAt(int position) {
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, functions.size());
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFunction;
        Button buttonDeleteFunction;

        ViewHolder(View itemView) {
            super(itemView);
            textViewFunction = itemView.findViewById(R.id.textViewFunctionText);
            buttonDeleteFunction = itemView.findViewById(R.id.buttonFunctionDelete);
        }
    }
}