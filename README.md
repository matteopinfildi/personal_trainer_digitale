# personal_trainer_digitale

Progetto di Basi di Dati. Progetto accademico realizzato per il corso di Laurea Triennale in Ingegneria Informatica presso l'Università degli Studi di Roma Tor Vergata (A.A. 2024/2025).

## Panoramica

Questo repository contiene il codice sorgente (Java/Maven), gli script SQL e la relazione tecnica per la realizzazione della piattaforma **"Personal Trainer Digitale"**. 
L'obiettivo del sistema è supportare l'ecosistema gestionale e operativo di una palestra moderna, coordinando le interazioni tra tre figure principali: il **Gestore della palestra**, i **Personal Trainer** e gli **Atleti**.

Il database gestisce l'intero ciclo di vita delle schede di allenamento personalizzate (creazione, avanzamento ed archiviazione automatica), consente il tracciamento in tempo reale delle sessioni di training (con monitoraggio degli esercizi completati o saltati) e fornisce strumenti analitici avanzati per il calcolo delle percentuali di completamento e dei volumi di lavoro.

## Principali Caratteristiche e Soluzioni Progettuali

- **Modellazione Concettuale e Normalizzazione BCNF:** Definizione formale del minimondo, disambiguazione dei requisiti e stesura del dizionario dei dati. Lo schema logico è stato ristrutturato (accorpamento delle generalizzazioni delle schede tramite attributo discriminante di stato) e validato formalmente in **Forma Normale di Boyce-Codd (BCNF)** su ogni relazione, azzerando le ridondanze e prevenendo anomalie di aggiornamento.
- **Analisi dei Volumi e Costo delle Operazioni:** Valutazione quantitativa del carico computazionale su un orizzonte temporale stimato di 10 anni (circa 260.000 sessioni e 1.000.000 di interazioni), ottimizzando gli accessi per bilanciare le operazioni ad alta frequenza di scrittura e di lettura.
- **Transazionalità e Controllo della Concorrenza:** Implementazione delle regole operative tramite **Stored Procedures** atomiche regolate da `EXIT HANDLER FOR SQLEXCEPTION` con rollback e riespressione dell'errore (`RESIGNAL`). La concorrenza è gestita tramite livelli di isolamento differenziati:
  - `REPEATABLE READ`: per operazioni critiche (creazione/archiviazione scheda, associazione esercizi, registrazione avanzamento) contro letture inconsistenti e *phantom reads*.
  - `READ COMMITTED` e transazioni `READ ONLY`: per consultazioni ad alte prestazioni e scritture indipendenti.
- **Integrità del Dominio e Business Rules (Triggers):** Protezione dei vincoli complessi del dominio applicativo tramite trigger `BEFORE INSERT`:
  - Validazione formale della lunghezza del Codice Fiscale (16 caratteri esatti).
  - Capienza scheda (massimo 5 esercizi assegnabili per scheda attiva).
  - Limite di sessione (massimo 3 esercizi saltati per sessione di allenamento).
  - Validazione temporale (durata minima della sessione non inferiore a 30 minuti).
  - Coerenza dello stato (mutua esclusione tra esercizio completato e saltato; impossibilità di saltare un esercizio già completato).
- **Viste e Automazione degli Eventi:** 
  - Viste analitiche (`dettagli_esercizi` e `report_allenamenti`) con aggregazioni complesse per calcolare KPI di rendimento, tempi di esecuzione e percentuali di completamento.
  - Automazione con **Event Scheduler** (`elimina_schede_archiviate_vecchie`) per l'applicazione programmata di data retention (rimozione automatica delle schede archiviate da oltre 5 anni).
- **Controllo degli Accessi (RBAC):** Profilazione di 4 livelli di privilegio (Login, Personal Trainer, Atleta, Gestore) con segregazione degli accessi (`EXECUTE`, `SELECT`, `INSERT`, `DELETE`) su tabelle e routine.
- **Integrazione Applicativa Java:** Architettura client/backend basata su Java e Maven per l'interazione diretta con il DBMS tramite connettore JDBC.

## Strumenti Utilizzati

- **DBMS Relazionale:** MySQL (InnoDB Storage Engine)
- **Linguaggio & Sviluppo Backend:** Java, Maven
- **Linguaggi Database:** SQL, Procedural SQL (Stored Procedures, Triggers, Views, Events)
- **Modellazione Dati:** MySQL Workbench (Diagrammi E-R concettuali e logici)

## Contenuto del Repository

- `Personal Trainer Digitale Pinfildi.pdf` - Relazione tecnica completa comprendente: descrizione del minimondo, requisiti, schema E-R concettuale e ristrutturato, tabella dei volumi e dei costi, normalizzazione BCNF e progettazione fisica (tabelle, indici, trigger, viste, procedure ed eventi).
- `schema.sql` - Script SQL completo contenente DDL e DML per la creazione del database, tabelle, indici, trigger, viste, eventi schedulati e tutte le stored procedure del sistema.
- `pom.xml` - File di configurazione Maven contenente le dipendenze di progetto (tra cui il driver `mysql-connector-j`) e i plugin di compilazione.
- `src/main/` - Codice sorgente Java contenente la logica applicativa per l'interazione con il database via JDBC.
