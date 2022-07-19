@file:OptIn(ExperimentalMaterial3Api::class)

package movie.app.main.presentation.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import movie.app.main.presentation.ui.theme.AppTheme
import com.google.type.Date

@JvmInline
value class Url(val url: String)

enum class Gender {
    MALE, FEMALE, ANOTHER
}

data class UserData(
    val name: String,
    val surname: String,
    val nickname: String,
    val phone: String,
    val email: String,
    val photo: Url,
    val status: String,
    val url: Url?,
    val watchedThings: Int,
    val lovelyCategory: String,
    val wasBlocking: Boolean,
    val gender: Gender,
    val dateOfBirthday: Date
)

data class UserInfo(
    val username: String,
    val nickname: String,
    val photo: Url,
    val status: String,
    val url: Url?,
    val watchedThings: Int,
    val lovelyCategory: String,
) {
    constructor(data: UserData) : this(
        username = "${data.name} ${data.surname}",
        nickname = "@${data.nickname}",
        photo = data.photo,
        status = data.status,
        url = data.url,
        watchedThings = data.watchedThings,
        lovelyCategory = data.lovelyCategory
    )
}

@Composable
fun ProfileScreen() {
    UserInformation(data = fakeUserInfo)
}

@Composable
fun UserInformation(data: UserInfo) {
    Surface {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            val (user, photo) = createRefs()
            Image(
                modifier = Modifier
                    .size(72.dp)
                    .constrainAs(photo) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start, margin = 16.dp)
                    },
                painter = rememberImagePainter(data = data.photo), contentDescription = null
            )
            Text(
                modifier = Modifier.constrainAs(user) {
                    top.linkTo(photo.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                },
                text = data.username,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

private val fakeUserInfo = UserInfo(
    username = "Viacheslav Urdzik",
    nickname = "@urdzik",
    photo = Url("https://scontent.fadb6-1.fna.fbcdn.net/v/t39.30808-6/270842124_2035233233318071_465379647039824965_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=CSwMYs7_JPsAX83sUtt&tn=VZbK7exTZQH1C5H0&_nc_ht=scontent.fadb6-1.fna&oh=00_AT_khTK22rdUTOMuka_F1Fe2Q7WzppdvkpdQqLha79k_bA&oe=62797D03"),
    status = "Love Ukraine🇺🇦",
    url = null,
    watchedThings = 11,
    lovelyCategory = "Fantastic"
)

@Composable
@Preview(showBackground = true)
fun PreviewUserInfoLight() {
    AppTheme(useDarkTheme = false) {
        UserInformation(data = fakeUserInfo)
    }
}