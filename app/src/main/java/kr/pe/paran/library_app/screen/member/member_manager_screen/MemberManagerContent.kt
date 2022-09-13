
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kr.pe.paran.library_app.component.GenieBarCodeView
import kr.pe.paran.library_app.component.GenieDropDownMenu
import kr.pe.paran.library_app.component.GenieOutlinedTextField
import kr.pe.paran.library_app.component.GenieRoundedButton
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.model.type.AccountStatus
import kr.pe.paran.library_app.utils.Logcat

@Composable
fun MemberManagerContent(
    memberData: MemberData,
    onChangValue: (MemberData) -> Unit,
    onClickAddress: () -> Unit = {},
    onClickSave: (MemberData) -> Unit,
    onClickSearch: () -> Unit = {}
) {
    Logcat.i(memberData.toString())
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
    ) {
        item {

            GenieOutlinedTextField(
                label = "이름",
                text = memberData.personData.name,
                onValueChange = {
                    onChangValue(memberData.copy(personData = memberData.personData.copy(name = it)))
                },
                trailingIcon = {
                    IconButton(onClick = {
                        onClickSearch()
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                    }
                }
            )
            GenieOutlinedTextField(
                label = "이메일",
                text = memberData.personData.email,
                onValueChange = {
                    onChangValue(memberData.copy(personData = memberData.personData.copy(email = it)))
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            GenieOutlinedTextField(
                label = "전화번호",
                text = memberData.personData.phone,
                onValueChange = {
                    onChangValue(memberData.copy(personData = memberData.personData.copy(phone = it)))
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                )
            )
            GenieOutlinedTextField(label = "주소",
                onValueChange = {},
                text = memberData.personData.addressData.getSummary(),
                trailingIcon = {
                    IconButton(onClick = { onClickAddress() }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "주소검색")
                    }
                })
            GenieOutlinedTextField(label = "상세주소", onValueChange = {
                onChangValue(
                    memberData.copy(
                        personData = memberData.personData.copy(
                            addressData = memberData.personData.addressData.copy(extraAddress = it)
                        )
                    )
                )
            })
            GenieOutlinedTextField(label = "사용ID",
                text = memberData.accountData.userid,
                onValueChange = {
                    onChangValue(memberData.copy(accountData = memberData.accountData.copy(userid = it)))
                })
            GenieOutlinedTextField(
                text = memberData.accountData.userpw,
                label = "비밀번호",
                onValueChange = {
                    onChangValue(memberData.copy(accountData = memberData.accountData.copy(userpw = it)))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done
                )
            )
            GenieDropDownMenu(
                items = listOf(AccountStatus.Active.toString(), AccountStatus.DeActive.toString()),
                text = memberData.accountData.status.name,
                label = "계정상태",
                onValueChange = {
                    onChangValue(
                        memberData.copy(
                            accountData = memberData.accountData.copy(
                                status = AccountStatus.valueOf(
                                    it
                                )
                            )
                        )
                    )
                }
            )

            GenieOutlinedTextField(
                label = "카드번호",
                text = memberData.cardNumber,
                onValueChange = {},
                enabled = false
            )

            GenieBarCodeView(barCode = memberData.barCode, isShowShareIcon = false)

            Spacer(modifier = Modifier.height(16.dp))
            GenieRoundedButton(
                text = "수정하기",
                enabled = memberData.isValid()
            ) {
                onClickSave(memberData)
            }
        }
    }
}