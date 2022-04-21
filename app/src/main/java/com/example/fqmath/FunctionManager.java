package com.example.fqmath;

import android.graphics.Color;
import java.util.List;
import java.util.Random;

//gestore dell'interfaccia,
public class FunctionManager {
    public static int getN() {  //numero progressivo
        return N++;
    }

    static int N = 1; //numero progressivo

    static int getColor(int position) {
        return COLOR[(int)(position % COLOR.length)];
    }

    static int[] COLOR = {  //array di colori
            Color.CYAN,
            Color.MAGENTA,
            Color.GREEN,
            Color.RED,
            Color.BLUE,
    };

    //aggiunge una funzione alla lista, da disegnare
    static void addFunction(
            String function, List<FunctionToDraw> functions, Graph graph,
            RecyclerViewFunctionsAdapter adapter) {
        //verifica se il valore inserito è valido
        try {
            //se è valido disegna la funziione e la aggiunge alla lista
            FunctionToDraw fx = new FunctionToDraw(graph, function, getColor(getN()));
            functions.add(fx);
            adapter.notifyItemInserted(functions.size() - 1);
            graph.drawAll();
        }
        catch (Exception e) {
            //se non è valido, non fa niente
        }
    }

    //elimina una funzione dalla lista
    static void deleteFunction(
            int position, List<FunctionToDraw> functions, Graph graph,
            RecyclerViewFunctionsAdapter adapter) {
        functions.remove(position);
        adapter.removeAt(position);
        graph.reDrawAll();
    }

    //aumenta lo zoom del grafico
    static void zoomIn(Graph graph) {
        graph.zoomIn();
    }

    //aumenta lo zoom del grafico
    static void zoomOut(Graph graph) {
        graph.zoomOut();
    }
}
