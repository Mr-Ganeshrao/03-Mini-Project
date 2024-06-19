package in.ganesh.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import in.ganesh.entity.EligibilityDtlsEntity;
import in.ganesh.repository.EligiDtlsRepository;
import in.ganesh.request.SearchRequest;
import in.ganesh.response.SearchResponse;

public class ReportServiceImpl implements ReportService {

	@Autowired
	private EligiDtlsRepository repository;

	@Override
	public List<String> getPlansName() {

		return repository.getPlans();
	}

	@Override
	public List<String> getPlanStatuses() {

		return repository.getStatuses();
	}

	@Override
	public List<SearchResponse> searchPlans(SearchRequest request) {
		
		List<EligibilityDtlsEntity> eligRecord=null;

		if (request == null) {
			 eligRecord = repository.findAll();
		} else {
			String planName = request.getPlanName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEndDate();

			EligibilityDtlsEntity entity = new EligibilityDtlsEntity();

			if (planName != null && !planName.equals("")) {
				entity.setPlanName(planName);

			}
			if (planStatus != null && !planStatus.equals("")) {
				entity.setPlanStatus(planStatus);
			}
			if (startDate != null && endDate != null) {
				entity.setStartDate(startDate);
				entity.setEndDate(endDate);
			}

			Example<EligibilityDtlsEntity> of = Example.of(entity);
			eligRecord  = repository.findAll(of);
		}
		
		List<SearchResponse> list=new ArrayList<>(); 
		
		for(EligibilityDtlsEntity records: eligRecord) {
			SearchResponse searchResponse =new SearchResponse();
			BeanUtils.copyProperties(records, searchResponse);
			
			list.add(searchResponse);
			
		}
		return list;
	}

}
