# mstore2

## Wprowadzenie

W przykładowych poleceniach podanych poniżej oraz w skryptach zakładam, że folder z zainstalowanym GlassFishem znajduje się w tym samym miejscu co folder z projektem.

## Skrypty

Aby uruchomić skrypty, najpierw należy przejść do folderu z projektem, a następnie nadać sobie odpowiednie uprawnienia poleceniem:  
`chmod 755 ./scripts/*.sh`

## Baza danych

Uruchamianie serwera HSQLDB:  
`./scripts/runHSQLDBServer.sh`

Uruchamianie klienta HSQLDB z graficznym interfejsem użytkownika:  
`./scripts/runHSQLDBClient.sh`

## JDBC Connection Pool i JNDI JDBC resource

1. Zatrzymaj GlassFish, jeśli jest uruchomiony:  
`./scripts/stopGlassFish.sh`
2. Skopiuj hsqldb-2.2.4.jar do folderu bibliotek domeny GlassFish:  
`cp ./scripts/hsqldb-2.2.4.jar ../glassfish3/glassfish/domains/domain1/lib/ext/.`
3. Zdefiniuj JDBC Connection Pool i JNDI JDBC resource:
 * Zedytuj domyślny plik konfiguracyjny domeny:  
`../glassfish3/glassfish/domains/domain1/config/domain.xml`
 * Znajdź element `<resources>`.
 * Skopiuj i wklej poniższy fragment kodu xml przed tagiem zamykającym `</resources>`:  
`<jdbc-connection-pool driver-classname="" datasource-classname="org.hsqldb.jdbc.JDBCDataSource"`  
`res-type="javax.sql.DataSource" description="" name="HSQLPool" ping="true">`  
`<property name="DatabaseName" value="jdbc:hsqldb:hsql://localhost/workdb"></property>`  
`<property name="User" value="SA"></property>`  
`<property name="Password" value=""></property>`  
`<property name="connectionAttributes" value=";ifexists=true"></property>`  
`</jdbc-connection-pool>`  
`<jdbc-resource pool-name="HSQLPool" description="DataSource for demo apps with HSQLDB"`  
`jndi-name="jdbc/demoapps"></jdbc-resource>`
4. Uruchom GlassFish:  
`./scripts/startGlassFish.sh`
5. Sprawdź połączenie z GlassFish:
 * otwórz w przeglądarce: [http://localhost:4848](http://localhost:4848)
 * z menu wybierz: Resources/JDBC/JDBC Connection Pools
 * kliknij: HSQLPool
 * kliknij przycisk Ping (powinien wyświetlić się komunikat: Ping Succeeded)

## Hibernate

1. Otwórz w przeglądarce:  
[http://localhost:4848](http://localhost:4848)
2. Z menu wybierz: Update Tool
3. Znajdź i zaznacz Hibernate w tabeli Available Add-Ons
4. Kliknij przycisk Install
5. Zrestartuj GlassFish:  
`./scripts/restartGlassFish.sh`

## Deployowanie

1. Uruchom:  
`./scripts/buildRedeploy.sh`
2. Otwórz w przeglądarce:  
[http://localhost:8080/mstore2](http://localhost:8080/mstore2)

## Źródła

Przy pisaniu projektu, korzystałem z repozytoriów:
 * dr Neumanna: [jeedemo](https://github.com/KubaNeumann/jeedemo), [restdemo](https://github.com/KubaNeumann/restdemo)
 * Łukasza Rybki: [introduction-to-jee-examples/jax-rs](https://github.com/Smoczysko/introduction-to-jee-examples/tree/master/jax-rs)
