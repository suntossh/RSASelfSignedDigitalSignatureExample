import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSASelfSignedDigitalSignatureExample {

    public static void main(String[] args) throws Exception {

        System.out.println("Hari Bol....");

        // Replace with your actual JSON object
        String jsonToSign = "{\"key\": \"value\"}";

        // Read private key from PEM file
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get("src/main/resources/Final/privkey.pem"));

        System.out.println("Private Key:" + new String(privateKeyBytes));

        String key = new String(privateKeyBytes);
        key = key.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\n", "");

        byte[] keyBytes = Base64.getDecoder().decode(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // Read public key from PEM file
        byte[] publicKeyBytes = Files.readAllBytes(Paths.get("src/main/resources/Final/public_key.pem"));

        System.out.println("Public Key:" + new String(publicKeyBytes));

        String pkey = new String(publicKeyBytes);
        pkey = pkey.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\n", "");

        byte[] pkeyBytes = Base64.getDecoder().decode(pkey);

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(pkeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        // Create a Signature object using SHA256withRSA algorithm
        Signature signature = Signature.getInstance("SHA256withRSA");

        // Initialize the Signature object with the private key for signing
        signature.initSign(privateKey);

        // Update the Signature object with the data to be signed
        signature.update(jsonToSign.getBytes(StandardCharsets.UTF_8));

        // Generate the digital signature
        byte[] digitalSignature = signature.sign();

        System.out.println("Digital Signature: " + Base64.getEncoder().encodeToString(digitalSignature));

        // Verify the signature using the public key
        signature.initVerify(publicKey);
        signature.update(jsonToSign.getBytes(StandardCharsets.UTF_8));

        // Verify the signature
        boolean isSignatureValid = signature.verify(digitalSignature);

        System.out.println("Is Signature Valid? " + isSignatureValid);
    }
}
