package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String typeFormula;
    static BufferedReader reader;
    static MathLogic mathLogic;

    public static void main(String[] args) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Lab3 \nThis program was coded by Pochikalin Vladislav P3210");
        System.out.println("What do you want to count? System equations or nonlinear equations?(S/N)");
        typeFormula = IOClass.inputTypeOfFormula();
        mathLogic = IOClass.chooseFormule(typeFormula);
        if(IOClass.chooseMethod(typeFormula,mathLogic) == null || mathLogic.iteration == 20000){
            if(typeFormula.equals("S")){
                System.out.println("The system has no solution");
            }
            System.out.println(mathLogic.resultString);
            System.exit(0);
        }
        System.out.println(mathLogic.resultString);
        JFrame frame =
                new JFrame("MinimalStaticChart");
        frame.getContentPane()
                .add(new ChartPanel(Draw.drawGraph(typeFormula,mathLogic,mathLogic.results)));
        frame.setSize(500,600);
        frame.show();




        
    }


    
}


