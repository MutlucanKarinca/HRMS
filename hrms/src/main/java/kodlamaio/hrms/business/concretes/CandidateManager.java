package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.services.abstracts.EmailCheckService;
import kodlamaio.hrms.core.utilities.services.abstracts.EmailSendService;
import kodlamaio.hrms.core.utilities.services.abstracts.MernisCheckService;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Candidate;
@Service
public class CandidateManager implements CandidateService{

	private CandidateDao candidateDao;
	private EmailCheckService emailCheckService;
	private MernisCheckService mernisCheckService;
	private EmailSendService emailSendService;
	private UserDao userDao;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao,
			EmailCheckService emailCheckService,
			MernisCheckService mernisCheckService,
			EmailSendService emailSendService, 
			UserDao userDao) {
		
		super();
		this.candidateDao = candidateDao;
		this.emailCheckService = emailCheckService;
		this.mernisCheckService=mernisCheckService;
		this.emailSendService=emailSendService;
		this.userDao = userDao;
	}


	@Override
	public Result add(Candidate candidate,String passwordAgain) {
		
		if (emailCheckService.emailCheck(candidate.getEmail()) 
				&& checkIfInformation(candidate)
				&& checkEmail(candidate.getEmail())
				&& checkNationalityId(candidate.getNationalityId())
				&& mernisCheckService.checkIfRealPerson(candidate)
				&& Objects.equal(passwordAgain, candidate.getPassword())) {
			emailSendService.emailSend(candidate.getEmail());
			this.candidateDao.save(candidate);
			return new SuccessResult("Kayıt başarılı");
		}
		return new ErrorResult();
		}


	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll());
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
	
	public boolean checkNationalityId(String nationalityId) {
		if(this.candidateDao.getByNationalityId(nationalityId).isEmpty()) {
			return true;
		}
		
		else {
			System.out.println("Bu TC NO ile daha önce üye olunmuş.");
			return false;
			
		}			
	}
	
	public boolean checkIfInformation(Candidate candidate) {
		if (candidate.getEmail() == "" || candidate.getPassword() == "" || candidate.getFirstName() == ""
				|| candidate.getLastName() == "" || candidate.getNationalityId() == ""
				|| candidate.getBirthDate() ==null) {
			System.out.println("Lütfen tüm alanları doldurun");
			return false;
		}
		return true;

	}


	
	}
