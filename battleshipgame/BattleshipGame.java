/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.battleshipgame;

public class BattleshipGame {
    public static void main(String[] args) {
        System.out.println("Welcome to Battleships!");
        System.out.println("Type M for map, F for fleet status, or a coordinate (A1..J10). Q to quit.");

        GameEngine engine = new GameEngine(); // requires Board + Ships to compile
        engine.run();

        System.out.println("Thanks for playing Battleships!");
    }
}
