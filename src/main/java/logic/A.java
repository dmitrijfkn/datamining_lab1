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

    public A(final String title, List<Integer> listX, List<Integer> listY, String XName, String YName) {
        super("Kostenko Lab1");
        IntervalXYDataset dataset = createDataset(listX, listY);
        JFreeChart chart = createChart(title, dataset,XName,YName);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private IntervalXYDataset createDataset(List<Integer> listX, List<Integer> listY) {
        final XYSeries series = new XYSeries("");

        for(int i = 0; i< listX.size();i++){
            series.add(listX.get(i),listY.get(i));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    private JFreeChart createChart(String title, IntervalXYDataset dataset, String XName, String YName) {
        final JFreeChart chart = ChartFactory.createXYBarChart(
                title,
                XName,
                false,
                YName,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        final IntervalMarker target = new IntervalMarker(400.0, 700.0);
        target.setLabel("Target Range");
        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target.setLabelAnchor(RectangleAnchor.LEFT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setPaint(new Color(222, 222, 255, 128));
        plot.addRangeMarker(target, Layer.BACKGROUND);
        return chart;
    }
}
