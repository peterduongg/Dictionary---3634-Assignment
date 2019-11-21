package com.example.dictionary.database;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WordGeneratorDatabase {

    public static String getRandomWords() {
        String[] randomwords = {"Abject",
                "Aberation",
                "Blandishment",
                "Bilk",
                "Cajole",
                "Calumny",
                "Defunct",
                "Denigrate",
                "Edict",
                "Ebullient",
                "Foil",
                "Fortuitous",
                "Gratuitous",
                "Grandiloquent",
                "Hapless",
                "Hegemony",
                "Impinge",
                "Inane",
                "Jump",
                "Juvenile",
                "Knell",
                "Knock",
                "Laconic",
                "Legerdemain",
                "Maudlin",
                "Maverick",
                "Nadir",
                "Onerous",
                "Ostracism",
                "Palliate",
                "Panacea",
                "Quaint",
                "Quandary",
                "Recalcitrant",
                "Relegate",
                "Sanguine",
                "Sorry",
                "Tome",
                "Toady",
                "Umbrage",
                "Utilitarian",
                "Vestige",
                "Vilify",
                "Wonton",
                "Wanton",
                "Yoke",
                "Zephyr"};

        Random random = new Random();
        int i = random.nextInt(randomwords.length);
        return randomwords[i];


    }
}