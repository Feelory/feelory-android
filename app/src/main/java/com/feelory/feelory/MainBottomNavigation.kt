import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.feelory.feelory.R
import com.feelory.feelory.feature.feed.navigation.FEED_ROUTE
import com.feelory.feelory.feature.feed.navigation.navigateFeed
import com.feelory.feelory.feature.home.navigation.HOME_ROUTE
import com.feelory.feelory.feature.home.navigation.navigateHome
import com.feelory.feelory.feature.log.navigation.LOG_ROUTE
import com.feelory.feelory.feature.log.navigation.navigateLog
import com.feelory.feelory.feature.mypage.navigation.MY_PAGE_ROUTE
import com.feelory.feelory.feature.mypage.navigation.navigateMyPage

@Composable
fun MainBottomBar(
    currentDestination: NavDestination,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit = {}
) {
    val density = LocalDensity.current

    val tabs = listOf(
        BottomTab.HOME,
        BottomTab.FEED,
        BottomTab.LOG,
        BottomTab.MY_PAGE
    )

    Box(
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(88.dp),
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val path = createCurvedPath(size, density)

            drawPath(
                path = path,
                color = Color.White,
                style = androidx.compose.ui.graphics.drawscope.Fill
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.take(2).forEach { tab ->
                NavigationBarItem(
                    tab = tab,
                    isSelected = currentDestination.route == tab.route,
                    onClick = {
                        navController.navigateToTabScreen(tab)
                    }
                )
            }

            Spacer(modifier = Modifier.width(100.dp))

            tabs.drop(2).forEach { tab ->
                NavigationBarItem(
                    tab = tab,
                    isSelected = currentDestination.route == tab.route,
                    onClick = {
                        navController.navigateToTabScreen(tab)
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = onFabClick,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = -25.dp)
                .size(56.dp),
            containerColor = colorResource(R.color.green),
            shape = CircleShape
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_bottom_fab_write),
                contentDescription = stringResource(R.string.write)
            )
        }
    }
}

private fun createCurvedPath(size: Size, density: Density): Path {
    val path = Path()

    // 뷰의 크기에 따른 계산
    val width = size.width
    val height = size.height

    // FAB 버튼의 반지름과 관련된 계산
    val fabRadius = with(density) { 28.dp.toPx() }
    val cornerRadius = with(density) { 20.dp.toPx() }

    // 원형 홀의 반지름 ]]
    val circleRadius = fabRadius + with(density) { 8.dp.toPx() }

    // 주요 포인트들 계산
    val centerX = width / 2f
    val centerY = 0f // 원의 중심 Y좌표

    // 완벽한 원형을 위한 베지어 곡선 제어점 계산
    val bezierConstant = 0.5522848f // 원을 만들기 위한 베지어 상수
    val controlOffset = circleRadius * bezierConstant

    // 원형 홀의 주요 점들
    val leftX = centerX - circleRadius    // 왼쪽 점
    val rightX = centerX + circleRadius   // 오른쪽 점
    val topY = centerY                    // 위쪽 점
    val bottomY = centerY + circleRadius  // 아래쪽 점

    // 부드러운 전환을 위한 반지름
    val smoothRadius = with(density) { 12.dp.toPx() }

    // 외곽 경로 구성
    path.apply {
        // 시작점으로 이동 (왼쪽 상단)
        moveTo(0f, 0f)

        // 왼쪽 상단 라운드 코너
        quadraticBezierTo(0f, 0f, cornerRadius, 0f)

        // 곡선 시작점 전까지 직선
        lineTo(leftX - smoothRadius * 1.8f, 0f)

        // 곡선 시작 부분 훨씬 더 둥글게 전환
        quadraticBezierTo(
            leftX, 0f,
            leftX, smoothRadius * 0.8f
        )

        // 원형 홀의 왼쪽 점까지 직선
        lineTo(leftX, topY)

        // 베지어 곡선 1: 왼쪽 → 아래 (90도 곡선)
        cubicTo(
            leftX, topY + controlOffset,          // 첫 번째 제어점
            centerX - controlOffset, bottomY,     // 두 번째 제어점
            centerX, bottomY                      // 끝점 (아래쪽)
        )

        // 베지어 곡선 2: 아래 → 오른쪽 (90도 곡선)
        cubicTo(
            centerX + controlOffset, bottomY,     // 첫 번째 제어점
            rightX, topY + controlOffset,         // 두 번째 제어점
            rightX, topY                          // 끝점 (오른쪽)
        )

        // 원형 홀의 오른쪽 점에서 위로
        lineTo(rightX, smoothRadius * 0.8f)

        // 곡선 끝 둥글게
        quadraticBezierTo(
            rightX, 0f,
            rightX + smoothRadius * 1.8f, 0f
        )

        // 오른쪽 상단 라운드 코너까지 직선
        lineTo(width - cornerRadius, 0f)

        // 오른쪽 상단 라운드 코너
        quadraticBezierTo(width, 0f, width, 0f)

        // 오른쪽 세로 직선
        lineTo(width, height)

        // 하단 직선
        lineTo(0f, height)

        // 왼쪽 세로 직선
        lineTo(0f, cornerRadius)

        close()
    }

    return path
}

@Composable
private fun NavigationBarItem(
    tab: BottomTab,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp)
            .width(56.dp)
    ) {
        Icon(
            painter = painterResource(
                id = if (isSelected) tab.iconSelectedResId else tab.iconUnSelectedResId
            ),
            contentDescription = stringResource(id = tab.contentDescription),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(id = tab.contentDescription),
            fontSize = 12.sp,
            color = colorResource(R.color.gray_700),
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
        )
    }
}

private fun NavController.navigateToTabScreen(bottomTab: BottomTab) {
    val tabNavOptions =
        navOptions {
            popUpTo(graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

    when (bottomTab) {
        BottomTab.HOME -> navigateHome(tabNavOptions)
        BottomTab.FEED -> navigateFeed(tabNavOptions)
        BottomTab.LOG -> navigateLog(tabNavOptions)
        BottomTab.MY_PAGE -> navigateMyPage(tabNavOptions)
    }
}

enum class BottomTab(
    val iconUnSelectedResId: Int,
    val iconSelectedResId: Int,
    @StringRes val contentDescription: Int,
    val route: String,
) {
    HOME(
        iconUnSelectedResId = R.drawable.ic_bottom_home,
        iconSelectedResId = R.drawable.ic_bottom_home_select,
        contentDescription = R.string.home,
        HOME_ROUTE,
    ),
    FEED(
        iconUnSelectedResId = R.drawable.ic_bottom_feed,
        iconSelectedResId = R.drawable.ic_bottom_feed_select,
        contentDescription = R.string.feed,
        FEED_ROUTE,
    ),
    LOG(
        iconUnSelectedResId = R.drawable.ic_bottom_log,
        iconSelectedResId = R.drawable.ic_bottom_log_select,
        contentDescription = R.string.log,
        LOG_ROUTE,
    ),
    MY_PAGE(
        iconUnSelectedResId = R.drawable.ic_bottom_my_page,
        iconSelectedResId = R.drawable.ic_bottom_my_page_select,
        contentDescription = R.string.my_page,
        MY_PAGE_ROUTE,
    ),
}
