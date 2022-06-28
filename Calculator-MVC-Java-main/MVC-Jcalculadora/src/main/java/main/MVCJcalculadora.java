/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package main;

import controller.Controller;
import model.Model;
import view.View;

/**
 *
 * @author bryam
 */
public class MVCJcalculadora {

    public static void main(String[] args) {
        View view = new View();
        Model model =  new Model();
        Controller controller= new Controller(model, view);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}
