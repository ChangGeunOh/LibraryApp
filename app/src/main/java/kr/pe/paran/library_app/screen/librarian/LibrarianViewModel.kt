package kr.pe.paran.library_app.screen.librarian

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.pe.paran.library_app.model.AccountData
import kr.pe.paran.library_app.model.LibrarianData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.model.PersonData
import kr.pe.paran.library_app.repository.Repository
import javax.inject.Inject

@HiltViewModel
class LibrarianViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var _librarianData = MutableStateFlow<LibrarianData>(LibrarianData())
    var librarianData = _librarianData.asStateFlow()

    fun loadLibrarianData() {
        viewModelScope.launch {
            repository.loadLoginLibrarianData().collectLatest {
                _librarianData.value = it
            }
        }
    }
}