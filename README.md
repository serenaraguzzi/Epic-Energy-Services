# BE-Epicode-ProgettoFinale

## Introduzione
Questo progetto consiste in un'applicazione di solo lato Back-End per la gestione di un sistema di clienti (CRM) per un'azienda di fornitura energetica, collegata ad un DataBase SQL.
E' stato aggiunto un sistema di autenticazione basato su token JWT. Si deve essere autenticati per accedere ai metodi. NOTA. Il sistema presenta 2 utenti di default (user e admin).
## Tecnologie
Il progetto è stato creato usando:
*	Java + Spring Boot
*	PostgreSQL
*	Spring Security + Token JWT
*	JUnit
*	Maven
*	Git (GitHub)
*	Comprensivo di Swagger e OpenApi per la documentazione
## Sicurezza
Come già accennato, per accedere al sistema gli utenti/user devono essere autenticati (è possibile effettuare una registrazione utente):
MODEL UTENTE PARAMETRIZZATO
``` java
{
  "userName": "Sere",
  "password": "provapassword",
  "nome": "Serena",
  "cognome": "Raguzzi",
  "mail": "sererag@prova.it",
  "roles": [
    "USER"
  ]
}
```

Anche gli utenti/user vengono salvati sul database, le password vengono criptate usando BCrypt Password Encoder.
Gli utenti sono divisi in Admin e User. Gli utenti con ruolo User possono soltanto accedere ai metodi di visualizzazione, i metodi di modifica/aggiorna/cancella sono disponibili soltanto per gli admin. Un utente può avere entrambi i ruoli contemporanemente.
## Spiegazione
L'applicazione viene utilizzata per la gestione delle seguenti entità:
*	Clienti
*	Fatture
*	StatoFatture
*	Indirizzi
*	Comuni
*	Province
*	User(Autenticazione)

Le entità vengono salvate sul database con un valore numerico (Long) come chiave primaria.
Abbiamo a disposizione tutte le funzionalità di CRUD per tutte le entità (eccetto Comuni e Province, che dispongono solo di metodi di visualizzazione in quanto vengono caricate da un file.CSV presente nel progetto), più la possibilità di visualizzare o cercare Clienti e Fatture tramite determinati parametri.
I clienti sono comprensivi di diversi dati:

ESEMPIO DI BODY JSON PER INSERIMENTO CLIENTE
``` java
{
  
  "ragioneSociale": "string",
  "email": "string",
  "dataInserimento": "2022-03-17",
  "dataUltimoContatto": "2022-03-17",
  "fatturatoAnnuale": 0,
  "pec": "string",
  "telefono": "string",
  "emailContatto": "string",
  "nomeContatto": "string",
  "cognomeContatto": "string",
  "numeroContatto": "string",
  "sedeLegale": {
    "id": 1,
   
    "comune": {
      "id": 2,
      "nome": "string"
    }
  },
  "sedeOperativa": {
    "id": 1,
   
    "comune": {
      "id": 1,
      "nome": "string"
    }
  },
  "tipoCliente": "PA"
 
}
```
Alla creazione di un cliente bisogna o passare entrambi gli indirizzi tramite gli ID di questi, o non passarne nessuno per poi aggiungerli coi metodi appositi in seguito (per comodità sono stati aggiunti metodi per modificare direttamente gli indirizzi singolarmente).

ESEMPIO DI BODY JSON PER INSERIMENTO FATTURA
``` java
 {

  "numeroFattura": 0,
  "anno": 0,
  "data": "2022-03-17",
  "importo": 0,
  "statoFattura": {
    "id": 1

  },
  "cliente": {
    "id": 2
   
}
}
  ```
Come si può vedere, la fattura deve necessariamente essere collegata ad un cliente (tramite la chiave primaria di quest'ultimo) per poter essere creata.
Lo Stato di una fattura è stato realizzato come un'entità a sé stante, comprensiva solo di una stringa che indica il nome dello stato, l'id verrà generato automaticamente.

ESEMPIO DI BODY JSON PER INSERIMENTO STATO FATTURA
``` java
 {
  
  "nome": "pagata"
}
```
NOTA. Con i dati di default, abbiamo due stati salvati rispettivamente con Id 5 (Pagata) e 6 (Non Pagata). La scelta di crearla come entità lascia la possibilità di aggiornare, inserire o eliminare gli stati già esistenti. E' stato aggiunto un metodo per aggiornare lo stato di una fattura direttamente, invece del classico update.
Rimangono gli indirizzi, come visto ogni cliente ne ha due (sede legale e sede operativa, che possono corrispondere al medesimo indirizzo).

ESEMPIO DI BODY JSON PER INSERIMENTO INDIRIZZO
``` java
{
  
  "via": "string",
  "civico": "string",
  "cap": "string",
  "localita": "string",
  "comune": {
    "id": 1
   
  }
}
```
Il comune viene passato tramite l'ID corrispondente. In caso di dubbi, ci sono metodi get (per Id e per Nome) per cercarli.
Comune e Provincie sono entità che vengono caricate direttamente da due file.CSV presenti. Sono comprensive di nome, sigle e collegate tra loro (ogni Provincia è collegata alla propria lista di Comuni, ogni Comune è collegato alla propria provincia).
## Link
Link di accesso alla collezione Postman comprensiva di tutte le chiamate ai controller (Per poter effettuare tutte le chiamate e provare i metodi): [https://www.postman.com/serenaraguzzi/workspace/sere/collection/19442049-a306b68f-8240-4b24-a6c0-4d10d886464c?action=share&creator=19442049]

Esempio di link di accesso a Swagger (Per la documentazione): http://localhost:8080/swagger-ui.html
## Test
Sono presenti alcuni test effettuati con Junit e MockMvc nelle apposite classi nel package di test.
