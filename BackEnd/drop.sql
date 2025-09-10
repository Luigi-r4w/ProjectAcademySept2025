
    set client_min_messages = WARNING;

    alter table if exists disegni 
       drop constraint if exists FK6alg6v09ceooiqxm5sbf64jpg;

    drop table if exists disegni cascade;

    drop table if exists oggetti cascade;

    drop table if exists utenti cascade;
