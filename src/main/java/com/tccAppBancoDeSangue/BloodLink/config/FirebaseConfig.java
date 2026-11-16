
package com.tccAppBancoDeSangue.BloodLink.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() throws IOException {

        if (!FirebaseApp.getApps().isEmpty()) {
            return;
        }

        String firebaseBase64 = System.getenv("FIREBASE_SERVICE_ACCOUNT_BASE64");

        GoogleCredentials credentials;

        if (firebaseBase64 != null && !firebaseBase64.isBlank()) {

            // Railway: utiliza a variável de ambiente
            byte[] decoded = Base64.getDecoder().decode(firebaseBase64);

            try (InputStream serviceAccount =
                    new ByteArrayInputStream(decoded)) {

                credentials = GoogleCredentials.fromStream(serviceAccount);
            }

        } else {

            // Ambiente local: utiliza o arquivo JSON
            InputStream serviceAccount = getClass()
                    .getClassLoader()
                    .getResourceAsStream("firebase/firebase-admin.json");

            if (serviceAccount == null) {
                throw new IOException(
                        "Arquivo de credenciais do Firebase não encontrado."
                );
            }

            try (serviceAccount) {
                credentials = GoogleCredentials.fromStream(serviceAccount);
            }
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        FirebaseApp.initializeApp(options);
    }
}
