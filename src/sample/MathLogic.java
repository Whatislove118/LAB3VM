package sample;

import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathLogic {
    int numberOfFormula;
    String typeOfFormula;
    Formula formula;
    List<Formula> formulas;
    double x0,y0;
    XYSeries results;
    double a, b, c, e, left, right;
    int iteration = 0;
    String resultString = "RESULTS:\n";

    public MathLogic(Formula formula, double a, double b, double c, String typeOfFormula) {
        this.formula = formula;
        this.a = a;
        this.b = b;
        this.c = c;
        this.typeOfFormula = typeOfFormula;
    }

    public MathLogic(Formula formula, double a, String typeOfFormula) {
        this.formula = formula;
        this.a = a;
        this.typeOfFormula = typeOfFormula;

    }
    public MathLogic(){

    }


    interface Formula {
        double func(double x);

    }


    public String bisectionMethod() {
        if(this.left == this.right || (numberOfFormula == 2 && a == 0) || (numberOfFormula == 1 && a == 0 && b == 0)){
            this.resultString = "Doesn't make sense";
            return null;
        }
        double changedLeft = this.left;
        double changedRight = this.right;
        this.results = new XYSeries("x");
        if (this.formula.func(this.left) * this.formula.func(this.right) > 0) {
            this.resultString = "No convergance";
            return null;
        }
        for (int i = 0; i < 20000; i++) {
            double x = (changedLeft + changedRight) / 2;
            if (this.formula.func(changedLeft) * this.formula.func(x) < 0) {
                changedRight = x;
            }
            if (this.formula.func(changedRight) * this.formula.func(x) < 0) {
                changedLeft = x;
            }
            this.iteration++;
            if (Math.abs(this.formula.func(x)) < e) {
                for (double j = x; j < this.right; j += 0.1) {
                    this.results.add(x, this.formula.func(j));
                }
                this.resultString += "x = " + x +"\n" + "f(x) = " + this.formula.func(x) + "\niteration = " + this.iteration;
                break;
            }
        }
        return resultString;

    }

    //TODO do this method
    public String simpleIterationMethod() {
        if(this.left == this.right || (numberOfFormula == 2 && a == 0) || (numberOfFormula == 1 && a == 0 && b == 0)) {
            this.resultString = "Doesn't make sense";
        }
            Formula representFormula = null;
        this.results = new XYSeries("x");
        Formula derivativeFormula = null;
        if (this.formula.func(this.left) * this.formula.func(this.right) > 0) {
            this.resultString = "No convergance";
            return null;
        }
        if (this.numberOfFormula == 1) {
            representFormula = x -> {
                return (-this.a * Math.pow(x, 2) - this.c) / this.b;
            };
            derivativeFormula = x -> {
                return -this.a * 2 * x / this.b;
            };
        } else {
            representFormula = x -> {
                return this.a * Math.sin(x) + x;
            };
            derivativeFormula = x -> {
                return this.a * Math.cos(x);
            };
        }
        double x;
        double x0 = (this.left + this.right) / 2;
        int n = 0;
        if (Math.abs(derivativeFormula.func(x0)) >= 1) {
            this.resultString = "No convergance: f'(x) = " + derivativeFormula.func(x0) + ">= 1";
            return null;
        }
        for (int i = 0; i < 20000; i++) {
            x = representFormula.func(x0);
            if (x < this.left || x > this.right) {
                resultString = "Out of range";
                return null;
            }
            if (Math.abs(x - x0) < this.e) {
                resultString = "results = " + x0 + " - " + this.formula.func(x0);
                for (double j = x; j < right; j += 0.1) {
                    this.results.add(x0, this.formula.func(j));
                }
                return resultString;
            } else {
                x0 = x;
                continue;
            }
        }
        return null;
    }

    public String newtonMethod(){
        results = new XYSeries("x|y");
        double x = this.x0;
        double y = this.y0;
        double xNext = 0;
        double yNext = 0;

        for (int i = 0; i < 20000 ; i++) {
            xNext = x - determinant(F1(x,y), dF1_Y(x,y),F2(x,y),dF2_Y(x,y)) / determinant(dF1_X(x,y),dF1_Y(x,y),dF2_X(x,y),dF2_Y(x,y));
            yNext = y - determinant(dF1_X(x,y),F1(x,y),dF2_X(x,y),F2(x,y)) / determinant(dF1_X(x,y),dF1_Y(x,y),dF2_X(x,y),dF2_Y(x,y));
            this.iteration++;
            if(Math.max((Math.abs(xNext - x)), (Math.abs(yNext - y))) < this.e){
                this.resultString += "x = " + xNext + "\ny = " + yNext +"\niteration = " + this.iteration;
                for (double j = x0; j < xNext ; j+=0.1) {
//                    this.results.add(xNext,yNe);
                }
//                this.results.add(xNext,yNext);
                return resultString;
            }
            x = xNext;
            y = yNext;
        }

        return null;
    }

    public double F1(double x,double y){
        return 0.1*x*x + x + 0.2*y*y - 0.3;
    }
    public double dF1_Y(double x,double y){
        return 0.4*y;
    }
    public double dF1_X(double x,double y){
        return  0.2 * x + 1;
    }
    public double F2(double x,double y){
        return x * x + y * y - 9;
    }
    public double dF2_X(double x,double y){
        return 2 * x;
    }
    public double dF2_Y(double x,double y){
        return 2 * y;
    }
    public double determinant(double a00, double a01, double a10, double a11){
        return (a00 * a11 - a01 * a10);
    }




}
