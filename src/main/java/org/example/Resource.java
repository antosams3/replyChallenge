package org.example;

public class Resource {
    public String RI; // identificativo risorsa
    public int RA; // activation cost (COSTO INIZIALE)
    public int RP; // periodic cost (COSTO MANUTENZIONE)
    public int RW; // number of consecutive turns in which the resource is active ang generate profit
    public int RM; // number of downtime turns required for a full cycle of activity (MANUTENZIONE)
    public int RL; // total lifecycle of the resource (TURNS), include RM + RW, after the resource is obsolete
    public int RU; // number of buildings the resource can power in each active turn
    public SpecialEffect RT;
}