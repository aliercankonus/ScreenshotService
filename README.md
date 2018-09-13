# 4DSight
Install Postgresql to local and create database which name must be fourdsight.
Install apache activemq as a service on windows. After download file, run InstallService.bat in win64 or win32 directory.
Open Services (Start -> Run -> services.msc).
Open the properties of the ActiveMQ service.
Verify that "Startup type" is set to Automatic.
Start the Service.

Download the project and open "ScreenshotService\build\libs" folder and run "java -jar fourdsight-0.0.1-SNAPSHOT.jar" on cmd. After that screenshots will be created under "ScreenshotService\build\libs\Secreenshots" folder.

If you use curl, you can send a request with below command. If you use postman, you can send a request by converting below command on 
"Import -> Paste Raw Text" tab.

curl -X POST \
  http://localhost:8080/api/v1/fourdsight \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 985fc97b-5212-8bed-b0f7-71f6cfe97025' \
  -d '{"urlList":["https://www.hackertyper.com", "https://www.aliexpress.com","https://www.facebook.com","http://www.milliyet.com.tr"]}'
