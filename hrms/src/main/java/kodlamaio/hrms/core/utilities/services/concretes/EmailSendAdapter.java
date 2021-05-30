package kodlamaio.hrms.core.utilities.services.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.services.abstracts.EmailSendService;
import kodlamaio.hrms.emailService.EmailSendManager;

@Service
public class EmailSendAdapter implements EmailSendService{

	private EmailSendManager emailSendManager;
	
	@Autowired
	public EmailSendAdapter(EmailSendManager emailSendManager) {
		super();
		this.emailSendManager = emailSendManager;
	}
	@Override
	public void emailSend(String email) {
		emailSendManager.emailSending(email);
		
	}

}
