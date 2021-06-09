package kodlamaio.hrms.entities.concretes;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work_experiences")
public class WorkExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(targetEntity = Resume.class)
	@JoinColumn(name="resume_id")
	private Resume resume;
	
	@NotBlank
	@Column(name="company_name")
	private String companyName;
	
	@ManyToOne(targetEntity = Job.class)
	@JoinColumn(name="job_id",referencedColumnName = "id",nullable = false)
	private Job job;
	

	@Column(name="started_date")
	private Date startedDate;
	
	
	@Column(name="ended_date")
	private Date endedDate;
	
	//@CreationTimestamp
	@Column(name="created_date")
	private Date createdDate;
}
