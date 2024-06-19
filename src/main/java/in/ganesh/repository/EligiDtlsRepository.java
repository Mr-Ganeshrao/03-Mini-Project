package in.ganesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ganesh.entity.EligibilityDtlsEntity;
import in.ganesh.response.SearchResponse;

public interface EligiDtlsRepository extends JpaRepository<EligibilityDtlsEntity, Integer>{
	
	
	@Query("select distinct(planName) from EligibilityDtlsEntity")
	public List<String> getPlans();
	
	
	@Query("select distinct(planStatus) from EligibilityDtlsEntity")
	public List<String> getStatuses();

}
