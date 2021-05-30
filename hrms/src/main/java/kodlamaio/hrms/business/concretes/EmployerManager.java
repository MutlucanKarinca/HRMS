package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.services.abstracts.EmailSendService;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;
	private UserDao userDao;
	private EmailSendService emailSendService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao,
						   UserDao userDao,
						   EmailSendService emailSendService) {
		super();
		this.employerDao = employerDao;
		this.userDao = userDao;
		this.emailSendService=emailSendService;
	}

	@Override
	public Result add(Employer employer,String passwordAgain) {
		if (checkIfInformation(employer)
				&& checkEmail(employer.getEmail())
			) {
			
			this.employerDao.save(employer);
			emailSendService.emailSend(employer.getEmail());
			return new SuccessResult("New employer added successfully");
		}
		
		return new ErrorResult();
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll());
	}
	
	public boolean checkEmail(String email) {
		 
		if(this.userDao.getByEmail(email).isEmpty()) {
			return true;
		}
		else {
			System.out.println("Bu email ile daha önce kayıt olunmuş.");
			return false;
		}
	}
	
	public boolean checkIfInformation(Employer employer) {
		if (employer.getEmail() == "" || employer.getPassword() == "" || employer.getWebAddress()== ""
				|| employer.getCompanyName() == "" || employer.getPhoneNumber() == "") {
			System.out.println("Lütfen tüm alanları doldurun");
			return false;
		}
		return true;

	}
}
