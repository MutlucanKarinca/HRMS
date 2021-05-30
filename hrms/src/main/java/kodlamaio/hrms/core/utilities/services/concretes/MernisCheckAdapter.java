package kodlamaio.hrms.core.utilities.services.concretes;

import java.rmi.RemoteException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.services.abstracts.MernisCheckService;
import kodlamaio.hrms.entities.concretes.Candidate;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;


@Component
public class MernisCheckAdapter implements MernisCheckService{

	@Override
	public boolean checkIfRealPerson(Candidate candidate) {
		KPSPublicSoapProxy client=new KPSPublicSoapProxy();
		boolean result=false;
		try {
			result=client.TCKimlikNoDogrula(
					Long.parseLong(candidate.getNationalityId()), 
					candidate.getFirstName(),
					candidate.getLastName(), 
					candidate.getBirthDate().getYear()
						);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;

	}

}
