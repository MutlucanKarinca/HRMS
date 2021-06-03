package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement,Integer>{

	List<JobAdvertisement> getByIsActiveTrue();
	

	@Query("From JobAdvertisement where isActive=true Order By createdDate DESC")
	List<JobAdvertisement> getAllByIsActiveAndCreatedDateDesc();
	
	//@Query("From JobAdvertisement where isActive=true ORDER BY applicationDeadline DESC")
	//List<JobAdvertisement> getAllActiveSorted();
	
	List<JobAdvertisement> getByisActiveTrueAndEmployer_CompanyName(String companyName);

	List<JobAdvertisement> getByisActiveTrueAndEmployer_Id(int id);
	
	List<JobAdvertisement> getByEmployer_Id(int id);
	
	List<JobAdvertisement> getByIsActiveTrueOrderByCreatedDateDesc();
	List<JobAdvertisement> getByIsActiveTrueOrderByCreatedDateAsc();
}
