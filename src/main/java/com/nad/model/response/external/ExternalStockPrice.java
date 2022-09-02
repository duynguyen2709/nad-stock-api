package com.nad.model.response.external;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author duynguyen
 */
@Getter
@AllArgsConstructor
public class ExternalStockPrice implements Serializable {
    private final String sym;
    private final float c;
    private final float f;
    private final float r;
    private final float lastPrice;
    private final int lastVolume;
    private final String ot;
    private final String changePc;
    private final String avePrice;
    private final String highPrice;
    private final String lowPrice;
}

/*
{
        "id": 2100,
        "sym": "BID",
        "mc": "10",
        "c": 40.85,
        "f": 35.55,
        "r": 38.2,
        "lastPrice": 37,
        "lastVolume": 12270,
        "lot": 157960,
        "ot": "1.20",
        "changePc": "3.14",
        "avePrice": "37.38",
        "highPrice": "37.85",
        "lowPrice": "37.00",
        "fBVol": "2750",
        "fBValue": "0",
        "fSVolume": "3830",
        "fSValue": "0",
        "fRoom": "667440491",
        "g1": "37.00|5180|d",
        "g2": "36.95|70|d",
        "g3": "36.90|420|d",
        "g4": "37.05|1010|d",
        "g5": "37.10|50|d",
        "g6": "37.20|390|d",
        "g7": "0|0|e",
        "mp": "0%",
        "CWUnderlying": "        ",
        "CWIssuerName": "                         ",
        "CWType": " ",
        "CWMaturityDate": "        ",
        "CWLastTradingDate": "        ",
        "CWExcersisePrice": "0.0000",
        "CWExerciseRatio": "           ",
        "CWListedShare": "763528520.00",
        "sType": "S",
        "sBenefit": "0"
    }
 */
