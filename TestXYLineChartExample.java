package task1;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class TestXYLineChartExample extends JFrame {


	public static void main(String[] args) {

		new TestXYLineChartExample().test();

		/*
		 * SwingUtilities.invokeLater(new Runnable() {
		 * 
		 * @Override public void run() { new
		 * XYLineChartExample().setVisible(true); } });
		 */

	}

	public void test() {
		setVisible(true);
	}

	public TestXYLineChartExample() {
		super("Title");

		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel chartPanel = createChartPanel();
		add(chartPanel, BorderLayout.CENTER);

	}

	private JPanel createChartPanel() {

		String chartTitle = "Objects Movement Chart";
		String xAxisLabel = "x";
		String yAxisLabel = "y";

		XYDataset dataset = createDataset();

		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset);

		return new ChartPanel(chart);
	}

	private XYDataset createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("Object 1"); 
		series1.add(1.0, 1.0);
		series1.add(2.0, 2.0);
		series1.add(3.0, 3.0);
		series1.add(4.0, 4.0);
		series1.add(5.0, 5.0);
		
		dataset.addSeries(series1);
		
		return dataset;
	}

}
