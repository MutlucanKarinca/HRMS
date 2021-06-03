package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.City;
import kodlamaio.hrms.entities.concretes.Job;

public interface CityDao extends JpaRepository<City, Integer>{
	List<Job> getByCityName(String cityName);
}
