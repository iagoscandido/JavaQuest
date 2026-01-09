package br.com.sciago.model;

public record TakeDamage(int damageTaken, int currentHp, int maxHp, Condition condition, String creatureName) {

}
