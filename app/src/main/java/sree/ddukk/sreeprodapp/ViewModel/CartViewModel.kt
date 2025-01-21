import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<Map<String, Int>>()
    val cartItems: LiveData<Map<String, Int>> get() = _cartItems

    val cartSize: LiveData<Int> = MutableLiveData<Int>().apply {
        _cartItems.observeForever { cartMap ->
            value = cartMap.values.sum()
        }
    }

    init {
        _cartItems.value = emptyMap()
    }
    fun addToCart(productId: String) {
        val currentCart = _cartItems.value.orEmpty()
        val updatedCart = currentCart.toMutableMap()
        updatedCart[productId] = (updatedCart[productId] ?: 0) + 1
        _cartItems.value = updatedCart
    }
}
