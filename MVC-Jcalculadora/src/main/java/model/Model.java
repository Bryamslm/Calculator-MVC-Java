/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.LinkedList;

/**
 *
 * @author bryam
 */
public class Model {
    private double valor1;
    private double valor2;
    private double resultado;
    private String operador;
    private String subVisor;
    LinkedList<Double> memoria;
    Bitacora bitacora  = new Bitacora();

    public Model() {
        this.valor1 = 0;
        this.valor2 = 0;
        this.resultado = 0;
        this.operador = "";
        this.subVisor = "";
        this.memoria = new LinkedList<>();
    }

    public String getOperador() {
        return operador;
    }

    public void setValor1(double valor1) {
        this.valor1 = valor1;
    }

    public void setValor2(double valor2) {
        this.valor2 = valor2;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }
    
    public double getValor1() {
        return valor1;
    }

    public double getResultado() {
        return resultado;
    }
    
    public String getSubVisorOperador(){
        
        if (this.valor1 % 1 == 0){//si es entero
            this.subVisor = String.valueOf(Math.round(this.valor1)) + " " + this.operador + " ";
        }else{// no es entero
            this.subVisor = String.valueOf(this.valor1) + " " + this.operador + " ";
        }
        
        return this.subVisor;
    }
    
    public String getSubVisorResultado(){
        if(operador.equals("")){
            if (this.valor1 % 1 == 0){
                this.subVisor = String.valueOf(Math.round(this.valor1)) + " =";
            }else{
                this.subVisor = String.valueOf(this.valor1) + " =";
            }
        }else if (this.valor1 % 1 == 0 && this.valor2 % 1 == 0){//si es entero
            this.subVisor = String.valueOf(Math.round(this.valor1)) + " " + this.operador + " " + String.valueOf(Math.round(this.valor2)) + " =";
        }else if(this.valor1 % 1 == 0 && this.valor2 % 1 != 0){
            this.subVisor = String.valueOf(Math.round(this.valor1)) + " " + this.operador + " " + String.valueOf(this.valor2) + " =";
        }else if(this.valor1 % 1 != 0 && this.valor2 % 1 == 0){
            this.subVisor = String.valueOf(this.valor1) + " " + this.operador + " " + String.valueOf(Math.round(this.valor2)) + " =";
        }else if(this.valor1 % 1 != 0 && this.valor2 % 1 != 0){// no es entero
            this.subVisor = String.valueOf(this.valor1) + " " + this.operador + " " + String.valueOf(this.valor2) + " =";
        }
        
        return this.subVisor;
    }
    
    public String realizarOperacion(){
        String res;
        switch (operador) {
            case "+":
                this.resultado = valor1 + valor2;
                break;
            case "*":
                this.resultado = valor1 * valor2;
                break;
            case "-":
                this.resultado = valor1 - valor2;
                break;
            case "/":
                this.resultado = valor1 / valor2;
                break;
            default:
                //el usuario no ingresa operador
                this.resultado = valor1;
                break;
        }
        if(operador.equals("")){
            if(this.resultado % 1 == 0){
                res = String.valueOf(Math.round(this.valor1));
            }else{
                res = String.valueOf(valor1);
            }
        }else{
            res = String.valueOf(valor1) + " " + operador + " " + String.valueOf(valor2);
        }
        String resultado;
        
        if(this.resultado % 1 == 0){
            resultado = String.valueOf(Math.round(this.resultado));
            res+= " = " + resultado;
            agregarABitacora(res);
            return resultado;
        }else{
            resultado = String.valueOf(this.resultado);
            res+= " = " + resultado;
            agregarABitacora(res);
            return resultado;
        }
    }

    public void setMemoria(Double num) {
        
        String res = "M+ " + String.valueOf(num) + " >";
        
        this.memoria.addLast(num);
        if(this.memoria.size() > 10){
            this.memoria.removeFirst();
        }
        
        for(Double n: memoria){
            res+= "  "+ String.valueOf(n);
        }
        agregarABitacora(res);
    }
    
    public String getAvg(){
        String res = "Avg";
        double promedio = 0;
        
        for(double num: this.memoria){
            promedio+=num;
            res+= "  " + String.valueOf(num);
        }
        if(promedio == 0){
            res+= "  =  0";
            agregarABitacora(res);
            return "0";
        }else{
            promedio = promedio / this.memoria.size();
            res+= "  = " + String.valueOf(promedio);
            agregarABitacora(res);
            return String.valueOf(promedio);
        }
    }
 
    //------- https://stackoverflow.com/questions/6359847/convert-double-to-binary-representation ---------------
    public String toBinary(double d) {
        String res = "Binario " + String.valueOf(d);
        long wholePart = (long) d;
        if(d % 1 != 0){//no tiene parte fraccional
            String resultado = wholeToBinary(wholePart) + '.' + fractionalToBinary(d - wholePart, 5);
            res+= "  " + resultado;
            agregarABitacora(res);
            return resultado;
        }else{
            String resultado = wholeToBinary(wholePart);
            res+= "  " + resultado;
            agregarABitacora(res);
            return resultado;
        }
    }

    private String wholeToBinary(long l) {
        return Long.toBinaryString(l);
    }

    private String fractionalToBinary(double num, int precision) {
        StringBuilder binary = new StringBuilder();
        while (num > 0 && binary.length() < precision) {
            double r = num * 2;
            if (r >= 1) {
                binary.append(1);
                num = r - 1;
            } else {
                binary.append(0);
                num = r;
            }
        }
        return binary.toString();
    }
    //------- https://stackoverflow.com/questions/6359847/convert-double-to-binary-representation ---------------
    
    public String esPrimo(double numero) {
        String res;
        // El 0, 1 y 4 no son primos
        if (numero == 0 || numero == 1 || numero == 4) {
            res =  "Primo " + String.valueOf(numero) + " false";
            agregarABitacora(res);
            return res;
        }
        for (int x = 2; x < numero / 2; x++) {
          // Si es divisible por cualquiera de estos números, no
          // es primo
          if (numero % x == 0){
            res =  "Primo " + String.valueOf(numero) + " false";
            agregarABitacora(res);
            return res;
          }
        }
        // Si no se pudo dividir por ninguno de los de arriba, sí es primo
        
        res = "Primo " + String.valueOf(numero) + " true";
        agregarABitacora(res);
        return res;
    }
    
    private void agregarABitacora(String line){
        this.bitacora.escribirEnBitacora(line);
    }
    
    public String getBitacora(){
        return this.bitacora.leerBitacora();
    }
    
}
