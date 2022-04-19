package com.example.fqmath;

//classe per salvare dati inerenti alla dimensione del riquadro (nel nostro caso, utilizzato per
// il riquadro di una tela)
public class Bounds {

    private float offset = 0.04f;   //moltiplicatore, serve epr adattare la distanza di scorrimento

    public float getCenterX() {     //getter centerX
        return centerX;
    }

    public void setCenterX(float centerX) {     //setter centerX
        this.centerX = centerX;
    }

    private float centerX;      //centro riquadro (x)

    public float getCenterY() {     //getter centerY
        return centerY;
    }

    public void setCenterY(float centerY) {     //setter centerY
        this.centerY = centerY;
    }

    private float centerY;      //centro riquadro (y)

    public float getEndX() {    //getter endX
        return endX;
    }

    private float endX;     //fine riquadro (x)

    public float getEndY() {        //getter endY
        return endY;
    }

    private float endY;     //fine riquadro (x)


    private Bounds() {      //costruttore di default, implementato per sicurezza...

    }

    public Bounds(float centerX, float centerY, float endX, float endY) {       //costruttore
        this.centerX = centerX;
        this.centerY = centerY;
        this.endX = endX;
        this.endY = endY;
    }

    public void setOffsetX(float offsetX) {     //sposta il centro del riquadro di offsetX
        setCenterX(getCenterX() - Math.round(offsetX*offset));
    }

    public void setOffsetY(float offsetY) {     //sposta il centro del riquadro di offsetY
        setCenterY(getCenterY() - Math.round(offsetY*offset));
    }
}
