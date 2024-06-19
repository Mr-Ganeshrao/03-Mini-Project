package in.ganesh.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ganesh.reports.ExcelGenarator;
import in.ganesh.reports.PdfGenerator;
import in.ganesh.request.SearchRequest;
import in.ganesh.response.SearchResponse;
import in.ganesh.service.ReportService;

@RestController
public class ReportRestController {

	private ReportService service;

	@GetMapping("/Plan-names")
	public List<String> planNames() {
		return service.getPlansName();
	}

	@GetMapping("/plan-status")
	public List<String> statusName() {

		return service.getPlanStatuses();
	}

	@PostMapping("/search-plans")
	public List<SearchResponse> searchRecord(@RequestBody SearchRequest request) {
		return service.searchPlans(request);
	}

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans.xlsx";

		response.setHeader(headerKey, headerValue);

		List<SearchResponse> searchPlans = service.searchPlans(null);

		ExcelGenarator genarator = new ExcelGenarator();
		genarator.generateExcel(searchPlans, response);
	}

	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans.pdf";

		response.setHeader(headerKey, headerValue);

		List<SearchResponse> searchPlans = service.searchPlans(null);
		PdfGenerator generator = new PdfGenerator();
		generator.generatePdf(searchPlans, response);
	}

}
