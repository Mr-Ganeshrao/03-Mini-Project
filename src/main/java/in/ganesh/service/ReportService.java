package in.ganesh.service;

import java.util.List;

import in.ganesh.request.SearchRequest;
import in.ganesh.response.SearchResponse;

public interface ReportService {
	
	
	public List<String> getPlansName();
	
	public List<String> getPlanStatuses();
	
	public List<SearchResponse> searchPlans(SearchRequest request);
	
	
	

}
