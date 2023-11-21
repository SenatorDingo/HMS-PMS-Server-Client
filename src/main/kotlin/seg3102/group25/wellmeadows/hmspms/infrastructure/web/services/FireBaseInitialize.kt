package seg3102.group25.wellmeadows.hmspms.infrastructure.web.services

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import java.io.FileInputStream


@Service
class FireBaseInitialize {

    @PostConstruct
    fun initialize(){
        try {
            val serviceAccount = FileInputStream("static/keys/databaseKey.json")
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