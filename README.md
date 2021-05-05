# Unified Embeddings Framework
## By Aastha Saraf
### Steps to Install:
#### 1. Download the project and open it on IntelliJ
#### 2. Setup the Database
#### MySQL Workbench needs to be installed in the system. It can be found on [this link](https://dev.mysql.com/downloads/workbench/)
#### Create a new database connection in the MySQL Workbench. It can be found [here](https://dev.mysql.com/doc/workbench/en/wb-mysql-connections-new.html)
#### NOTE: the username = root and password = Kunwar_IIT13 for connecting to MySQL workbench through IntelliJ. If the username and password for MySQL Connection is different, then please change it in sqlConnect.java and also in encoder-decoder.py
#### Import all the .csv files from Unified_Embeddings_Framework/database schema to MySQL workbench. Please make sure that the name of the schema is "database_schema". Please find how to import and export data to MySQL workbench [here](https://dev.mysql.com/doc/workbench/en/wb-admin-export-import-table.html)
#### 3. Connect to MySQL database from IntelliJ. You can find the instructions [here](https://www.jetbrains.com/help/idea/connecting-to-a-database.html#connect-to-bigquery-database)
#### 4. Configure python SDK on IntelliJ. You can find the detailed instructions [here](https://www.jetbrains.com/help/idea/configuring-python-sdk.html)
#### 5. Configure python system interpreter on IntelliJ. You can find the detailed instructions [here](https://www.jetbrains.com/help/idea/configuring-local-python-interpreters.html)
#### 6. Add mysql-connector-java jar, JUnit5 jar to the IntelliJ project. You can find the details [here](https://www.jetbrains.com/help/idea/testing.html#add-testing-libraries) and [here](https://downloads.mysql.com/archives/c-j/).
#### 7. Install sbt for mac/windows. You can find the document [here](https://www.scala-sbt.org/1.x/docs/Setup.html)
#### 8. Create a sbt build folder by running the following commands: $mkdir \_buildfolder, $cd \_buildfolder, $touch \_buildfolder. You can find an example [here](https://www.scala-sbt.org/1.x/docs/sbt-by-example.html)
#### 9. Upload the build.sbt file from Unified_Embeddings_Framework/build_folder/build.sbt to the \_buildfolder
#### 10. Since the project requires JUnit5 to run, we need to set SBT's test interface for JUnit Jupiter. You can find the details [here](https://github.com/maichler/sbt-jupiter-interface). Put the plugins.sbt file under \_buildfolder\project, you can also copy the file from Unified_Embeddings_Framework/build_folder/project/plugins.sbt
#### 11. Start sbt shell by running: $sbt
#### 12. Compile the project by running: compile
#### 13. Run the project by running: run
#### 14. Test the project by running: test



