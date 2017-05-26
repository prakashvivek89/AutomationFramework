set projectLocation=E:\Automation\bakker_redesign_testsuite
cd %projectLocation%
set classpath=%projectLocation%\target\classes;%projectLocation%\lib\*
echo %classpath%
java org.testng.TestNG %projectLocation%\src\resources\master\Smoke_Test_en-GB.xml
pause