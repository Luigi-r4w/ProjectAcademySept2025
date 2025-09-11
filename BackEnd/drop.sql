
    set client_min_messages = WARNING;

    alter table if exists disegni 
       drop constraint if exists FK6alg6v09ceooiqxm5sbf64jpg;

    alter table if exists illustrazioni 
       drop constraint if exists FKkwmktow89vxupk4xghal0uson;

    drop table if exists disegni cascade;

    drop table if exists illustrazioni cascade;

    drop table if exists oggetti cascade;

    drop table if exists utenti cascade;
