Encryption
It's all About Confidentiality.
A message Encrypted for an intended End Party must only be able to Decrypt it.
Any other Party should not be able to Decrypt it.

    To do this Key based Encryption must be used, because hackers can reverse Engineer Known
    Algorithm to Decrypt the data. So adding a Secret Key in the message and then running Encryption
    on it will make it impossible for hacker.

Encryption Algorithm are created to Work with Mathematically Linked Keys.
Which means Encryption with One Key (X) can be decrypted by its Mathematically Linked Key (Y).
These 2 Keys are called as Public Key and Private Key.
So Users Can Share its Public Key to the Worlds and World can use this Pub Key to Encrypt when message is intended or this User.
And Only this user can Decrypt the message using Mathematically linked Private Key which it only has.

Eg of Asymmetric Encrption Algo
DSA
RSA
Diffie-Hellman
.
.


This Simple projects provides you with working code which (Encrypts using private key and Decrypts using public key)
generates a Digital Signature using private key and validates the signature using public key.

This feature is used as a means to securely transfer data over https using SSL/TLS.

Run below 3 openssl commands on cmd Terminal to geneate a CSR and then generate a self-signed certificate.

below command will generate private key (privkey.pem) and csr CSR.pem
    openssl req -new -out CSR.pem -subj "/C=US/ST=NJ/L=WoodBridge/O=MyOrganization/CN=Santossh" -nodes

below command will generate a self-signed certificate using csr and private key
    openssl x509 -req -days 365 -in CSR.pem -signkey privkey.pem -out CERT.pem

below command will generate public key out of signed Certificate
    openssl x509 -inform PEM -in CERT.pem -pubkey -noout > public_key.pem

Bonus, 
use below 3 commands to ensure private key, csr and selfsigned certificate are matching
    Openssl req -in CSR.pem -noout -modulus | sha1sum
    Openssl x509 -in CERT.pem -noout -modulus | sha1sum
    Openssl rsa -in privkey.pem -noout -modulus | sha1sum

Finally Run RSASelfSignedDigitalSignatureExample code to See the Demo.
