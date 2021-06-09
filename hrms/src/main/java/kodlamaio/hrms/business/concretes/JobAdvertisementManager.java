package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{

	private JobAdvertisementDao jobAdvertisementDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	

	@Override
	public DataResult<List<JobAdvertisement>> getAllByIsActiveAndCreatedDateDesc() {
		var result=this.jobAdvertisementDao.getAllByIsActiveAndCreatedDateDesc();
		if (result!=null) {
			return new SuccessDataResult<List<JobAdvertisement>>(result,"İş ilanları son tarihine göre listelendi.");
		}
		return new ErrorDataResult<List<JobAdvertisement>>("İş ilanı bulunamadı!");
	}

	

	@Override
	public DataResult<List<JobAdvertisement>> getByisActiveTrueAndEmployer_CompanyName(String companyName) {
		var result=this.jobAdvertisementDao.getByisActiveTrueAndEmployer_CompanyName(companyName);
		if (result!=null) {
			return new SuccessDataResult<List<JobAdvertisement>>(result,"Şirkete göre iş ilanları listelendi");
		}
		return new ErrorDataResult<List<JobAdvertisement>>("Şirketin iş ilanı bulunamadı");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByEmployer_Id(int id) {
		var result=this.jobAdvertisementDao.getByEmployer_Id(id);
		if (result!=null) {
			return new SuccessDataResult<List<JobAdvertisement>>(result,"İşverene göre listelendi ");
		}
		return new ErrorDataResult<List<JobAdvertisement>>("İşverenin iş ilanı bulunamadı");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByCreatedDateDesc() {
		var result=this.jobAdvertisementDao.getByIsActiveTrueOrderByCreatedDateDesc();
		if (result!=null) {
			return new SuccessDataResult<List<JobAdvertisement>>(result,"İş ilanları oluşturulma tarihine son tarihine göre listelendi.");
		}
		return new ErrorDataResult<List<JobAdvertisement>>("İş ilanı bulunamadı!");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByCreatedDateAsc() {
		var result=this.jobAdvertisementDao.getByIsActiveTrueOrderByCreatedDateAsc();
		if (result!=null) {
			return new SuccessDataResult<List<JobAdvertisement>>(result,"İş ilanları oluşturulma ilk tarihine göre listelendi.");
		}
		return new ErrorDataResult<List<JobAdvertisement>>("İş ilanı bulunamadı!");
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		if (checkIfInformation(jobAdvertisement)) {
			this.jobAdvertisementDao.save(jobAdvertisement);
			return new SuccessResult("İş ilanı eklendi.");
		};
		return new ErrorResult("Lütfen tüm alanları doldurunuz.");
	}


	@Override
	public DataResult<List<JobAdvertisement>> getByIsActiveTrue() {
		var result=jobAdvertisementDao.getByIsActiveTrue();
		if (result!=null) {
			return new SuccessDataResult<List<JobAdvertisement>>(result,"Aktif iş ilanları listelendi.");
		}
		return new ErrorDataResult<List<JobAdvertisement>>("Aktif iş ilanı bulunamadı!");
	}



	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll());
	}

	public boolean checkIfInformation(JobAdvertisement jobAdvertisement) {
		if (jobAdvertisement.getJobDescription() == "" || jobAdvertisement.getJob() == null || 
				jobAdvertisement.getCity() == null
				|| jobAdvertisement.getPositionAmount() == 0) {
			
			return false;
		}
		return true;

	}



	@Override
	public DataResult<List<JobAdvertisement>> getByisActiveTrueAndEmployer_Id(int id) {
		var result=this.jobAdvertisementDao.getByisActiveTrueAndEmployer_Id(id);
		if (result!=null) {
			return new SuccessDataResult<List<JobAdvertisement>>(result,"Şirkete göre iş ilanları listelendi");
		}
		return new ErrorDataResult<List<JobAdvertisement>>("Şirketin iş ilanı bulunamadı");
	}



	@Override
	public Result setPassiveAdvertisement(int id) {
		if (getById(id) == null) {
			return new ErrorResult("Böyle bir iş ilanı bulunmamaktadır.");

		}
		if (getById(id).getData().isActive() == false) {
			return new ErrorResult("Bu iş ilanı zaten pasif durumdadır.");
		}

		JobAdvertisement jobAdvertisement = getById(id).getData();
		jobAdvertisement.setActive(false);
		update(jobAdvertisement);
		return new SuccessResult("İş ilanı pasif yapıldı.");
	
	}



	@Override
	public DataResult<JobAdvertisement> getById(int id) {
		return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.getById(id));
	}



	@Override
	public Result update(JobAdvertisement jobAdvertisement) {
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("İş ilanı güncellendi");
	}



	
	

}
