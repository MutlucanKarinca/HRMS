package kodlamaio.hrms.core.utilities.services.concretes;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import kodlamaio.hrms.core.utilities.services.abstracts.EmailCheckService;

@Primary
@Component
public class FakeEmailCheckManager implements EmailCheckService{

	@Override
	public boolean emailCheck(String email) {
		return true;
		
	}

}
