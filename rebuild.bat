@echo off
echo ========================================================
echo SmartCourier Complete Rebuild and Launch Script
echo ========================================================
echo WARNING: This will kill any running Java and Node processes!
pause

echo Killing existing Java processes...
taskkill /F /IM java.exe /T 2>NUL

echo Cleaning and Rebuilding Backend Microservices...
cd Backend\SmartCourierDeliveryManagementSystem_Sprint-smartcourier-upload

echo Building Eureka Server...
cd eureka-server && call mvn clean install -U -DskipTests && cd ..

echo Building Config Server...
cd config-server && call mvn clean install -U -DskipTests && cd ..

echo Building API Gateway...
cd api-gateway && call mvn clean install -U -DskipTests && cd ..

echo Building Auth Service...
cd auth-service && call mvn clean install -U -DskipTests && cd ..

echo Building Delivery Service...
cd delivery-service && call mvn clean install -U -DskipTests && cd ..

echo Building Tracking Service...
cd tracking-service && call mvn clean install -U -DskipTests && cd ..

echo Building Admin Service...
cd admin-service && call mvn clean install -U -DskipTests && cd ..

echo ========================================================
echo All Microservices Built Successfully!
echo Starting Services in Correct Order...
echo ========================================================

start "Eureka Server" cmd /k "cd eureka-server && mvn spring-boot:run"
echo Waiting for Eureka to initialize...
timeout /t 10 /nobreak > NUL

start "Config Server" cmd /k "cd config-server && mvn spring-boot:run"
echo Waiting for Config Server to initialize...
timeout /t 10 /nobreak > NUL

start "API Gateway" cmd /k "cd api-gateway && mvn spring-boot:run"
start "Auth Service" cmd /k "cd auth-service && mvn spring-boot:run"
start "Delivery Service" cmd /k "cd delivery-service && mvn spring-boot:run"
start "Tracking Service" cmd /k "cd tracking-service && mvn spring-boot:run"
start "Admin Service" cmd /k "cd admin-service && mvn spring-boot:run"

echo ========================================================
echo Backend Services Started in separate windows.
echo Now rebuilding and starting the Frontend...
echo ========================================================

cd ..\..\frontend
echo Installing Node dependencies...
call npm install
echo Starting React Frontend...
start "React Frontend" cmd /k "npm run dev"

echo ========================================================
echo SYSTEM REBUILD COMPLETE!
echo ALL SERVICES ARE STARTING. PLEASE WAIT FOR TERMINALS TO LOAD.
echo ========================================================
pause
