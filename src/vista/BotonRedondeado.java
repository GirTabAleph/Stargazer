/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author Usuario
 */
public class BotonRedondeado extends JButton{ // implements Cloneable{
    
    
    public BotonRedondeado(String texto){
        
        super(texto);
        
        setBorder(new BordeRedondeado());
    }
    
    public BotonRedondeado(BotonRedondeado boton){
    
        this.setText(boton.getText());
        
        setBorder(new BordeRedondeado());
    }
    
     //Paso 3: Sobreescribir el metodo clone().
    @Override
    protected BotonRedondeado clone() throws CloneNotSupportedException{
        
        return new BotonRedondeado(this);
    }
    
    private class BordeRedondeado implements Border{
    

        private int radio = 7;
        
        public BordeRedondeado(){
        
         //   this.radio = boton.getRadio();
        }

        public int getRadio(){

            return radio;
        }

        public Insets getBorderInsets(Component componente){

            return  new Insets(this.radio + 1, this.radio + 1, 
                this.radio + 2, this.radio);
        }

        public boolean isBorderOpaque(){

            return true;
        }

        public void paintBorder(Component componente, Graphics grafico,
            int columna, int renglon, int ancho, int alto){

            grafico.drawRoundRect(columna, renglon, ancho - 1, alto, radio, radio);
        }

       

        
    }   
}

