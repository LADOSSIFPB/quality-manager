package managedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
@ViewScoped
public class RelatorioBean {

	private BarChartModel chartModel;

	public RelatorioBean() {

	}

	public BarChartModel getChartModel() {
		return chartModel;
	}

	public void setChartModel(BarChartModel chartModel) {
		this.chartModel = chartModel;
	}

	@PostConstruct
	public void init() {
		
		chartModel = initBarModel();

		chartModel.setTitle("Servidores com projetos");
		chartModel.setLegendPosition("ne");

		Axis xAxis = chartModel.getAxis(AxisType.X);
		xAxis.setLabel("Campus");

		Axis yAxis = chartModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
		yAxis.setMax(200);
		
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Projetos");
		boys.set("Campina Grande", 120);
		boys.set("Patos", 100);
		boys.set("João Pessoa", 44);
		boys.set("Gurarabira", 150);

		model.addSeries(boys);
		
		return model;
	}
}
