package seg3102.group25.wellmeadows.hmspms.infrastructure.database.services

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import seg3102.group25.wellmeadows.hmspms.HmspmsApplication
import java.io.File
import java.io.FileInputStream
import java.util.*


@Service
class FireBaseInitialize {

    @PostConstruct
    fun initialize(){
        try {
            val classLoader: ClassLoader = HmspmsApplication::class.java.classLoader

            val file = File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).file)
            val serviceAccount = FileInputStream(file.absolutePath)

            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://seg3102-josiahbigras-default-rtdb.firebaseio.com/")
                .build()
            FirebaseApp.initializeApp(options)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}