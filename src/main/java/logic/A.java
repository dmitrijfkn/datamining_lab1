package logic;

import java.awt.*;
import java.util.List;

import entities.CSV;
import entities.WordWithType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

public class A extends ApplicationFrame {

    public A(final String title, List<Integer> listX, List<Integer> listY) {
        super(title);
        IntervalXYDataset dataset = createDataset(listX, listY);
        JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private IntervalXYDataset createDataset(List<Integer> listX, List<Integer> listY) {
        final XYSeries series = new XYSeries("Random Data");


        for(WordWithType wordWithType: list){
            if (wordWithType.getType().equals(type)){

            }
        }
        series.add(1.0, 400.2);
        series.add(5.0, 294.1);
        series.add(4.0, 100.0);
        series.add(12.5, 734.4);
        series.add(17.3, 453.2);
        series.add(21.2, 500.2);
        series.add(21.9, null);
        series.add(25.6, 734.4);
        series.add(30.0, 453.2);
        final XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }
}
