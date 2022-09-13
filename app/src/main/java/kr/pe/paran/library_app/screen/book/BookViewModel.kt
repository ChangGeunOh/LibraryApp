package kr.pe.paran.library_app.screen.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.pe.paran.library_app.model.*
import kr.pe.paran.library_app.model.type.AccountStatus
import kr.pe.paran.library_app.model.type.BookStatus
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_CODE_BOOK_ITEM
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_CODE_MEMBER
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_INSERT_AUTHOR
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_INSERT_BOOK
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_INSERT_BOOK_ITEM
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_LOAN_BOOK_ITEM
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_STATUS_BOOK_ITEM
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_SEARCH_AUTHOR
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_SEARCH_BOOK
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_SEARCH_BOOK_ITEM
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_UPDATE_BOOK_ITEM
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.repository.Repository
import kr.pe.paran.library_app.screen.ViewModelInterface
import kr.pe.paran.library_app.utils.Logcat
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(), ViewModelInterface {

    override var _networkStatus: MutableStateFlow<NetworkStatus> = MutableStateFlow(NetworkStatus.IDLE)
    override var _message: MutableStateFlow<String> = MutableStateFlow("")

    /** BookViewModel Common ------------------------------> */
    private suspend fun loadBookItem(barCode: String): Any? {
        _networkStatus.value = NetworkStatus.LOADING
        val networkStatus =
            repository.getBookItem(barcode = barCode, request = REQUEST_CODE_BOOK_ITEM)
        _networkStatus.value = NetworkStatus.IDLE
        return processNetworkStatus(networkStatus)
    }

    /** <------------------------------ BookViewModel Common */


    fun insertBookData(bookData: BookData) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            _networkStatus.value =
                repository.insertBookData(bookData = bookData, request = REQUEST_INSERT_BOOK)
        }
    }

    fun searchAuthor(it: String) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            _networkStatus.value =
                repository.searchAuthor(query = it, request = REQUEST_SEARCH_AUTHOR)
            clearNetworkState()
        }

    }

    fun insertAuthor(authorData: AuthorData) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            _networkStatus.value =
                repository.insertAuthor(authorData = authorData, request = REQUEST_INSERT_AUTHOR)
        }
    }

    /** BookItemScreen.kt ---------------------------------> */

    // Scan 한 MemberCardNumber
    private var _memberCardNumber = MutableStateFlow("")
    val memberCardNumber = _memberCardNumber.asStateFlow()
    fun setMemberCardNumber(number: String) {
        _memberCardNumber.value = number
    }

    private var _memberData = MutableStateFlow<MemberData?>(null)
    val memberData = _memberData.asStateFlow()

    // Scan MemberCard -> MemberData
    fun getMemberData(cardNo: String) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            val networkStatus =
                repository.getMemberData(cardNo = cardNo, request = REQUEST_CODE_MEMBER)
            processNetworkStatus(networkStatus = networkStatus)?.let {
                val memberData = it as MemberData
                _memberData.value = memberData
                if (memberData.accountData.status == AccountStatus.DeActive) {
                    _message.value = "정지된 계정(카드) 입니다."
                }
            } ?: kotlin.run {
                _message.value = "유효한 카드가 아닙니다."
            }
            _networkStatus.value = NetworkStatus.IDLE
            clearMessage()
        }
    }

    // 스캔한 BookItemData 누적을 위한 변수
    private var _bookItemList = MutableStateFlow<MutableList<BookItemData>>(mutableListOf())
    val bookItemList = _bookItemList.asStateFlow()

    // 스캔한 BookItem BarCode -> BookItemData
    fun appendBookItem(barCode: String) {
        viewModelScope.launch {
            loadBookItem(barCode = barCode)?.let {
                if (!_bookItemList.value.contains(it)) {
                    _bookItemList.value.add(it as BookItemData)
                }
            }
        }
    }

    // 대여 도서 취소
    fun removeBookItem(bookItemData: BookItemData) {
        _bookItemList.value.remove(bookItemData)
    }

    // 도서 대여하기 (상태변경 : Idle -> Loaned)
    fun loanBookItem(memberCard: String, bookItemList: List<BookItemData>) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            bookItemList.forEach {
                val bookItemStatusData = BookItemStatusData(
                    bookItemBarCode = it.barCode,
                    memberCardCode = memberCard,
                    bookItemStatus = BookStatus.Loaned
                )

                val networkStatus = repository.updateBookItemStatus(
                    reserveData = bookItemStatusData,
                    request = REQUEST_LOAN_BOOK_ITEM
                )
                processNetworkStatus(networkStatus = networkStatus)?.let { data ->
                    val bookItemData = data as BookItemData
                    _bookItemList.value.removeIf { item ->
                        item.barCode == bookItemData.barCode
                    }
                }
            }
            _message.value = "도서 대여 처리를 하였습니다."
        }
    }

    /** <--------------------------------- BookItemScreen.kt */

    /** BookItemManagerScreen.kt --------------------------> */

    private var _bookItemData = MutableStateFlow<BookItemData>(BookItemData())
    val bookItemData = _bookItemData.asStateFlow()

    fun getBookItem(barCode: String) {
        viewModelScope.launch {
            loadBookItem(barCode = barCode)?.let {
                _bookItemData.value = loadBookItem(barCode = barCode) as BookItemData
            }
        }
    }

    fun setBookItemData(bookItemData: BookItemData) {
        _bookItemData.value = bookItemData
    }

    fun updateBookItemData(bookItemData: BookItemData) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            repository.updateBookItemData(bookItemData, request = REQUEST_UPDATE_BOOK_ITEM)
            _networkStatus.value = NetworkStatus.IDLE
        }
    }

    /** <-------------------------- BookItemManagerScreen.kt */

    fun insertBookItem(bookItemData: BookItemData) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            _networkStatus.value =
                repository.insertBookItem(bookItemData, request = REQUEST_INSERT_BOOK_ITEM)
        }
    }

    fun searchBook(query: String) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            _networkStatus.value =
                repository.searchBook(query = query, request = REQUEST_SEARCH_BOOK)
            clearNetworkState()
        }
    }

    fun searchBookItem(query: String) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            val networkStatus =
                repository.searchBookItem(query = query, request = REQUEST_SEARCH_BOOK_ITEM)
            Logcat.i(":::::${networkStatus.toString()}")
            setNetworkStatus(networkStatus)

        }
    }

    fun updateBookItemStatus(memberCard: String, bookItemBarCode: String) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            val reserveData = BookItemStatusData(
                bookItemBarCode = bookItemBarCode,
                memberCardCode = memberCard,
                bookItemStatus = BookStatus.Reserved
            )
            _networkStatus.value = repository.updateBookItemStatus(
                reserveData = reserveData,
                request = REQUEST_STATUS_BOOK_ITEM
            )
            clearNetworkState()
        }
    }

    fun updateBookItemStatus(bookItemStatusData: BookItemStatusData) {

        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            _networkStatus.value = repository.updateBookItemStatus(
                reserveData = bookItemStatusData,
                request = REQUEST_STATUS_BOOK_ITEM
            )
            clearNetworkState()
        }
    }



}