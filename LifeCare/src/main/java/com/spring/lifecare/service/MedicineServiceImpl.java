package com.spring.lifecare.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.spring.lifecare.entites.MedicineDetail;
import com.spring.lifecare.entites.User;
import com.spring.lifecare.exception.ServiceException;
import com.spring.lifecare.exception.UnauthorizedAccessException;
import com.spring.lifecare.repository.MedicineRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public MedicineDetail createMedicineDetail(@NonNull MedicineDetail medicineDetail) throws ServiceException {
		try {
			MedicineDetail savedMedicineDetail = medicineRepository.save(medicineDetail);
			log.info("Created Medicine Details: {}", savedMedicineDetail.getMedicineName());

			return savedMedicineDetail;
		} catch (DataIntegrityViolationException e) {
			log.error("Error occurred while creating new medicine details: {}", e.getMessage(), e);

			throw new ServiceException("Failed to create medicine Details. Medicine details already exist.", e);
		} catch (Exception e) {
			log.error("Error occurred while creating new medicine details: {}", e.getMessage(), e);

			throw new ServiceException("Failed to create medicine Details", e);
		}
	}

	@Override
	public MedicineDetail checkMedicineDetailAlreadyExist(MedicineDetail medicineDetail, User user) {
		try {
			String medicineName = medicineDetail.getMedicineName();
			MedicineDetail details = medicineRepository.findByUserAndMedicineName(user, medicineName);
			log.info("Retrieving a Medicines Details according to seller");

			return details;
		} catch (Exception e) {
			log.error("Error occurred while retrieving a medicines details according to seller", e.getMessage(), e);

			throw new ServiceException("Failed to retrieve medicine Details which already exists", e);
		}
	}

	@Override
	public List<MedicineDetail> getAllMedicineDetails() {
		try {
			log.info("Retrieving a List of All medicines");

			return medicineRepository.findAll();
		} catch (Exception e) {
			log.error("Error occurred while retrieving all medicine details: {}", e.getMessage(), e);

			throw new ServiceException("Failed to get all medicine Details", e);
		}
	}

	@Override
	public List<MedicineDetail> searchMedicine(User user, String searchTerm) {
		try {
			log.info("Searching Medicine for user {}:{}", user.getUserName(), searchTerm);

			return medicineRepository.findByUserAndMedicineNameContaining(user, searchTerm);
		} catch (Exception e) {
			log.error("Error occurred while searching medicine: {}", e.getMessage(), e);

			throw new ServiceException("Failed to search medicine Details", e);
		}
	}

	@Override
	public MedicineDetail getMedicineById(int id) {
		try {
			log.info("Retrieving Medicine by ID:{}", id);

			return medicineRepository.findById(id).orElse(null);
		} catch (Exception e) {
			log.error("Error occurred while Retrieving medicine by ID: {}", e.getMessage(), e);

			throw new ServiceException("Failed to get medicine Details by id", e);
		}
	}

	@Override
	public List<MedicineDetail> getAllMedicineDetailsByUserId(int userId) {
		log.info("Retrieving a List of medicines for user with ID: " + userId);

		return medicineRepository.findByUser(userId);
	}

	@Override
	public List<MedicineDetail> searchingMedicineFromBuyer(String searchTerm) {
		try {
			log.info("Searching Medicine:{}", searchTerm);

			return medicineRepository.findByMedicineNameContaining(searchTerm);
		} catch (Exception e) {
			log.error("Error occurred while searching medicine: {}", e.getMessage(), e);

			throw new ServiceException("Failed to search medicine Details from buyer", e);
		}
	}

	@Override
	public void deleteMedicine(User user, int medicineId) throws ServiceException {
		Optional<MedicineDetail> medicineOptional = medicineRepository.findById(medicineId);

		if (medicineOptional.isPresent()) {
			MedicineDetail medicine = medicineOptional.get();

			if (medicine.getUser().getUserId() == user.getUserId()) {
				medicineRepository.deleteByMedicineId(medicineId);
				log.info("Successfully deleted medicine with id: {}", medicineId);
			} else {
				throw new UnauthorizedAccessException("Unauthorised access to delete medicine");
			}
		} else {
			throw new ServiceException("Failed to delete medicine Details");
		}
	}

	@Override
	public Page<MedicineDetail> listAll(int pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 5);
		Page<MedicineDetail> result = medicineRepository.findAll(pageable);
		log.info("Listed all medicine details for page number {}: {}", pageNo, result);
		return result;
	}

	@Override
	public Page<MedicineDetail> listAllBySeller(int pageNo, int sellerId) {
		Pageable pageable = PageRequest.of(pageNo - 1, 5);
		Page<MedicineDetail> result = medicineRepository.findAllBySellerId(sellerId, pageable);
		log.info("Listed all medicine details for seller with ID {} on page number {}: {}", sellerId, pageNo, result);
		return result;
	}

	@Override
	public Page<MedicineDetail> searchMedicineByPage(User user, String searchTerm, int pageNo) {
		int pageSize = 5;
		PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);
		Page<MedicineDetail> result = medicineRepository.searchMedicines(searchTerm, user.getUserId(), pageRequest);
		log.info("Searched medicine details for user with ID {} with search term '{}' on page number {}: {}",
				user.getUserId(), searchTerm, pageNo, result);
		return result;
	}

	@Override
	public Page<MedicineDetail> searchMedicineByPages(String searchTerm, int pageNo) {
		int pageSize = 5;
		PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);
		Page<MedicineDetail> result = medicineRepository.searchMedicinesBuyer(searchTerm, pageRequest);
		log.info("Searched medicine details with general search term '{}' on page number {}: {}", searchTerm, pageNo,
				result);
		return result;
	}

	@Override
	public void updateMedicineCount(int medicineId, int count) {
		MedicineDetail medicine = medicineRepository.findById(medicineId)
				.orElseThrow(() -> new EntityNotFoundException("Medicine not found with id " + medicineId));
		medicine.setCount(count);
		medicineRepository.save(medicine);
		log.info("Updated count for medicine with ID {}: {}", medicineId, count);
	}

	

	@Override
	public boolean checkMedicineExistsForUser(String medicineName, User user) {
		log.info("Checking if medicine already exists for user {}: {}", user.getUserName(), medicineName);
		return medicineRepository.existsByUserAndMedicineName(user, medicineName);
	}

}