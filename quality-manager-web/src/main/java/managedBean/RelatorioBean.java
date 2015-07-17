package managedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@ViewScoped
public class RelatorioBean {

	private BarChartModel chartModel;
	private PieChartModel pieModel1;

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
		
		createBarModels();
		createPieModels();

	}
	
	public void createPieModels() {
		
		pieModel1 = new PieChartModel();
        
        pieModel1.set("Pesquisa", 540);
        pieModel1.set("Extensão", 325);
         
        pieModel1.setTitle("Quantidade de projetos");
        pieModel1.setLegendPosition("w");
        pieModel1.setShowDataLabels(true);
		
	}

	public void createBarModels() {

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

		ChartSeries pesquisa = new ChartSeries();
		pesquisa.setLabel("Pesquisa");
		pesquisa.set("Campina Grande", 120);
		pesquisa.set("Patos", 100);
		pesquisa.set("João Pessoa", 44);
		pesquisa.set("Gurarabira", 150);
		
		ChartSeries extensao = new ChartSeries();
		extensao.setLabel("Extensão");
		extensao.set("Campina Grande", 90);
		extensao.set("Patos", 37);
		extensao.set("João Pessoa", 60);
		extensao.set("Gurarabira", 32);
		
		model.addSeries(pesquisa);
		model.addSeries(extensao);

		return model;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}
}
