package moe.pine.stkrep.sheets;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.Credentials;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.experimental.UtilityClass;
import moe.pine.stkrep.sheets.exceptions.NonRetryableException;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

@UtilityClass
class SheetsFactory {
    Sheets create(
            String applicationName,
            InputStream credentialsStream
    ) {
        final Credentials credentials;
        try {
            credentials = GoogleCredentials.fromStream(credentialsStream)
                    .createScoped(SheetsScopes.all());
        } catch (IOException e) {
            throw new NonRetryableException("Unable to read credentials.", e);
        }

        final HttpTransport httpTransport;
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException | IOException e) {
            throw new NonRetryableException("Unable to create HttpTransport.", e);
        }

        final JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        final HttpRequestInitializer httpRequestInitializer = new HttpCredentialsAdapter(credentials);

        return new Sheets.Builder(httpTransport, jsonFactory, httpRequestInitializer)
                .setApplicationName(applicationName)
                .build();
    }
}
