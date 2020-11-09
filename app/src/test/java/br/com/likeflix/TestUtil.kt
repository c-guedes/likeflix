package br.com.likeflix

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import io.mockk.spyk
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import org.robolectric.shadows.ShadowActivity
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader


inline fun <reified T> readResource(filename: String): T {
    return Gson().fromJson(readJSONFromResource(filename), T::class.java)
}

fun readJSONFromResource(filename: String): String {
    val bufferReader =
        BufferedReader(InputStreamReader(FileInputStream("../app/src/main/assets/${filename}")))

    val stringBuilder = StringBuilder()
    var line = bufferReader.readLine()
    while (line != null) {
        stringBuilder.append(line)
        line = bufferReader.readLine()
    }

    return stringBuilder.toString()
}

infix fun <T> T.assertInstanceOf(clazz: Class<*>) {
    Assert.assertThat(this, CoreMatchers.instanceOf(clazz))
}

data class RoboletricBuilder<T : Activity>(
    val controller: ActivityController<T>,
    var spykActivity: T,
    val shadowActivity: ShadowActivity
)

inline fun <reified T : AppCompatActivity> startActivityPausingLooper(
    intent: Intent = Intent()
): RoboletricBuilder<T> {
    Robolectric.getForegroundThreadScheduler().pause()
    val robolectricBuilder: RoboletricBuilder<T> = buildRobolectricActivity(intent)
    robolectricBuilder.startResumeVisible()
    return robolectricBuilder
}

fun <T : Activity> RoboletricBuilder<T>.startResumeVisible() {
    controller.start().postCreate(null).resume().visible()
    spykActivity = controller.get()
}

inline fun <reified T : Activity> buildRobolectricActivity(intent: Intent = Intent()): RoboletricBuilder<T> {
    val controller = Robolectric.buildActivity(T::class.java, intent)
    val spykActivity = spyk(controller.setup().get(), recordPrivateCalls = true)
    val shadowActivity = shadowOf(spykActivity)
    return RoboletricBuilder<T>(controller, spykActivity, shadowActivity)
}

fun RecyclerView.expandOnRobolectric(): RecyclerView{
    measure(0,0)
    layout(0,0,1000,1000)
    return this
}
