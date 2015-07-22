package managedBean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.relatorios.Pizza;
import br.edu.ifpb.qmanager.relatorios.Quadro;

@ManagedBean
@ViewScoped
public class RelatorioBean {

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

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

		Pizza pizza = service.relatorioQuantidadeProjetos();

		for (Map.Entry<String, Integer> fatia : pizza.getFatias().entrySet())
			pieModel1.set(fatia.getKey(), fatia.getValue());

		pieModel1.setTitle("Quantidade de Projetos");
		pieModel1.setLegendPosition("w");
		pieModel1.setShowDataLabels(true);
	}

	public void createBarModels() {

		chartModel = initBarModel();

		chartModel.setTitle("Quantidade de Projetos por Campus");
		chartModel.setLegendPosition("ne");

		Axis xAxis = chartModel.getAxis(AxisType.X);
		xAxis.setLabel("Campus");

		Axis yAxis = chartModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
		yAxis.setMax(200);

	}

	private BarChartModel initBarModel() {
		
		List<Quadro> quadros = service.relatorioQuantidadeProjetosPorCampus();
		
		BarChartModel model = new BarChartModel();

		ChartSeries chart;

		for (Quadro quadro : quadros) {

			chart = new ChartSeries();

			String label = quadro.getLabel();
			chart.setLabel(label);
			
			for (Map.Entry<String, Integer> barra : quadro.getMap().entrySet())
				chart.set(barra.getKey(), barra.getValue());
			
			model.addSeries(chart);
		}

		return model;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}
}
