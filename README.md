Prosjeket er laget av Sigurd Vågane, Vetle Elnes, Marius Gade og Hans Eivind Skinstad for et prosjekt i ITF20319 Software engineering og testing på HIOF i 2021.<br/>

Hva trenger du for å kjøre programmet?
 - Dette er et program som er utviklet i IntelliJ og bruker funksjoner som ligger i IntelliJ, så det kreves at maskinen som skal kjøre prosjektet har det. Prosjektet kan lastes ned fra GitHub og du vil da få med alle moduler og eksterne plugins som er brukt. Det trengs JAVA JDK 17. Det må lages en konfigurasjon på Main.java for å få programmet til å kjøre. Her må det også legges til VM options (Edit Configurations... --> Modify options --> Add VM options). Det trengs å legge til det som ligger nedenfor, men pathen foran /SoftwareEngineering_Antikvitetsplattform, må endres til hvor ditt prosjekt blir lagt lokalt.
   
--module-path
"C:\Users\vetle_93dri9c\Downloads\SoftwareEngineering_Antikvitetsplattform\javafx-sdk-17.0.1\lib"
--add-modules
javafx.controls,javafx.fxml
--add-exports
javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED


![](https://github.com/heskinstad/SoftwareEngineering_Antikvitetsplattform/actions/workflows/main.yml/badge.svg)
