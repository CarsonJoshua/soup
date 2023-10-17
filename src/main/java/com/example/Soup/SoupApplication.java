package com.example.Soup;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class SoupApplication {

	public static void main(String[] args) throws IOException {



		FileInputStream serviceAccount =
				new FileInputStream(
						Objects.requireNonNull(
								SoupApplication.class.getClassLoader()
										.getResource("serviceAccountKey.json")
						)
								.getFile()
				);

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				//do I need to set firebase url here?
				//.setDatabaseUrl()
				.build();

		FirebaseApp.initializeApp(options);


		SpringApplication.run(SoupApplication.class, args);
	}

}
