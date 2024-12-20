run java version via terminal:
C:\Users\Alex\.jdks\corretto-17.0.10\bin\java

Example to run .jar:
C:\Users\Alex\.jdks\corretto-17.0.10\bin\java -jar .\target\api-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

BUT IN THIS CASE I NEED TO DEFINE env VARIABLE ON COMMAND:
C:\Users\Alex\.jdks\corretto-17.0.10\bin\java -jar .\target\api-0.0.1-SNAPSHOT.jar
    --spring.profiles.active=prod
    --DB_HOST=
    --DB_NAME=
    --DB_USER=
    --DB_PASSWORD=
    --API_SECURITY_SECRET=