package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Draw {

    public static JFreeChart drawGraph(String typeOfFormula, MathLogic mathLogic, XYSeries results){
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        String name;
        XYSeries mainGraph = null;
        if(typeOfFormula.equals("N")) {
            if (mathLogic.numberOfFormula == 1) {
                name = mathLogic.a + "x^2 + " + mathLogic.b+"x + " + mathLogic.c;
                mainGraph = new XYSeries(name);
            } else {
                name = mathLogic.a + "sin(X)";
                mainGraph = new XYSeries(name);
            }
            for (double i = mathLogic.left; i <= mathLogic.right; i += 0.1) {
                mainGraph.add(i, mathLogic.formula.func(i));
            }
        }else{
            name = "System";
            XYSeries firstLine = new XYSeries(0.1+"x+" +"xy^3 - ");
            mainGraph = new XYSeries("xy+" + "xy^2 - ");
            for (double i = -10.29; i < 0.29; i += 0.01) {
                double y = Math.pow((1.5 - 0.5 * i * i - 5 * i), 0.5);
                series1.getData().add(new XYChart.Data(i, y));
                series3.getData().add(new XYChart.Data(i, -y));
            }
            for (double i = -11; i < 1; i += 0.01) {
                double y2 = ((-0.2 * i * i + 0.7) / (1 - 0.1 * i));
                series2.getData().add(new XYChart.Data(i, y2));
            }
            for (double i = -3; i < 3; i += 0.01) {
                double y = Math.pow((9 - i * i), 0.5);
                series1.getData().add(new XYChart.Data(i, y));
                series3.getData().add(new XYChart.Data(i, -y));
            }
            for (double i = -1.5; i < 1.5; i += 0.01) {
                double y2 = i * i * i;
                series2.getData().add(new XYChart.Data(i, y2));
            }

        }
        xySeriesCollection.addSeries(mainGraph);
        xySeriesCollection.addSeries(results);
        JFreeChart chart = ChartFactory
                .createXYLineChart(name, "x", "y",
                        xySeriesCollection,
                        PlotOrientation.VERTICAL,
                        true, true, true);

        return chart;
    }
}
