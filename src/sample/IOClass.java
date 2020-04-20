package sample;

import org.jfree.data.xy.XYSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IOClass{
    static int numberOfFormula;
    static String typeFormula;
    static  double a,b,c,d;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static String inputTypeOfFormula() {
        while (true) {
            try {
                typeFormula = reader.readLine();
                if (typeFormula.equals("S") || typeFormula.equals("N")) {
                    break;
                } else {
                    System.err.println("Input is not correct! Write char S or N!");
                    continue;
                }
            } catch (IOException e) {
                System.err.println("Error input! Please try again");

            }
        }
        return typeFormula;
    }

    public static MathLogic chooseFormule(String typeFormula) {
        MathLogic mathLogic = null;
        if (typeFormula.equals("N")) {
            System.out.println("Please, choose the formula: (write a number of formula)");
            System.out.println("1. ax^2 + bx + c");
            System.out.println("2. a*sin(X)");
            while (true) {
                try {
                    numberOfFormula = Integer.parseInt(reader.readLine());
                    if (numberOfFormula == 1) {
                        while (true) {
                            try {
                                System.out.println("Enter a");
                                a = Double.parseDouble(reader.readLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.err.println("[a] must be a number!");
                                continue;
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Enter b");
                                b = Double.parseDouble(reader.readLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.err.println("[b] must be a number!");
                                continue;
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Enter c");
                                c = Double.parseDouble(reader.readLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.err.println("[c] must be a number!");
                                continue;
                            }
                        }
                        mathLogic = new MathLogic(x -> {
                            return a * Math.pow(x, 2) + b * x + c;
                        }, a, b, c, typeFormula);
                        break;
                    }
                    if (numberOfFormula == 2) {
                        while (true) {
                            try {
                                System.out.println("Enter a");
                                a = Double.parseDouble(reader.readLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.err.println("[a] must be a number!");
                                continue;
                            }
                        }
                        mathLogic = new MathLogic(x -> {
                            return a * Math.sin(x);
                        }, a, typeFormula);
                        break;
                    } else {
                        System.err.println("Please choose a formula!");
                        continue;
                    }
                } catch (IOException e) {
                    System.err.println("Error input! Please try again");
                } catch (NumberFormatException e) {
                    System.err.println("Number of formula must be a number");
                    continue;
                }
            }
            System.out.println("Enter isolation separated by space(left and right){Must be a number!!}");
            String[] isolation = enterIsolation();
            mathLogic.left = Double.parseDouble(isolation[0]);
            mathLogic.right = Double.parseDouble(isolation[1]);
            mathLogic.e = enterE();
            mathLogic.numberOfFormula = numberOfFormula;
            return mathLogic;
        }
        if (typeFormula.equals("S")) {
            List<Double> koef = new ArrayList<>();
            System.out.println("System:");
            System.out.println("{ 0.1x^2 + x + 0.2y^2  - 0.3= 0");
            System.out.println("{x^2 + y^2 - 9 = 0");
                mathLogic = new MathLogic();
                System.out.println("Enter a start scope (x0 and y0 separated by space)");
                String[] startScope = enterIsolation();
                mathLogic.x0 = Double.parseDouble(startScope[0]);
                mathLogic.y0 = Double.parseDouble(startScope[1]);
                mathLogic.e = enterE();
                return mathLogic;
            }
            return mathLogic;
        }


    public static String[] enterIsolation(){
        while (true){
            try {
                String[] isolation = reader.readLine().split(" ");
                try {
                    Double.parseDouble(isolation[0]);
                    Double.parseDouble(isolation[1]);
                    return isolation;
                }catch (NumberFormatException e){
                    System.err.println("Input must contains only a number!");
                    continue;
                }catch (ArrayIndexOutOfBoundsException e){
                    System.err.println("Input must conatains only TWO numbers!");
                }
            } catch (IOException e) {
                System.err.println("Error in input. Try again");
                continue;
            }
        }
    }

    public static Double enterE(){
        while (true){
            System.out.println("Enter accuracy");
            try {
                double e = Double.parseDouble(reader.readLine());
                if(e <=0){
                    System.err.println("Accuracy must be upper than 0!");
                    continue;
                }
                return e;
            } catch (IOException ex) {
                System.err.println("Error in input. Try again");
                continue;
            }catch (NumberFormatException e){
                System.err.println("Input must contains only a number!");
                continue;
            }
        }
    }

    public static String chooseMethod(String typeFormula, MathLogic mathLogic){
        String resultString = null;
        while (true) {
            if (typeFormula.equals("N")) {
                System.out.println("Choose a method:(write a number)");
                System.out.println("1. bisection method");
                System.out.println("2. simple iteration method");
                try {
                    int numberMethod = Integer.parseInt(reader.readLine());
                    if (numberMethod == 1) {
                        resultString = mathLogic.bisectionMethod();
                        break;
                    }
                    if (numberMethod == 2) {
                       resultString =  mathLogic.simpleIterationMethod();
                        break;
                    }else{
                        System.err.println("Choose from 1 and 2");
                        continue;
                    }
                } catch (IOException e) {
                    System.err.println("Must be a nubmer!");
                    continue;
                } catch (NumberFormatException e){
                    System.err.println("Number of method must be a number!");
                    continue;
                }

            }else{
                resultString = mathLogic.newtonMethod();
                break;
            }
        }
        return resultString;
    }

}
