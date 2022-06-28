/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Model;
import view.View;
import javax.swing.*;
import java.awt.event.*;
import view.DataView;
/**
 *
 * @author bryam
 */
public class Controller{
    private final Model model;
    private final View view;
    private final JLabel visor;
    private final JLabel subVisor;
    private final JPanel panelCabezera;
    private DataView dataView;
    private boolean resMostrado;//para saber si en el visor se esta mostrando el resultado de haber realizado operacion
    private boolean negativo;//para saber si el usuario quiere ingresar el primer num negativo
    private boolean mPlus;//para saber si M+ fue usado
    
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.visor = view.getVisor();
        this.subVisor = view.getSubVisor();
        this.panelCabezera = view.getPanelCabezera();
        this.resMostrado = false;
        this.negativo = false;
        this.mPlus = false;
        
        this.view.setFocusable(true);
        this.view.requestFocusInWindow();
        
        KeyListener k;
        k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char tecla = e.getKeyChar();
                switch (tecla) {
                    case '0' -> numeroPresionado(Character.getNumericValue(tecla));
                    case '1' -> numeroPresionado(Character.getNumericValue(tecla));
                    case '2' -> numeroPresionado(Character.getNumericValue(tecla));
                    case '3' -> numeroPresionado(Character.getNumericValue(tecla));
                    case '4' -> numeroPresionado(Character.getNumericValue(tecla));
                    case '5' -> numeroPresionado(Character.getNumericValue(tecla));
                    case '6' -> numeroPresionado(Character.getNumericValue(tecla));
                    case '7' -> numeroPresionado(Character.getNumericValue(tecla));
                    case '8' -> numeroPresionado(Character.getNumericValue(tecla));
                    case '9' -> numeroPresionado(Character.getNumericValue(tecla));
                    case '.' -> puntoPresionado();
                    case '+' -> operadorPresionado(String.valueOf(tecla));
                    case '-' -> operadorPresionado(String.valueOf(tecla));
                    case '*' -> operadorPresionado(String.valueOf(tecla));
                    case '/' -> operadorPresionado(String.valueOf(tecla));
                    case '=' -> realizarOperacion();
                    case 'c' -> reiniciar(0);
                    case 'C' -> reiniciar(0);
                        
                    default -> {
                        //pass
                    }
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
               
            }
        };
        this.view.addKeyListener(k);
        
        iniciarBotones();
    }

    private void iniciarBotones() { 
        //Metodo para poner a la escucha de los botones de la vista
        
        // botones numericos
        this.btn0 = this.view.getBtn0();
        btn0.addActionListener((ActionEvent evt) -> {
            numeroPresionado(Integer.parseInt(btn0.getText()));
        });
        
        this.btn1 = this.view.getBtn1();
        btn1.addActionListener((ActionEvent evt) -> {
            numeroPresionado(Integer.parseInt(btn1.getText()));
        });
        
        this.btn2 = this.view.getBtn2();
        btn2.addActionListener((ActionEvent evt) -> {
            numeroPresionado(Integer.parseInt(btn2.getText()));
        });
        
        this.btn3 = this.view.getBtn3();
        btn3.addActionListener((ActionEvent evt) -> {
            numeroPresionado(Integer.parseInt(btn3.getText()));
        });
        
        this.btn4 = this.view.getBtn4();
        btn4.addActionListener((ActionEvent evt) -> {
            numeroPresionado(Integer.parseInt(btn4.getText()));
        });
        
        this.btn5 = this.view.getBtn5();
        btn5.addActionListener((ActionEvent evt) -> {
            numeroPresionado(Integer.parseInt(btn5.getText()));
        });
        
        this.btn6 = this.view.getBtn6();
        btn6.addActionListener((ActionEvent evt) -> {
            numeroPresionado(Integer.parseInt(btn6.getText()));
        });
        
        this.btn7 = this.view.getBtn7();
        btn7.addActionListener((ActionEvent evt) -> {
            numeroPresionado(Integer.parseInt(btn7.getText()));
        });
        
        this.btn8 = this.view.getBtn8();
        btn8.addActionListener((ActionEvent evt) -> {
            numeroPresionado(Integer.parseInt(btn8.getText()));
        });
        
        this.btn9 = this.view.getBtn9();
        btn9.addActionListener((ActionEvent evt) -> {
            numeroPresionado(Integer.parseInt(btn9.getText()));
        });
        
        // botones operadores
        
        this.btnSum = this.view.getBtnAdd();
        btnSum.addActionListener((ActionEvent evt) -> {
            operadorPresionado(btnSum.getText());
        });
        
        this.btnRes = this.view.getBtnSub();
        btnRes.addActionListener((ActionEvent evt) -> {
            operadorPresionado(btnRes.getText());
        });
        
        this.btnMul = this.view.getBtnMul();
        btnMul.addActionListener((ActionEvent evt) -> {
            operadorPresionado(btnMul.getText());
        });
        
        this.btnDiv = this.view.getBtnDiv();
        btnDiv.addActionListener((ActionEvent evt) -> {
            operadorPresionado(btnDiv.getText());
        });
        //boton .
        this.btnPunto = this.view.getBtnDot();
        btnPunto.addActionListener((ActionEvent evt) -> {
            puntoPresionado();
        });
        
        //boton realizar operacion
        
        this.btnOperacion = this.view.getBtnCalcular();
        btnOperacion.addActionListener((ActionEvent evt) -> {
            realizarOperacion();
        });
        
        //boton reiniciar C
        JButton btnReiniciar = this.view.getBtnC();
        btnReiniciar.addActionListener((ActionEvent evt) -> {
            reiniciar(0);
        });
        
        //boton M+
        this.btnMPlus = this.view.getBtnMplus();
        btnMPlus.addActionListener((ActionEvent evt) -> {
            model.setMemoria(Double.parseDouble(this.visor.getText()));
            this.mPlus = true;
        });
        
        //boton Avg
        this.btnAvg = this.view.getBtnAvg();
        btnAvg.addActionListener((ActionEvent evt) -> {
            String avg = model.getAvg();
            visor.setText(avg);
        });
        
        //boton Binario
        this.btnBinario = this.view.getBtnBinario();
        btnBinario.addActionListener((ActionEvent evt) -> {
            String binario = model.toBinary(Double.parseDouble(this.visor.getText()));
            visor.setText(binario);
            subVisor.setText("");
            desactivarBotones();
        });
        
        //boton Primo
        this.btnPrimo = this.view.getBtnPrimo();
        btnPrimo.addActionListener((ActionEvent evt) -> {
            String primo = model.esPrimo(Double.parseDouble(this.visor.getText()));
            visor.setText(primo);
            subVisor.setText("");
            desactivarBotones();
        });
        //boton Data
        JButton btnData = this.view.getBtnData();
        btnData.addActionListener((ActionEvent evt) -> {
            dataView = new DataView(model.getBitacora());
            dataView.setLocationRelativeTo(null);
            dataView.setVisible(true);
            dataView.getPanelData().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    xDataView = evt.getX();
                    yDataView = evt.getY();
                }
            });
        
            dataView.getPanelData().addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                @Override
                public void mouseDragged(java.awt.event.MouseEvent evt) {
                
                    dataView.setLocation(dataView.getLocation().x + evt.getX() - xDataView, dataView.getLocation().y + evt.getY() - yDataView);
                }
            });
        });
        
        //eventos para que se mueva la ventana porque es undecorated
        panelCabezera.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                x = evt.getX();
                y = evt.getY();
            }
        });
        
        panelCabezera.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                view.setLocation(view.getLocation().x + evt.getX() - x, view.getLocation().y + evt.getY() - y);
            }
        });
    }
    
    private void puntoPresionado(){
        visor.setText(visor.getText() + ".");
    }
    
    private void numeroPresionado(int numBoton) {
        if (visor.getText().equals("0")){//caso 1 donde el cero esta a la izquierda
            visor.setText(String.valueOf(numBoton));
            if (subVisor.getText().equals("")){
                model.setValor1(Double.parseDouble(visor.getText()));
            }
            
        }else if(this.resMostrado || this.mPlus){//caso 2 se quiere realizar una operacion nueva
            reiniciar(numBoton);
        }
        else if (subVisor.getText().equals("")){ // caso 3 donde se esta ingrasando el primer num a operar
            visor.setText(visor.getText() + String.valueOf(numBoton));
            model.setValor1(Double.parseDouble(visor.getText()));
            
        }else if (subVisor.getText().equals("") ==  false && 
            Double.parseDouble(visor.getText()) == model.getValor1()){//caso 4 se va a empezar a ingresar segundo num a operar
            visor.setText(String.valueOf(numBoton));
        }else{// caso 5 se esta ingresando el segundo num a operar
            visor.setText(visor.getText() + String.valueOf(numBoton));
        }
    }
    
    private void reiniciar(int numVisor){
        this.resMostrado = false;
        this.negativo = false;
        this.mPlus = false;
        model.setValor2(0);
        model.setOperador("");
        visor.setText(String.valueOf(numVisor));
        subVisor.setText("");
        model.setValor1(Double.parseDouble(visor.getText()));
        activarBotones();
    }
    
    private void operadorPresionado(String opereador){
        model.setOperador(opereador);
        
        try{
            if(subVisor.getText().equals("") && opereador.equals("-")){
                this.negativo = true;
            } 

            if (Double.parseDouble(visor.getText()) == model.getValor1() || subVisor.getText().equals("")){
                // si el valor 1 y 2 son igual o se presiona un operador por primera vez
                //model.setValor1(Double.parseDouble(visor.getText()));
                String subVisor = model.getSubVisorOperador();
                this.subVisor.setText(subVisor);
            }else if(this.resMostrado){
                model.setValor1(Double.parseDouble(visor.getText()));
                String subVisor = model.getSubVisorOperador();
                this.subVisor.setText(subVisor);    
                this.resMostrado = false;
            }else if(this.negativo){
                model.setValor1(-Double.parseDouble(visor.getText()));
                String subVisor = model.getSubVisorOperador();
                this.subVisor.setText(subVisor);
                visor.setText("-" + visor.getText());
                this.negativo = false;
            }
            else{
                realizarOperacion();
            }
        }catch(NumberFormatException e){
            reiniciar(0);
        }
       
    }
    
   private void realizarOperacion(){
        model.setValor2(Double.parseDouble(visor.getText()));
        String res = model.realizarOperacion();
        if(res.equals("Infinity")){
            res = "Indefinido";
        }
        String subVisor = model.getSubVisorResultado();
        if(model.getOperador().equals("") == false){
            this.resMostrado = true;
        }       
        visor.setText(String.valueOf(res));
        this.subVisor.setText(subVisor);
   }
   
   private void desactivarBotones(){
       btn0.setEnabled(false);
       btn1.setEnabled(false);
       btn2.setEnabled(false);
       btn3.setEnabled(false);
       btn4.setEnabled(false);
       btn5.setEnabled(false);
       btn6.setEnabled(false);
       btn7.setEnabled(false);
       btn8.setEnabled(false);
       btn9.setEnabled(false);
       
      btnSum.setEnabled(false);
      btnRes.setEnabled(false);
      btnMul.setEnabled(false);
      btnDiv.setEnabled(false);
      
      btnPunto.setEnabled(false);
      btnOperacion.setEnabled(false);
      btnMPlus.setEnabled(false);
      btnAvg.setEnabled(false);
      btnBinario.setEnabled(false);
      btnPrimo.setEnabled(false);
   }
   
   private void activarBotones(){
       btn0.setEnabled(true);
       btn1.setEnabled(true);
       btn2.setEnabled(true);
       btn3.setEnabled(true);
       btn4.setEnabled(true);
       btn5.setEnabled(true);
       btn6.setEnabled(true);
       btn7.setEnabled(true);
       btn8.setEnabled(true);
       btn9.setEnabled(true);
       
      btnSum.setEnabled(true);
      btnRes.setEnabled(true);
      btnMul.setEnabled(true);
      btnDiv.setEnabled(true);
      
      btnPunto.setEnabled(true);
      btnOperacion.setEnabled(true);
      btnMPlus.setEnabled(true);
      btnAvg.setEnabled(true);
      btnBinario.setEnabled(true);
      btnPrimo.setEnabled(true);
   }
   
   //buttons
   private JButton btn0;
   private JButton btn1;
   private JButton btn2;
   private JButton btn3;
   private JButton btn4;
   private JButton btn5;
   private JButton btn6;
   private JButton btn7;
   private JButton btn8;
   private JButton btn9;
   
   private JButton btnSum;
   private JButton btnRes;
   private JButton btnDiv;
   private JButton btnMul;
   
   private JButton btnPunto;
   private JButton btnOperacion;
   private JButton btnMPlus;
   private JButton btnAvg;
   private JButton btnBinario;
   private JButton btnPrimo;
   
   private int x;
   private int y;
   private int xDataView;
   private int yDataView;
}
