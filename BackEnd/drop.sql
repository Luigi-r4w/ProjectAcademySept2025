
    set client_min_messages = WARNING;

    alter table if exists carrello_utente_oggetto 
       drop constraint if exists FKp6ar9aetrito7509c8rx71iy3;

    alter table if exists carrello_utente_oggetto 
       drop constraint if exists FK49ir7lff7ua5vf372uc6l9vue;

    alter table if exists disegni 
       drop constraint if exists FK6alg6v09ceooiqxm5sbf64jpg;

    alter table if exists foto 
       drop constraint if exists FK5ums9iqv49uko7mid0pxv6arb;

    alter table if exists illustrazioni 
       drop constraint if exists FKkwmktow89vxupk4xghal0uson;

    alter table if exists illustrazioni 
       drop constraint if exists FKm5r0v4t2lwuwdw0wt890wm8cj;

    drop table if exists carrello_utente_oggetto cascade;

    drop table if exists disegni cascade;

    drop table if exists foto cascade;

    drop table if exists illustrazioni cascade;

    drop table if exists oggetti cascade;

    drop table if exists utenti cascade;
