package apiCalculadoraGradoDeConfianza;

import okhttp3.OkHttpClient;
import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.SecureRandom;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class UnsafeOkHttpClient {
    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Crear un gestor de confianza que no valide las cadenas de certificados
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Instalar el gestor de confianza todo-confiable
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            // Crear una fábrica de sockets SSL que use nuestro gestor de confianza todo-confiable
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true; // No realizar ninguna verificación de nombre de host
                }
            });

            return builder.build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }
}
