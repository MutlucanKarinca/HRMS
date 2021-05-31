package kodlamaio.hrms.core.utilities.services.concretes;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import kodlamaio.hrms.core.utilities.services.abstracts.MernisCheckService;
import kodlamaio.hrms.entities.concretes.Candidate;

//@Primary
@Component
public class FakeMernisCheckService implements MernisCheckService{

	@Override
	public boolean checkIfRealPerson(Candidate candidate) {
		return true;
	}

}
