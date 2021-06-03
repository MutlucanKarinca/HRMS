package kodlamaio.hrms.business.abstracts;

import java.util.List;



import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {

	DataResult<List<JobAdvertisement>> getAll();
	
	DataResult<List<JobAdvertisement>> getByIsActiveTrue();
	
	DataResult<List<JobAdvertisement>> getAllByIsActiveAndCreatedDateDesc();
	
	DataResult<List<JobAdvertisement>> getByisActiveTrueAndEmployer_Id(int id);
	
	DataResult<List<JobAdvertisement>> getByisActiveTrueAndEmployer_CompanyName(String companyName);

	DataResult<List<JobAdvertisement>> getByEmployer_Id(int id);
	
	DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByCreatedDateDesc();
	
	DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByCreatedDateAsc();
	
	Result Add(JobAdvertisement jobAdvertisement);
}
