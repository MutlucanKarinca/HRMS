package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobAdvertisement")
public class JobAdvertisementsController {

	private JobAdvertisementService jobAdvertisementService;

	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll()
	{
		return this.jobAdvertisementService.getAll();
	}	
	
	@GetMapping("/getByIsActiveTrue")
	public DataResult<List<JobAdvertisement>> getByIsActiveTrue()
	{
		return this.jobAdvertisementService.getByIsActiveTrue();
	}	
	@PostMapping("/add")
	public Result jobAdvertisementAdd(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.jobAdvertisementService.Add(jobAdvertisement);
	}
	
	@GetMapping("/getByIsActiveTrueOrderByCreatedDateDesc")
	public DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByCreatedDateDesc()
	{
		return this.jobAdvertisementService.getByIsActiveTrueOrderByCreatedDateDesc();
	}
	
	@GetMapping("/getByIsActiveTrueOrderByCreatedDateAsc")
	public DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByCreatedDateAsc()
	{
		return this.jobAdvertisementService.getByIsActiveTrueOrderByCreatedDateAsc();
	}
	
	@GetMapping("/getByisActiveTrueAndEmployer_CompanyName")
	public DataResult<List<JobAdvertisement>> getByisActiveTrueAndEmployer_CompanyName(String companyName)
	{
		return this.jobAdvertisementService.getByisActiveTrueAndEmployer_CompanyName(companyName);
	}
	
	@GetMapping("/getByEmployer_Id")
	public DataResult<List<JobAdvertisement>> getByEmployer_Id(int id)
	{
		return this.jobAdvertisementService.getByEmployer_Id(id);
	}

	@GetMapping("/getByisActiveTrueAndEmployer_Id")
	public DataResult<List<JobAdvertisement>> getByisActiveTrueAndEmployer_Id(int id)
	{
		return this.jobAdvertisementService.getByisActiveTrueAndEmployer_Id(id);
	}
	
	@GetMapping("/getAllByIsActiveAndCreatedDateDesc")
	public DataResult<List<JobAdvertisement>> getAllByIsActiveAndCreatedDateDesc()
	{
		return this.jobAdvertisementService.getAllByIsActiveAndCreatedDateDesc();
	}
}
