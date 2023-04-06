package dev.rranndt.projectexpenses.presentation.feature_setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.UniversalText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun showSnackBar() {
        viewModelScope.launch(Dispatchers.IO) {
            _eventFlow.emit(
                UiEvent.ShowSnackBar(
                    message = UniversalText.Resource(R.string.in_development)
                )
            )
        }
    }
}

sealed class UiEvent {
    data class ShowSnackBar(val message: UniversalText) : UiEvent()
}