package com.spring.lifecare.service;

import java.util.List;

import com.spring.lifecare.entites.Cart;
import com.spring.lifecare.entites.CartItems;
import com.spring.lifecare.entites.MedicineDetail;


/**
 * CartService defines the interface for managing user shopping carts, providing
 * operations such as adding and deleting medicines, calculating total prices,
 * and handling cart items.
 */
public interface CartService {

	/**
	 * Adds a specified quantity of a medicine to the user's shopping cart.
	 *
	 * @param userId     The ID of the user.
	 * @param medicineId The ID of the medicine to be added.
	 * @param quantity   The quantity of the medicine to be added.
	 * @return The updated Cart after adding the medicine.
	 */
	Cart addMedicineToCart(int userId, int medicineId, int quantity);

	/**
	 * Deletes a medicine from the user's shopping cart.
	 *
	 * @param userId     The ID of the user.
	 * @param medicineId The ID of the medicine to be deleted.
	 * @return The updated Cart after deleting the medicine.
	 */
	Cart deleteMedicineFromCart(int userId, int medicineId);

	/**
	 * Calculates the total price of items in the user's shopping cart.
	 *
	 * @param userId The ID of the user.
	 * @return The total price of items in the user's shopping cart.
	 */
	double calculateTotalPrice(int userId);

	/**
	 * Retrieves the list of cart items for a specific user.
	 *
	 * @param userId The ID of the user.
	 * @return The list of CartItems associated with the user's shopping cart.
	 */
	List<CartItems> getCartItemsByUserId(int userId);

	/**
	 * Increments the quantity of a specific cart item.
	 *
	 * @param itemId The ID of the cart item to be incremented.
	 * @param userId The ID of the user.
	 */
	void incrementCartItemQuantity(int itemId, int userId);

	/**
	 * Decrements the quantity of a specific cart item.
	 *
	 * @param itemId The ID of the cart item to be decremented.
	 * @param userId The ID of the user.
	 */
	void decrementCartItemQuantity(int itemId, int userId);

	/**
	 * Finds a cart item based on the associated medicine and cart.
	 *
	 * @param cart     The Cart to search within.
	 * @param medicine The MedicineDetail associated with the cart item.
	 * @return The CartItems object if found, null otherwise.
	 */
	CartItems findCartItemByMedicineAndCart(Cart cart, MedicineDetail medicine);

	/**
	 * Updates the total price of a shopping cart.
	 *
	 * @param cart The Cart to be updated.
	 */
	void updateTotalPrice(Cart cart);

	/**
	 * Adds selected medicines to the user's shopping cart.
	 *
	 * @param userId          The ID of the user.
	 * @param selectedIdsList The list of medicine IDs to be added to the cart.
	 * @return The updated Cart after adding the selected medicines.
	 */
	Cart addToCart(int userId, List<Integer> selectedIdsList);

	/**
	 * Calculates the total price of SOS (emergency) medicines in the user's
	 * shopping cart.
	 *
	 * @param userId The ID of the user.
	 * @return The total price of SOS medicines in the user's shopping cart.
	 */

	/**
	 * Clears all cart items for a specific user.
	 *
	 * @param userId The ID of the user.
	 */
	void clearCartItemsForUser(int userId);

	/**
	 * Calculates the payable price for a card holder based on the user ID.
	 *
	 * @param userId The ID of the user.
	 * @return The calculated payable price.
	 */
	double calculatePayablePriceForCardHolder(int userId);

	/**
	 * Calculates the total non-SOS price in the user's cart.
	 *
	 * @param userId The ID of the user.
	 * @return The calculated total non-SOS price in the cart.
	 */
	double calculateTotalPriceInCart(int userId);

	/**
	 * Calculates the total price of SOS items for a card holder based on the user
	 * ID.
	 *
	 * @param userId The ID of the user.
	 * @return The calculated total SOS price for the card holder.
	 */

}
