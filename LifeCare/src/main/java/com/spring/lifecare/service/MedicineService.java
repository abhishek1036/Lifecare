package com.spring.lifecare.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import com.spring.lifecare.entites.MedicineDetail;
import com.spring.lifecare.entites.User;
import com.spring.lifecare.exception.ServiceException;


/**
 * This interface defines the service methods related to medicine operations in
 * the LifeCare application.
 */
public interface MedicineService {

	/**
	 * Creates a new medicine detail.
	 *
	 * @param medicineDetail The medicine detail entity to be created.
	 * @return The created medicine detail entity.
	 */
	MedicineDetail createMedicineDetail(@NonNull MedicineDetail medicineDetail);

	/**
	 * Retrieves a list of all medicine details.
	 *
	 * @return A list of all medicine details.
	 */
	List<MedicineDetail> getAllMedicineDetails();

	/**
	 * Searches for medicine details based on a user and a search term.
	 *
	 * @param user       The user associated with the search.
	 * @param searchTerm The term to search for in medicine details.
	 * @return A list of medicine details matching the search criteria.
	 */
	List<MedicineDetail> searchMedicine(User user, String searchTerm);

	/**
	 * Retrieves a medicine detail by its ID.
	 *
	 * @param medicineId The ID of the medicine detail to be retrieved.
	 * @return The medicine detail entity associated with the specified ID.
	 */
	MedicineDetail getMedicineById(int medicineId);

	/**
	 * Retrieves a list of all medicine details associated with a user.
	 *
	 * @param userId The ID of the user.
	 * @return A list of medicine details associated with the specified user.
	 */
	List<MedicineDetail> getAllMedicineDetailsByUserId(int userId);

	/**
	 * Checks if a medicine detail already exists for a user.
	 *
	 * @param medicineDetail The medicine detail to be checked.
	 * @param user           The user associated with the medicine detail.
	 * @return The existing medicine detail if found; otherwise, null.
	 */
	MedicineDetail checkMedicineDetailAlreadyExist(MedicineDetail medicineDetail, User user);

	/**
	 * Deletes a medicine detail associated with a user.
	 *
	 * @param user       The user associated with the medicine detail.
	 * @param medicineId The ID of the medicine detail to be deleted.
	 * @throws Exception If an error occurs during deletion.
	 */
	void deleteMedicine(User user, int medicineId) throws ServiceException;

	/**
	 * Searches for medicine details from a buyer's perspective.
	 *
	 * @param searchTerm The term to search for in medicine details.
	 * @return A list of medicine details matching the search criteria.
	 */
	List<MedicineDetail> searchingMedicineFromBuyer(String searchTerm);

	/**
	 * Lists all medicine details with pagination.
	 *
	 * @param pageNo The page number to retrieve.
	 * @return A page of medicine details.
	 */
	Page<MedicineDetail> listAll(int pageNo);

	/**
	 * Lists all medicine details by seller with pagination.
	 *
	 * @param pageNo   The page number to retrieve.
	 * @param sellerId The ID of the seller.
	 * @return A page of medicine details for the specified seller.
	 */
	Page<MedicineDetail> listAllBySeller(int pageNo, int sellerId);

	/**
	 * Searches for medicine details by page based on a user and a search term.
	 *
	 * @param user       The user associated with the search.
	 * @param searchTerm The term to search for in medicine details.
	 * @param pageNo     The page number to retrieve.
	 * @return A page of medicine details matching the search criteria.
	 */
	Page<MedicineDetail> searchMedicineByPage(User user, String searchTerm, int pageNo);

	/**
	 * Searches for medicine details by page based on a general search term.
	 *
	 * @param searchTerm The term to search for in medicine details.
	 * @param pageNo     The page number to retrieve.
	 * @return A page of medicine details matching the search criteria.
	 */
	Page<MedicineDetail> searchMedicineByPages(String searchTerm, int pageNo);

	/**
	 * Updates the count of a medicine detail.
	 *
	 * @param medicineId The ID of the medicine detail to be updated.
	 * @param count      The new count value.
	 */
	void updateMedicineCount(int medicineId, int count);

	/**
	 * Checks if a medicine exists for a user based on the medicine name.
	 *
	 * @param medicineName The name of the medicine to check.
	 * @param user         The user associated with the medicine.
	 * @return True if the medicine exists for the user, false otherwise.
	 */
	boolean checkMedicineExistsForUser(String medicineName, User user);
}