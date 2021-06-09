package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.entities.concretes.City;


@Service
public class CityManager implements CityService{
	private CityDao cityDao;
	
	@Autowired
	public CityManager(CityDao cityDao) {
		super();
		this.cityDao = cityDao;
	}

	@Override
	public DataResult<List<City>> getAll() {
		return new SuccessDataResult<List<City>>(this.cityDao.findAll(),"Data Listelendi");
	}

	/*
	 * @Override public Result add(City city) { if
	 * (checkCityName(city.getCityName())) { this.cityDao.save(city); return new
	 * SuccessResult("Şehir eklendi"); } return new ErrorResult(); }
	 */
	@Override
	public Result add(City city) {
		this.cityDao.save(city);
	    return new SuccessResult("City has been added.");
	}
	
	public boolean checkCityName(String cityName) {
		 
		if(this.cityDao.getByCityName(cityName).isEmpty()) {
			return true;
		}
		else {
			System.out.println("Bu şehir daha önce eklenmiş.");
			return false;
		}
	}
}
