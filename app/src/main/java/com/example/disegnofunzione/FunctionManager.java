package com.example.disegnofunzione;

import android.graphics.Color;

import java.util.List;

public class FunctionManager {
    static int N_COLORS = 5;
    static int[] COLORS = {
            Color.BLUE,
            Color.RED,
            Color.GREEN,
            Color.MAGENTA,
            Color.CYAN
    };


    static void addFunction(
            String function, List<FunctionToDraw> functions, Graph graph,
            RecyclerViewFunctionsAdapter adapter) {
        FunctionToDraw fx = new FunctionToDraw(graph, function, COLORS[functions.size()]);
        functions.add(fx);
        adapter.notifyItemInserted(functions.size() - 1);
        graph.drawAll();
    }

    static void deleteFunction(
            int position, List<FunctionToDraw> functions, Graph graph,
            RecyclerViewFunctionsAdapter adapter) {
        functions.remove(position);
        adapter.removeAt(position);
        graph.reDrawAll();
        
    }
}
