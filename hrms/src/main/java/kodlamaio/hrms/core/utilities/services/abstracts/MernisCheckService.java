package kodlamaio.hrms.core.utilities.services.abstracts;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.entities.concretes.Candidate;
@Service
public interface MernisCheckService {
	public boolean checkIfRealPerson(Candidate candidate);
}
