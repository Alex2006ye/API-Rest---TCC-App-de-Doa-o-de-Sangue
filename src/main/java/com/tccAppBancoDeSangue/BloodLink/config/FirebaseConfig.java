package com.tccAppBancoDeSangue.BloodLink.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

// basicamente essa classe é responsável por inicializar o Firebase com as credenciais do arquivo JSON, garantindo que a aplicação 
// possa se comunicar com os serviços do Firebase.

@Configuration
public class FirebaseConfig {
    
    // essa anotação @PostConstruct indica que o método initialize() deve ser executado logo após a criação do bean, 
    // garantindo que o Firebase seja inicializado antes de qualquer operação que dependa dele.
    @PostConstruct
    public void initialize() throws IOException {
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase/firebase-admin.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

}
